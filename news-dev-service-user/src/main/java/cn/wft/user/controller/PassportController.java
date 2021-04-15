package cn.wft.user.controller;

import cn.wft.api.controller.BaseController;
import cn.wft.api.controller.user.PassportControllerApi;
import cn.wft.common.enums.UserStatus;
import cn.wft.common.grace.result.GraceJSONResult;
import cn.wft.common.grace.result.ResponseStatusEnum;
import cn.wft.common.utils.IPUtil;
import cn.wft.common.utils.JsonUtils;
import cn.wft.common.utils.SMSUtils;
import cn.wft.model.pojo.AppUser;
import cn.wft.model.pojo.bo.RegistLoginBO;
import cn.wft.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

/**
 * @author zyh
 */
@Slf4j
@RestController
public class PassportController extends BaseController implements PassportControllerApi {

    @Autowired
    private SMSUtils smsUtils;

    @Autowired
    private UserService userService;


    @Override
    public GraceJSONResult sendCode(String mobile, HttpServletRequest request) {
        //根据用户ip 限制用户60秒内只能获取一次验证
        String ip = IPUtil.getRequestIp(request);
        //生成6位随机数
        Integer random = (int) (Math.random() * 9 + 1) * 100000;
        String code = random.toString();


        redis.setnx60s(MOBILE_SMSCODE + ":" + ip, ip);

        // smsUtils.sendSMS(mobile,code);

        redis.set(MOBILE_SMSCODE + ":" + mobile, code, 30 * 60);

        log.info(mobile + "验证码:" + code);

        return GraceJSONResult.ok();
    }

    @Override
    public GraceJSONResult doLogin(@RequestBody @Valid RegistLoginBO registLoginBO,
                                   BindingResult result,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        // 0.判断BindingResult中是否保存了错误的验证信息，如果有，则需要返回
        if (result.hasErrors()) {
            Map<String, String> map = getErrors(result);
            return GraceJSONResult.errorMap(map);
        }

        String mobile = registLoginBO.getMobile();
        String smsCode = registLoginBO.getSmsCode();

        // 1. 校验验证码是否匹配
        String redisSMSCode = redis.get(MOBILE_SMSCODE + ":" + mobile);
        if (StringUtils.isBlank(redisSMSCode) || !redisSMSCode.equalsIgnoreCase(smsCode)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SMS_CODE_ERROR);
        }

        // 2. 查询数据库，判断该用户注册
        AppUser user = userService.queryMobileIsExist(mobile);
        if (user != null && user.getActiveStatus().equals(UserStatus.FROZEN.type)) {
            // 如果用户不为空，并且状态为冻结，则直接抛出异常，禁止登录
            return GraceJSONResult.errorCustom(ResponseStatusEnum.USER_FROZEN);
        } else if (user == null) {
            // 如果用户没有注册过，则为null，需要注册信息入库
            user = userService.createUser(mobile);
        }

        // 3. 保存用户分布式会话的相关操作
        int userActiveStatus = user.getActiveStatus();
        if (userActiveStatus != UserStatus.FROZEN.type) {
            // 保存token到redis
            String uToken = UUID.randomUUID().toString();
            redis.set(REDIS_USER_TOKEN + ":" + user.getId(), uToken);
            redis.set(REDIS_USER_INFO + ":" + user.getId(), JsonUtils.objectToJson(user));

            // 保存用户id和token到cookie中
            setCookie(request, response, "utoken", uToken, COOKIE_MONTH);
            setCookie(request, response, "uid", user.getId(), COOKIE_MONTH);
        }

        // 4. 用户登录或注册成功以后，需要删除redis中的短信验证码，验证码只能使用一次，用过后则作废
        redis.del(MOBILE_SMSCODE + ":" + mobile);

        // 5. 返回用户状态
        return GraceJSONResult.ok(userActiveStatus);
    }

    @Override
    public GraceJSONResult logout(String userId, HttpServletRequest request, HttpServletResponse response) {
        redis.del(REDIS_USER_TOKEN + ":" + userId);

        setCookie(request, response, "utoken", "", COOKIE_DELETE);
        setCookie(request, response, "uid", "", COOKIE_DELETE);

        return GraceJSONResult.ok();
    }


}

package cn.wft.user.controller;

import cn.wft.api.controller.BaseController;
import cn.wft.api.controller.user.PassportControllerApi;
import cn.wft.common.grace.result.GraceJSONResult;
import cn.wft.common.utils.IPUtil;
import cn.wft.common.utils.SMSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class PassportController extends BaseController implements PassportControllerApi {

    @Autowired
    private SMSUtils smsUtils;

    @Autowired
    private IPUtil ipUtil;

    @Override
    public GraceJSONResult sendCode(String mobile, HttpServletRequest request){
        //根据用户ip 限制用户60秒内只能获取一次验证
        String ip = ipUtil.getRequestIp(request);
        //生成6位随机数
        Integer random = (int)(Math.random() * 9 +1)*100000;
        String code = random.toString();


        redis.setnx60s(MOBILE_SMSCODE + ":" + ip, ip);

       // smsUtils.sendSMS(mobile,code);

        redis.set(MOBILE_SMSCODE + mobile,code,30*60);

        log.info(mobile+"验证码:"+code);

        return GraceJSONResult.ok();
    }
}

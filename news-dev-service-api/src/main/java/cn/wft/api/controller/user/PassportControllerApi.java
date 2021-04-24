package cn.wft.api.controller.user;

import cn.wft.common.grace.result.GraceJSONResult;
import cn.wft.model.pojo.bo.RegistLoginBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(value = "短信发送",tags = "短信验证码")
@RequestMapping("/passport")
public interface PassportControllerApi {

    @GetMapping("/getSMSCode")
    @ApiOperation(value = "发送短信验证码",notes = "短信验证码",httpMethod = "GET")
    GraceJSONResult sendCode(@RequestParam String mobile, HttpServletRequest request);



    @PostMapping("/doLogin")
    @ApiOperation(value = "短信登录",notes = "短信登录",httpMethod = "POST")
    GraceJSONResult doLogin(@RequestBody@Valid RegistLoginBO registLoginBO,
                            BindingResult result,
                            HttpServletRequest request,
                            HttpServletResponse response);

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    GraceJSONResult logout(@RequestParam String userId,
                                  HttpServletRequest request,
                                  HttpServletResponse response);
}

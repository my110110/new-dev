package cn.wft.api.controller.user;

import cn.wft.common.grace.result.GraceJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Api(value = "短信发送",tags = "短信验证码")
@RequestMapping("/passport")
public interface PassportControllerApi {

    @GetMapping("/getSMSCode")
    @ApiOperation(value = "发送短信验证码",notes = "短信验证码",httpMethod = "GET")
    GraceJSONResult sendCode(@RequestParam String mobile, HttpServletRequest request);
}

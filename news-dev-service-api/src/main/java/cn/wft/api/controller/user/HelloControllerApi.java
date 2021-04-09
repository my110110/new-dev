package cn.wft.api.controller.user;

import cn.wft.common.grace.result.GraceJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;


@Api(value = "测试机",hidden = false,tags = {"测试"})
public interface HelloControllerApi {

    @GetMapping("/hello")
    @ApiOperation(value = "hello",httpMethod = "GET",notes = "测试")
    GraceJSONResult hello();
}

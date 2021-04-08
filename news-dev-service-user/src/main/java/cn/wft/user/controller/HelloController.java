package cn.wft.user.controller;

import cn.wft.api.controller.user.HelloControllerApi;
import cn.wft.common.grace.result.GraceJSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController implements HelloControllerApi {

    public GraceJSONResult hello(){

        return GraceJSONResult.ok("hello");
    }
}

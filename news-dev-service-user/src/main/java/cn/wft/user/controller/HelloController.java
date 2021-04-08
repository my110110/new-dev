package cn.wft.user.controller;

import cn.wft.api.controller.user.HelloControllerApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController implements HelloControllerApi {

    public String hello(){
        log.info("hello");
        return "hello";
    }
}

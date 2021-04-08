package cn.wft.api.controller.user;

import org.springframework.web.bind.annotation.GetMapping;


public interface HelloControllerApi {

    @GetMapping("/hello")
    public String hello();
}

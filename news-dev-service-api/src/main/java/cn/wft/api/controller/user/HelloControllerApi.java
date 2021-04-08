package cn.wft.api.controller.user;

import cn.wft.common.grace.result.GraceJSONResult;
import org.springframework.web.bind.annotation.GetMapping;


public interface HelloControllerApi {

    @GetMapping("/hello")
    public GraceJSONResult hello();
}

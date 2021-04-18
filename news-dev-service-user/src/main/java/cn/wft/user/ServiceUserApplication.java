package cn.wft.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan(basePackages = "cn.wft.user.mapper")
@ComponentScan(basePackages = {"cn.wft", "cn.wft.common.idworker"})
public class ServiceUserApplication {


    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class,args);

    }
}

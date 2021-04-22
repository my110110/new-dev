package cn.wft.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * //x
 * @author zyh
 */
@SpringBootApplication//(exclude = MongoAutoConfiguration.class)
@MapperScan(basePackages = "cn.wft.admin.mapper")
@ComponentScan(basePackages = {"cn.wft", "cn.wft.common.idworker"})
public class AdminApplication {


    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}


package cn.jinronga.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName EduApplication
 * @Author 郭金荣
 * @Date 2021/2/18 21:15
 * @Description EduApplication
 * @Version 1.0
 */
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = "cn.jinronga")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}

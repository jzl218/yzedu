package api.yzeduapi;

import org.jsets.shiro.config.EnableJsetsShiro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableJsetsShiro
@SpringBootApplication
public class YzeduApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YzeduApiApplication.class, args);
    }
}

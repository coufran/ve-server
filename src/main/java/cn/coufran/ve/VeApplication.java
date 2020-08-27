package cn.coufran.ve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeApplication.class, args);
    }

}

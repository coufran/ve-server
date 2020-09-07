package cn.coufran.ve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 微鱼应用入口
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@SpringBootApplication
@EnableScheduling // 启用定时任务
public class VeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeApplication.class, args);
    }

}

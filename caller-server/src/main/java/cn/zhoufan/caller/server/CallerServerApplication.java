package cn.zhoufan.caller.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author zhoufan
 * @Date 2021/3/30 17:00
 */
@SpringBootApplication(scanBasePackages ={"cn.zhoufan"} )
@EnableFeignClients(basePackages = {"cn.zhoufan"})
public class CallerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CallerServerApplication.class, args);
    }
}

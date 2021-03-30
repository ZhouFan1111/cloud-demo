package cn.zhoufan.cloudgw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("cn.zhoufan")
public class CloudGwApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGwApplication.class, args);
    }

}

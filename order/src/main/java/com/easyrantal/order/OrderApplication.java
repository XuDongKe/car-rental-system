package com.easyrantal.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @Author Peter Ke
 * @Date $ $
 */

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.easyrantal.order.api")

public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}


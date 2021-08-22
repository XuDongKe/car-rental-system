package com.easyrantal.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @Author Peter Ke
 * @Date $ $
 */

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class CarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }

}

package com.cloud.study.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = "com.cloud.study.security")
@EnableFeignClients(basePackages = "com.cloud.study.security")
@EnableHystrix
public class CloudSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudSecurityApplication.class, args);
    }

}

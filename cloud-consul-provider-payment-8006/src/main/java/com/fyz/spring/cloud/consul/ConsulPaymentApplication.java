package com.fyz.spring.cloud.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentApplication.class, args);
    }
}

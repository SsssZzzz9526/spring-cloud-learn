package com.fyz.spring.cloud.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperPaymentApplication.class, args);
    }
}

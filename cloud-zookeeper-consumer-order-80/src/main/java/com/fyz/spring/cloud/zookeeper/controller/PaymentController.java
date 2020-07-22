package com.fyz.spring.cloud.zookeeper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    private static final String PAYMENT_SERVICE_NAME = "http://cloud-zookeeper-provider-payment";
    private static final String PAYMENT_URI = "/payment/zk";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String test() {
        return restTemplate.getForObject(PAYMENT_SERVICE_NAME + PAYMENT_URI, String.class);
    }
}

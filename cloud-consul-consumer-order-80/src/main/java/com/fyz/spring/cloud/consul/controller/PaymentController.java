package com.fyz.spring.cloud.consul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    private static final String PAYMENT_SERVICE_NAME = "http://cloud-consul-provider-payment";
    private static final String PAYMENT_URI = "/payment/consul";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String test() {
        return restTemplate.getForObject(PAYMENT_SERVICE_NAME + PAYMENT_URI, String.class);
    }
}

package com.gaffey.cloud.alibaba.controller;

import com.gaffey.cloud.alibaba.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @Value("${services.payment}")
    private String paymentServiceName;

    @GetMapping("/consumer/payment")
    public String payment() {
        return restTemplate.getForObject(paymentServiceName + "/payment", String.class);
    }

    @GetMapping("/consumer/payment/feign")
    public String paymentFeign() {
        return paymentService.payment();
    }
}

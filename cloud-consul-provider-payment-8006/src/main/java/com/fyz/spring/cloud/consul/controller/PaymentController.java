package com.fyz.spring.cloud.consul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/consul")
    public String test() {
        return String.format("Hello Spring Cloud Consul. port: %s, serial: %s", this.port, UUID.randomUUID());
    }
}
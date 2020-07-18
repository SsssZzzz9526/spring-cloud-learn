package com.fyz.spring.cloud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fyz.spring.cloud.dto.CommonResult;
import com.fyz.spring.cloud.entity.Payment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
//    private static final String BASE_URL = "http://localhost:8001/payment";
//    通过服务名调用
    private static final String BASE_URL = "http://CLOUD-PAYMENT-SERVICE/payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> selectById(@PathVariable Long id) {
        return restTemplate.getForObject(String.format("%s/%s", BASE_URL, id), CommonResult.class);
    }

    @PostMapping("/consumer/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Payment> request = new HttpEntity<>(payment, headers);
        return restTemplate.postForObject(BASE_URL, request, CommonResult.class);
    }
}

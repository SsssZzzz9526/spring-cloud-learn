package com.gaffey.cloud.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider-payment")
public interface PaymentService {

    @GetMapping("/payment")
    String payment();
}

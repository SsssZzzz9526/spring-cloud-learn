package com.fyz.spring.cloud.service;

import com.fyz.spring.cloud.dto.CommonResult;
import com.fyz.spring.cloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLOUD-PAYMENT-SERVICE")
public interface OpenfeignPaymentService {

    @GetMapping("/payment/{id}")
    CommonResult<Payment> selectById(@PathVariable("id") Long id);

    @GetMapping("/sleep")
    String sleep();
}

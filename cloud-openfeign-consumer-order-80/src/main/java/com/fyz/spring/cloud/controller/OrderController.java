package com.fyz.spring.cloud.controller;

import com.fyz.spring.cloud.dto.CommonResult;
import com.fyz.spring.cloud.entity.Payment;
import com.fyz.spring.cloud.service.OpenfeignPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OpenfeignPaymentService paymentService;

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> selectById(@PathVariable Long id) {
        return paymentService.selectById(id);
    }

    @GetMapping("/sleep")
    public String sleep() {
        return paymentService.sleep();
    }
}

package com.fyz.spring.cloud.controller;

import com.fyz.spring.cloud.dto.CommonResult;
import com.fyz.spring.cloud.entity.Payment;
import com.fyz.spring.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> selectById(@PathVariable Long id) {
        log.info("查询支付 port:{}", port);
        Payment payment = paymentService.selectByPrimaryKey(id);
        return CommonResult.success(payment);
    }

    @PostMapping("/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        log.info("添加支付123");
        return paymentService.insert(payment) == 1 ?
                CommonResult.success() :
                CommonResult.failure();
    }
}

package com.fyz.spring.cloud.controller;

import com.fyz.spring.cloud.dto.CommonResult;
import com.fyz.spring.cloud.entity.Payment;
import com.fyz.spring.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
        CommonResult<Payment> res = CommonResult.success(payment);
        res.setMsg(port);
        return res;
    }

    @PostMapping("/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        log.info("添加支付123");
        return paymentService.insert(payment) == 1 ?
                CommonResult.success() :
                CommonResult.failure();
    }

    @GetMapping("/sleep")
    public String sleep() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}

package com.fyz.cloud.controller;

import com.fyz.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;
    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/ok")
    public String ok() {
        log.info("ok");
        return paymentService.ok();
    }

    @GetMapping("/payment/timeout")
    public String timeout() {
        log.error("timeout");
        return paymentService.timeout();
    }

    @GetMapping("/payment/circuit/{id}")
    public String circuit(@PathVariable("id") Integer id) {
        log.error("circuit");
        return paymentService.circuitBreaker(id);
    }
}

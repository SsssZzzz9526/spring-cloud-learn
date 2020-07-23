package com.fyz.cloud.controller;

import com.fyz.cloud.service.FeignPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "paymentDefaultFallback")
public class OrderController {

    @Resource
    private FeignPaymentService paymentService;

    @GetMapping("/consumer/payment/ok")
    public String ok() {
        return this.paymentService.ok();
    }

    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    @GetMapping("/consumer/payment/timeout")
    public String timeout() {
        return this.paymentService.timeout();
    }

    @GetMapping("/consumer/payment/timeout/default")
    @HystrixCommand
    public String timeoutWithoutCustomFallback() {
        int i = 1 / 0;
        return "Success";
    }

    public String fallback() {
        return "消费者-服务降级";
    }

    public String paymentDefaultFallback() {
        return "消费者-服务降级（默认）";
    }
}

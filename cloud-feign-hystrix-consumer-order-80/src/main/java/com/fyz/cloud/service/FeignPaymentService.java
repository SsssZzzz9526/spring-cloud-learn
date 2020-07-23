package com.fyz.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CLOUD-HYSTRIX-PAYMENT-SERVICE", fallback = FeignPaymentServiceImpl.class)
public interface FeignPaymentService {

    @GetMapping("/payment/ok")
    String ok();

    @GetMapping("/payment/timeout")
    String timeout();
}

package com.gaffey.cloud.service;

import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.service.impl.PaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-alibaba-provider-payment", fallback = PaymentServiceImpl.class)
public interface PaymentService {

    @GetMapping("/payment/{id}")
    CommonResult<String> payment(@PathVariable("id") Integer id);
}

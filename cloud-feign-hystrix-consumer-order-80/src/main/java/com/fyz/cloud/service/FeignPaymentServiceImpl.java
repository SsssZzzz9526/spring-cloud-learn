package com.fyz.cloud.service;

import org.springframework.stereotype.Service;

@Service
public class FeignPaymentServiceImpl implements FeignPaymentService {

    @Override
    public String ok() {
        return "feign实现类服务降级";
    }

    @Override
    public String timeout() {
        return null;
    }
}

package com.gaffey.cloud.service.impl;

import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public CommonResult<String> payment(Integer id) {
        CommonResult<String> res = new CommonResult<>();
        res.setCode(500);
        res.setMsg("openfeign-服务降级");
        return res;
    }
}

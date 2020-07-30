package com.gaffey.cloud.controller;

import com.fyz.spring.cloud.dto.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class PaymentController {

    private static final Map<Integer, String> data;

    static {
        data = new ConcurrentHashMap<>();
        data.put(1, "hello spring cloud");
        data.put(2, "hello spring cloud alibaba");
        data.put(3, "hello spring cloud alibaba sentinel");
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/{id}")
    public CommonResult<String> payment(@PathVariable("id") Integer id) {
        CommonResult<String> res = CommonResult.success(data.get(id));
        res.setCode(Integer.parseInt(port));
        return res;
    }
}

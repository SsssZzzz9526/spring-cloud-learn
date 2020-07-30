package com.gaffey.cloud.alibaba.controller;

import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.alibaba.entity.Order;
import com.gaffey.cloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/api/order")
    public CommonResult<Order> create(Order order) {
        return CommonResult.success(
                orderService.insert(order)
        );
    }
}

package com.fyz.spring.cloud.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.fyz.spring.cloud.entity.Payment;
import com.fyz.spring.cloud.mapper.PaymentMapper;
import com.fyz.spring.cloud.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService{

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public int insert(Payment record) {
        return paymentMapper.insert(record);
    }

    @Override
    public Payment selectByPrimaryKey(Long id) {
        return paymentMapper.selectByPrimaryKey(id);
    }

}

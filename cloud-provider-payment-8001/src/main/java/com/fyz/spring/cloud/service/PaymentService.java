package com.fyz.spring.cloud.service;

import com.fyz.spring.cloud.entity.Payment;
public interface PaymentService{


    int insert(Payment record);

    Payment selectByPrimaryKey(Long id);

}

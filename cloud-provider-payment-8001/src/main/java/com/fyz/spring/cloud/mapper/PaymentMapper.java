package com.fyz.spring.cloud.mapper;

import com.fyz.spring.cloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    int insert(Payment record);

    Payment selectByPrimaryKey(Long id);
}
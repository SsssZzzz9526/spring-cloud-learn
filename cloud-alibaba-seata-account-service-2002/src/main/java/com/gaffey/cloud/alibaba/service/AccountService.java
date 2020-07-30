package com.gaffey.cloud.alibaba.service;

import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.alibaba.entity.Account;

import java.math.BigDecimal;

public interface AccountService{


    int insert(Account record);

    CommonResult<Account> update(Long userId, BigDecimal money);
}

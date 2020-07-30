package com.gaffey.cloud.alibaba.service.impl;

import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.alibaba.dao.AccountMapper;
import com.gaffey.cloud.alibaba.entity.Account;
import com.gaffey.cloud.alibaba.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService{

    @Resource
    private AccountMapper accountMapper;

    @Override
    public int insert(Account record) {
        return accountMapper.insert(record);
    }

    @Override
    public CommonResult<Account> update(Long userId, BigDecimal money) {
        accountMapper.update(userId, money);
        Account account = accountMapper.selectByPk(userId);
        return CommonResult.success(account);
    }
}

package com.gaffey.cloud.alibaba.controller;

import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.alibaba.entity.Account;
import com.gaffey.cloud.alibaba.service.AccountService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @PutMapping("/api/account")
    public CommonResult<Account> updateAccount(Long userId, BigDecimal money) {
        return accountService.update(userId, money);
    }
}

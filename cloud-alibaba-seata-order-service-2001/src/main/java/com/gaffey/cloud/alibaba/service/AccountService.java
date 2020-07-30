package com.gaffey.cloud.alibaba.service;

import com.fyz.spring.cloud.dto.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "account-service")
public interface AccountService {

    @PutMapping("/api/account")
    CommonResult<?> updateAccount(@RequestParam("userId") Long userId,
                                  @RequestParam("money") BigDecimal money);
}

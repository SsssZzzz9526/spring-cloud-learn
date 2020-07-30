package com.gaffey.cloud.alibaba.dao;

import com.gaffey.cloud.alibaba.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountMapper {
    int insert(Account record);

    int update(@Param("userId") Long userId, @Param("money") BigDecimal money);

    Account selectByPk(Long userId);
}
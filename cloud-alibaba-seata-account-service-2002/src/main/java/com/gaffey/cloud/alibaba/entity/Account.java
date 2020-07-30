package com.gaffey.cloud.alibaba.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    private Long id;

    /**
    * 用户ID
    */
    private Long userId;

    /**
    * 总额度
    */
    private BigDecimal total;

    /**
    * 已用额度
    */
    private BigDecimal used;

    /**
    * 剩余额度
    */
    private BigDecimal residue;

    private static final long serialVersionUID = 1L;
}
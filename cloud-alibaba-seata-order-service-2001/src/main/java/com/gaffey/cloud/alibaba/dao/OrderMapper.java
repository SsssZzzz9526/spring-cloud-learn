package com.gaffey.cloud.alibaba.dao;

import com.gaffey.cloud.alibaba.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    int insert(Order record);

    int update(@Param("id") Long id, @Param("status") Integer status);
}
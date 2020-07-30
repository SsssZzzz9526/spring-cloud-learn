package com.gaffey.cloud.alibaba.dao;

import com.gaffey.cloud.alibaba.entity.Storage;
import org.apache.ibatis.annotations.Param;

public interface StorageMapper {

    Storage selectByProductId(Long productId);

    int updateByProductId(@Param("productId") Long productId, @Param("count") Integer count);
}
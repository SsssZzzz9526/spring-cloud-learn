package com.gaffey.cloud.alibaba.service;

import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.alibaba.entity.Storage;

public interface StorageService {
    CommonResult<Storage> updateStorage(Long productId, Integer count);
}


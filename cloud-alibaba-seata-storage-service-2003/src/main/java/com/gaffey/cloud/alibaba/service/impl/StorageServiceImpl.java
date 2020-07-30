package com.gaffey.cloud.alibaba.service.impl;

import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.alibaba.dao.StorageMapper;
import com.gaffey.cloud.alibaba.entity.Storage;
import com.gaffey.cloud.alibaba.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public CommonResult<Storage> updateStorage(Long productId, Integer count) {
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storageMapper.updateByProductId(productId, count);
        Storage storage = storageMapper.selectByProductId(productId);
        return CommonResult.success(storage);
    }
}



package com.gaffey.cloud.alibaba.controller;

import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.alibaba.entity.Storage;
import com.gaffey.cloud.alibaba.service.StorageService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @PutMapping("/api/storage")
    public CommonResult<Storage> updateStorage(Long productId, Integer count) {
        return storageService.updateStorage(productId, count);
    }
}

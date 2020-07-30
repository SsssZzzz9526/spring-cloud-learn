package com.gaffey.cloud.alibaba.service;

import com.fyz.spring.cloud.dto.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "storage-service")
public interface StorageService {

    @PutMapping("/api/storage")
    CommonResult<?> updateStorage(@RequestParam("productId") Long productId,
                                  @RequestParam("count") Integer count);
}

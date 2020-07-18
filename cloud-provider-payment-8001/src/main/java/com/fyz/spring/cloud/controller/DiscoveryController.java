package com.fyz.spring.cloud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyz.spring.cloud.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class DiscoveryController {

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public CommonResult<DiscoveryClient> discovery() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.error("description: {}", discoveryClient.description());
        for (String service : discoveryClient.getServices()) {
            log.error("service: {}", service);
            for (ServiceInstance instance : discoveryClient.getInstances(service)) {
                log.error("InstanceId: {}\nServiceId: {}\nHost: {}\nPort: {}\nScheme: {}\nMetadata: {}\n",
                        instance.getInstanceId(),
                        instance.getServiceId(),
                        instance.getHost(),
                        instance.getPort(),
                        instance.getScheme(),
                        objectMapper.writeValueAsString(instance.getMetadata())
                        );
            }
        }
        return CommonResult.success(discoveryClient);
    }
}

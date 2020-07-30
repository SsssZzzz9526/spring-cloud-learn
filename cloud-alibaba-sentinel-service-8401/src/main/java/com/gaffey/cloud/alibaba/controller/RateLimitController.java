package com.gaffey.cloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gaffey.cloud.alibaba.advice.GlobalBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @SentinelResource(value = "customHandler",
            blockHandlerClass = GlobalBlockHandler.class,
            blockHandler = "handle1")
    @GetMapping("/test/custom")
    public String customHandler() {
        return "test custom";
    }

    @GetMapping("/test/url")
    public String url() {
        return "test url";
    }
}

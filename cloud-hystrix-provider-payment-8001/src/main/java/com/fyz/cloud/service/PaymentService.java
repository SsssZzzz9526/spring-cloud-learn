package com.fyz.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    private static final Random random = new Random();
    public String ok() {
        return "it is ok";
    }

    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeout() {
        int time = random.nextInt(5);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "it is ok, but timeout: " + time;
    }

    public String fallback() {
        return "服务降级";
    }

    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            // 开始熔断
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 统计请求阈值 当10秒内请求数大于这个值的时候，才能统计
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 休眠时间窗口 触发熔断后休眠10秒 然后重新尝试恢复熔断
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 错误比率阈值 当10秒内错误请求数量超过50% 触发熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
    })
    public String circuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("失败");
        }
        return "成功";
    }

    public String fallback(Integer id) {
        return "服务降级 id: " + id;
    }
}

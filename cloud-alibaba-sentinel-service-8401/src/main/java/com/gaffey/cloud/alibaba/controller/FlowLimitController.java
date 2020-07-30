package com.gaffey.cloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

    @GetMapping("/test/a")
    public String testA() {
        System.out.println(Thread.currentThread() + " -- test A");
        return "test A";
    }

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/test/b")
    public String testB() {
        String res = restTemplate.getForObject("http://localhost:8401/test/a", String.class);
        return "test B -- " + res;
    }

    @GetMapping("/test/c")
    public String testC() {
        String res = restTemplate.getForObject("http://localhost:8401/test/a", String.class);
        return "test C -- " + res;
    }

    @GetMapping("/test/d")
    public String testD() {
        try {
            TimeUnit.MILLISECONDS.sleep(400);
            System.out.println("test D");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test D";
    }

    private static volatile boolean flag = false;

    @GetMapping("/test/e")
    public synchronized String testE() {
        if (flag) {
            flag = !flag;
            System.out.println("success");
        }else {
            flag = !flag;
            throw new RuntimeException("failed");
        }
        return "test E";
    }

    @GetMapping("/test/f")
    public synchronized String testF() {
        if (flag) {
            flag = !flag;
            System.out.println("success");
        }else {
            flag = !flag;
            throw new RuntimeException("failed");
        }
        return "test F";
    }

    @SentinelResource(value = "goodsInfo", blockHandler = "blockHandler")
    @GetMapping("/goods/categories/{categoryId}/{goodsId}")
    public String goodsInfo(@PathVariable("categoryId") Integer categoryId,
                         @PathVariable("goodsId") Integer goodsId) {

        return String.format("categoryId: %d,goodsId: %d", categoryId, goodsId);
    }

    public String blockHandler(Integer categoryId, Integer goodsId, BlockException e) {
        return "blockHandler: " + e;
    }
}

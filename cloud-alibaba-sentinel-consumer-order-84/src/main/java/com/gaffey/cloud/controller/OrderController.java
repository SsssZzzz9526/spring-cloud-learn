package com.gaffey.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fyz.spring.cloud.dto.CommonResult;
import com.gaffey.cloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
public class OrderController {

    private static final String PAYMENT_SERVICE = "http://cloud-alibaba-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/{id}")
    // Case1:没有配置任何服务降级和限流处理
//    @SentinelResource(value = "order")
    // Case2:配置服务降级
//    @SentinelResource(value = "order", fallback = "orderFallback")
    // Case3:配置限流处理
//    @SentinelResource(value = "order", blockHandler = "orderBlockHandler")
    // Case4:配置服务降级和限流处理
//    @SentinelResource(value = "order", fallback = "orderFallback", blockHandler = "orderBlockHandler")
    // Case5:配置服务降级和限流处理，并忽略指定限流
    @SentinelResource(value = "order", fallback = "orderFallback", blockHandler = "orderBlockHandler", exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<String> order(@PathVariable("id") Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must bigger than 0");
        }
        String url = String.format("%s/payment/%d", PAYMENT_SERVICE, id);
        CommonResult res = restTemplate.getForObject(url, CommonResult.class);
        if (Objects.isNull(res.getData())) {
            throw new NullPointerException("result is null");
        }
        return res;
    }

    /**
     * 服务降级
     * @param id
     * @param e
     * @return
     */
    public CommonResult<String> orderFallback(Integer id, Throwable e) {
        CommonResult<String> res = new CommonResult<>();
        res.setCode(400);
        res.setMsg("Fallback");
        res.setData(e.getMessage());
        return res;
    }

    /**
     * 限流处理
     * @param id
     * @param e
     * @return
     */
    public CommonResult<String> orderBlockHandler(Integer id, BlockException e) {
        CommonResult<String> res = new CommonResult<>();
        res.setCode(401);
        res.setMsg("BlockHandler");
        res.setData(e.getMessage());
        return res;
    }

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/feign/payment/{id}")
    public CommonResult<String> feignPayment(@PathVariable("id") Integer id) {
        return paymentService.payment(id);
    }
}

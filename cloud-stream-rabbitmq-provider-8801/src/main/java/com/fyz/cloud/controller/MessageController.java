package com.fyz.cloud.controller;

import com.fyz.cloud.service.IMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {
    @Resource
    private IMessageService messageService;

    @GetMapping("/send")
    public String send() {
        return messageService.send();
    }
}

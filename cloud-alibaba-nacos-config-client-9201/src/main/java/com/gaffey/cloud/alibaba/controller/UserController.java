package com.gaffey.cloud.alibaba.controller;

import com.gaffey.cloud.alibaba.config.UserInfo;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
public class UserController {
    @Resource
    private UserInfo userInfo;

    @GetMapping("/user")
    public UserInfo getUserInfo() {
        return userInfo;
    }
}

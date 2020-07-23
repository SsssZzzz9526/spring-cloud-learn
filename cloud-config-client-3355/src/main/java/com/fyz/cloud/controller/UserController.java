package com.fyz.cloud.controller;

import com.fyz.cloud.config.User;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RefreshScope
@RestController
public class UserController {
    @Resource
    private User user;

    @GetMapping("/user")
    public User getUser() {
        // jackson无法序列化配置类
        User user = new User();
        BeanUtils.copyProperties(this.user, user);
        return user;
    }
}

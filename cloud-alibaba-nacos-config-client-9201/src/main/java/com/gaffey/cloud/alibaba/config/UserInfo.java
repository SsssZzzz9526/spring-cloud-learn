package com.gaffey.cloud.alibaba.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "user")
public class UserInfo {

    private String username;

    private String password;

    private String version;
}

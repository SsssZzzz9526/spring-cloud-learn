package com.gaffey.cloud.alibaba;

import com.gaffey.cloud.alibaba.config.UserInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Bean
    public UserInfo userInfo() {
        return new UserInfo();
    }
}



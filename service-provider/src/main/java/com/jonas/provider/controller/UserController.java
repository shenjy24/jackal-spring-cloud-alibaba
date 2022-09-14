package com.jonas.provider.controller;

import com.jonas.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjy
 * @createTime 2022/7/4 11:01
 * @description UserController
 */
@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${user}")
    private User user;

    @RequestMapping("/info")
    public User getUser() {
        return user;
    }

    @RequestMapping("/getUserName")
    public String getUserName(int userId) {
        return "user" + userId;
    }
}

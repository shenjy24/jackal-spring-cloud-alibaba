package com.jonas.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjy
 * @createTime 2022/7/4 11:01
 * @description UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUserName")
    public String getUserName(int userId) {
        return "user" + userId;
    }
}

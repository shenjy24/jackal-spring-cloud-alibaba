package com.jonas.consumer.controller;

import com.jonas.domain.User;
import com.jonas.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjy
 * @createTime 2022/9/14 20:40
 * @description UserFeignController
 */
@RestController
@RequestMapping("/user/feign")
public class UserFeignController {

    @Autowired
    private UserFeignService userService;

    @RequestMapping("/getName")
    public String feignName(int userId) {
        return userService.getName(userId);
    }

    @RequestMapping("/getUser")
    public User getUser() {
        return userService.getUser();
    }
}

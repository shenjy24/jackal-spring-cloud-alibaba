package com.jonas.consumer.controller;

import com.jonas.consumer.service.UserRestService;
import com.jonas.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private UserFeignService userService;
    @Autowired
    private UserRestService userRestService;

    @RequestMapping("/feign/getName")
    public String feignName(int userId) {
        return userService.getName(userId);
    }

    @RequestMapping("/rest/getName")
    public String getName(int userId) {
        return userRestService.getName(userId);
    }

    @PostMapping("/rest/postName")
    public String postName(Integer userId) {
        return userRestService.postName(userId);
    }
}

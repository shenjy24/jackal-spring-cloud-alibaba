package com.jonas.consumer.controller;

import com.jonas.consumer.service.UserRestService;
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
@RequestMapping("/user/rest")
public class UserRestController {

    @Autowired
    private UserRestService userRestService;

    @RequestMapping("/getName")
    public String getName(int userId) {
        return userRestService.getName(userId);
    }

    @PostMapping("/postName")
    public String postName(Integer userId) {
        return userRestService.postName(userId);
    }
}

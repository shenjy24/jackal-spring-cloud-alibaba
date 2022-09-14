package com.jonas.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2022/7/4 11:01
 * @description UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String GET_API = "http://service-provider/user/getUserName?userId={userId}";
    private static final URI POST_API = URI.create("http://service-provider/user/getUserName");

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    @RequestMapping("/feignName")
    public String feignName(int userId) {
        return userService.getName(userId);
    }

    @RequestMapping("getName")
    public String getName(int userId) {
        Map<String, Object> args = new HashMap<String, Object>() {{
           put("userId", userId);
        }};
        return restTemplate.getForObject(GET_API, String.class, args);
    }

    @PostMapping("postName")
    public String postName(Integer userId) {
        MultiValueMap<String, Integer> request = new LinkedMultiValueMap<>();
        request.add("userId", userId);
        return restTemplate.postForObject(POST_API, request, String.class);
    }
}

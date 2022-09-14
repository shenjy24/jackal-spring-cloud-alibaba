package com.jonas.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2022/9/14 19:26
 * @description UserRestService
 */
@Service
public class UserRestService {
    private static final String GET_API = "http://service-provider/user/getUserName?userId={userId}";
    private static final URI POST_API = URI.create("http://service-provider/user/getUserName");

    @Autowired
    private RestTemplate restTemplate;

    public String getName(int userId) {
        Map<String, Object> args = new HashMap<String, Object>() {{
            put("userId", userId);
        }};
        return restTemplate.getForObject(GET_API, String.class, args);
    }

    public String postName(Integer userId) {
        MultiValueMap<String, Integer> request = new LinkedMultiValueMap<>();
        request.add("userId", userId);
        return restTemplate.postForObject(POST_API, request, String.class);
    }
}

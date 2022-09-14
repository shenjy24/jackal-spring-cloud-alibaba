package com.jonas.feign;

import com.jonas.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author shenjy
 * @createTime 2022/9/14 17:22
 * @description UserService
 */
// service-provider 是微服务的 spring.application.name
@FeignClient("service-provider")
public interface UserFeignService {
    @GetMapping("/user/getUserName")
    String getName(@RequestParam int userId);

    @GetMapping("/user/info")
    User getUser();
}

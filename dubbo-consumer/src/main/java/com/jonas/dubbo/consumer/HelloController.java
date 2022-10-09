package com.jonas.dubbo.consumer;

import com.jonas.dubbo.api.IHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjy
 * @createTime 2022/10/9 16:06
 * @description HelloController
 */
@RestController
public class HelloController {

    @DubboReference
    private IHelloService helloService;

    @GetMapping("/say")
    public String sayHello(String name){
        return helloService.sayHello(name);
    }
}

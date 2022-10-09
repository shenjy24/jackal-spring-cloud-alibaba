package com.jonas.dubbo.provider;

import com.jonas.dubbo.api.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author shenjy
 * @createTime 2022/10/9 15:47
 * @description HelloService
 */
@DubboService
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}

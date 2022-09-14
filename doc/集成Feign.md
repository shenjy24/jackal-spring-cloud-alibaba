### 添加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### 添加注解
在Application类添加`@EnableFeignClients`注解

### 添加接口
```java
// service-provider 是微服务的 spring.application.name
@FeignClient("service-provider")
public interface UserService {
    @GetMapping("/user/getUserName")
    String getName(@RequestParam int userId);
}
```

### 接口调用
```
@Autowired
private UserService userService;

@RequestMapping("/feignName")
public String feignName(int userId) {
    return userService.getName(userId);
}
```

### 问题
1.`java.lang.NoSuchMethodError: org.springframework.cloud.client.loadbalancer.LoadBalancerProperties.isUseRawStatusCodeInResponseData()Z`

问题：版本问题，`spring-cloud-starter-alibaba-nacos-discovery`中的`spring-cloud-commons`与
`spring-cloud-starter-openfeign`中的版本冲突。

解决办法：将`spring-cloud-starter-openfeign`的版本改为3.1.1
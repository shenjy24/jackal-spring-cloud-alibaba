### 一.版本说明

- `spring-boot`: 2.6.3

- `spring-cloud-alibaba`: 2021.0.1.0

- `loadbalancer`: 3.1.0

### 二.配置依赖

1.增加`dependencyManagement`配置

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2021.0.1.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

2.增加`nacos-discovery`依赖

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

3.增加`loadbalancer`依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    <version>3.1.0</version>
</dependency>
```

注：此处只列出了Nacos相关依赖，详细依赖可参考[仓库代码](https://github.com/shenjy24/jackal-spring-cloud-alibaba-client)。

### 三.服务配置

1.在`application.yml`配置文件配置服务名以及Nacos服务地址。

```yaml
spring:
  application:
    name: service-consumer
  cloud:
    nacos:
      server-addr: 127.0.0.1:18848
```

2.在启动类增加`@EnableDiscoveryClient`注解。

### 四.服务验证

启动服务后，在 Nacos 控制台查看服务是否注册。

### 五.服务调用

1.配置`RestTemplate`

```java
@Configuration
public class WebConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

2.`get`请求

```java
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String GET_API = "http://service-provider/user/getUserName?userId={userId}";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("getName")
    public String getName(int userId) {
        Map<String, Object> args = new HashMap<String, Object>() {{
           put("userId", userId);
        }};
        return restTemplate.getForObject(GET_API, String.class, args);
    }
}
```

3.`post`请求

```java
@RestController
@RequestMapping("/user")
public class UserController {

    private static final URI POST_API = URI.create("http://service-provider/user/getUserName");

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("postName")
    public String postName(Integer userId) {
        MultiValueMap<String, Integer> request = new LinkedMultiValueMap<>();
        request.add("userId", userId);
        return restTemplate.postForObject(POST_API, request, String.class);
    }
}
```

### 六.仓库地址
[jackal-spring-cloud-alibaba-client](https://github.com/shenjy24/jackal-spring-cloud-alibaba-client)

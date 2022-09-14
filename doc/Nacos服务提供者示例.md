### 一.版本说明

- `spring-boot`: 2.6.3

- `spring-cloud-alibaba`: 2021.0.1.0

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

注：此处只列出了Nacos相关依赖，详细依赖可参考[仓库代码](https://github.com/shenjy24/jackal-spring-cloud-alibaba-server)。

### 三.服务配置

1.在`application.yml`配置文件配置服务名以及Nacos服务地址。

```yaml
spring:
  application:
    name: service-provider
  cloud:
    nacos:
      server-addr: 127.0.0.1:18848
```

2.在启动类增加`@EnableDiscoveryClient`注解。

### 四.服务接口

```java
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUserName")
    public String getUserName(int userId) {
        return "user" + userId;
    }
}
```

### 五.服务验证

启动服务后，在 Nacos 控制台查看服务是否注册。
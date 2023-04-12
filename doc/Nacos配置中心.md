### 1. 启动`Nacos`

### 2. 增加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
    <version>3.1.1</version>
</dependency>
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

### 3. 增加`bootstrap.properties`配置
需要将`Nacos`相关的配置放置于该文件中，如果配置在`application.yml`将不起作用。
```properties
spring.application.name=service-provider
spring.cloud.nacos.discovery.server-addr=127.0.0.1:18848
spring.cloud.nacos.config.server-addr=127.0.0.1:18848
spring.cloud.nacos.config.file-extension=yaml
```

### 4. 在`Nocas`控制台页面创建`service-provider`配置

![image](https://github.com/shenjy24/document/raw/master/images/spring/nacos-config.jpeg)

### 5. 在代码中就可以使用`@Value`来获取相关的配置信息
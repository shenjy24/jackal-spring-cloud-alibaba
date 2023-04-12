### 一. routes 参数详解

配置示例
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: service-consumer-feign        # 路由id，没有固定规则，但唯一，建议与服务名对应
          # uri: http://localhost:18081      # 匹配后提供服务的路由地址
          uri: lb://service-consumer        # 动态路由，使用服务名代替上面的IP带端口
          predicates:
            #以下是断言条件，必须全部符合条件
            - Path=/user/feign/**               #断言，路径匹配 注意：Path 中 P 为大写
            - Method=GET                       #只能时 GET 请求时，才能访问
```

#### 1. id
在一个网关中，id要是唯一的，命名的时候方便区分即可。

#### 2. uri

请求转发的目标地址，可以以`http`、`https`、`lb`和`ws`等为前缀。例如：

##### (1) `http`、`https` 方式
```yaml
uri: http://localhost:8001/
```

##### (2) `lb`(注册中心中服务名字)方式 
```yaml
uri: lb://cloud-payment-service
```

以`lb`为前缀的时候表示这是一个动态路由，GateWay会根据`lb://`后面的服务名称从服务注册中心中获取服务实例，然后将请求转发到具体的服务地址，实现负载均衡。

##### (3) `websocket`方式
```yaml
uri: ws://localhost:8001/
```

#### 3. predicates

通过断言可以设置一组路由条件，可以根据请求方式、请求头、请求参数、请求主机和请求路径等断言进行设置，通常我们使用请求路径进行设置，
只要请求地址和Path中的地址匹配，就会将请求转发，多个路径以逗号分隔，例如：
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: path_route
          uri: https://example.org
          predicates:
            - Path=/red/{segment},/blue/{segment}
```

#### 4. filters

使用过滤器可以在网关转发请求前和接收响应后执行一些操作，例如鉴权、限流等，例如StripPrefix可以去掉客户端请求地址中的若干路径，然后将剩余的路径和uri中的地址拼接后转发。

Filter分两种，GatewayFilter和GlobalFilter。
- GatewayFilter应用在单个路由上。
- GlobalFilter应用在全局路由上。因为GlobalFilter可以使所有的路由都生效，所以可以用来实现统一的鉴权、日志记录等功能。

## 参考资料

[Spring Cloud Gateway--配置路由的方法](https://blog.51cto.com/knifeedge/5458217)
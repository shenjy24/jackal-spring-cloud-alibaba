package com.jonas.gateway.config;

import com.jonas.gateway.filter.AFilter;
import com.jonas.gateway.filter.BFilter;
import com.jonas.gateway.filter.CFilter;
import com.jonas.gateway.filter.ElapsedFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author shenjy
 * @createTime 2022/9/14 20:13
 * @description GatewayConfig
 */
@Configuration
public class GatewayConfig {

    @Bean
    @Order(-1)
    public GlobalFilter a() {
        return new AFilter();
    }

    @Bean
    @Order(0) // 值越大则优先级越低
    public GlobalFilter b() {
        return new BFilter();
    }

    @Bean
    @Order(1)
    public GlobalFilter c() {
        return new CFilter();
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/filter/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(new ElapsedFilter()))
                        .uri("lb://service-consumer"))
                .build();
    }

}

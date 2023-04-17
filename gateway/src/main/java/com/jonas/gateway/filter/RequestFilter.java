package com.jonas.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

@Slf4j
public class RequestFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求体
        ServerHttpRequest request = exchange.getRequest();
        Flux<DataBuffer> body = request.getBody();

        StringBuilder sb = new StringBuilder();
        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            //ReferenceCountUtil.release(buffer); 谨慎注意！！！这里的释放导致了内存泄露
            try {
                String bodyString = new String(bytes, "utf-8");
                sb.append(bodyString);
            } catch (UnsupportedEncodingException e) {
                log.info(e.getMessage());
            }finally {
                DataBufferUtils.release(buffer);
            }
        });

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {

        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

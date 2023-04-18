package com.jonas.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 先把请求体从流的方式读取出来，然后再往下进行传递
 * <a href="https://blog.csdn.net/m0_57640408/article/details/128335546">从Spring Cloud Gateway过滤器中获取请求体的最优方案</a>
 */
@Component
public class ReqBodyGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (request.getHeaders().getContentType() == null
                && !HttpMethod.POST.equals(request.getMethod())) {
            return chain.filter(exchange);
        }

        return DataBufferUtils.join(request.getBody())
                .flatMap(dataBuffer -> {
                    DataBufferUtils.retain(dataBuffer);
                    System.out.println(dataBuffer);
                    Flux<DataBuffer> cachedFlux = Flux.defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
                    ServerHttpRequest mutatedReq = new ServerHttpRequestDecorator(request) {
                        @Override
                        public Flux<DataBuffer> getBody() {
                            return cachedFlux;
                        }
                    };
                    return chain.filter(exchange.mutate().request(mutatedReq).build());
                });
    }

    @Override
    public int getOrder() {
        return -2;
    }
}

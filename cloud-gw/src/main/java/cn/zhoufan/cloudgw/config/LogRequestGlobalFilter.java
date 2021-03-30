package cn.zhoufan.cloudgw.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.zhoufan.cloudgw.entity.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * @Description : 输出日志
 * @Author : zhoufan
 * @Date : 2021/2/22
 */
@Slf4j
@Configuration
public class LogRequestGlobalFilter {

    @Bean(name = "logReqGlobalFilter")
    @Order(-1)
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            // 获取请求信息
            ServerHttpRequest request = exchange.getRequest();
            // 获取请求body
            RequestInfo requestInfo = new RequestInfo();
            // 获取请求query
            Map queryMap = request.getQueryParams();
            String requestParams = JSONUtil.toJsonStr(queryMap);
            String requestUrl = request.getPath().toString();
            String requestMethod = request.getMethodValue();
            HttpHeaders requestHeaders = request.getHeaders();
            InetSocketAddress address = request.getRemoteAddress();

            requestInfo.setAddress(address.toString());
            requestInfo.setHeaders(requestHeaders);
            requestInfo.setRequestUrl(requestUrl);
            requestInfo.setMethod(requestMethod);
            requestInfo.setParams(requestParams);

            if (StrUtil.equals(requestMethod, HttpMethod.POST.name())) {
                return getRequestBody(exchange, chain, requestInfo);
            }

            log.info("【请求信息】：{}", requestInfo.toString());
            return chain.filter(exchange.mutate().request(exchange.getRequest()).build());
        };

    }

    /**
     * 获取响应体
     *
     * @param exchange
     * @param chain
     * @param requestInfo
     * @return
     */
    private Mono<Void> getRequestBody(ServerWebExchange exchange, GatewayFilterChain chain, RequestInfo requestInfo) {
        //获取请求体信息
        ServerRequest serverRequest = new DefaultServerRequest(exchange);
        // read & modify body
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
                .flatMap(body -> {
                    // origin body map
                    requestInfo.setRequestBody(JSONUtil.toJsonStr(StrUtil.removeAllLineBreaks(body)));
                    log.info("【请求信息】：{}", requestInfo.toString());
                    return Mono.just(body);
                });

        BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());

        // the new content type will be computed by bodyInserter and then set in the request decorator
        headers.remove(HttpHeaders.CONTENT_LENGTH);

        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage, new BodyInserterContext())
                .then(Mono.defer(() -> {
                    ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(
                            exchange.getRequest()) {
                        @Override
                        public HttpHeaders getHeaders() {
                            long contentLength = headers.getContentLength();
                            HttpHeaders httpHeaders = new HttpHeaders();
                            httpHeaders.putAll(super.getHeaders());
                            if (contentLength > 0) {
                                httpHeaders.setContentLength(contentLength);
                            } else {
                                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                            }
                            return httpHeaders;
                        }

                        @Override
                        public Flux<DataBuffer> getBody() {
                            return outputMessage.getBody();
                        }
                    };
                    return chain.filter(exchange.mutate().request(decorator).build());
                }));
    }

}

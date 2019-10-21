package com.fishingtime.framework.springcloud.gateway;

import cn.hutool.core.util.URLUtil;
import com.fishingtime.framework.common.base.util.JSONUtil;
import com.fishingtime.framework.common.web.exception.AuthenticationException;
import com.fishingtime.framework.common.web.exception.ForbiddenException;
import com.fishingtime.framework.common.web.response.R;
import com.fishingtime.framework.common.web.response.ResultStatus;
import com.fishingtime.framework.springcloud.gateway.service.PermissionService;
import com.fishingtime.framework.springcloud.gateway.service.UserService;
import com.fishingtime.framework.uaa.bean.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-08 16:28
 **/
@Slf4j
public class PermissionFilter implements GlobalFilter, Ordered {

    public PermissionFilter() {

    }

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = null;
        List<String> tokens = headers.get("ticket");
        if (null != tokens && tokens.size() > 0) {
            token = tokens.get(0);
        }

        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException(new R().setStatus(ResultStatus.AUTH_ERROR).setMessage("认证失败，请重新登录！"));
        }
        SystemUser systemUser = userService.getUserByToken(token);

        if (!permissionService.hasPermission(systemUser.getPrivileges(), exchange.getRequest().getURI().getPath())) {
            throw new ForbiddenException(new R().setStatus(ResultStatus.FORBIDDEN_ERROR).setMessage("您无权访问服务！"));
        }
        return chain.filter(exchange);
//        return handleHeader(exchange, chain, systemUser);
    }


    private Mono<Void> handleHeader(ServerWebExchange exchange, GatewayFilterChain chain, SystemUser systemUser) {
        ServerHttpRequest host = exchange.getRequest().mutate().header("user", URLUtil.encode(JSONUtil.toJSONString(systemUser))).build();
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);
    }


//    /**
//     * 处理请求(添加全局类参数),暂时未用
//     *
//     * @param
//     * @param exchange
//     * @param chain
//     * @return
//     */
//    private Mono<Void> handleParameter(ServerWebExchange exchange, GatewayFilterChain chain, String token) {
//        ServerHttpRequest serverHttpRequest = exchange.getRequest();
//        HttpMethod method = serverHttpRequest.getMethod();
//        String contentType = serverHttpRequest.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
//        SystemUser systemUser = userService.getUserByToken(token);
//        if (HttpMethod.POST != method &&
//                (contentType.toLowerCase().indexOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE.toLowerCase()) != -1
//                        || contentType.toLowerCase().indexOf(MediaType.APPLICATION_JSON_VALUE.toLowerCase()) != -1)) {
//            ServerRequest serverRequest = ServerRequest.create(exchange, null);
//            // mediaType
//            MediaType mediaType = exchange.getRequest().getHeaders().getContentType();
//            // read & modify body
//            Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
//                    .flatMap(body -> {
//                        String newBody = body;
//                        try {
//                            Map mapBody = JSONUtil.parseObject(body);
//                            mapBody.put(EntityBase.CREATE_STAFF, systemUser.getSysUserId());
//                            mapBody.put(EntityBase.UPDATE_STAFF, systemUser.getSysUserId());
//                            newBody = JSONUtil.toJSONString(mapBody);
//                        } catch (Exception e) {
//                            log.error(body, e);
//                        }
//                        return Mono.just(newBody);
//                    });
//
//            BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
//            HttpHeaders headers = new HttpHeaders();
//            headers.putAll(exchange.getRequest().getHeaders());
//
//            // the new content type will be computed by bodyInserter
//            // and then set in the request decorator
//            headers.remove(HttpHeaders.CONTENT_LENGTH);
//
//            CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
//            return bodyInserter.insert(outputMessage, new BodyInserterContext())
//                    .then(Mono.defer(() -> {
//                        ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(
//                                exchange.getRequest()) {
//                            @Override
//                            public HttpHeaders getHeaders() {
//                                long contentLength = headers.getContentLength();
//                                HttpHeaders httpHeaders = new HttpHeaders();
//                                httpHeaders.putAll(super.getHeaders());
//                                httpHeaders.add("user", JSONUtil.toJSONString(systemUser));
//                                if (contentLength > 0) {
//                                    httpHeaders.setContentLength(contentLength);
//                                } else {
//                                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
//                                }
//                                return httpHeaders;
//                            }
//
//                            @Override
//                            public Flux<DataBuffer> getBody() {
//                                return outputMessage.getBody();
//                            }
//                        };
//                        return chain.filter(exchange.mutate().request(decorator).build());
//                    }));
//        }
//        return chain.filter(exchange);
//    }
}

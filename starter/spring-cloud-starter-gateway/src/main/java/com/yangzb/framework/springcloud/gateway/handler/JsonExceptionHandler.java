package com.yangzb.framework.springcloud.gateway.handler;


import com.yangzb.framework.common.web.Constants;
import com.yangzb.framework.common.web.Response;
import com.yangzb.framework.common.web.ResponseBodyError;
import com.yangzb.framework.common.web.exception.AuthenticationException;
import com.yangzb.framework.common.web.exception.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * 自定义异常处理
 * 解决问题：
 * 1 统一网关响应消息格式"{\"status\":" + httpStatus + ",\"message\": \"" + message + "\"}"
 * 2 特殊异常处理，不让其直接抛往前端，比如feign调用过程，某些服务不可用“syscall:getsockopt(..) failed: 拒绝连接: /******:11340”
 *
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-16 17:57
 **/
@Slf4j
public class JsonExceptionHandler implements ErrorWebExceptionHandler {
    /**
     * MessageReader
     */
    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

    /**
     * MessageWriter
     */
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();

    /**
     * ViewResolvers
     */
    private List<ViewResolver> viewResolvers = Collections.emptyList();

//    /**
//     * 存储处理异常后的信息
//     */
    private ThreadLocal<ResponseEntity<ResponseBodyError>> exceptionHandlerResult = new ThreadLocal<>();

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        Assert.notNull(messageReaders, "'messageReaders' must not be null");
        this.messageReaders = messageReaders;
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Assert.notNull(messageWriters, "'messageWriters' must not be null");
        this.messageWriters = messageWriters;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        // 按照异常类型进行处理
        HttpStatus httpStatus;
        String message;
        String errorCode = Constants.ERROR_9999.getCode();

        if (ex instanceof NotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
            message = "Service Not Found";
        }else if (ex instanceof AuthenticationException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            message = StringUtils.isEmpty(ex.getMessage()) ?Constants.ERROR_8000.getMessage():ex.getMessage();
            errorCode = Constants.ERROR_8000.getCode();
        }else if (ex instanceof ForbiddenException) {
            httpStatus = HttpStatus.FORBIDDEN;
            message = StringUtils.isEmpty(ex.getMessage()) ?Constants.ERROR_8000.getMessage():ex.getMessage();
            errorCode = Constants.ERROR_8000.getCode();
        }else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            httpStatus = responseStatusException.getStatus();
            message = responseStatusException.getMessage();
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = StringUtils.isEmpty(ex.getMessage()) ? "Internal Server Error" : ex.getMessage();
        }
        ResponseBodyError responseBodyError = new ResponseBodyError(errorCode,message,message);
        ResponseEntity<ResponseBodyError> r = new ResponseEntity(responseBodyError, Response.PROBLEM_HEADERS,httpStatus);
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        exceptionHandlerResult.set(r);
        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
        return RouterFunctions
                .route(RequestPredicates.all(), this::renderErrorResponse)
                .route(newRequest)
                .switchIfEmpty(Mono.error(ex))
                .flatMap((handler) -> handler.handle(newRequest))
                .flatMap((response) -> write(exchange, response));

    }

    /**
     * 参考DefaultErrorWebExceptionHandler
     * <p>
     * 屏蔽特殊异常：{"status":500,"message": "syscall:getsockopt(..) failed: 拒绝连接: /******:11340"}
     */
    private final static String GET_SOCKOPT_FAILED = "syscall:getsockopt(..) failed";

    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        ResponseEntity<ResponseBodyError> result = exceptionHandlerResult.get();
//        HttpStatus httpStatus = (HttpStatus) result.get("httpStatus");
//        String body = (String) result.get("body");
//        if (httpStatus == HttpStatus.INTERNAL_SERVER_ERROR) {
//            log.error("path = {},body = {}", request.path(), body);
//            if (body.contains(GET_SOCKOPT_FAILED)) {
//                body = "{\"status\":" + httpStatus + ",\"message\": \"系统繁忙，请稍后重试\"}";
//            }
//        }
        return ServerResponse.status(result.getStatusCode())
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(BodyInserters.fromObject(result.getBody()));
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    private Mono<? extends Void> write(ServerWebExchange exchange,
                                       ServerResponse response) {
        exchange.getResponse().getHeaders()
                .setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    private class ResponseContext implements ServerResponse.Context {

        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return JsonExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return JsonExceptionHandler.this.viewResolvers;
        }

    }

}
package com.fishingtime.framework.springboot.web.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 返回格式统一包装
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-30 15:58
 **/
@ControllerAdvice
@Slf4j
public class ResponseBodyAdvice implements org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            if(request instanceof ServletServerHttpRequest){
                ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest)request;
                servletServerHttpRequest.getServletRequest().setAttribute(Consts.RESPONSE_BODY,body);
            }
        } catch (Exception e) {
            //不做异常处理
        }
        return body;
    }
}

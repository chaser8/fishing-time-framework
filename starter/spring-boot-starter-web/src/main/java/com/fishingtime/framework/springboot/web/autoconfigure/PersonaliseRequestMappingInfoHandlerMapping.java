package com.fishingtime.framework.springboot.web.autoconfigure;

import com.fishingtime.framework.common.web.ControllerBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * Override  RequestMappingHandlerMapping.registerHandlerMethod
 * 当出现Mapping路径重复时，优先使用子类实现
 *
 * @program:
 * @description:
 * @author:
 * @create: 2019-06-26 11:33
 **/
@Slf4j
public class PersonaliseRequestMappingInfoHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        boolean register = true;
        Class<?> declaringClass = method.getDeclaringClass();
        //判断是否为ControllerBase的子类 如果是才去处理
        if (ControllerBase.class.isAssignableFrom(declaringClass)) {
            HandlerMethod handlerMethod = super.getHandlerMethods().get(mapping);
            //如果该路径已经被注册
            if (handlerMethod != null) {
                Class<?> aClass = handlerMethod.getMethod().getDeclaringClass();
                //发现如果已经注册的mapping是当前注册的mapping的父类，则unregisterMapping父类，注册子类
                if (aClass.isAssignableFrom(declaringClass)&&!declaringClass.equals(aClass)) {
                    super.unregisterMapping(mapping);
                }
                //发现如果已经注册的mapping是当前注册的mapping的子类，则跳过不进行注册
                if(declaringClass.isAssignableFrom(aClass)){
                    register = false;
                }
            }
        }
        if (register) {
            super.registerHandlerMethod(handler, method, mapping);
            log.info("Mapped {}:{}",mapping,method);
        }
    }
}
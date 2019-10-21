package com.fishingtime.framework.springboot.web.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 配置自定义RequestMappingHandlerMapping
 * @see PersonaliseRequestMappingInfoHandlerMapping
 * @program:
 * @description:
 * @author:
 * @create: 2019-06-27 11:28
 **/
@Configuration
public class WebMvcRegistrations implements org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new PersonaliseRequestMappingInfoHandlerMapping();
    }
}
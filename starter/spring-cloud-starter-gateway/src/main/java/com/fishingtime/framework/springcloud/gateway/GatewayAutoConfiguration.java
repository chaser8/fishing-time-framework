package com.fishingtime.framework.springcloud.gateway;

import com.fishingtime.framework.springcloud.gateway.handler.JsonExceptionHandler;
import com.fishingtime.framework.springcloud.gateway.service.PermissionService;
import com.fishingtime.framework.springcloud.gateway.service.impl.PermissionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @program:  用于默认异常返回
 * @description:
 * @author:
 * @create: 2019-05-08 14:28
 **/
@Configuration
@ComponentScan(basePackages = "com.fishingtime.dev1")
@Slf4j
public class GatewayAutoConfiguration {
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer) {
        JsonExceptionHandler jsonExceptionHandler = new JsonExceptionHandler();
        jsonExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        jsonExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        jsonExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return jsonExceptionHandler;
    }

    @Bean
    @ConditionalOnMissingBean(PermissionService.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    public PermissionService permissionService() {
        return new PermissionServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(PermissionFilter.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    public PermissionFilter permissionFilter() {
        return new PermissionFilter();
    }

}
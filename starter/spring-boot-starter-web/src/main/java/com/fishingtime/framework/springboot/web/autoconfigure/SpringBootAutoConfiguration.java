package com.fishingtime.framework.springboot.web.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-03-05 10:56
 **/
@ComponentScan(basePackages = {"com.fishingtime"})
@Configuration
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
@EnableConfigurationProperties({Config.class,ServerProperties.class, ResourceProperties.class, WebMvcProperties.class})
@ImportAutoConfiguration(WebMvcRegistrations.class)
public class SpringBootAutoConfiguration {
    @Bean
    public org.springframework.boot.web.servlet.error.DefaultErrorAttributes errorAttributes(@Autowired ServerProperties serverProperties) {
        return new DefaultErrorAttributes(
                serverProperties.getError().isIncludeException());
    }
}
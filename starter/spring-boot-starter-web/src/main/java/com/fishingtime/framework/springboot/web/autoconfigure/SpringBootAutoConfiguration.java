package com.fishingtime.framework.springboot.web.autoconfigure;

import com.fishingtime.framework.springboot.web.filter.RequestWrapperFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
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
@EnableConfigurationProperties({ServerProperties.class, ResourceProperties.class, WebMvcProperties.class})
@ImportAutoConfiguration(WebMvcRegistrations.class)
@ServletComponentScan
public class SpringBootAutoConfiguration {
    @Bean
    public org.springframework.boot.web.servlet.error.DefaultErrorAttributes errorAttributes(@Autowired ServerProperties serverProperties) {
        return new DefaultErrorAttributes(
                serverProperties.getError().isIncludeException());
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new RequestWrapperFilter());
        registration.addUrlPatterns("/*"); //
//        registration.addInitParameter("requestWrapperFilter", "paramValue"); //
        registration.setName("requestWrapperFilter");
        registration.setOrder(1);
        return registration;
    }
}
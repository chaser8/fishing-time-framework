package com.yangzb.framework.springboot.web.autoconfigure;

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
 * @ComponentScan(basePackages = {"com.tydic.dev1"},excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.tydic\\.dev1\\.sale\\.base\\.clue\\.controller\\.([^\\.].*)BaseController")})
 * @program: dic-framework-v3
 * @description:
 * @author: yzb
 * @create: 2019-03-05 10:56
 **/
@ComponentScan(basePackages = {"com.tydic.dev1"})
//@ComponentScan(basePackages = {"com.tydic.dev1"},excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.tydic\\.dev1\\.(.*)BaseController")})
@Configuration
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
@EnableConfigurationProperties({ServerProperties.class, ResourceProperties.class, WebMvcProperties.class})
@ImportAutoConfiguration(WebMvcRegistrations.class)
public class SpringBootAutoConfiguration {
    @Bean
    public org.springframework.boot.web.servlet.error.DefaultErrorAttributes errorAttributes(@Autowired ServerProperties serverProperties) {
        return new DefaultErrorAttributes(
                serverProperties.getError().isIncludeException());
    }
}
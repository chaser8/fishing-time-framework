package com.fishingtime.framework.springcloud.openfeign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-03-05 10:56
 **/
@Configuration
public class FeignAutoConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor(@Autowired ObjectMapper objectMapper){
        return new FeignRequestInterceptor(objectMapper);
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignServerErrorDecoder();
    }

    @Bean
    public ResponseEntityDecoder responseEntityDecoder(@Autowired ObjectFactory<HttpMessageConverters> messageConverters){
        return new ResponseEntityDecoder(new SpringDecoder(messageConverters));
    }
}
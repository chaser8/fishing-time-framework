package com.fishingtime.framework.swagger.autoconfigure;

import cn.hutool.core.collection.CollUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-03-05 10:56
 **/
@ConditionalOnWebApplication
@EnableConfigurationProperties(Swagger2Properties.class)
@ConditionalOnProperty(name = "swagger2.enable", havingValue = "true")
@Configuration
@EnableSwagger2
public class Swagger2AutoConfiguration {
    @Bean
    public Docket api() {

        Docket docket= new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(buildApiInf())
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .useDefaultResponseMessages(true)
                .globalResponseMessage(RequestMethod.GET,
                        CollUtil.newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("500 message")
                                .responseModel(new ModelRef("Error"))
                                .build()))
//                .securitySchemes(CollUtil.newArrayList(apiKey()))
//                .securityContexts(CollUtil.newArrayList(securityContext()))
                .enableUrlTemplating(false);
//                .additionalModels(typeResolver.resolve(Map.class));
        return docket;
    }
    @Bean
    UiConfiguration uiConfig() {
        UiConfiguration uiConfiguration = new UiConfiguration("http://www.fishingtime.com");
        return uiConfiguration;
    }
    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title("openApi")
                .description("openApi")
                .contact(new Contact("fishingtime", "http://www.fishingtime.com", "fishingtime@fishingtime.com"))
                .build();
    }
}
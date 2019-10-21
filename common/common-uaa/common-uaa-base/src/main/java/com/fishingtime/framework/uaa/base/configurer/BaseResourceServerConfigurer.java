package com.fishingtime.framework.uaa.base.configurer;

import com.fishingtime.framework.uaa.base.handler.AccessDeniedHandler;
import com.fishingtime.framework.uaa.base.handler.AuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-04-28 18:54
 **/
public class BaseResourceServerConfigurer extends ResourceServerConfigurerAdapter {
    protected ResourceServerProperties resourceServerProperties;
    public BaseResourceServerConfigurer(ResourceServerProperties resourceServerProperties) {
        this.resourceServerProperties = resourceServerProperties;
    }

    @Autowired
    protected OAuth2WebSecurityExpressionHandler expressionHandler;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(new AuthenticationEntryPoint());
        resources.accessDeniedHandler(new AccessDeniedHandler());
    }

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }
}

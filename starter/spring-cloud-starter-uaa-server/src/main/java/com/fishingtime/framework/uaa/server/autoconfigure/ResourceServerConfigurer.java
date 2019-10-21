package com.fishingtime.framework.uaa.server.autoconfigure;

import com.fishingtime.framework.uaa.base.configurer.BaseResourceServerConfigurer;
import com.fishingtime.framework.uaa.base.configurer.ResourceServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-04-20 10:38
 **/
@EnableResourceServer
public class ResourceServerConfigurer extends BaseResourceServerConfigurer {
    public ResourceServerConfigurer(ResourceServerProperties resourceServerProperties) {
        super(resourceServerProperties);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();//关闭跨域访问
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();

        for (String au : resourceServerProperties.getWhiteList()) {
            registry.antMatchers(au).permitAll();
        }
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl = registry.anyRequest();
        //经过测试这句话只能写在最后
        authorizedUrl.authenticated();
    }

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
////        resources.expressionHandler(expressionHandler);
////        resources.authenticationEntryPoint(new AuthenticationEntryPoint());
////        resources.accessDeniedHandler(new AccessDeniedHandler());
//    }

//    @Bean
//    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
//        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
//        expressionHandler.setApplicationContext(applicationContext);
//        return expressionHandler;
//    }
}
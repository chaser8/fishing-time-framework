package com.yangzb.framework.uaa.resource.server.autoconfigure;

import com.yangzb.framework.uaa.base.configurer.BaseResourceServerConfigurer;
import com.yangzb.framework.uaa.base.configurer.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-04-20 10:38
 **/
@Configuration
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
        //自定义验证规则
        authorizedUrl.access("@permissionService.hasPermission(request,authentication)");
        //经过测试这句话只能写在最后
//        authorizedUrl.authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        //设置expressionHandler以后registry.anyRequest().access("@permissionService.hasPermission(request,authentication)");这种表达式才会生效
        resources.expressionHandler(expressionHandler);
        super.configure(resources);
    }
}
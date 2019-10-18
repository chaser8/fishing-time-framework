package com.yangzb.framework.uaa.server.autoconfigure;

import com.yangzb.framework.uaa.base.configurer.ResourceServerProperties;
import com.yangzb.framework.uaa.base.configurer.WebSecurityProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-04-26 17:41
 **/
@Configuration
@AutoConfigureBefore(OAuth2AutoConfiguration.class)
@EnableConfigurationProperties({WebSecurityProperties.class, ResourceServerProperties.class})
public class UAAServerAutoConfiguration {

    @Configuration
    @Import(AuthorizationServerConfigurer.class)
    protected static class AuthorizationServer {

    }
    @Configuration
    @Import(WebSecurityConfigurer.class)
    protected static class WebSecurity {

    }
    @Configuration
    @Import(ResourceServerConfigurer.class)
    protected static class ResourceServer {

    }
}
package com.fishingtime.framework.uaa.resource.server.autoconfigure;

import com.fishingtime.framework.uaa.base.configurer.ResourceServerProperties;
import com.fishingtime.framework.uaa.base.configurer.WebSecurityProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-04-28 19:29
 **/
@Configuration
@AutoConfigureBefore(OAuth2AutoConfiguration.class)
@EnableConfigurationProperties({WebSecurityProperties.class, ResourceServerProperties.class})
//@Import(value = {WebSecurityConfigurer.class,ResourceServerConfigurer.class})
@Import(value = {ResourceServerConfigurer.class})
public class ResourceServerAutoConfiguration {
}
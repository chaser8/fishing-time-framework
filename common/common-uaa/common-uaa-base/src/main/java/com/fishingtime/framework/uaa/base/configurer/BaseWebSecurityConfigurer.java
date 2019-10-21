package com.fishingtime.framework.uaa.base.configurer;

import cn.hutool.core.util.ArrayUtil;
import com.fishingtime.framework.common.base.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-04-28 19:07
 **/
@Slf4j
public class BaseWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = new String[]
        {
                "/error",
                "/favicon.ico",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/doc.html",
                "/webjars/**"
        };
    protected WebSecurityProperties webSecurityProperties;

    public BaseWebSecurityConfigurer(WebSecurityProperties webSecurityProperties) {
        this.webSecurityProperties = webSecurityProperties;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        if (webSecurityProperties.getWhiteList() == null) {
            webSecurityProperties.setWhiteList(AUTH_WHITELIST);
        } else {
            webSecurityProperties.setWhiteList(ArrayUtil.append(webSecurityProperties.getWhiteList(), AUTH_WHITELIST));
        }
        /**
         * 要忽略的静态资源最好是在这里设置，效率更高
         */
        //如果不设置这个访问报错的页面（比如 404）也会出现invalid_token
        log.info("WhiteList-> {}", JSONUtil.toJSONString(webSecurityProperties.getWhiteList()));
        web.ignoring().mvcMatchers(webSecurityProperties.getWhiteList());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}

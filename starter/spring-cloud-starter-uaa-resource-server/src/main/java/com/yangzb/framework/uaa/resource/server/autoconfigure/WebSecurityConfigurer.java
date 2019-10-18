/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：SecurityConfig.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.yangzb.framework.uaa.resource.server.autoconfigure;

import com.yangzb.framework.uaa.base.configurer.BaseWebSecurityConfigurer;
import com.yangzb.framework.uaa.base.configurer.WebSecurityProperties;


/**
 * 
 * @program:
 * @description: 
 * @author: yzb
 * @date 2019/4/28 16:19
 **/
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends BaseWebSecurityConfigurer {
    public WebSecurityConfigurer(WebSecurityProperties webSecurityProperties) {
        super(webSecurityProperties);
    }
}
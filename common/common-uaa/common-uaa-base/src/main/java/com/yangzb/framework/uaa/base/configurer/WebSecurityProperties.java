package com.yangzb.framework.uaa.base.configurer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-04-29 09:39
 **/
@ConfigurationProperties(prefix = "security.oauth2")
@Getter
@Setter
public class WebSecurityProperties {
    private String [] whiteList;
}
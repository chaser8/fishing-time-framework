package com.fishingtime.framework.springboot.web.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @program: fishing-time-frameworkœ
 * @description:
 * @author:
 * @create: 2019-10-21 16:26
 **/
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class Config {
    @NestedConfigurationProperty
    private Sign sign = new Sign();
    @Getter
    @Setter
    public class Sign {
        public static final String KEY = "sign";
        private boolean enable = true;
        private String secretKey = "123";
        private String [] patterns;
    }
}
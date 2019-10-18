package com.tydic.dev1.cache.autoconfigure;

import com.tydic.cache.config.CacheClientConfig;
import com.tydic.cache.config.JedisClientConfig;
import com.tydic.cache.config.LettuceClientConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author yzb
 * @Description:
 * @return
 * @date 2019/2/25 16:52
 */
@ConfigurationProperties(prefix = "cache")
@Getter
@Setter
public class CacheProperties extends CacheClientConfig {
    @NestedConfigurationProperty
    private SpringBootCtgClientConfig ctg;
    @NestedConfigurationProperty
    private JedisClientConfig jedis;
    @NestedConfigurationProperty
    private LettuceClientConfig lettuce;

    public CacheClientConfig getConfig() {
        switch (this.getMode()) {
            case CTG:
                return ctg;
            case JEDIS:
                return jedis;
            case LETTUCE:
                return lettuce;
            default:
                return null;
        }

    }
}

package com.tydic.dev1.cache.autoconfigure;

import com.tydic.cache.config.CtgClientConfig;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: framework
 * @description:
 * @author: yzb
 * @create: 2019-06-10 16:33
 **/
public class SpringBootCtgClientConfig extends CtgClientConfig {
    @Override
    public JedisPoolConfig getPoolConfig() {
        return poolConfig;
    }

    @Override
    public CtgClientConfig setPoolConfig(JedisPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
        return this;
    }

    /**
     * Apache pool 配置，默认为JedisPoolConfig
     */
    @NestedConfigurationProperty
    private JedisPoolConfig poolConfig;
}

package com.tydic.dev1.cache.autoconfigure;

import com.tydic.cache.CacheClientFactory;
import com.tydic.cache.Impl.CtgCacheClientFactory;
import com.tydic.cache.Impl.JedisCacheClientFactory;
import com.tydic.cache.Impl.LettuceCacheClientFactory;
import com.tydic.cache.config.CacheClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:  
 *
 * @return 
 * @author yzb
 * @date 2019/2/25 17:10
 */
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheAutoConfiguration {

    @Bean
    @Autowired
    @ConditionalOnProperty(value = "cache.mode",havingValue = "ctg",matchIfMissing = false)
    public CacheClientFactory ctgClientFactory(CacheProperties cacheProperties){
        return new CtgCacheClientFactory(cacheProperties.getConfig());
    }

    @Bean
    @Autowired
    @ConditionalOnProperty(value = "cache.mode",havingValue = "jedis",matchIfMissing = false)
    public CacheClientFactory jedisClientFactory(CacheProperties cacheProperties){
        return new JedisCacheClientFactory(cacheProperties.getConfig());
    }
    @Bean
    @Autowired
    @ConditionalOnProperty(value = "cache.mode",havingValue = "lettuce",matchIfMissing = false)
    public CacheClientFactory LettuceClientFactory(CacheProperties cacheProperties){
        return new LettuceCacheClientFactory(cacheProperties.getConfig());
    }
}
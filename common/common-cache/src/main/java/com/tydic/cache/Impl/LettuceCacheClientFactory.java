package com.tydic.cache.Impl;

import cn.hutool.core.util.StrUtil;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.cluster.RedisClusterClient;
import com.tydic.cache.CacheClientFactory;
import com.tydic.cache.ICacheClient;
import com.tydic.cache.config.CacheClientConfig;
import com.tydic.cache.config.JedisClientConfig;
import com.tydic.cache.config.LettuceClientConfig;
import com.tydic.cache.excption.CacheException;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: framework
 * @description:
 * @author: yzb
 * @create: 2019-06-06 17:29
 **/
public class LettuceCacheClientFactory extends CacheClientFactory {
    private RedisClusterClient redisClusterClient;
    public LettuceCacheClientFactory(CacheClientConfig cacheClientConfig) {
        super(cacheClientConfig);
    }

    @Override
    public void init() {
        LettuceClientConfig lettuceClientConfig = (LettuceClientConfig) cacheClientConfig;
        List<RedisURI> redisURIs = new ArrayList<>();
        String[] split = lettuceClientConfig.getIpPort().split(",");
        for (int i = 0; i < split.length; i++) {
            if (StrUtil.isBlank(lettuceClientConfig.getPassword())) {
                redisURIs.add(RedisURI.create("redis://" + split[i] + "/0"));
            } else {
                redisURIs.add(RedisURI.create("redis://" + lettuceClientConfig.getPassword() + "@" + split[i] + "/0"));
            }
        }
        redisClusterClient = RedisClusterClient.create(redisURIs);
    }

    @Override
    public ICacheClient getClient() {
        if(redisClusterClient==null){
            init();
        }
        return new LettuceRedisClient(redisClusterClient);
    }

    @Override
    public void destroy() {
        this.redisClusterClient.shutdown();
    }
}

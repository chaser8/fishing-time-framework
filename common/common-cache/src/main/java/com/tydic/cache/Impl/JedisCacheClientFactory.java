package com.tydic.cache.Impl;

import cn.hutool.core.util.StrUtil;
import com.lambdaworks.redis.RedisURI;
import com.tydic.cache.CacheClientFactory;
import com.tydic.cache.ICacheClient;
import com.tydic.cache.config.CacheClientConfig;
import com.tydic.cache.config.JedisClientConfig;
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
public class JedisCacheClientFactory extends CacheClientFactory {
    private static final int DEFAULT_TIMEOUT = 30000;
    private static final int DEFAULT_REDIRECTIONS = 20;
    private static JedisPoolConfig DEFAULT_CONFIG = new JedisPoolConfig();
    JedisCluster jedisCluster = null;
    public JedisCacheClientFactory(CacheClientConfig cacheClientConfig) {
        super(cacheClientConfig);
    }

    @Override
    public void init() {
        JedisClientConfig jedisClientConfig = (JedisClientConfig)cacheClientConfig;
        List<RedisURI> redisURIs = new ArrayList<>();
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        String[] iplist = jedisClientConfig.getIpPort().split(",");
        String[] ls = null;
        for (String ipport : iplist) {
            ls = ipport.split(":");
            jedisClusterNodes.add(new HostAndPort(ls[0], Integer.valueOf(ls[1])));
        }
        if (StrUtil.isBlank(jedisClientConfig.getPassword())) {
            jedisCluster = new JedisCluster(jedisClusterNodes, DEFAULT_TIMEOUT, DEFAULT_REDIRECTIONS);
        } else {
            jedisCluster = new JedisCluster(jedisClusterNodes, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT,
                    DEFAULT_REDIRECTIONS, jedisClientConfig.getPassword(), DEFAULT_CONFIG);
        }
    }

    @Override
    public ICacheClient getClient() {
        if(jedisCluster==null){
            init();
        }
        return new JedisRedisClient(jedisCluster);
    }

    @Override
    public void destroy() {
        try {
            this.jedisCluster.close();
        } catch (IOException e) {
            throw new CacheException(e);
        }
    }
}

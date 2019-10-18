package com.tydic.cache.Impl;

import com.ctg.itrdc.cache.pool.CtgJedisPool;
import com.ctg.itrdc.cache.pool.CtgJedisPoolConfig;
import com.ctg.itrdc.cache.pool.CtgJedisPoolException;
import com.ctg.itrdc.cache.pool.ProxyJedis;
import com.tydic.cache.CacheClientFactory;
import com.tydic.cache.ICacheClient;
import com.tydic.cache.config.CacheClientConfig;
import com.tydic.cache.config.CtgClientConfig;
import com.tydic.cache.excption.CacheException;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @program: framework
 * @description:
 * @author: yzb
 * @create: 2019-06-06 17:29
 **/
@Slf4j
public class CtgCacheClientFactory extends CacheClientFactory {
    private static CtgJedisPool ctgJedisPool = null;
    public CtgCacheClientFactory(CacheClientConfig cacheClientConfig) {
        super(cacheClientConfig);
    }

    @Override
    public void init() {
        try {
            CtgClientConfig ctgClientConfig = (CtgClientConfig)cacheClientConfig;

            //做个类型转换
            CtgJedisPoolConfig config = new CtgJedisPoolConfig(new ArrayList<HostAndPort>(){{
                for (com.tydic.cache.config.HostAndPort node : ctgClientConfig.getNodes()) {
                    add(new HostAndPort(node.getHost(),node.getPort()));
                }
            }});
            config.setDatabase(ctgClientConfig.getDatabase())
                    .setPassword(ctgClientConfig.getPassword())
                    .setClientName(ctgClientConfig.getClientName())
                    .setMonitorLog(ctgClientConfig.isMonitorLog())
                    .setPeriod(ctgClientConfig.getPeriod())
                    .setMonitorErrorNum(ctgClientConfig.getMonitorErrorNum())
                    .setConnectionTimeout(ctgClientConfig.getConnectionTimeout())
                    .setSoTimeout(ctgClientConfig.getSoTimeout())
                    .setMonitorTimeout(ctgClientConfig.getMonitorTimeout())
                    .setMonitorSwitch(true);

            Optional.ofNullable(ctgClientConfig.getPoolConfig()).ifPresent(jedisPoolConfig -> {
                config.setPoolConfig(jedisPoolConfig);
            });

            ctgJedisPool = new CtgJedisPool(config);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public ICacheClient getClient() {

            if (ctgJedisPool == null) {
                init();
            }

            ProxyJedis jedis = null;
            try {
                if (ctgJedisPool != null) {
                    jedis = ctgJedisPool.getResource();
                }
            } catch (Exception e) {
                log.error("获取redis失败 : {}", e);
            }
            if (jedis == null) {
                return null;
            }else {
                return new CtgRedisClient(jedis);
            }


    }

    @Override
    public void destroy() {
        ctgJedisPool.close();
    }
}

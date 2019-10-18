package com.tydic.cache;

import org.springframework.lang.Nullable;
import redis.clients.jedis.Transaction;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by guomenghao on 2018/8/30.
 */
public interface IJedisRedisClient extends IRedisClient {
    Transaction multi();

    void discard();

    @Nullable
    Boolean multiSetIfAbsent(Map map);

    public Object bitget(String key);

    public void multiSet(Map map);

    Long del(Collection keys);
}
package com.tydic.cache.Impl;

import cn.hutool.core.util.StrUtil;
import com.tydic.cache.IJedisRedisClient;
import com.tydic.cache.excption.CacheException;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.*;

/**
 * Created by guomenghao on 2018/8/14.
 */
public class JedisRedisClient implements IJedisRedisClient {

    private static JedisCluster jedisCluster;
    public JedisRedisClient(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    @Override
    public Long hdel(String key, String... value) {
        return jedisCluster.hdel(key, value);
    }

    @Override
    public Boolean hexists(String key, String value) {
        return jedisCluster.hexists(key, value);
    }

    @Override
    public String hget(String key, String value) {
        return jedisCluster.hget(key, value);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jedisCluster.hgetAll(key);
    }

    @Override
    public Long hincrBy(String key, String value, long arg2) {
        return jedisCluster.hincrBy(key, value, arg2);
    }

    @Override
    public List<String> hkeys(String key) {
        Set<String> hkeys = jedisCluster.hkeys(key);
        List<String> ret = new ArrayList<>();
        ret.addAll(hkeys);
        return ret;
    }

    @Override
    public Long hlen(String key) {
        return jedisCluster.hlen(key);
    }

    @Override
    public String hmget(String key, String... value) {
        List<String> hmget = jedisCluster.hmget(key, value);
        return StrUtil.join(",",hmget);
    }

    @Override
    public int hmset(String key, Map<String, String> value) {
        jedisCluster.hmset(key, value);
        return 1;
    }

    @Override
    public Long hset(String key, String value, String arg2) {
        jedisCluster.hset(key, value, arg2);
        return 1l;
    }

    @Override
    public Long hsetnx(String key, String value, String arg2) {
        Long hsetnx = jedisCluster.hsetnx(key, value, arg2);
        return hsetnx;

    }

    @Override
    public List<String> hvals(String key) {
        return jedisCluster.hvals(key);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long incrBy(String key, long value) {
        return jedisCluster.incrBy(key, value);
    }

    @Override
    public String lindex(String key, long value) {
        return jedisCluster.lindex(key, value);
    }



    @Override
    public Long llen(String key) {
        return this.jedisCluster.llen(key);
    }

    @Override
    public String lpop(String key) {
        return this.jedisCluster.lpop(key);
    }

    @Override
    public Long lpush(String key, String... value) {
        return this.jedisCluster.lpush(key, value);
    }

    @Override
    public Long lpushx(String key, String value) {
        return this.jedisCluster.lpushx(key, value);
    }

    @Override
    public List<String> lrange(String key, long value, long arg2) {
        return this.jedisCluster.lrange(key, value, arg2);
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return this.jedisCluster.lrem(key, count, value);
    }

    @Override
    public String lset(String key, long index, String value) {
        return this.jedisCluster.lset(key, index, value);
    }

    @Override
    public String ltrim(String key, long start, long stop) {
        return this.jedisCluster.ltrim(key, start, stop);
    }

    @Override
    public Long persist(String key) {
        return this.jedisCluster.persist(key);
    }

    @Override
    public String rpop(String key) {
        return this.jedisCluster.rpop(key);
    }

    @Override
    public Long rpush(String key, String... value) {
        return this.jedisCluster.rpush(key, value);
    }

    @Override
    public Long rpushx(String key, String value) {
        return this.jedisCluster.rpushx(key, value);
    }

    @Override
    public Long sadd(String key, String... value) {
        return this.jedisCluster.sadd(key, value);
    }

    @Override
    public Long scard(String key) {
        return this.jedisCluster.scard(key);
    }

    @Override
    public boolean sismember(String key, String value) {
        return this.jedisCluster.sismember(key, value);
    }

    @Override
    public List<String> smembers(String key) {
        return new ArrayList<>(this.jedisCluster.smembers(key));
    }

    @Override
    public Long srem(String key, String... value) {
        return this.jedisCluster.srem(key, value);
    }

    @Override
    public boolean sreplace(String key, String value, String arg2) {
        return false;
    }

    @Override
    public Long strlen(String key) {
        return this.jedisCluster.strlen(key);
    }

    @Override
    public Long ttl(String key) {
        return this.jedisCluster.ttl(key);
    }

    @Override
    public String type(String key) {
        return this.jedisCluster.type(key);
    }

    @Override
    public Long zadd(String key, double value, String arg2) {
        return this.jedisCluster.zadd(key, value, arg2);
    }

    @Override
    public Long zcard(String key) {
        return this.jedisCluster.zcard(key);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return this.jedisCluster.zcount(key, min, max);
    }

    @Override
    public Double zincrby(String key, double amount, String member) {
        return this.jedisCluster.zincrby(key, amount, member);
    }

    @Override
    public List<String> zrange(String key, long start, long stop) {
        Set<String> zrange = JedisRedisClient.jedisCluster.zrange(key, start, stop);
        List<String> ret = new ArrayList<>();
        ret.addAll(zrange);
        return ret;
    }

    @Override
    public List<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Set<String> zrangeByScore = JedisRedisClient.jedisCluster.zrangeByScore(key, min, max, offset, count);
        List<String> ret = new ArrayList<>();
        ret.addAll(zrangeByScore);
        return ret;
    }

    @Override
    public List<String> zrangeByScore(String key, double min, double max) {
        Set<String> zrangeByScore = JedisRedisClient.jedisCluster.zrangeByScore(key, min, max);
        List<String> ret = new ArrayList<>();
        ret.addAll(zrangeByScore);
        return ret;
    }

    @Override
    public Long zrem(String key, String... value) {
        return this.jedisCluster.zrem(key, value);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long stop) {
        return this.jedisCluster.zremrangeByRank(key, start, stop);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        return this.jedisCluster.zremrangeByScore(key, min, max);
    }

    @Override
    public Double zscore(String key, String value) {
        return this.jedisCluster.zscore(key, value);
    }

    @Override
    public Long append(String key, String value) {
        return this.jedisCluster.append(key, value);
    }

    @Override
    public boolean exists(String key) {
        return this.jedisCluster.exists(key);
    }

    @Override
    public int expire(String key, int seconds) {
        Long expire = jedisCluster.expire(key, seconds);
        if (expire == 1l) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String get(String key) {
        return this.jedisCluster.get(key);
    }

    @Override
    public Object[] getKeys(String... key) {
        List<String> ret = new ArrayList<>();
        for (String keyone : key) {
            ret.add(this.jedisCluster.get(keyone));
        }
        return ret.toArray();
    }

    @Override
    public int set(String key, String value) {
        this.jedisCluster.set(key, value);
        return 1;
    }

    @Override
    public String getSet(String key, String value) {
        return this.jedisCluster.getSet(key, value);
    }

    @Override
    public Long setnx(String key, String value) {
        return this.setnx(key, value);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return this.jedisCluster.setex(key, seconds, value);
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return this.jedisCluster.setrange(key, offset, value);
    }

    @Override
    public void close(){
    }

    @Override
    public String rename(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long renamenx(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long del(String... key) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Transaction multi() {
        return null;
    }


    @Override
    public void discard() {

    }

    @Override
    public Boolean multiSetIfAbsent(Map map) {
        return null;
    }

    @Override
    public Object bitget(String key) {
        return null;
    }

    @Override
    public void multiSet(Map map) {

    }

    @Override
    public Long del(Collection keys) {
        return null;
    }
}

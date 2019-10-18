package com.tydic.cache.Impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.ctg.itrdc.cache.pool.ProxyJedis;
import com.tydic.cache.IJedisRedisClient;
import com.tydic.cache.serializer.JdkSerializationRedisSerializer;
import com.tydic.cache.serializer.RedisSerializer;
import com.tydic.cache.serializer.StringRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Transaction;

import java.util.*;

/**
 * Created by guomenghao on 2018/8/30.
 */
@Slf4j
public class CtgRedisClient<K,V> implements IJedisRedisClient {
    private  static ProxyJedis jedis;
    private @Nullable
    ClassLoader classLoader;
    @SuppressWarnings("rawtypes") private @Nullable RedisSerializer valueSerializer = new StringRedisSerializer();
    @SuppressWarnings("rawtypes") private @Nullable RedisSerializer keySerializer = new StringRedisSerializer();

    public CtgRedisClient(ProxyJedis jedis) {
        this.jedis = jedis;
    }
    @Override
    public void close() {
        jedis.close();
    }
    @Override
    public Long hdel(String key, String... value) {
        return jedis.hdel(key, value);
    }

    @Override
    public Boolean hexists(String key, String value) {
        return jedis.hexists(key, value);
    }

    @Override
    public String hget(String key, String value) {
        return jedis.hget(key, value);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jedis.hgetAll(key);
    }

    @Override
    public Long hincrBy(String key, String value, long arg2) {
        return jedis.hincrBy(key, value, arg2);
    }

    @Override
    public List<String> hkeys(String key) {
        Set<String> hkeys = jedis.hkeys(key);
        List<String> ret = new ArrayList<>();
        ret.addAll(hkeys);
        return ret;
    }

    @Override
    public Long hlen(String key) {
        return jedis.hlen(key);
    }

    @Override
    public String hmget(String key, String... value) {
        List<String> hmget = jedis.hmget(key, value);
        return StrUtil.join(",",hmget);
    }

    @Override
    public int hmset(String key, Map<String, String> value) {
        jedis.hmset(key, value);

        return 1;
    }

    @Override
    public Long hset(String key, String value, String arg2) {
        jedis.hset(key, value, arg2);
        return 1L;
    }

    @Override
    public Long hsetnx(String key, String value, String arg2) {
        Long hsetnx = jedis.hsetnx(key, value, arg2);
        return hsetnx;

    }

    @Override
    public List<String> hvals(String key) {
        List<String> s = jedis.hvals(key);
        List<String> rs =  new ArrayList<String>();
        for (String bs : s) {
            rs.add(bs);
        }

        return rs;
    }

    @Override
    public Long incr(String key) {
        return jedis.incr(key);
    }

    @Override
    public Long incrBy(String key, long value) {
        return jedis.incrBy(key, value);
    }

    @Override
    public String lindex(String key, long value) {
        return new String(jedis.lindex(key, value));
    }



    @Override
    public Long llen(String key) {
        return jedis.llen(key);
    }

    @Override
    public String lpop(String key) {
        return jedis.lpop(key);
    }

    @Override
    public Long lpush(String key, String... value) {
        return jedis.lpush(key, value);
    }

    @Override
    public Long lpushx(String key, String value) {
        return jedis.lpushx(key, value);
    }

    @Override
    public List<String> lrange(String key, long value, long arg2) {
        List<String> s = jedis.lrange(key, value, arg2);
        List<String> rs =  new ArrayList<String>();
        for (String bs : s) {
            rs.add(bs);
        }
        return rs;
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return jedis.lrem(key, count, value);
    }

    @Override
    public String lset(String key, long index, String value) {
        return jedis.lset(key, index, value);
    }

    @Override
    public String ltrim(String key, long start, long stop) {
        return jedis.ltrim(key, start, stop);
    }

    @Override
    public Long persist(String key) {
        return jedis.persist(key);
    }


    @Override
    public String rpop(String key) {
        return new String(jedis.rpop(key));
    }

    @Override
    public Long rpush(String key, String... value) {
        return jedis.rpush(key, value);
    }

    @Override
    public Long rpushx(String key, String value) {
        return jedis.rpushx(key, value);
    }

    @Override
    public Long sadd(String key, String... value) {
        return jedis.sadd(key, value);
    }

    @Override
    public Long scard(String key) {
        return jedis.scard(key);
    }

    @Override
    public boolean sismember(String key, String value) {
        return jedis.sismember(key, value);
    }

    @Override
    public List<String> smembers(String key) {
        Set<String> s = jedis.smembers(key);
        List<String> rs =  new ArrayList<String>();
        for (String bs : s) {
            rs.add(bs);
        }

        return rs;
    }

    @Override
    public Long srem(String key, String... value) {
        return jedis.srem(key, value);
    }

    @Override
    public boolean sreplace(String key, String value, String arg2) {
        return false;
    }

    @Override
    public Long strlen(String key) {
        return jedis.strlen(key);
    }

    @Override
    public Long ttl(String key) {
        return jedis.ttl(key);
    }

    @Override
    public String type(String key) {
        return jedis.type(key);
    }

    @Override
    public Long zadd(String key, double value, String arg2) {
        return jedis.zadd(key, value, arg2);
    }

    @Override
    public Long zcard(String key) {
        return jedis.zcard(key);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return jedis.zcount(key, min, max);
    }

    @Override
    public Double zincrby(String key, double amount, String member) {
        return jedis.zincrby(key, amount, member);
    }

    @Override
    public List<String> zrange(String key, long start, long stop) {
        Set<String> zrange = CtgRedisClient.jedis.zrange(key, start, stop);
        List<String> ret = new ArrayList<>();
        ret.addAll(zrange);
        return ret;
    }

    @Override
    public List<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Set<String> zrangeByScore = CtgRedisClient.jedis.zrangeByScore(key, min, max, offset, count);
        List<String> ret = new ArrayList<>();
        ret.addAll(zrangeByScore);
        return ret;
    }

    @Override
    public List<String> zrangeByScore(String key, double min, double max) {
        Set<String> zrangeByScore = CtgRedisClient.jedis.zrangeByScore(key, min, max);
        List<String> ret = new ArrayList<>();
        ret.addAll(zrangeByScore);
        return ret;
    }

    @Override
    public Long zrem(String key, String... value) {
        return jedis.zrem(key, value);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long stop) {
        return jedis.zremrangeByRank(key, start, stop);
    }

    public Long zremrangeByScore(String key, long min, long max) {
        return jedis.zremrangeByScore(key, min, max);
    }

    @Override
    public Double zscore(String key, String value) {
        return jedis.zscore(key, value);
    }

    @Override
    public Long append(String key, String value) {
        return jedis.append(key, value);
    }


    @Override
    public boolean exists(String key) {
        return jedis.exists(key);
    }

    @Override
    public int expire(String key, int seconds) {
        Long expire = jedis.expire(key, seconds);
        if (expire == 1l) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public Object[] getKeys(String... key) {
        List<String> ret = new ArrayList<>();
        for (String keyone : key) {
            ret.add(jedis.get(keyone));
        }
        return ret.toArray();
    }

    @Override
    public int set(String key, String value) {
        jedis.set(key, value);
        return 1;
    }

    @Override
    public String getSet(String key, String value) {
        return new String(jedis.getSet(key, value));
    }

    @Override
    public Long setnx(String key, String value) {
        return jedis.setnx(key, value);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return jedis.setex(key, seconds, value);
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return jedis.setrange(key, offset, value);
    }



    @Override
    public String rename(String key, String value) {
        return null;
    }

    @Override
    public Long renamenx(String key, String value) {
        return null;
    }

    @Override
    public Long del(String... key) {


        return jedis.del(key);
    }

    @Override
    public Long del(Collection keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return 0L;
        }

        byte[][] rawKeys = rawKeys(keys);
        return jedis.del(rawKeys);
    }

    @Override
    public Long zremrangeByScore(String key, double value, double arg2) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Transaction multi() {
        Transaction t =  jedis.multi();
        return t;
    }


    @Override
    public void discard() {


    }

    @Override
    public Boolean multiSetIfAbsent(Map map) {
        if (map.isEmpty()) {
            return true;
        }

        String[] result = new String[map.size()*2];
        int index = 0;
        for (Object key : map.keySet()) {
            result[index++] = String.valueOf(key);
            result[index++] = String.valueOf(map.get(key));
        }
        if (1==jedis.msetnx(result)) {
            return true;
        }else {
            return false;
        }


    }
    @Override
    public Object bitget(String key){
       return valueSerializer.deserialize(jedis.get(rawKey(key)));
    }

    @Override
    public void multiSet(Map map) {
        if (map.isEmpty()) {
            return ;
        }

        String[] result = new String[map.size()*2];
        int index = 0;
        for (Object key : map.keySet()) {
            result[index++] = String.valueOf(key);
            result[index++] = String.valueOf(map.get(key));
        }
        jedis.mset(result);

    }


    private JdkSerializationRedisSerializer createRedisSerializer(){

        JdkSerializationRedisSerializer defaultSerializer = new JdkSerializationRedisSerializer(
                classLoader != null ? classLoader : this.getClass().getClassLoader());
        return defaultSerializer;
    }

    byte[] rawKey(Object key) {

        Assert.notNull(key, "non null key required");

        if (keySerializer == null && key instanceof byte[]) {
            return (byte[]) key;
        }

        return keySerializer.serialize(key);
    }


    @SuppressWarnings("unchecked")
    byte[] rawValue(Object value) {

        if (valueSerializer == null && value instanceof byte[]) {
            return (byte[]) value;
        }

        return valueSerializer.serialize(value);
    }

    private byte[][] rawKeys(Collection<K> keys) {
        final byte[][] rawKeys = new byte[keys.size()][];

        int i = 0;
        for (K key : keys) {
            rawKeys[i++] = rawKey(key);
        }

        return rawKeys;
    }
}

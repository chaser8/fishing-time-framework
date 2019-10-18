package com.tydic.cache.Impl;

import cn.hutool.core.util.StrUtil;
import com.lambdaworks.redis.cluster.ClusterClientOptions;
import com.lambdaworks.redis.cluster.RedisClusterClient;
import com.lambdaworks.redis.cluster.api.StatefulRedisClusterConnection;
import com.lambdaworks.redis.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import com.lambdaworks.redis.cluster.api.sync.RedisAdvancedClusterCommands;
import com.tydic.cache.ILettuceRedisClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by guomenghao on 2018/8/14.
 */
public class LettuceRedisClient implements ILettuceRedisClient {

    private static RedisClusterClient redisClusterClient;
    private static RedisAdvancedClusterAsyncCommands<String, String> asyncommands;
    private static RedisAdvancedClusterCommands<String, String> syncommands;

    public LettuceRedisClient(RedisClusterClient clusterClient) {
        this.redisClusterClient = clusterClient;
        this.redisClusterClient.setOptions(
                ClusterClientOptions.builder().autoReconnect(true).pingBeforeActivateConnection(true).build());
        StatefulRedisClusterConnection<String, String> connect = LettuceRedisClient.redisClusterClient.connect();
        connect.flushCommands();
        this.asyncommands = connect.async();
        StatefulRedisClusterConnection<String, String> connect2 = LettuceRedisClient.redisClusterClient.connect();
        connect2.flushCommands();
        this.syncommands = connect2.sync();
    }
    @Override
    public Long hdel(String key, String... value) {
        return syncommands.hdel(key, value);
    }

    @Override
    public Boolean hexists(String key, String value) {
        return syncommands.hexists(key, value);
    }

    @Override
    public String hget(String key, String value) {
        return syncommands.hget(key, value);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return syncommands.hgetall(key);
    }

    @Override
    public Long hincrBy(String key, String value, long arg2) {
        return syncommands.hincrby(key, value, arg2);
    }

    @Override
    public List<String> hkeys(String key) {
        return syncommands.hkeys(key);
    }

    @Override
    public Long hlen(String key) {
        return syncommands.hlen(key);
    }

    @Override
    public String hmget(String key, String... value) {
        List<String> hmget = syncommands.hmget(key, value);
        return StrUtil.join(",",hmget);
    }

    @Override
    public int hmset(String key, Map<String, String> value) {
        syncommands.hmset(key, value);
        return 1;
    }

    @Override
    public Long hset(String key, String value, String arg2) {
        syncommands.hset(key, value, arg2);
        return 1l;
    }

    @Override
    public Long hsetnx(String key, String value, String arg2) {
        if (syncommands.hsetnx(key, value, arg2)) {
            return 1l;
        } else {
            return 0l;
        }

    }

    @Override
    public List<String> hvals(String key) {
        return syncommands.hvals(key);
    }

    @Override
    public Long incr(String key) {
        return syncommands.incr(key);
    }

    @Override
    public Long incrBy(String key, long value) {
        return syncommands.incrby(key, value);
    }

    @Override
    public String lindex(String key, long value) {
        return syncommands.lindex(key, value);
    }

    @Override
    public Long linsert(String key, boolean before, String pivot, String value) {

        return syncommands.linsert(key, before, pivot, value);
    }

    @Override
    public Long llen(String key) {
        return this.syncommands.llen(key);
    }

    @Override
    public String lpop(String key) {
        return this.syncommands.lpop(key);
    }

    @Override
    public Long lpush(String key, String... value) {
        return this.syncommands.lpush(key, value);
    }

    @Override
    public Long lpushx(String key, String value) {
        return this.syncommands.lpushx(key, value);
    }

    @Override
    public List<String> lrange(String key, long value, long arg2) {
        return this.syncommands.lrange(key, value, arg2);
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return this.syncommands.lrem(key, count, value);
    }

    @Override
    public String lset(String key, long index, String value) {
        return this.syncommands.lset(key, index, value);
    }

    @Override
    public String ltrim(String key, long start, long stop) {
        return this.syncommands.ltrim(key, start, stop);
    }

    @Override
    public Long persist(String key) {
        if (this.syncommands.persist(key)) {
            return 1l;
        } else {
            return 0l;
        }
    }

    @Override
    public String rename(String key, String value) {
        return this.syncommands.rename(key, value);
    }

    @Override
    public Long renamenx(String key, String value) {
        if (this.syncommands.renamenx(key, value)) {
            return 1l;
        } else {
            return 0l;
        }
    }

    @Override
    public String rpop(String key) {
        return this.syncommands.rpop(key);
    }

    @Override
    public Long rpush(String key, String... value) {
        return this.syncommands.rpush(key, value);
    }

    @Override
    public Long rpushx(String key, String value) {
        return this.syncommands.rpushx(key, value);
    }

    @Override
    public Long sadd(String key, String... value) {
        return this.syncommands.sadd(key, value);
    }

    @Override
    public Long scard(String key) {
        return this.syncommands.scard(key);
    }

    @Override
    public boolean sismember(String key, String value) {
        return this.syncommands.sismember(key, value);
    }

    @Override
    public List<String> smembers(String key) {
        return new ArrayList<>(this.syncommands.smembers(key));
    }

    @Override
    public Long srem(String key, String... value) {
        return this.syncommands.srem(key, value);
    }

    @Override
    public boolean sreplace(String key, String value, String arg2) {
        return false;
    }

    @Override
    public Long strlen(String key) {
        return this.syncommands.strlen(key);
    }

    @Override
    public Long ttl(String key) {
        return this.syncommands.ttl(key);
    }

    @Override
    public String type(String key) {
        return this.syncommands.type(key);
    }

    @Override
    public Long zadd(String key, double value, String arg2) {
        return this.syncommands.zadd(key, value, arg2);
    }

    @Override
    public Long zcard(String key) {
        return this.syncommands.zcard(key);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return this.syncommands.zcount(key, min, max);
    }

    @Override
    public Double zincrby(String key, double amount, String member) {
        return this.syncommands.zincrby(key, amount, member);
    }

    @Override
    public List<String> zrange(String key, long start, long stop) {
        return this.syncommands.zrange(key, start, stop);
    }

    @Override
    public List<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return this.syncommands.zrangebyscore(key, min, max, offset, count);
    }

    @Override
    public List<String> zrangeByScore(String key, double min, double max) {
        return this.syncommands.zrangebyscore(key, min, max);
    }

    @Override
    public Long zrem(String key, String... value) {
        return this.syncommands.zrem(key, value);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long stop) {
        return this.syncommands.zremrangebyrank(key, start, stop);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        return this.syncommands.zremrangebyscore(key, min, max);
    }

    @Override
    public Double zscore(String key, String value) {
        return this.syncommands.zscore(key, value);
    }

    @Override
    public Long append(String key, String value) {
        return this.syncommands.append(key, value);
    }

    @Override
    public Long del(String... key) {
        return this.syncommands.del(key);
    }

    @Override
    public boolean exists(String key) {
        return this.syncommands.exists(key);
    }

    @Override
    public int expire(String key, int seconds) {
        if (this.syncommands.expire(key, seconds)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String get(String key) {
        return this.syncommands.get(key);
    }

    @Override
    public Object[] getKeys(String... key) {
        List<String> ret = new ArrayList<>();
        for (String keyone : key) {
            ret.add(this.syncommands.get(keyone));
        }
        return ret.toArray();
    }

    @Override
    public int set(String key, String value) {
        this.syncommands.set(key, value);
        return 1;
    }

    @Override
    public String getSet(String key, String value) {
        return this.syncommands.getset(key, value);
    }

    @Override
    public Long setnx(String key, String value) {
        return this.setnx(key, value);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return this.syncommands.setex(key, seconds, value);
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return this.syncommands.setrange(key, offset, value);
    }

    @Override
    public void close() {
         this.syncommands.close();
         this.asyncommands.close();
    }

    @Override
    public RedisAdvancedClusterAsyncCommands<String, String> getAsynCommonds() {
        return this.asyncommands;
    }

    @Override
    public RedisAdvancedClusterCommands<String, String> getSynCommonds() {
        return this.syncommands;
    }

}

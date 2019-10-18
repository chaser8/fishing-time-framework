package com.tydic.cache.Impl;

import com.tydic.cache.IRedisClient;

import java.util.List;
import java.util.Map;

/**
 * Created by guomenghao on 2018/8/14.
 */
public class RedisClient implements IRedisClient {

    @Override
    public Long append(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long del(String... key) {
        // TODO Auto-generated method stub
        return 0l;
    }

    @Override
    public boolean exists(String key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int expire(String key, int value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String get(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] getKeys(String... key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int set(String key, String value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getSet(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long setnx(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String setex(String key, int value, String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long setrange(String key, long value, String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public Long hdel(String key, String... value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean hexists(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String hget(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long hincrBy(String key, String value, long arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> hkeys(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long hlen(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String hmget(String key, String... value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int hmset(String key, Map<String, String> value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Long hset(String key, String value, String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long hsetnx(String key, String value, String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> hvals(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long incr(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long incrBy(String key, long value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String lindex(String key, long value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long llen(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String lpop(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long lpush(String key, String... value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long lpushx(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> lrange(String key, long value, long arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long lrem(String key, long value, String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String lset(String key, long value, String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String ltrim(String key, long value, long arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long persist(String key) {
        // TODO Auto-generated method stub
        return null;
    }

	/*@Override
	public String query(String key, long value, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryNonBusiness(String key, long value, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}*/

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
    public String rpop(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long rpush(String key, String... value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long rpushx(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long sadd(String key, String... value) {
        // TODO Auto-generated method stub
        return 0l;
    }

    @Override
    public Long scard(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean sismember(String key, String value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<String> smembers(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long srem(String key, String... value) {
        return 0l;
    }

    @Override
    public boolean sreplace(String key, String value, String arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Long strlen(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long ttl(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String type(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long zadd(String key, double value, String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long zcard(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long zcount(String key, double value, double arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double zincrby(String key, double value, String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> zrange(String key, long value, long arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> zrangeByScore(String key, double arg0, double arg2, int arg3, int arg4) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> zrangeByScore(String key, double value, double arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long zrem(String key, String... value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long zremrangeByRank(String key, long arg0, long arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long zremrangeByScore(String key, double value, double arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double zscore(String key, String value) {
        // TODO Auto-generated method stub
        return null;
    }

}

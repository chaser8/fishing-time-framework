package com.tydic.cache;

import java.util.List;
import java.util.Map;

/**
 * Created by guomenghao on 2018/8/30.
 */
public interface IRedisClient extends ICacheClient{


    public Long hdel(String key, String... value);

    public Boolean hexists(String key, String value);

    public String hget(String key, String value);

    public Map<String, String> hgetAll(String key);

    public Long hincrBy(String key, String value, long arg2);

    public List<String> hkeys(String key);

    public Long hlen(String key);

    public String hmget(String key, String... value);

    public int hmset(String key, Map<String, String> value);

    public Long hset(String key, String value, String arg2);

    public Long hsetnx(String key, String value, String arg2);

    public List<String> hvals(String key);

    public Long incr(String key);

    public Long incrBy(String key, long value);

    public String lindex(String key, long value);

    public Long llen(String key);

    public String lpop(String key);

    public Long lpush(String key, String... value);

    public Long lpushx(String key, String value);

    public List<String> lrange(String key, long value, long arg2);

    public Long lrem(String key, long value, String arg2);

    public String lset(String key, long value, String arg2);

    public String ltrim(String key, long value, long arg2);

    public Long persist(String key);

	/*public String query(String key, long value, long arg2);

	public String queryNonBusiness(String key, long value, long arg2);*/

    public String rename(String key, String value);

    public Long renamenx(String key, String value);

    public String rpop(String key);

    public Long rpush(String key, String... value);

    public Long rpushx(String key, String value);

    public Long sadd(String key, String... value);

    public Long scard(String key);

    public boolean sismember(String key, String value);

    public List<String> smembers(String key);

    public Long srem(String key, String... value);

    public boolean sreplace(String key, String value, String arg2);

    public Long strlen(String key);

    public Long ttl(String key);

    public String type(String key);

    public Long zadd(String key, double value, String arg2);

    public Long zcard(String key);

    public Long zcount(String key, double value, double arg2);

    public Double zincrby(String key, double value, String arg2);

    public List<String> zrange(String key, long value, long arg2);

    public List<String> zrangeByScore(String key, double arg0, double arg2, int arg3, int arg4);

    public List<String> zrangeByScore(String key, double value, double arg2);

    public Long zrem(String key, String... value);

    public Long zremrangeByRank(String key, long arg0, long arg2);

    public Long zremrangeByScore(String key, double value, double arg2);

    public Double zscore(String key, String value);
    
    public void close();
}

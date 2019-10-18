package com.tydic.cache;

import java.util.Collection;

/**
 * Created by guomenghao on 2018/8/30.
 */
public interface ICacheClient extends AutoCloseable {
    public Long append(String key, String value);

    public Long del(String... key);


    public boolean exists(String key);

    public int expire(String key, int value);

    public String get(String key);

    public Object[] getKeys(String... key);

    public int set(String key, String value);

    public String getSet(String key, String value);

    public Long setnx(String key, String value);

    public String setex(String key, int value, String arg2);

    public Long setrange(String key, long value, String arg2);
}

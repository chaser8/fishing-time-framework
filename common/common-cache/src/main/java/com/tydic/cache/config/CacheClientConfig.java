package com.tydic.cache.config;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by guomenghao on 2018/8/30.
 */
@Setter
@Getter
public class CacheClientConfig {
    /**
     * 缓存模式
     */
    private CacheMode mode;

    public enum CacheMode {
        LETTUCE,
        JEDIS,
        CTG
    }

}

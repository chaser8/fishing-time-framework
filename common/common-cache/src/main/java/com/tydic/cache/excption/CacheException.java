package com.tydic.cache.excption;

/**
 * Created by guomenghao on 2018/8/30.
 */
public class CacheException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CacheException() {
        super();
    }

    public CacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }
}

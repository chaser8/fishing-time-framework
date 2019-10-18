package com.tydic.cache.serializer;

import org.springframework.core.NestedRuntimeException;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-24.
 */
public class SerializationException extends NestedRuntimeException {

    /**
     * Constructs a new {@link SerializationException} instance.
     *
     * @param msg
     */
    public SerializationException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new {@link SerializationException} instance.
     *
     * @param msg the detail message.
     * @param cause the nested exception.
     */
    public SerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
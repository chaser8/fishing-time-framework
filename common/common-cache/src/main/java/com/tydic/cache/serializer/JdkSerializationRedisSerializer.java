package com.tydic.cache.serializer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-24.
 */
public class JdkSerializationRedisSerializer implements RedisSerializer<Object> {
    private final Converter<Object, byte[]> serializer;
    private final Converter<byte[], Object> deserializer;

    /**
     * Creates a new {@link JdkSerializationRedisSerializer} using the default class loader.
     */
    public JdkSerializationRedisSerializer() {
        this(new SerializingConverter(), new DeserializingConverter());
    }

    /**
     * Creates a new {@link JdkSerializationRedisSerializer} using a {@link ClassLoader}.
     *
     * @param classLoader
     * @since 1.7
     */
    public JdkSerializationRedisSerializer(ClassLoader classLoader) {
        this(new SerializingConverter(), new DeserializingConverter(classLoader));
    }

    /**
     * Creates a new {@link JdkSerializationRedisSerializer} using a {@link Converter converters} to serialize and
     * deserialize objects.
     *
     * @param serializer must not be {@literal null}
     * @param deserializer must not be {@literal null}
     * @since 1.7
     */
    public JdkSerializationRedisSerializer(Converter<Object, byte[]> serializer, Converter<byte[], Object> deserializer) {

        Assert.notNull(serializer, "Serializer must not be null!");
        Assert.notNull(deserializer, "Deserializer must not be null!");

        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    @Override
    public Object deserialize(@Nullable byte[] bytes) {

        if (SerializationUtils.isEmpty(bytes)) {
            return null;
        }

        try {
            return deserializer.convert(bytes);
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize", ex);
        }
    }

    @Override
    public byte[] serialize(@Nullable Object object) {
        if (object == null) {
            return SerializationUtils.EMPTY_ARRAY;
        }
        try {
            return serializer.convert(object);
        } catch (Exception ex) {
            throw new SerializationException("Cannot serialize", ex);
        }
    }
}

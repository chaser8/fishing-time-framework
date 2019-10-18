package com.tydic.cache.serializer;

import org.springframework.core.CollectionFactory;
import org.springframework.lang.Nullable;

import java.util.*;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-24.
 */
public class SerializationUtils {
    static final byte[] EMPTY_ARRAY = new byte[0];

    static boolean isEmpty(@Nullable byte[] data) {
        return (data == null || data.length == 0);
    }

    @SuppressWarnings("unchecked")
    static <T extends Collection<?>> T deserializeValues(@Nullable Collection<byte[]> rawValues, Class<T> type,
                                                         @Nullable RedisSerializer<?> redisSerializer) {
        // connection in pipeline/multi mode
        if (rawValues == null) {
            return (T) CollectionFactory.createCollection(type, 0);
        }

        Collection<Object> values = (List.class.isAssignableFrom(type) ? new ArrayList<>(rawValues.size())
                : new LinkedHashSet<>(rawValues.size()));
        for (byte[] bs : rawValues) {
            values.add(redisSerializer.deserialize(bs));
        }

        return (T) values;
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> deserialize(@Nullable Set<byte[]> rawValues, @Nullable RedisSerializer<T> redisSerializer) {
        return deserializeValues(rawValues, Set.class, redisSerializer);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> deserialize(@Nullable List<byte[]> rawValues,
                                          @Nullable RedisSerializer<T> redisSerializer) {
        return deserializeValues(rawValues, List.class, redisSerializer);
    }

    @SuppressWarnings("unchecked")
    public static <T> Collection<T> deserialize(@Nullable Collection<byte[]> rawValues,
                                                RedisSerializer<T> redisSerializer) {
        return deserializeValues(rawValues, List.class, redisSerializer);
    }

    public static <T> Map<T, T> deserialize(@Nullable Map<byte[], byte[]> rawValues, RedisSerializer<T> redisSerializer) {

        if (rawValues == null) {
            return Collections.emptyMap();
        }
        Map<T, T> ret = new LinkedHashMap<>(rawValues.size());
        for (Map.Entry<byte[], byte[]> entry : rawValues.entrySet()) {
            ret.put(redisSerializer.deserialize(entry.getKey()), redisSerializer.deserialize(entry.getValue()));
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public static <HK, HV> Map<HK, HV> deserialize(@Nullable Map<byte[], byte[]> rawValues,
                                                   @Nullable RedisSerializer<HK> hashKeySerializer, @Nullable RedisSerializer<HV> hashValueSerializer) {

        if (rawValues == null) {
            return Collections.emptyMap();
        }
        Map<HK, HV> map = new LinkedHashMap<>(rawValues.size());
        for (Map.Entry<byte[], byte[]> entry : rawValues.entrySet()) {
            // May want to deserialize only key or value
            HK key = hashKeySerializer != null ? hashKeySerializer.deserialize(entry.getKey()) : (HK) entry.getKey();
            HV value = hashValueSerializer != null ? hashValueSerializer.deserialize(entry.getValue())
                    : (HV) entry.getValue();
            map.put(key, value);
        }
        return map;
    }
}

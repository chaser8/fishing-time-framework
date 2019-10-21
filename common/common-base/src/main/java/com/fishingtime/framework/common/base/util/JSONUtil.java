package com.fishingtime.framework.common.base.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JSONUtil {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectMapper UNKNOWN_PROPERTIES_MAPPER = new ObjectMapper();

    static {
        UNKNOWN_PROPERTIES_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJSONString(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static String toFormatString(Object value) {
        return toFormatString(value, false);
    }

    public static String toFormatString(Object value, boolean failOnUnknowProperties) {
        if (value == null) {
            return null;
        }
        try {
            if (failOnUnknowProperties) {
                return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
            } else {
                return UNKNOWN_PROPERTIES_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> T toBean(InputStream inputStream, Class<T> clazz) {
        return toBean(inputStream, clazz, false);
    }
    public static <T> T toBean(InputStream inputStream, Class<T> clazz,boolean failOnUnknowProperties) {
        if (inputStream == null) {
            return null;
        }
        try {
            if (failOnUnknowProperties) {
                return MAPPER.readValue(inputStream,clazz);
            } else {
                return UNKNOWN_PROPERTIES_MAPPER.readValue(inputStream,clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toBean(Object value, Class<T> clazz) {
        if (value == null) {
            return null;
        }
        return toBean(value, clazz, false);
    }

    public static boolean isJson(String str) {
        return cn.hutool.json.JSONUtil.isJson(str);
    }

    public static boolean isJsonObj(String str) {
        return cn.hutool.json.JSONUtil.isJsonObj(str);
    }

    public static boolean isJsonArray(String str) {
        return cn.hutool.json.JSONUtil.isJsonArray(str);
    }

    public static <T> T toBean(Object value, Class<T> clazz, boolean failOnUnknowProperties) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return parseObject((String) value, clazz, failOnUnknowProperties);
        } else {
            if (failOnUnknowProperties) {
                return MAPPER.convertValue(value, clazz);
            } else {
                return UNKNOWN_PROPERTIES_MAPPER.convertValue(value, clazz);
            }
        }
    }

    /**
     * 将json字符串转换成bean
     *
     * @param value
     * @param clazz
     * @param failOnUnknowProperties true:如果有未知属性，转换会失败
     * @param <T>
     * @return
     * @Author wangjiajun
     */
    public static <T> T parseObject(String value, Class<T> clazz, boolean failOnUnknowProperties) {
        if (StrUtil.isBlank(value)) {
            return null;
        }
        try {
            if (failOnUnknowProperties) {
                return MAPPER.readValue(value, clazz);
            } else {
                return UNKNOWN_PROPERTIES_MAPPER.readValue(value, clazz);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String value, Class<T> clazz) {
        return parseObject(value, clazz, true);
    }

    public static void main(String[] args) {
    }

}

package com.fishingtime.framework.common.base.util;

import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;

/**
 * @program: framework
 * @description:
 * @author:
 * @create: 2019-08-27 11:07
 **/
public class ClassUtil extends cn.hutool.core.util.ClassUtil {
    /**
     * 查找指定类中的所有字段（包括非public字段）包含父类， 字段不存在则返回<code>null</code>
     *
     * @param clazz 被查找字段的类
     * @param fieldName 字段名
     * @return 字段
     * @throws SecurityException 安全异常
     */
    public static Field getDeclaredFieldAndSuper(Class<?> clazz, String fieldName) throws SecurityException {
        if (null == clazz || StrUtil.isBlank(fieldName)) {
            return null;
        }
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            // e.printStackTrace();
            if(!clazz.getSuperclass().equals(Object.class)){
                return getDeclaredFieldAndSuper(clazz.getSuperclass(),fieldName);
            }
        }
        return null;
    }
}

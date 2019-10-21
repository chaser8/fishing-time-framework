package com.fishingtime.framework.common.base.util;

import cn.hutool.core.util.ReflectUtil;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-04-03 09:34
 **/
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {
    // --------------------------------------------------------------------------------------------- fillBean
    /**
     * 对象或Map转Bean
     *
     * @param source Bean对象或Map
     * @param clazz 目标的Bean类型
     * @return Bean对象
     * @since 4.1.20
     */
    public static <T> T toBean(Object source, Class<T> clazz) {
        if(source==null){
            return null;
        }
        final T target = ReflectUtil.newInstance(clazz);
        copyProperties(source, target);
        return target;
    }
}

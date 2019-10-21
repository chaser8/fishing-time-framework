package com.baomidou.mybatisplus.core.metadata;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.fishingtime.framework.common.base.util.ClassUtil;
import org.apache.ibatis.builder.MapperBuilderAssistant;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 改造必须加了 TableField 注解的才解析成 sql
 * @program:
 * @description:
 * @author:
 * @create: 2019-06-28 10:43
 **/
public class FieldFilterSqlInjector extends DefaultSqlInjector {
    @Override
    public void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {
        String className = mapperClass.toString();
        Set<String> mapperRegistryCache = GlobalConfigUtils.getMapperRegistryCache(builderAssistant.getConfiguration());
        if (!mapperRegistryCache.contains(className)) {
            List<AbstractMethod> methodList = this.getMethodList(mapperClass);
            Assert.notEmpty(methodList, "No effective injection method was found.");
            Class<?> modelClass = extractModelClass(mapperClass);

            if (modelClass != null) {
                TableInfo tableInfo = TableInfoHelper.initTableInfo(builderAssistant, modelClass);
                List<TableFieldInfo> fieldList = tableInfo.getFieldList();
                if(fieldList.size()>0){
                    fieldList = fieldList.stream().filter(tableFieldInfo -> {
                        /* 过滤注解非表字段属性 */
                        Field declaredField = ClassUtil.getDeclaredFieldAndSuper(tableInfo.getEntityType(), tableFieldInfo.getProperty());
                        TableField tableField = declaredField.getAnnotation(TableField.class);
                        return tableField != null;
                    }).collect(Collectors.toList());
                }

                tableInfo.setFieldList(fieldList);
                // 循环注入自定义方法
//                for (AbstractMethod abstractMethod : methodList) {
//                    abstractMethod.inject(builderAssistant, mapperClass, modelClass, tableInfo);
//                }
                methodList.forEach(m -> m.inject(builderAssistant, mapperClass, modelClass, tableInfo));
            }
            mapperRegistryCache.add(className);
        }
    }
    public Class<?> getCustomModel(Set<Class<?>> classes){
        Set<Class<?>> subClasses = new HashSet<>();
        if(null != classes){
            for (Class<?> aClass : classes) {
                if(aClass.getName().endsWith("Custom")){
                    subClasses.add(aClass);
                }
            }
        }
        if(subClasses.size()>1){
            throw new RuntimeException("多个子类");
        }else if(subClasses.size()==1){
            return subClasses.iterator().next();
        }
        return null;
    }
}

package com.yangzb.framework.hbase.api;


import org.apache.hadoop.hbase.client.Result;

import java.util.Map;

/**
 * @program: dic-framework-v2
 * @description:
 * @author: yzb
 * @create: 2019-02-27 15:52
 **/
public abstract class Call<T> {
    public abstract void next(T obj);
    void typeConvertErrorHandler(Result result, Map resultMap, Exception e){

    }
}
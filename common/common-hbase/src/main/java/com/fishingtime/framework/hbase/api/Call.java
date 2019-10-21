package com.fishingtime.framework.hbase.api;


import org.apache.hadoop.hbase.client.Result;

import java.util.Map;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-02-27 15:52
 **/
public abstract class Call<T> {
    public abstract void next(T obj);
    void typeConvertErrorHandler(Result result, Map resultMap, Exception e){

    }
}
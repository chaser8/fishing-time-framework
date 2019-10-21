package com.fishingtime.framework.hbase.api;

import lombok.Getter;
import lombok.Setter;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-02-27 16:40
 **/
@Setter
@Getter
public abstract class HBaseOperationsParams {
    public HBaseOperationsParams(String tableName){
        this.tableName = tableName;
    }
    private String tableName;
}

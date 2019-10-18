package com.yangzb.framework.hbase.api;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: dic-framework-v2
 * @description:
 * @author: yzb
 * @create: 2019-02-27 16:55
 **/
@Setter
@Getter
public abstract class HBaseQuery extends HBaseOperationsParams {
    private Call call;
    /**
     * 只有当返回为 map 时生效
     */
    private boolean keyContainsVersion=false;
    /**
     * keyContainsFamily 只有当返回为 map 时生效
     */
    private boolean keyContainsFamily=false;

    public HBaseQuery(String tableName) {
        super(tableName);
    }
}
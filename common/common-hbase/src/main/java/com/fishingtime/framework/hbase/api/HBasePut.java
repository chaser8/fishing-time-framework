package com.fishingtime.framework.hbase.api;

import cn.hutool.core.collection.CollUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-02-27 16:57
 **/
@Setter
@Getter
public class HBasePut<T> extends HBaseOperationsParams{
    private String family;
    private List<T> datas;
    public HBasePut(String tableName){
        super(tableName);
    }
    public HBasePut(String tableName,String family){
        super(tableName);
        this.setFamily(family);
    }

    public void setData(T data) {
        if (null==datas){
            datas=CollUtil.newArrayList();
        }
        datas.add(data);
    }
    public void setDatas(T ... datas) {
        if (null==this.datas){
            this.datas=CollUtil.newArrayList();
        }
        CollUtil.addAll(this.datas,datas);
    }
    public void setDatas(List<T> datas) {
        this.datas=datas;
    }
}

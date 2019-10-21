package com.fishingtime.framework.common.base.entity;

/**
 * 动作编码
 *
 * @author wang.zhang
 */
public enum Operation {
    ADD("add", "添加"),
    REMOVE("remove", "删除"),
    REPLACE("replace", "更新");

    public String code;
    public String desc;

    Operation(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
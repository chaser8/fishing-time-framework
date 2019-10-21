package com.fishingtime.framework.common.base.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-04-01 09:30
 **/
@Getter
@AllArgsConstructor
public enum StatusCd {

    CD_1000("1000", "有效","正常有效数据"),
    CD_1100("1100", "无效","逻辑删除"),
    CD_1200("1200", "未生效","草稿状态");

    private String code;
    private String name;
    private String desc;
}
package com.fishingtime.framework.hbase.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-02-28 14:58
 **/
@Getter
@Setter
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private int age;
}

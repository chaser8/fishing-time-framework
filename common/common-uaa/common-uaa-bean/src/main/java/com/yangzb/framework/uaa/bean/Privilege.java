package com.yangzb.framework.uaa.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限 可以包含菜单，url，按钮
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-04-25 16:54
 **/
@Getter
@Setter
@JsonIgnoreProperties(value = { "effDate", "expDate" })
public class Privilege implements Serializable {
    private static final long serialVersionUID = 2179037393108205286L;

    private Long privId;
    private String privCode;
    private String privName;
    private String privType;
    private String privDesc;
    private String privManageClass;
    private Date effDate;
    private Date expDate;
    private Long systemInfoId;
    private Long regionId;

    private String urlPattern;

    enum Type{
        /**
         * 菜单
         */
        MENU,
        /**
         * 功能点
         */
        FUNCTION,
        /**
         * 接口
         */
        INTERFACE;
    }
}
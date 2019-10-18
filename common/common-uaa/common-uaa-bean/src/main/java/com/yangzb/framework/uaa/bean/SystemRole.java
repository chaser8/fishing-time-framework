package com.yangzb.framework.uaa.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/***
 * 
 * @program:
 * @description: 
 * @author: yzb
 * @date 2019/5/27 18:05
 **/
@Data
public class SystemRole implements Serializable {
    private static final long serialVersionUID = 2179037393108205286L;

    private Long sysRoleId;
    private String sysRoleName;
    private String sysRoleCode;
    /**
     * 应用系统自行定义
     */
    private String sysRoleType;
    private String sysRoleDesc;
    private Integer initFlag;
    private Long regionId;
    private Long systemInfoId;
    private String statusCd;
}

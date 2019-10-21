package com.fishingtime.framework.uaa.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-16 09:36
 **/
public class Staff implements Serializable {
    private static final long serialVersionUID = 3881610071550902762L;
    /**
     * 员工标识
     */
    @Getter
    @Setter
    private String staffId;
    /**
     * 参与人标识,参与人唯一标识
     */
    @Getter
    @Setter
    private String partyId;
    /**
     * 员工编码
     */
    @Getter
    @Setter
    private String staffCode;
    /**
     * 员工账号
     */
    private String staffAccount;
    /**
     * 组织标识外键,员工的归属管理组织
     */
    private String orgId;
    /**
     * 员工类型，LOVB=STF-C-0005
     */
    @Getter
    @Setter
    private String staffType;
    /**
     * 员工名称
     */
    @Getter
    @Setter
    private String staffName;
    /**
     * 员工描述
     */
    @Getter
    @Setter
    private String staffDesc;
    @Getter
    @Setter
    private String statusCd;
    @Getter
    @Setter
    private String statusDate;
    @Getter
    @Setter
    private String createDate;
    @Getter
    @Setter
    private String createStaff;
    @Getter
    @Setter
    private String updateDate;
    @Getter
    @Setter
    private String updateStaff;
    /**
     * 销售员编码，LOVB=STF-0004。
     */
    @Getter
    @Setter
    private String salesstaffCode;
    /**
     * 公用管理区域标识,记录区域唯一标识
     */
    private String commonRegionId;



}

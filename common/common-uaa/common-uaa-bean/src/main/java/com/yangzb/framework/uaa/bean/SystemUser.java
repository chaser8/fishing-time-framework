package com.yangzb.framework.uaa.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @program:
 * @description: 用户权限
 * @author: yzb
 * @date 2019/4/25 17:56
 **/
@Getter
@Setter
@JsonIgnoreProperties(value = { "effDate", "expDate" })
public class SystemUser implements Serializable {

    /**
     * 1000	有效
     */
    public static final String STATUS_CD_VALID = "1000";
    /**
     * 1100	无效
     */
    public static final String STATUS_CD_INVALID = "1100";
    /**
     * 1200	冻结
     */
    public static final String STATUS_CD_FREEZE = "1200";
    private static final long serialVersionUID = 3881610071550902762L;
    private Long sysUserId;
    private Long staffId;
    private String sysUserCode;
    private String password;
    private String pwdErrCnt;
    private String pwdSmsTel;
    private String pwdStatus;
    private String pwdNewtime;
    private Integer pwdEffectDays;

    /**
     * 这里我理解为当前登录用户岗位所属regionId,用户可有多个岗位，存在岗位切换
     */
    private Long regionId;
    private String regionName;
    private int regionLevel;

    private Long provinceRegionId;
    private String provinceRegionName;

    private Long cityRegionId;
    private String cityRegionName;



    /**
     * 这里我理解为当前登录用户岗位所属机构标识,用户可有多个岗位，存在岗位切换
     */
    private Long currentOrgId;

    private Long systemInfoId;
    private Integer limitCount;
    private Integer loginedNum;
    private String sysUserDesc;
    private Date effDate;
    private Date expDate;
    private Staff staff;

    /**
     * 当前机构信息，正常来说需要和岗位关联
     */
    private Organization currentOrganization;

    /**
     * 1000	有效 valid
     * 1100	无效 invalid
     * 1200	冻结 freeze
     */
    private String statusCd;

    /**
     * 角色，暂时不管角色
     */
    private List<SystemRole> systemRoles;

    /**
     * 权限
     */
    private List<Privilege> privileges;
}

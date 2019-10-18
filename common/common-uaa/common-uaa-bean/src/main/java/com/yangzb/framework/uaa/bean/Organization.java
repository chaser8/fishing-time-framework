package com.yangzb.framework.uaa.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 描述所有参与电信运营活动的组织，包括内部组织、经营主体、销售点、店中商等。
 * </p>
 *
 * @author yzb
 * @since 2019-04-15
 */
@Data
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orgId;

    private Long partyId;

    private String orgCode;

    private String orgName;

    private Long regionId;

    private String orgType;

    private String orgSubtype;

    private String villageFlag;

    private Long parentOrgId;

    private Long orgLevel;

    private Long orgIndex;

    private String salesorgCode;

    private Long divorgFlag;

    private String orgDesc;

    private String remark;
}

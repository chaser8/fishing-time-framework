package com.yangzb.framework.common.service.entity;

import com.yangzb.framework.common.base.entity.EntityBase;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.yangzb.framework.common.base.validation.ValidGoup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.*;

/**
 * <p>
 * 描述所有参与电信运营活动的组织，包括内部组织、经营主体、销售点、店中商等。
 * </p>
 *
 * @author yzb
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Organization", description="描述所有参与电信运营活动的组织，包括内部组织、经营主体、销售点、店中商等。")
public class Organization extends EntityBase<Organization> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电信组织标识主键")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableId(value = "ORG_ID", type = IdType.AUTO)
    private Long orgId;

    @ApiModelProperty(value = "参与人标识,参与人唯一标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("PARTY_ID")
    private Long partyId;

    @ApiModelProperty(value = "电信组织编码")
    @Length(max=30,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "电信组织名称")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ORG_NAME")
    private String orgName;

    @ApiModelProperty(value = "公用管理区域标识,记录区域唯一标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REGION_ID")
    private Long regionId;

    @ApiModelProperty(value = "记录电信组织类型,LOVB=STF-C-0002。")
    @Length(max=4,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ORG_TYPE")
    private String orgType;

    @ApiModelProperty(value = "记录电信组织小类,LOVB=STF-C-0001。")
    @Length(max=10,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ORG_SUBTYPE")
    private String orgSubtype;

    @ApiModelProperty(value = "记录城乡标志,LOVB=PUB-C-0004。")
    @Length(max=4,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("VILLAGE_FLAG")
    private String villageFlag;

    @ApiModelProperty(value = "上级组织标识,直接记录组织的直接管理上级标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("PARENT_ORG_ID")
    private Long parentOrgId;

    @ApiModelProperty(value = "电信组织级别,从0开始,0级为最高级")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ORG_LEVEL")
    private Long orgLevel;

    @ApiModelProperty(value = "电信组织排序号，同一级别组织的排序号，主要用于组织机构树展示先后排序")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ORG_INDEX")
    private Long orgIndex;

    @ApiModelProperty(value = "销售点编码")
    @Length(max=30,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("SALESORG_CODE")
    private String salesorgCode;

    @ApiModelProperty(value = "是否划小机构")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("DIVORG_FLAG")
    private Long divorgFlag;

    @ApiModelProperty(value = "电信组织描述")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ORG_DESC")
    private String orgDesc;

    @ApiModelProperty(value = "记录备注信息")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REMARK")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.orgId;
    }

}

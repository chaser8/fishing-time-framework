package com.yangzb.framework.common.service.entity;

import com.yangzb.framework.common.base.entity.EntityBase;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.*;
import com.yangzb.framework.common.base.validation.ValidGoup;

/**
 * <p>
 * 指对于各种专业电信管理区域的共性管理区域信息的抽象表达，包括省公司、本地网、营业区。因为使用目的不同，可以定义不同使用类
 * </p>
 *
 * @author yzb
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="CommonRegion", description="指对于各种专业电信管理区域的共性管理区域信息的抽象表达，包括省公司、本地网、营业区。因为使用目的不同，可以定义不同使用类")
public class CommonRegion extends EntityBase<CommonRegion> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公共管理区域标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableId(value = "COMMON_REGION_ID", type = IdType.AUTO)
    private Long commonRegionId;

    @ApiModelProperty(value = "上级区域标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("PAR_REGION_ID")
    private Long parRegionId;

    @ApiModelProperty(value = "区域名称")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REGION_NAME")
    private String regionName;

    @ApiModelProperty(value = "区域拼音名称")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REGION_PY_NAME")
    private String regionPyName;

    @ApiModelProperty(value = "记录区域编码。LOVB=LOC-0002")
    @Length(max=30,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REGION_NBR")
    private String regionNbr;

    @ApiModelProperty(value = "记录区域类型。LOVB=LOC-0001")
    @Length(max=6,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REGION_TYPE")
    private String regionType;

    @ApiModelProperty(value = "区域描述")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REGION_DESC")
    private String regionDesc;

    @ApiModelProperty(value = "记录区域的级别。LOVB=LOC-C-0004")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REGION_LEVEL")
    private Long regionLevel;

    @ApiModelProperty(value = "区域排序")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REGION_SORT")
    private Long regionSort;

    @ApiModelProperty(value = "记录省分编码，冗余区域表省分的编码")
    @Length(max=32,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("PROVINCE_NBR")
    private String provinceNbr;

    @ApiModelProperty(value = "记录是城市还是乡村，LOVB=PUB-C-0004")
    @Length(max=4,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("CITY_FLAG")
    private String cityFlag;

    @ApiModelProperty(value = "备注")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REMARK")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.commonRegionId;
    }

}

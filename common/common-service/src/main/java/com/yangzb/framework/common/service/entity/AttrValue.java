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
 * 属性值规格
 * </p>
 *
 * @author yzb
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="AttrValue", description="属性值规格")
public class AttrValue extends EntityBase<AttrValue> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性值标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableId(value = "ATTR_VALUE_ID", type = IdType.AUTO)
    private Long attrValueId;

    @ApiModelProperty(value = "属性标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_ID")
    private Long attrId;

    @ApiModelProperty(value = "属性值名称")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_VALUE_NAME")
    private String attrValueName;

    @ApiModelProperty(value = "属性值描述")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_VALUE_DESC")
    private String attrValueDesc;

    @ApiModelProperty(value = "属性值")
    @Length(max=4000,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_VALUE")
    private String attrValue;

    @ApiModelProperty(value = "属性值排序号")
    @Range(max=2147483647,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_VALUE_SORT")
    private Integer attrValueSort;

    @ApiModelProperty(value = "备注")
    @Length(max=2000,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "集团属性值标识")
    @Range(max=2147483647,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("GROUP_ATTR_VALUE_ID")
    private Integer groupAttrValueId;

    @ApiModelProperty(value = "属性规格值长度")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_LENGTH")
    private Long attrLength;

    @ApiModelProperty(value = "属性规格值格式(正则表达式),用于属性值")
    @Length(max=64,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_FORMAT")
    private String attrFormat;

    @ApiModelProperty(value = "属性值分类")
    @Length(max=3,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_VALUE_TYPE")
    private String attrValueType;

    @ApiModelProperty(value = "外部属性值标识")
    @Length(max=32,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("EXT_ATTR_VALUE_ID")
    private String extAttrValueId;

    @TableField("GRP_ATTR_VALUE_NBR")
    private String grpAttrValueNbr;


    @Override
    protected Serializable pkVal() {
        return this.attrValueId;
    }

}

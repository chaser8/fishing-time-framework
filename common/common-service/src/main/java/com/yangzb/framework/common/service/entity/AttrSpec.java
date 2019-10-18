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
 * 属性规格
 * </p>
 *
 * @author yzb
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="AttrSpec", description="属性规格")
public class AttrSpec extends EntityBase<AttrSpec> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性目录标识")
    @Range(max=2147483647,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_CATALOG_ID")
    private Integer attrCatalogId;

    @ApiModelProperty(value = "属性描述")
    @Length(max=2000,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_DESC")
    private String attrDesc;

    @ApiModelProperty(value = "属性格式")
    @Length(max=2000,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_FORMAT")
    private String attrFormat;

    @ApiModelProperty(value = "属性标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableId(value = "ATTR_ID", type = IdType.AUTO)
    private Long attrId;

    @ApiModelProperty(value = "属性长度")
    @Range(max=2147483647,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_LENGTH")
    private Integer attrLength;

    @ApiModelProperty(value = "属性名称")
    @Length(max=128,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_NAME")
    private String attrName;

    @ApiModelProperty(value = "属性编码")
    @Length(max=100,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_NBR")
    private String attrNbr;

    @ApiModelProperty(value = "属性归属的系统")
    @Length(max=3,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_SRC")
    private String attrSrc;

    @ApiModelProperty(value = "属性来源：T：纵表；C：横表")
    @Length(max=3,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_SRC_TYPE")
    private String attrSrcType;

    @ApiModelProperty(value = "属性单位")
    @Length(max=8,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_UNIT")
    private String attrUnit;

    @ApiModelProperty(value = "属性取值编码")
    @Length(max=3,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_VALUE_CODE")
    private String attrValueCode;

    @ApiModelProperty(value = "属性值数据类型")
    @Length(max=4,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_VALUE_DATA_TYPE")
    private String attrValueDataType;

    @ApiModelProperty(value = "属性值分类")
    @Length(max=4,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("ATTR_VALUE_TYPE")
    private String attrValueType;

    @ApiModelProperty(value = "业务大类标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("BUSI_TYPE_ID")
    private Long busiTypeId;

    @ApiModelProperty(value = "默认值")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("DEFAULT_VALUE")
    private String defaultValue;

    @ApiModelProperty(value = "集团属性标识")
    @Length(max=32,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("EXT_ATTR_ID")
    private String extAttrId;

    @ApiModelProperty(value = "是否动态属性")
    @Range(max=2147483647,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("IS_DANY_ATTR")
    private Integer isDanyAttr;

    @ApiModelProperty(value = "是否实例化。Y-是 N-否")
    @TableField("IS_INSTANTIATION")
    private String isInstantiation;

    @ApiModelProperty(value = "是否可空")
    @Range(max=2147483647,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("IS_NULLABLE")
    private Integer isNullable;

    @ApiModelProperty(value = "唯一性")
    @Range(max=2147483647,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("IS_UNIQUE")
    private Integer isUnique;

    @ApiModelProperty(value = "对象标识")
    @Length(max=64,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("OBJ_ID")
    private String objId;

    @ApiModelProperty(value = "对象类型")
    @Length(max=5,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("OBJ_TYPE")
    private String objType;

    @ApiModelProperty(value = "父级属性标识")
    @Range(groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("PAR_ATTR_ID")
    private Long parAttrId;

    @ApiModelProperty(value = "父级属性编码")
    @Length(max=30,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("PRE_ATTR_CD")
    private String preAttrCd;

    @ApiModelProperty(value = "备注")
    @Length(max=2000,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "系统来源")
    @Length(max=20,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("SYSTEM_FROM")
    private String systemFrom;

    @ApiModelProperty(value = "1:强制前台展示默认值")
    @Range(max=2147483647,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("VAL_FLAG")
    private Integer valFlag;

    @ApiModelProperty(value = "起始值")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("VALUE_FROM")
    private String valueFrom;

    @ApiModelProperty(value = "取值语句")
    @Length(max=2000,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("VALUE_SQL")
    private String valueSql;

    @ApiModelProperty(value = "结束值")
    @Length(max=250,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("VALUE_TO")
    private String valueTo;

    @TableField("GRP_ATTR_NBR")
    private String grpAttrNbr;


    @Override
    protected Serializable pkVal() {
        return this.attrId;
    }

}

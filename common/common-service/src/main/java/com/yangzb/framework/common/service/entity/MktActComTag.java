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
import org.hibernate.validator.constraints.*;
import com.yangzb.framework.common.base.validation.ValidGoup;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzb
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="MktActComTag", description="")
public class MktActComTag extends EntityBase<MktActComTag> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "tag编码")
    @Length(max=50,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableId(value = "TAG_CODE", type = IdType.INPUT)
    private String tagCode;

    @ApiModelProperty(value = "tag描述")
    @Length(max=500,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("TAG_DESC")
    private String tagDesc;

    @ApiModelProperty(value = "tag-sql语句")
    @Length(max=4000,groups = {ValidGoup.Add.class,ValidGoup.Update.class,ValidGoup.Page.class})
    @TableField("TAG_SQL")
    private String tagSql;

    @Override
    protected Serializable pkVal() {
        return this.tagCode;
    }

}

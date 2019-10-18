package com.yangzb.framework.common.base.entity;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-03-22 10:34
 **/
@Getter
@Setter
public class EntityBase<T extends Model<?>> extends Model<T> {
    public static final String CREATE_STAFF="createStaff";
    public static final String UPDATE_STAFF="updateStaff";

    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Operation op;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getHref() {
        StringBuffer href = new StringBuffer();
        href.append("/").append(StrUtil.lowerFirst(this.getClass().getSimpleName())).append("/").append(getId());
        return href.toString();
    }

    public Serializable getId() {
        return this.pkVal();
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public void setId(Serializable id) {
        Class tempClass = this.getClass();
        while (tempClass != null&&!tempClass.equals(EntityBase.class)) {//当父类为null的时候说明到达了最上层的父类(Object类).
            Field[] fields = ClassUtil.getDeclaredFields(tempClass);
            for (Field field : fields) {
                TableId idAnnotation = field.getAnnotation(TableId.class);
                if (null != idAnnotation) {
                    ReflectUtil.setFieldValue(this, field, id);
                    return;
                }
            }
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
    }

    @ApiModelProperty(value = "记录状态变更的时间", example = "2019-10-10 12:23:24")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "STATUS_DATE")
    private Date statusDate;

    @ApiModelProperty(value = "记录状态。LOVB=PUB-C-0001。")
    @TableField(value = "STATUS_CD")
//    @NotEmpty(message = "不能为空", groups = {ValidGoup.Update.class, ValidGoup.Add.class})
    private String statusCd;

    @ApiModelProperty(value = "记录首次创建的员工标识")
    @TableField(value = "CREATE_STAFF")
//    @NotNull(message = "不能为空", groups = {ValidGoup.Add.class})
    @Range
    private Long createStaff;

    @ApiModelProperty(value = "记录首次创建的时间", example = "2019-10-10 12:23:24")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CREATE_DATE")
    private Date createDate;

    @ApiModelProperty(value = "记录每次修改的员工标识")
    @TableField(value = "UPDATE_STAFF")
    @Range
//    @NotNull(message = "不能为空", groups = {ValidGoup.Update.class, ValidGoup.Add.class})
    private Long updateStaff;

    @ApiModelProperty(value = "记录每次修改的时间", example = "2019-10-10 12:23:24")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "UPDATE_DATE", update = "now()")
    private Date updateDate;
}

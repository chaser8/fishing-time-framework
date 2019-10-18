package com.yangzb.framework.springcloud.gateway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yangzb.framework.common.base.entity.EntityBase;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 应用api信息
 * </p>
 *
 * @author yzb
 * @since 2019-05-22
 */
@Data
public class ApplicationInterface extends EntityBase<ApplicationInterface> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "INTERFACE_ID", type = IdType.AUTO)
    private Integer interfaceId;

    @TableField("INTERFACE_NAME")
    private String interfaceName;

    @TableField("INTERFACE_CODE")
    private String interfaceCode;

    @TableField("INTERFACE_URL")
    private String interfaceUrl;

    @TableField("HTTP_METHOD")
    private String httpMethod;

    @TableField("REMARK")
    private String remark;

    @TableField("INTERFACE_URL_PATTERN")
    private String interfaceUrlPattern;

    @Override
    protected Serializable pkVal() {
        return this.interfaceId;
    }

}

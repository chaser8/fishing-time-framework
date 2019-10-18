package com.tydic.dev1.springcloud.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 记录每一个优惠券实例信息
 * </p>
 *
 * @author yzb
 * @since 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("opp_source")
public class OppSourceEntity extends Model<OppSourceEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 来源标识
     */
    @TableId("OPP_SOURCE_ID")
    private Long oppSourceId;

    /**
     * 来源名称
     */
    @TableField("OPP_SOURCE_NAME")
    private String oppSourceName;

    /**
     * 来源编号
     */
    @TableField("OPP_SOURCE_CODE")
    private String oppSourceCode;

    /**
     * 状态
     */
    @TableField("STATUS_CD")
    private String statusCd;

    /**
     * 状态时间
     */
    @TableField("STATUS_DATE")
    private Date statusDate;

    /**
     * 创建人
     */
    @TableField("CREATE_STAFF")
    private Long createStaff;

    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
    private Date createDate;

    /**
     * 修改人
     */
    @TableField("UPDATE_STAFF")
    private Long updateStaff;

    /**
     * 修改时间
     */
    @TableField("UPDATE_DATE")
    private Date updateDate;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.oppSourceId;
    }

}

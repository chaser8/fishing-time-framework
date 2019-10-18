package com.tydic.dev1.springcloud.example.controller.vo;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@ApiModel(value="OppSource", description="记录每一个优惠券实例信息")
public class OppSource implements Serializable  {

private static final long serialVersionUID = 1L;
    private String href;
    @ApiModelProperty(value = "来源标识")
    private Long oppSourceId;
    @ApiModelProperty(value = "来源名称")
    private String oppSourceName;
    @ApiModelProperty(value = "来源编号")
    private String oppSourceCode;
    @ApiModelProperty(value = "状态")
    private String statusCd;
    @ApiModelProperty(value = "状态时间",example="2019-10-10 12:23:24")
    private Date statusDate;
    @ApiModelProperty(value = "创建人")
    private Long createStaff;
    @ApiModelProperty(value = "创建时间",example="2019-10-10 12:23:24")
    private Date createDate;
    @ApiModelProperty(value = "修改人")
    private Long updateStaff;
    @ApiModelProperty(value = "修改时间",example="2019-10-10 12:23:24")
    private Date updateDate;
    @ApiModelProperty(value = "备注")
    private String remark;

    public String getHref() {
        if(StrUtil.isEmpty(this.href)){
            StringBuffer href = new StringBuffer();
            href.append("/").append("oppSource").append("/").append(oppSourceId);
            this.setHref(href.toString());
        }
        return href;
    }
    public String getId(){
        return StrUtil.toString(oppSourceId);
    }
}
package com.fishingtime.framework.springcloud.gateway.entity;

import com.fishingtime.framework.springboot.starter.tk.mybatis.entity.EntityBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * <p>
 * 应用api信息
 * </p>
 *
 * @author
 * @since 2019-05-22
 */
@Data
public class ApplicationInterface extends EntityBase {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "INTERFACE_ID")
    private Integer interfaceId;

    @Column(name = "INTERFACE_NAME")
    private String interfaceName;

    @Column(name = "INTERFACE_CODE")
    private String interfaceCode;

    @Column(name = "INTERFACE_URL")
    private String interfaceUrl;

    @Column(name = "HTTP_METHOD")
    private String httpMethod;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "INTERFACE_URL_PATTERN")
    private String interfaceUrlPattern;

}

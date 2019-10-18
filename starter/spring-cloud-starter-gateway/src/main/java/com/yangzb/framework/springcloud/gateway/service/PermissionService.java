package com.yangzb.framework.springcloud.gateway.service;

import com.yangzb.framework.uaa.bean.Privilege;

import java.util.List;

/**
 * 
 * @program:
 * @description: 
 * @author: yzb
 * @date 2019/4/28 19:01
 **/
public interface PermissionService {
    boolean hasPermission(List<Privilege> privileges, String uri);
}

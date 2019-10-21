package com.fishingtime.framework.springcloud.gateway.service;

import com.fishingtime.framework.uaa.bean.Privilege;

import java.util.List;

/**
 * 
 * @program:
 * @description: 
 * @author:
 * @date 2019/4/28 19:01
 **/
public interface PermissionService {
    boolean hasPermission(List<Privilege> privileges, String uri);
}

package com.yangzb.framework.uaa.base.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @program:
 * @description: 
 * @author: yzb
 * @date 2019/4/28 19:01
 **/
public interface PermissionService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}

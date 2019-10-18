package com.yangzb.framework.uaa.server.service;

import com.yangzb.framework.uaa.bean.SystemUser;

/**
 *
 * @Description:
 * @author yzb
 * @date 2019/4/22 17:28
 * @throws
 */
public interface UserService {
    /**
     * 通过用户登录账号获取用户信息 包括角色，权限 
     * @Description:  
     * @param account
     * @return com.tydic.dev1.common.auth.server.config.SystemUser
     * @author yzb
     * @date 2019/4/26 17:18
     * @throws
     */
    SystemUser findByAccount(String account);
}
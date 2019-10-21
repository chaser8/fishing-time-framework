package com.fishingtime.framework.uaa.server.service.impl;

import com.fishingtime.framework.uaa.bean.SystemUser;
import com.fishingtime.framework.uaa.server.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 
 * @program:
 * @description: 
 * @author:
 * @date 2019/4/26 17:17
 **/
@Service
public class UserServiceImpl implements UserService {
    /**
     * 通过用户登录账号获取用户信息 包括角色，权限
     * @Description:  
     * @param account
     * @return com.fishingtime.dev1.common.auth.server.config.SystemUser
     * @author
     * @date 2019/4/26 17:18
     * @throws
     */
    @Override
    public SystemUser findByAccount(String account){
        SystemUser systemUser = new SystemUser();
//        systemUser.setId(1);
//        systemUser.setUserCode("");
//        List<SystemRole> systemRoles = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            SystemRole systemRoles = new SystemRole();
//            systemRoles.setId(i);
//            systemRoles.setName("角色_"+i);
//            systemRoles.setCode("code_"+i);
//            List<Privilege> privileges = new ArrayList<>();
//
//            for (int j = 1; j <4 ; j++) {
//                Privilege privilege = new Privilege();
//                privilege.setCode("privilege_code_"+j);
//                privilege.setName("权限_"+j);
//                privilege.setUrl("/privilege/"+j);
//                privilege.setUrlPattern("/actuator/health");
//                privileges.add(privilege);
//            }
//            systemRoles.setPrivileges(privileges);
//            systemRoles.add(systemRoles);
//        }
//        systemUser.setSystemRoles(systemRoles);
        systemUser.setPassword(new BCryptPasswordEncoder().encode("123456"));
        return systemUser;
    }
}

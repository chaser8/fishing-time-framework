package com.yangzb.framework.uaa.server.service.impl;

import com.yangzb.framework.uaa.bean.SystemUser;
import com.yangzb.framework.uaa.server.authority.GrantedAuthority;
import com.yangzb.framework.uaa.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author yzb
 * @Description:
 * @date 2019/4/20 18:41
 * @throws
 */
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        SystemUser systemUserDetails = userService.findByAccount(username);

        //判断用户是否为空
        Optional.ofNullable(systemUserDetails).orElseThrow(() -> new UsernameNotFoundException(username));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        switch (systemUserDetails.getStatusCd()) {
            //用户未生效
            case SystemUser.STATUS_CD_INVALID:
                enabled = false;
                break;
            //冻结
            case SystemUser.STATUS_CD_FREEZE:
                accountNonLocked = false;
            default:
        }

        //如果不为空则添加角色信息
//        Optional.ofNullable(systemUserDetails.getSystemRoles()).ifPresent(roles -> roles.forEach(role -> {
//            GrantedAuthority grantedAuthority = new GrantedAuthority("ROLE_" + role.getCode(), role.getPrivileges());
//            grantedAuthorities.add(grantedAuthority);
//        }));

        return new org.springframework.security.core.userdetails.User(systemUserDetails.getSysUserCode(), systemUserDetails.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}

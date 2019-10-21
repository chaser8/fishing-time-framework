package com.fishingtime.framework.uaa.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.fishingtime.framework.common.base.util.JSONUtil;
import com.fishingtime.framework.uaa.bean.Privilege;
import com.fishingtime.framework.uaa.base.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 判断权限服务,如果要自定义则实现PermissionService
 *
 * @program:
 * @description:
 * @author:
 * @date 2019/4/26 16:11
 **/
@Service("permissionService")
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    public static final String AUTHORITIES = "authorities";
    public static final String PRIVILEGES = "privileges";

    /**
     * 可以做URLs匹配，规则如下
     * <p>
     * ？匹配一个字符
     * *匹配0个或多个字符
     * **匹配0个或多个目录
     * 用例如下
     * <p>https://www.cnblogs.com/zhangxiaoguang/p/5855113.html</p>
     */

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 判断权限
     *
     * @param request
     * @param authentication
     * @return boolean
     * @throws
     * @Description:
     * @author
     * @date 2019/4/26 16:10
     */
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        if(authentication instanceof AnonymousAuthenticationToken){
            return false;
        }
        Object principal = authentication.getPrincipal();
        String requestUrl = request.getRequestURI();
        Authentication auth = ((OAuth2Authentication) authentication).getUserAuthentication();
        Map<String, Object> authDetails = (Map<String, Object>) auth.getDetails();
        List<Privilege> privileges = getPrivileges(authDetails);

        boolean hasPermission = false;

        principal:
        if (principal != null) {
            if (CollectionUtils.isEmpty(privileges)) {
                break principal;
            }
            for (Privilege privilege : privileges) {
                String urlPattern = privilege.getUrlPattern();
                if (null != urlPattern && antPathMatcher.match(urlPattern, requestUrl)) {
                    hasPermission = true;
                    break principal;
                }
            }
        }
        //false 时打印日志
        if (!hasPermission) {
            log.debug("requestUrl:{},privileges:{}", requestUrl, JSONUtil.toJSONString(privileges));
        }

        return hasPermission;
    }

    /**
     * 获取所有权限
     *
     * @param authDetails
     * @return java.util.List<com.tydic.dev1.common.auth.upms.config.Privilege>
     * @throws
     * @Description:
     * @author
     * @date 2019/4/26 16:10
     */
    private List<Privilege> getPrivileges(Map<String, Object> authDetails) {
        List<Privilege> privileges = new ArrayList<>();
        //判断authorities是否为空
        Optional.ofNullable(authDetails.get(PermissionServiceImpl.AUTHORITIES)).ifPresent(authorities -> {
            List<Map<String, Object>> auths = (List<Map<String, Object>>) authorities;
            //过滤privileges为空的循环添加到privileges
            auths.parallelStream().filter(auth -> null != auth.get(PermissionServiceImpl.PRIVILEGES)).forEach(auth -> {
                List<Map<String, Object>> privs = (List<Map<String, Object>>) auth.get(PermissionServiceImpl.PRIVILEGES);
                privs.forEach(privilege -> {
                    privileges.add(BeanUtil.toBean(privilege, Privilege.class));
                });
            });
        });
        return privileges;
    }
}

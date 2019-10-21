package com.fishingtime.framework.springcloud.gateway.service.impl;

import com.fishingtime.framework.common.base.util.JSONUtil;
import com.fishingtime.framework.springcloud.gateway.service.PermissionService;
import com.fishingtime.framework.uaa.bean.Privilege;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 判断权限服务,如果要自定义则实现PermissionService
 *
 * @program:
 * @description:
 * @author:
 * @date 2019/4/26 16:11
 **/
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

    /***
     * hasPermission 
     * @Description:
     * @param privileges
     * @param uri
     * @return boolean
     * @author
     * @date 2019/5/10 18:04
     * @throws
     */
    @Override
    public boolean hasPermission(List<Privilege> privileges, String uri) {
        boolean hasPermission = false;
        if (!CollectionUtils.isEmpty(privileges)) {
            for (Privilege privilege : privileges) {
                String urlPattern = privilege.getUrlPattern();
                if (null != urlPattern && antPathMatcher.match(urlPattern, uri)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        //false 时打印日志
        if (!hasPermission) {
            log.debug("requestUrl:{},privileges:{}", uri, JSONUtil.toJSONString(privileges));
        }

        return hasPermission;
    }
}

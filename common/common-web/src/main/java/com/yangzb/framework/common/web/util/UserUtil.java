package com.yangzb.framework.common.web.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.yangzb.framework.common.base.util.JSONUtil;
import com.yangzb.framework.common.web.Constants;
import com.yangzb.framework.common.web.exception.AuthenticationException;
import com.yangzb.framework.uaa.bean.SystemUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-23 09:18
 **/
public class UserUtil {

    /**
     * @return SystemUser
     */
    public static SystemUser getUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userinfo = request.getHeader("user");

        userinfo = URLUtil.decode(userinfo);

        if(StrUtil.isEmpty(userinfo)){
            throw new AuthenticationException(Constants.ERROR_8000,"用户认证失败，请重新登录！");
        }
        return JSONUtil.parseObject(userinfo, SystemUser.class,false);
    }
}

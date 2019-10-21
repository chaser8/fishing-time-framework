package com.fishingtime.framework.common.web.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.fishingtime.framework.common.base.util.JSONUtil;
import com.fishingtime.framework.common.web.exception.AuthenticationException;
import com.fishingtime.framework.common.web.response.R;
import com.fishingtime.framework.common.web.response.ResultStatus;
import com.fishingtime.framework.uaa.bean.SystemUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program:
 * @description:
 * @author:
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
            throw new AuthenticationException(new R().setStatus(ResultStatus.AUTH_ERROR),"用户认证失败，请重新登录！");
        }
        return JSONUtil.parseObject(userinfo, SystemUser.class,false);
    }
}

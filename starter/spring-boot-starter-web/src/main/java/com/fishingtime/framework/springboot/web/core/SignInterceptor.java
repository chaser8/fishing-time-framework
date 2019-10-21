package com.fishingtime.framework.springboot.web.core;

import cn.hutool.crypto.SecureUtil;
import com.fishingtime.framework.common.web.exception.SystemException;
import com.fishingtime.framework.common.web.response.ResultStatus;
import com.fishingtime.framework.springboot.web.autoconfigure.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @program:
 * @description:
 * @create: 2019-08-26 11:27
 **/
@Slf4j
public class SignInterceptor extends HandlerInterceptorAdapter {
    private Config.Sign sign;
    public SignInterceptor(Config.Sign sign){
        this.sign = sign;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestWrapper requestWrapper = (RequestWrapper) request;
        String sortBody = requestWrapper.getSortBody();
        String requestSign = request.getHeader(Config.Sign.KEY);
        Optional.ofNullable(requestSign).orElseThrow(() -> new SystemException(ResultStatus.SIGN_ERROR,"无签名"));
        String sign = SecureUtil.md5(sortBody + this.sign.getSecretKey());
        if(requestSign.equals(sign)){
            return true;
        }else {
            throw new SystemException(ResultStatus.SIGN_ERROR,"签名验证失败:"+requestSign);
        }
    }

}
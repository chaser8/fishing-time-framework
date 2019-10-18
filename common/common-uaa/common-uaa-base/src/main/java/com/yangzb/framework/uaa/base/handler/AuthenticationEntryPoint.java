package com.yangzb.framework.uaa.base.handler;

import com.yangzb.framework.common.base.util.JSONUtil;
import com.yangzb.framework.common.web.ResponseBodyError;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-04-28 17:33
 **/

/**
 * 认证失败时通过该类来处理
 * @program: 
 * @description: 
 * @author: yzb
 * @date 2019/4/28 17:55
 **/
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE);
        ResponseBodyError responseBodyError = new ResponseBodyError("8000").setMessage(authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            JSONUtil.MAPPER.writeValue(response.getOutputStream(), responseBodyError);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}

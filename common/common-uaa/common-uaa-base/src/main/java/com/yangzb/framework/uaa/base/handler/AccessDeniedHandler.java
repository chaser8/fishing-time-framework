package com.yangzb.framework.uaa.base.handler;

import com.yangzb.framework.common.base.util.JSONUtil;
import com.yangzb.framework.common.web.ResponseBodyError;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功后，无权限错误处理
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @date 2019/4/28 18:11
 **/
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE);
        ResponseBodyError responseBodyError = new ResponseBodyError("8000").setMessage(accessDeniedException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            JSONUtil.MAPPER.writeValue(response.getOutputStream(), responseBodyError);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}

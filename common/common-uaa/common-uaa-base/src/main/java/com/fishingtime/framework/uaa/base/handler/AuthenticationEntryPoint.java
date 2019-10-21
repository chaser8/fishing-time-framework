package com.fishingtime.framework.uaa.base.handler;

import com.fishingtime.framework.common.base.util.JSONUtil;
import com.fishingtime.framework.common.web.response.Response;
import com.fishingtime.framework.common.web.response.ResultStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-04-28 17:33
 **/

/**
 * 认证失败时通过该类来处理
 *
 * @program:
 * @description:
 * @author:
 * @date 2019/4/28 17:55
 **/
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            JSONUtil.MAPPER.writeValue(response.getOutputStream(),
                    Response.fail(ResultStatus.AUTH_ERROR, authException.getMessage()).getBody());
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}

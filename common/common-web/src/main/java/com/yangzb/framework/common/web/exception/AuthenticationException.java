package com.yangzb.framework.common.web.exception;


import com.yangzb.framework.common.web.ResponseBodyError;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-17 09:09
 **/
public class AuthenticationException extends SystemException {
    public AuthenticationException(ResponseBodyError responseContent) {
        super(responseContent);
    }

    public AuthenticationException(ResponseBodyError responseContent, String message, Throwable cause) {
        super(responseContent, message, cause);
    }

    public AuthenticationException(ResponseBodyError responseContent, String message) {
        super(responseContent, message);
    }

    public AuthenticationException(ResponseBodyError responseContent, Throwable cause) {
        super(responseContent, cause);
    }
}
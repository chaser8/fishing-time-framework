package com.yangzb.framework.common.web.exception;


import com.yangzb.framework.common.web.ResponseBodyError;

/**
 *
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-17 09:29
 **/
public class ForbiddenException extends BusiException {
    public ForbiddenException(ResponseBodyError responseContent) {
        super(responseContent);
    }

    public ForbiddenException(ResponseBodyError responseContent, String message, Throwable cause) {
        super(responseContent, message, cause);
    }

    public ForbiddenException(ResponseBodyError responseContent, String message) {
        super(responseContent, message);
    }

    public ForbiddenException(ResponseBodyError responseContent, Throwable cause) {
        super(responseContent, cause);
    }
}

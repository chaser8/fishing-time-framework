package com.fishingtime.framework.common.web.exception;


import com.fishingtime.framework.common.web.response.ResultStatus;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-17 09:09
 **/
public class AuthenticationException extends SystemException {
    public AuthenticationException(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public AuthenticationException(ResultStatus resultStatus, String message) {
        super(resultStatus, message);
    }

    public AuthenticationException(ResultStatus resultStatus, String message, Throwable cause) {
        super(resultStatus, message, cause);
    }

    public AuthenticationException(ResultStatus resultStatus, Throwable cause) {
        super(resultStatus, cause);
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.fishingtime.framework.common.web.exception;


import com.fishingtime.framework.common.web.response.R;
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
    public AuthenticationException(ResultStatus resultStatus,String message) {
        super(resultStatus,message);
    }
    public AuthenticationException(R responseContent) {
        super(responseContent);
    }

    public AuthenticationException(R responseContent, String message, Throwable cause) {
        super(responseContent, message, cause);
    }

    public AuthenticationException(R responseContent, String message) {
        super(responseContent, message);
    }

    public AuthenticationException(R responseContent, Throwable cause) {
        super(responseContent, cause);
    }
}
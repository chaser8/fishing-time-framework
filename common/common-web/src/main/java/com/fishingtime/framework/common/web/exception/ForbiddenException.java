package com.fishingtime.framework.common.web.exception;


import com.fishingtime.framework.common.web.response.R;
import com.fishingtime.framework.common.web.response.ResultStatus;

/**
 *
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-17 09:29
 **/
public class ForbiddenException extends BusiException {
    public ForbiddenException(ResultStatus resultStatus) {
        super(resultStatus);
    }
    public ForbiddenException(ResultStatus resultStatus,String message) {
        super(resultStatus,message);
    }
    public ForbiddenException(R responseContent) {
        super(responseContent);
    }

    public ForbiddenException(R responseContent, String message, Throwable cause) {
        super(responseContent, message, cause);
    }

    public ForbiddenException(R responseContent, String message) {
        super(responseContent, message);
    }

    public ForbiddenException(R responseContent, Throwable cause) {
        super(responseContent, cause);
    }
}

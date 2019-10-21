package com.fishingtime.framework.common.web.exception;


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

    public ForbiddenException(ResultStatus resultStatus, String message) {
        super(resultStatus, message);
    }

    public ForbiddenException(ResultStatus resultStatus, String message, Throwable cause) {
        super(resultStatus, message, cause);
    }

    public ForbiddenException(ResultStatus resultStatus, Throwable cause) {
        super(resultStatus, cause);
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}

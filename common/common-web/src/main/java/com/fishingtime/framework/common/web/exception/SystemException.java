package com.fishingtime.framework.common.web.exception;

import com.fishingtime.framework.common.web.response.Response;
import com.fishingtime.framework.common.web.response.ResultStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

/**
 * 系统级异常，返回http状态码 500
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-30 11:28
 **/
@Getter
@Setter
public class SystemException extends RuntimeException{
    protected ResponseEntity response =null;
    public SystemException(ResultStatus resultStatus) {
        super();
        response = Response.fail(resultStatus);
    }
    public SystemException(ResultStatus resultStatus,String message) {
        super(message);
        response = Response.fail(resultStatus,message);
    }

    public SystemException(ResultStatus resultStatus, String message, Throwable cause) {
        super(message, cause);
        response = Response.fail(resultStatus,message);
    }
    public SystemException(ResultStatus resultStatus, Throwable cause) {
        super(cause);
        response = Response.fail(resultStatus);
    }
    public SystemException(String message) {
        super(message);
        response = Response.fail(message);
    }
    public SystemException(String message,Throwable cause) {
        super(message,cause);
        response = Response.fail(message);
    }
}

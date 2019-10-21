package com.fishingtime.framework.common.web.exception;

import com.fishingtime.framework.common.web.response.R;
import com.fishingtime.framework.common.web.response.Response;
import com.fishingtime.framework.common.web.response.ResultStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

/**
 * 业务异常，需要调用方处理的 返回http状态码200
 * @program:
 * @description: 业务异常
 * @author:
 * @create: 2019-03-08 16:59
 **/
@Getter
@Setter
public class BusiException extends RuntimeException{
    protected ResponseEntity response =null;
    public BusiException(ResultStatus resultStatus) {
        super();
        response = Response.fail(resultStatus);
    }
    public BusiException(ResultStatus resultStatus,String message) {
        super(message);
        response = Response.fail(resultStatus,message);
    }

    public BusiException(ResultStatus resultStatus, String message, Throwable cause) {
        super(message, cause);
        response = Response.fail(resultStatus,message);
    }
    public BusiException(ResultStatus resultStatus, Throwable cause) {
        super(cause);
        response = Response.fail(resultStatus);
    }
    public BusiException(String message) {
        super(message);
        response = Response.fail(message);
    }
    public BusiException(String message,Throwable cause) {
        super(message,cause);
        response = Response.fail(message);
    }
}

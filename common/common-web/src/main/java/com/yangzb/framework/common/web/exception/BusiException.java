package com.yangzb.framework.common.web.exception;

import com.yangzb.framework.common.web.Response;
import com.yangzb.framework.common.web.ResponseBodyError;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常，需要调用方处理的 返回http状态码200
 * @program: dic-framework-v3
 * @description: 业务异常
 * @author: yzb
 * @create: 2019-03-08 16:59
 **/
@Getter
@Setter
public class BusiException extends RuntimeException{
    protected Response<ResponseBodyError> response =null;

    public BusiException(ResponseBodyError responseContent) {
        super();
        response = Response.busiError(responseContent);
    }
    public BusiException(ResponseBodyError responseContent, String message, Throwable cause) {
        super(message, cause);
        response = Response.busiError(responseContent);
    }
    public BusiException(ResponseBodyError responseContent, String message) {
        super(message);
        response = Response.busiError(responseContent);
    }
    public BusiException(ResponseBodyError responseContent, Throwable cause) {
        super(cause);
        response = Response.busiError(responseContent);
    }
}

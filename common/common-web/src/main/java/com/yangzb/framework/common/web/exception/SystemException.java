package com.yangzb.framework.common.web.exception;

import com.yangzb.framework.common.web.Response;
import com.yangzb.framework.common.web.ResponseBodyError;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统级异常，返回http状态码 500
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-30 11:28
 **/
@Getter
@Setter
public class SystemException extends RuntimeException{
    protected Response<ResponseBodyError> response =null;

    public SystemException(ResponseBodyError responseContent) {
        super();
        response = Response.busiError(responseContent);
    }
    public SystemException(ResponseBodyError responseContent, String message, Throwable cause) {
        super(message, cause);
        response = Response.busiError(responseContent);
    }
    public SystemException(ResponseBodyError responseContent, String message) {
        super(message);
        response = Response.busiError(responseContent);
    }
    public SystemException(ResponseBodyError responseContent, Throwable cause) {
        super(cause);
        response = Response.busiError(responseContent);
    }
}

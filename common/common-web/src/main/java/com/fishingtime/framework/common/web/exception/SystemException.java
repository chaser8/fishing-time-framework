package com.fishingtime.framework.common.web.exception;

import com.fishingtime.framework.common.web.response.R;
import com.fishingtime.framework.common.web.response.ResultStatus;
import lombok.Getter;
import lombok.Setter;

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
    protected R response =null;
    public SystemException(ResultStatus resultStatus) {
        super();
        response = new R().setStatus(resultStatus);
    }
    public SystemException(ResultStatus resultStatus,String message) {
        super(message);
        response = new R().setMessage(message).setStatus(resultStatus);
    }
    public SystemException(R responseContent) {
        super();
        response = responseContent;
    }
    public SystemException(R responseContent, String message, Throwable cause) {
        super(message, cause);
        response = responseContent;
    }
    public SystemException(R responseContent, String message) {
        super(message);
        response = responseContent;
    }
    public SystemException(R responseContent, Throwable cause) {
        super(cause);
        response = responseContent;
    }
}

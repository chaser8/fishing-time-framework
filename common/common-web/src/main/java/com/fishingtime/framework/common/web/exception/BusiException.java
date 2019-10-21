package com.fishingtime.framework.common.web.exception;

import com.fishingtime.framework.common.web.response.R;
import com.fishingtime.framework.common.web.response.ResultStatus;
import lombok.Getter;
import lombok.Setter;

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
    protected R response =null;
    public BusiException(ResultStatus resultStatus) {
        super();
        response = new R().setStatus(resultStatus);
    }
    public BusiException(ResultStatus resultStatus,String message) {
        super(message);
        response = new R().setMessage(message).setStatus(resultStatus);
    }

    public BusiException(R responseContent) {
        super();
        response = responseContent;
    }
    public BusiException(R responseContent, String message, Throwable cause) {
        super(message, cause);
        response = responseContent.setMessage(message);
    }
    public BusiException(R responseContent, String message) {
        super(message);
        response = responseContent.setMessage(message);
    }
    public BusiException(R responseContent, Throwable cause) {
        super(cause);
        response = responseContent;
    }
}

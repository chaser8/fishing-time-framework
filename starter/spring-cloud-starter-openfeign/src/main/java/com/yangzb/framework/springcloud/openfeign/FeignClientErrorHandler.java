package com.yangzb.framework.springcloud.openfeign;

import com.yangzb.framework.common.web.Constants;
import com.yangzb.framework.common.web.Response;
import com.yangzb.framework.common.web.ResponseBodyError;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 客户端异常通用处理
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-29 15:04
 **/
@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class FeignClientErrorHandler {
    /**
     * 对发起请求时，非服务端异常进行通用异常处理
     * @Description:
     * @param ex
     * @return
     * @author yzb
     * @date 2019/4/10 08:56
     * @throws
     */
    @ResponseBody
    @ExceptionHandler(value = FeignException.class)
    public Response errorHandler(FeignException ex) {
        Response response;
        ResponseBodyError responseBodyError = Constants.ERROR_9999;
        responseBodyError.setMessage("调用服务异常："+ex.getMessage());
        response = Response.sysError(responseBodyError);
        log.error("",ex);
        return response;
    }
}

package com.fishingtime.framework.springcloud.openfeign;

import com.fishingtime.framework.common.web.response.R;
import com.fishingtime.framework.common.web.response.Response;
import com.fishingtime.framework.common.web.response.ResultStatus;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 客户端异常通用处理
 * @program:
 * @description:
 * @author:
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
     * @author
     * @date 2019/4/10 08:56
     * @throws
     */
    @ResponseBody
    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<R> errorHandler(FeignException ex) {
        log.error("",ex);
        return Response.fail(new R().setStatus(ResultStatus.INTF_ERROR).setMessage("调用服务异常："+ex.getMessage()));
    }
}

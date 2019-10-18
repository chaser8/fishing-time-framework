package com.yangzb.framework.springcloud.openfeign;

import com.yangzb.framework.common.base.util.JSONUtil;
import com.yangzb.framework.common.web.Constants;
import com.yangzb.framework.common.web.ResponseBodyError;
import com.yangzb.framework.common.web.exception.SystemException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/***
 * 对服务端返回非200的情况进行通用处理，貌似当设置feign.client.config.XXX.decode404=true时无法拦截
 * @program:
 * @description: 
 * @author: yzb
 * @date 2019/5/30 09:08
 **/
@Slf4j
public class FeignServerErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String responseBodyStr="";
        if(null!=response.body()){
            responseBodyStr = response.body().toString();
        }
        ResponseBodyError responseBodyError = Constants.ERROR_9999;
        try {
            responseBodyError = JSONUtil.parseObject(responseBodyStr, ResponseBodyError.class);
        } catch (Exception e) {
            responseBodyError.setMessage(responseBodyStr);
        }
        responseBodyError.setMessage("服务调用异常："+ responseBodyError.getMessage());
        return new SystemException(responseBodyError);
    }
}
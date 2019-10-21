package com.fishingtime.framework.springcloud.openfeign;

import com.fishingtime.framework.common.base.util.JSONUtil;
import com.fishingtime.framework.common.web.exception.SystemException;
import com.fishingtime.framework.common.web.response.R;
import com.fishingtime.framework.common.web.response.ResultStatus;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/***
 * 对服务端返回非200的情况进行通用处理，貌似当设置feign.client.config.XXX.decode404=true时无法拦截
 * @program:
 * @description: 
 * @author:
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
        R responseBodyError = new R().setStatus(ResultStatus.SYSTEM_ERROR);
        try {
            responseBodyError = JSONUtil.parseObject(responseBodyStr, R.class);
        } catch (Exception e) {
            responseBodyError.setMessage(responseBodyStr);
        }
        responseBodyError.setMessage("服务调用异常："+ responseBodyError.getMessage());
        return new SystemException(responseBodyError);
    }
}
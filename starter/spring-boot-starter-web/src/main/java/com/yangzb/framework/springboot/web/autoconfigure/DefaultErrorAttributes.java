package com.yangzb.framework.springboot.web.autoconfigure;

import cn.hutool.core.util.StrUtil;
import com.yangzb.framework.common.base.util.JSONUtil;
import com.yangzb.framework.common.web.Constants;
import com.yangzb.framework.common.web.ResponseBodyError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 重新定义DefaultErrorAttributes 解决springmv 处理消息404等返回不统一问题
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-29 16:53
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultErrorAttributes extends org.springframework.boot.web.servlet.error.DefaultErrorAttributes {
    public DefaultErrorAttributes(boolean includeException) {
        super(includeException);
    }

    private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        //在com.tydic.dev1.common.base.api.ResponseBodyAdvice中判断是否为错误返回，如果为错误返回则不转换格式，这里是为了避免ResponseBody的重复嵌套
        webRequest.setAttribute("isError",true, RequestAttributes.SCOPE_REQUEST);

        Map<String, Object> errorMap = super.getErrorAttributes(webRequest, includeStackTrace);
        String message = StrUtil.toString(errorMap.get(ERROR))+","+StrUtil.toString(errorMap.get(MESSAGE));
        ResponseBodyError responseBodyError = Constants.ERROR_9999.setMessage(message);
        return JSONUtil.parseObject(JSONUtil.toJSONString(responseBodyError),Map.class);
    }
}
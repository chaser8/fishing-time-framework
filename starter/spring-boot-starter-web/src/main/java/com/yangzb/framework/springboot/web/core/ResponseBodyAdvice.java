package com.yangzb.framework.springboot.web.core;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yangzb.framework.common.web.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

/**
 * 返回格式统一包装
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-30 15:58
 **/
@ControllerAdvice
@Slf4j
public class ResponseBodyAdvice implements org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object result = body;

        boolean openapi = true;
        try {
            List<String> header = request.getHeaders().get("openapi");
            openapi = Boolean.valueOf(header.get(0));
        } catch (Exception e) {
            //不做异常处理
        }

        //见com.tydic.dev1.springboot.web.autoconfigure.DefaultErrorAttributes,这里是为了避免ResponseBody的重复嵌套
        boolean isError = false;

        try {
            if(request instanceof ServletServerHttpRequest){
                isError = Convert.toBool(((ServletServerHttpRequest) request).getServletRequest().getAttribute("isError"));
            }
        } catch (Exception e) {
            //不做异常处理
        }
        if(isError){
            return result;
        }

        //判断不是openapi，并且返回格式为json
        if(!openapi&&(selectedContentType.equalsTypeAndSubtype(new MediaType(MediaType.APPLICATION_JSON.getType(),MediaType.APPLICATION_JSON.getSubtype()))||MappingJackson2HttpMessageConverter.class.isAssignableFrom(selectedConverterType))){
            ResponseBody<Object> objectResponseBody = new ResponseBody<>();
            //如果对象为字符串则转换为 json对象
            if(body instanceof String){
                String strBody = StrUtil.toString(body);
                try{
                    Object dataBody = body;
                    if(JSONUtil.isJsonObj(strBody)){
                        dataBody = JSONUtil.parseObj(strBody);
                    }else if(JSONUtil.isJsonArray(strBody)){
                        dataBody = JSONUtil.parseArray(strBody);
                    }
                    objectResponseBody.setData(dataBody);
                }catch (Exception e){
                    log.debug("response json 转换失败",e);
                    objectResponseBody.setData(JSONUtil.parseObj(body));
                }
                result = com.yangzb.framework.common.base.util.JSONUtil.toJSONString(objectResponseBody);
            }else if(body == null||!ResponseBody.class.isAssignableFrom(body.getClass())){//不为ResponseBody的实例
                objectResponseBody.setData(body);
                result = objectResponseBody;
            }
        }
        return result;
    }
}

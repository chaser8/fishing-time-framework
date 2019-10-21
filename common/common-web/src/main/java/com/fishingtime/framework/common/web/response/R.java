package com.fishingtime.framework.common.web.response;

import cn.hutool.core.bean.BeanUtil;
import com.fishingtime.framework.common.base.util.JSONUtil;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * @program: fishing-time-framework
 * @description:
 * @author:
 * @create: 2019-10-18 17:24
 **/
public class R<T> extends LinkedHashMap<String,Object> implements Serializable {
    R(){
    }
    public String getCode() {
        return status.getCode();
    }

    public String getMessage() {
        message = Optional.ofNullable(message).orElse(status.getMessage());
        return message;
    }

    public static final String CODE = "code";
    public static final String MESSAGE = "message";

    public T getData() {
        return data;
    }

    public R setStatus(ResultStatus status) {
        this.status = status;
        return  this;
    }

    public R setMessage(String message) {
        this.message = message;
        return  this;
    }

    public R setData(T data) {
        Optional.ofNullable(data).ifPresent(o -> {
            this.putAll(BeanUtil.beanToMap(data));
        });
        this.data = data;
        return  this;
    }

    private void set(ResultStatus status,String message){
        message = Optional.ofNullable(message).orElse(status.getMessage());
        status = Optional.ofNullable(status).orElse(status);

        this.put(CODE,status.getCode());
        this.put(MESSAGE,message);
    }

    private ResultStatus status = ResultStatus.SUCCESS;

    private String message;
    private T data;

    public R clone(){
        return JSONUtil.toBean(this,R.class);
    }
}
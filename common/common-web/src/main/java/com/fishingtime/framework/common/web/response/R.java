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
    R(ResultStatus status,String message,T data){
        this.data = data;
        Optional.ofNullable(data).ifPresent(o -> {
            this.putAll(BeanUtil.beanToMap(this.data));
        });
        this.status = Optional.ofNullable(status).orElse(this.status);
        this.put(CODE,this.status.getCode());
        this.put(SUCCESS,this.status.equals(ResultStatus.SUCCESS));

        this.message = Optional.ofNullable(message).orElse(this.status.getMessage());
        this.put(MESSAGE,this.message);
    }
    public String getMessage() {
        message = Optional.ofNullable(message).orElse(status.getMessage());
        return message;
    }

    public String getCode() {
        return status.getCode();
    }

    public static final String CODE = "code";
    public static final String SUCCESS = "success";
    public static final String MESSAGE = "message";

    public T getData() {
        return data;
    }

    private ResultStatus status = ResultStatus.SUCCESS;

    private String message;
    private T data;

    public R clone(){
        return JSONUtil.toBean(this,R.class);
    }
}
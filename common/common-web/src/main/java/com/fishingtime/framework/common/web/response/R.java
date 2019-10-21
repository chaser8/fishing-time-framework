package com.fishingtime.framework.common.web.response;

import com.fishingtime.framework.common.base.util.JSONUtil;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * @program: fishing-time-framework
 * @description:
 * @author:
 * @create: 2019-10-18 17:24
 **/
@Setter
@Accessors(chain = true)
public class R<T> extends LinkedHashMap<String,Object> implements Serializable {
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

    private ResultStatus status = ResultStatus.SUCCESS;

    private String message;
    private T data;



    public R clone(){
        return JSONUtil.toBean(this,R.class);
    }
}
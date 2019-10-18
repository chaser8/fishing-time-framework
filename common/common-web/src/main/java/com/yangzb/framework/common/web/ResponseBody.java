package com.yangzb.framework.common.web;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

/**
 * @program: dic-framework-v3
 * @description:
 * @author: yzb
 * @create: 2019-03-06 11:01
 **/
@Getter
@Accessors(chain = true)
public class ResponseBody<T> {
    public ResponseBody(){

    }

    public ResponseBody(T data){
        this.data=data;
    }

    public ResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseBody(String code) {
        this.code = code;
    }

    public ResponseBody setCode(String code) {
        this.code = code;
        return this;
    }

    public ResponseBody setMessage(String message) {
        this.message = message;
        return this;
    }

//    @JsonFormat(pattern = "yyyyMMddHHmmssSSS")
    protected Date timestamp = new Date();
    @Setter
    protected T data;
    protected String code = "0000";
    protected String message = "";
    protected String transactionId = UUID.randomUUID().toString();
}
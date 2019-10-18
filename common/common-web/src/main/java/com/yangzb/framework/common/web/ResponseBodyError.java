package com.yangzb.framework.common.web;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @program: dic-framework-v3
 * @description:
 * @author: yzb
 * @create: 2019-03-06 11:01
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ResponseBodyError extends ResponseBody<Void> {
    protected ResponseBodyError(){
        super();
    }
    public ResponseBodyError(String code, String message, String reason) {
        super(code,message);
        this.reason = reason;
    }

    public ResponseBodyError(String code) {
        super(code);
    }

    @Override
    public ResponseBodyError setData(Void data) {
        super.setData(data);
        return this;
    }

    @Override
    public ResponseBodyError setCode(String code) {
        super.setCode(code);
        return this;
    }

    @Override
    public ResponseBodyError setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    private String reason;
    private String referenceError;
}
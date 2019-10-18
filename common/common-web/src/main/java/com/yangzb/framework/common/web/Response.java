package com.yangzb.framework.common.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

/**
 * @program: dic-framework-v3
 * @description:
 * @author: yzb
 * @create: 2019-03-06 11:50
 **/
public class Response<T> extends org.springframework.http.ResponseEntity<T> {
    Response(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public static final HttpHeaders PROBLEM_HEADERS = new HttpHeaders() {{
        add("Content-Type", MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE);
    }};
    public static final HttpHeaders HEADERS = new HttpHeaders() {{
        add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
    }};

    public static final Object DEFAULT_RETURN_VALUE = null;

    private static Response build(HttpStatus httpStatus) {
        return Response.build(DEFAULT_RETURN_VALUE, HEADERS, httpStatus);
    }

    /**
     * sysError
     * @Description:  系统异常
     * @param responseContent
     * @return com.tydic.dev1.common.base.api.Response<com.tydic.dev1.common.base.api.ResponseBodyError>
     * @author yzb
     * @date 2019/5/30 13:54
     * @throws
     */
    public static Response<ResponseBodyError> sysError(ResponseBodyError responseContent) {
        return build(responseContent, PROBLEM_HEADERS, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     *
     * @Description: 业务异常
     * @param responseContent
     * @return com.tydic.dev1.common.base.api.Response<com.tydic.dev1.common.base.api.ResponseBodyError>
     * @author yzb
     * @date 2019/5/30 13:53
     * @throws
     */
    public static Response<ResponseBodyError> busiError(ResponseBodyError responseContent) {
        return build(responseContent, PROBLEM_HEADERS, HttpStatus.OK);
    }

    /**
     * sysError
     * @Description:  系统异常，业务开发中不要使用
     * @param responseContent
     * @param headers
     * @return com.tydic.dev1.common.base.api.Response<com.tydic.dev1.common.base.api.ResponseBodyError>
     * @author yzb
     * @date 2019/5/30 13:54
     * @throws
     */
    public static Response<ResponseBodyError> sysError(ResponseBodyError responseContent, HttpHeaders headers) {
        headers.putAll(PROBLEM_HEADERS);
        return build(responseContent, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 业务异常
     * @Description:
     * @param responseContent
     * @param headers
     * @return com.tydic.dev1.common.base.api.Response<com.tydic.dev1.common.base.api.ResponseBodyError>
     * @author yzb
     * @date 2019/5/30 13:53
     * @throws
     */
    public static Response<ResponseBodyError> busiError(ResponseBodyError responseContent, HttpHeaders headers) {
        headers.putAll(PROBLEM_HEADERS);
        return build(responseContent, headers, HttpStatus.OK);
    }

    private static <T> Response<T> build(T body, HttpStatus status) {
        return build(body, HEADERS, status);
    }

    private static <T> Response<T> build(T body, HttpHeaders headers, HttpStatus status) {
        return body == null ? new Response(DEFAULT_RETURN_VALUE, headers, status) : new Response(body, headers, status);
    }

    /**
     * 成功返回
     * @param body
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(T body) {
        return build(body, HttpStatus.OK);
    }

    /**
     * 带HttpHeaders 的成功返回
     * @Description:
     * @param body
     * @param headers
     * @return com.tydic.dev1.common.base.api.Response<T>
     * @author yzb
     * @date 2019/5/30 13:51
     * @throws
     */
    public static <T> Response<T> ok(T body, HttpHeaders headers) {
        return build(body, headers,HttpStatus.OK);
    }

    private static Response build(HttpHeaders headers, HttpStatus status) {
        return Response.build(DEFAULT_RETURN_VALUE, headers, status);
    }
    /**
     * 创建成功 
     * @Description:  
     * @param body
     * @return com.tydic.dev1.common.base.api.Response<T>
     * @author yzb
     * @date 2019/5/30 13:51
     * @throws
     */
    public static <T> Response<T> create(T body) {
        return Response.build(body,HEADERS, HttpStatus.CREATED);
    }

    /**
     * 不返回任何数据 前台获取空json "{}",比如删除成功时返回
     * @Description:  
     * @param 
     * @return com.tydic.dev1.common.base.api.Response
     * @author yzb
     * @date 2019/5/30 13:52
     * @throws
     */
    public static Response empty() {
        return Response.build(HEADERS, HttpStatus.NO_CONTENT);
    }

}


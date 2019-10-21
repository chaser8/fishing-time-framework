package com.fishingtime.framework.common.web.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @program: fishing-time-framework
 * @description:
 * @author:
 * @create: 2019-10-18 17:50
 **/
public class Response {
    public static final HttpHeaders PROBLEM_HEADERS = new HttpHeaders() {{
        add("Content-Type", MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE);
    }};
    public static final HttpHeaders HEADERS = new HttpHeaders() {{
        add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
    }};
    public static final R DEFAULT_RESPONSE_BODY_OK = new R();

    public static ResponseEntity<R> ok(ResultStatus resultStatus,String message) {
        return result(null,null,new R().setStatus(resultStatus).setMessage(message));
    }

    public static ResponseEntity<R> ok(Object responseBody) {
        return ResponseEntity.ok(new R().setData(responseBody));
    }
    public static ResponseEntity<R> ok(HttpStatus status, HttpHeaders httpHeaders,Object responseBody) {
        return result(status,httpHeaders,new R().setData(responseBody));
    }
    public static ResponseEntity<R> ok(HttpStatus status, Object responseBody) {
        return result(status,null,new R().setData(responseBody));
    }

    private static ResponseEntity<R> result(HttpStatus status,HttpHeaders httpHeaders,R responseBody) {
        status = Optional.ofNullable(status).orElse(HttpStatus.OK);
        httpHeaders = Optional.ofNullable(httpHeaders).orElse(HEADERS);
        responseBody = Optional.ofNullable(responseBody).orElse(DEFAULT_RESPONSE_BODY_OK);
        return ResponseEntity.status(status).headers(httpHeaders).body(responseBody);
    }
    public static ResponseEntity<R> fail(String message) {
        return result(HttpStatus.SERVICE_UNAVAILABLE,PROBLEM_HEADERS,new R().setMessage(message));
    }
    public static ResponseEntity<R> fail(ResultStatus resultStatus,String message) {
        return result(HttpStatus.SERVICE_UNAVAILABLE,PROBLEM_HEADERS,new R().setStatus(resultStatus).setMessage(message));
    }
    public static ResponseEntity<R> fail(HttpStatus status, String message, HttpHeaders httpHeaders,R responseBody) {
        return result(status,httpHeaders,responseBody);
    }
    public static ResponseEntity<R> fail(HttpStatus httpStatus, ResultStatus resultStatus,String message) {
        return result(httpStatus,PROBLEM_HEADERS,new R().setMessage(message).setStatus(resultStatus));
    }
    public static ResponseEntity<R> fail(HttpStatus httpStatus, ResultStatus resultStatus) {
        return result(httpStatus,PROBLEM_HEADERS,new R().setStatus(resultStatus));
    }
    public static ResponseEntity<R> fail(HttpStatus status, String message) {
        return result(status,PROBLEM_HEADERS,new R().setMessage(message).setStatus(ResultStatus.SYSTEM_ERROR));
    }
    public static ResponseEntity<R> fail(Object responseBody) {
        return result(HttpStatus.SERVICE_UNAVAILABLE,PROBLEM_HEADERS,new R().setData(responseBody));
    }
    public static ResponseEntity<R> fail(ResultStatus resultStatus) {
        return result(HttpStatus.SERVICE_UNAVAILABLE,PROBLEM_HEADERS,new R().setStatus(resultStatus));
    }
}

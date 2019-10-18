package com.yangzb.framework.common.web;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-04-25 19:31
 **/
public class Constants {
    /**
     * 未知异常
     */
    public static final ResponseBody SUCCESS_0000 = new ResponseBody();


    /**
     * 未知异常
     */
    public static final ResponseBodyError ERROR_9999 = new ResponseBodyError("9999").setMessage("未知异常").setReason("未知异常");
    /**
     * 内容不存在，一般用于详情查询未查询到数据
     */
    public static final ResponseBodyError ERROR_9998 = new ResponseBodyError("9998").setMessage("内容不存在").setReason("内容不存在");
    /**
     * 参数校验失败
     */
    public static final ResponseBodyError ERROR_9997= new ResponseBodyError("9997","入参校验失败","请检查入参");

    /**
     * 权限认证异常
     */
    public static final ResponseBodyError ERROR_8000 = new ResponseBodyError("8000","认证失败！","认证失败！");

    /**
     * 权限认证异常
     */
    public static final ResponseBodyError ERROR_8001 = new ResponseBodyError("8001","您无权进行本操作！","您无权进行本操作！");

    /**
     * 服务调用异常
     */
    public static final ResponseBodyError ERROR_7000 = new ResponseBodyError("7000","服务调用异常","服务调用异常");



    public static final String HTTP_REQUEST_HEADER_OPENAPI = "openapi";
}

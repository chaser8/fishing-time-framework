package com.fishingtime.framework.springboot.web.core;

import cn.hutool.core.date.format.FastDateFormat;

/**
 * @program:
 * @description:

 * @create: 2019-09-09 10:15
 **/
public class Consts {
    public final static FastDateFormat MM_DATE_FORMAT = FastDateFormat.getInstance("MM");
    public static final String API_CALL_RECORD = "apiCallRecord";
    public static final String CREATE_DATETIME = "createDatetime";
    public static final String CONNECTION = "connection";
    public static final String  RESPONSE_BODY= "responseBody";
}

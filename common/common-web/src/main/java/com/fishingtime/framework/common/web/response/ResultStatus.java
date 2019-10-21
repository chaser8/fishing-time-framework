package com.fishingtime.framework.common.web.response;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @program:
 * @description:
 * @create: 2019-08-26 17:14
 **/
@Getter
@Accessors(chain = true)
public class ResultStatus {
    public static ResultStatus SUCCESS = new ResultStatus("0000", "成功");

    public static ResultStatus PARAM_ERROR = new ResultStatus("1001", "无效的请求，请求参数不正确");
    public static ResultStatus NOT_EXIST_ERROR = new ResultStatus("1002", "数据不存在");
    public static ResultStatus DATA_REPEAT_ERROR = new ResultStatus("1003", "数据重复");

    public static ResultStatus SIGN_ERROR = new ResultStatus("8000", "签名错误");
    public static ResultStatus FORBIDDEN_ERROR = new ResultStatus("8001", "访问拒绝");
    public static ResultStatus AUTH_ERROR = new ResultStatus("8001", "认证错误");

    public static ResultStatus SYSTEM_ERROR = new ResultStatus("9000", "系统错误");
    public static ResultStatus THIRD_INTF_ERROR = new ResultStatus("9001", "三方接口错误");
    public static ResultStatus INTF_ERROR = new ResultStatus("9002", "调用接口错误");

    private String code;
    private String message;

    private ResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

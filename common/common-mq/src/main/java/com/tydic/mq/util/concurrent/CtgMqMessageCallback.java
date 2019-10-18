package com.tydic.mq.util.concurrent;

import com.ctg.mq.api.bean.MQSendCallback;
import com.ctg.mq.api.bean.MQSendResult;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
public interface CtgMqMessageCallback {
    public void success(MQSendResult mqSendResult);
    public void fail(Throwable throwable);
}

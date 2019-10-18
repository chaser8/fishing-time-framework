package com.tydic.mq.api.ctgmq.producer;
import com.ctg.mq.api.bean.MQSendResult;
/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
public interface ProducerCallback {
    public void success(MQSendResult mqSendResult);
    public void fail(Throwable throwable);
}
package com.tydic.dev1.common.mq.api.producer;

import com.tydic.mq.util.concurrent.FailureCallback;
import com.tydic.mq.util.concurrent.SuccessCallback;
import com.tydic.mq.util.support.MqSendResult;

/**
 * @program: framework
 * @description:
 * @author: yzb
 * @create: 2019-08-07 15:27
 **/
public interface Producer {
    /**
     *
     * 发送异步消息
     * @author guomenghao
     * @date 2019-07-08
     * @return void
     */
    public <K,V>void sendAsyncMessage(String topic, Integer partition, Long timestamp, K key, V data, SuccessCallback<MqSendResult> successCallback, FailureCallback failureCallback);

    /**
     *  发送同步消息
     *
     * @author guomenghao
     * @date 2019-07-08
     * @return void
     */
    public <K,V> MqSendResult sendsyncMessage(String topic, Integer partition, Long timestamp, K key, V data);

    public void start();

    public void close();
}
package com.tydic.mq.service;


import com.tydic.mq.util.concurrent.FailureCallback;
import com.tydic.mq.util.concurrent.SuccessCallback;
import com.tydic.mq.util.support.MqSendResult;


/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */

public interface MqProductTemplate {

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

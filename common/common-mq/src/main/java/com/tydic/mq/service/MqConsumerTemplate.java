package com.tydic.mq.service;

import com.tydic.mq.util.concurrent.EventCallback;
import com.tydic.mq.util.support.MessageEvent;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
public interface MqConsumerTemplate {


    public void consumerMessage(String topic,EventCallback eventEventCallback);

    public void start();


    public void close();

    public void offset(Object object);
}

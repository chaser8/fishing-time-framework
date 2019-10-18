package com.tydic.mq.api.kafka.consumer;

import com.tydic.mq.util.concurrent.EventCallback;
import com.tydic.mq.util.support.MessageEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-26.
 */
public class ConsumerWorker implements Runnable {

    private ConsumerRecord<String, String> consumerRecord;
    private EventCallback eventEventCallback;

    public ConsumerWorker(ConsumerRecord record, EventCallback eventEventCallback) {
        this.consumerRecord = record;
        this.eventEventCallback = eventEventCallback;
    }

    @Override
    public void run() {

        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setKey(consumerRecord.key());
        messageEvent.setOffset(consumerRecord.offset());
        messageEvent.setPartition(consumerRecord.partition());
        messageEvent.setTimestamp(consumerRecord.timestamp());
        messageEvent.setTopic(consumerRecord.topic());
        messageEvent.setValue(consumerRecord.value());
        eventEventCallback.onEvent(messageEvent);
    }
}
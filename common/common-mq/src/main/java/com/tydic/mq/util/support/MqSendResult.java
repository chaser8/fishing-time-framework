package com.tydic.mq.util.support;

import lombok.Data;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
@Data
public class MqSendResult<K, V> {
    private final ProducerRecord<K, V> producerRecord;

    private final RecordMetadata recordMetadata;

    private String messageID;

    public MqSendResult(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata,String messageID) {
        this.producerRecord = producerRecord;
        this.recordMetadata = recordMetadata;
        this.messageID = messageID;
    }

    public ProducerRecord<K, V> getProducerRecord() {
        return this.producerRecord;
    }

    public RecordMetadata getRecordMetadata() {
        return this.recordMetadata;
    }

    @Override
    public String toString() {
        return "SendResult [producerRecord=" + this.producerRecord + ", recordMetadata=" + this.recordMetadata + "]";
    }
}

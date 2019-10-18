package com.tydic.mq.api.kafka.producer;

import com.tydic.mq.configure.KafkaProductConfiguration;
import com.tydic.mq.service.MqProductTemplate;
import com.tydic.mq.util.concurrent.FailureCallback;
import com.tydic.mq.util.concurrent.SuccessCallback;
import com.tydic.mq.util.support.MqSendResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-08.
 */
@Slf4j
public class KafkaProducer implements MqProductTemplate {

    private static ProducerFactory producerFactory;

    private KafkaTemplate kafkaTemplate;

    public KafkaProducer(KafkaProductConfiguration kafkaProductConfiguration) {

        super();
        if (producerFactory == null) {
            log.error("kafka producerFactory is null");
        }else {
            this.kafkaTemplate = new KafkaTemplate<String, String>(producerFactory);
        }

    }

    public static int createProducerFactory(KafkaProductConfiguration kafkaProductConfiguration){
        producerFactory = producerFactory(kafkaProductConfiguration);

        if (producerFactory!=null) {
            return 1;
        }else {
            return 0;
        }
    }



    private static ProducerFactory<String, String> producerFactory(KafkaProductConfiguration kafkaProductConfiguration) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProductConfiguration.getBootstrapServer());
        props.put(ProducerConfig.ACKS_CONFIG,"-1");
        props.put("acks", kafkaProductConfiguration.getAcks());
        props.put("retries", kafkaProductConfiguration.getRetries());
        props.put("batch.size", kafkaProductConfiguration.getBatchSize());
        props.put("linger.ms", kafkaProductConfiguration.getLingerMs());
        props.put("buffer.memory", kafkaProductConfiguration.getBufferMemory());
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return new DefaultKafkaProducerFactory<String,String>(props);
    }

    @Override
    public <K, V> void sendAsyncMessage(String topic, Integer partition, Long timestamp, K key, V data, SuccessCallback<MqSendResult> successCallback, FailureCallback failureCallback) {
        ListenableFuture<SendResult> future  = this.kafkaTemplate.send(topic,partition,timestamp,key,data);
        future.addCallback(new ListenableFutureCallback<SendResult>() {

            @Override
            public void onSuccess(final SendResult message) {
                MqSendResult mqSendResult = new MqSendResult(message.getProducerRecord(),message.getRecordMetadata(),"");
                successCallback.onSuccess(mqSendResult);
                log.debug("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                failureCallback.onFailure(throwable);
                log.error("unable to send message" , throwable);
            }
        });
    }

    @Override
    public <K, V> MqSendResult sendsyncMessage(String topic, Integer partition, Long timestamp, K key, V data) {
        ListenableFuture<SendResult> future  = this.kafkaTemplate.send(topic,partition,timestamp,key,data);
        MqSendResult mqSendResult = null;
        try {
            SendResult sendResult = future.get();
            mqSendResult = new MqSendResult(sendResult.getProducerRecord(),sendResult.getRecordMetadata(),"");
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("kafka sync message fail",e);
        } catch (ExecutionException e) {
            e.printStackTrace();
            log.error("kafka sync message fail",e);
        }finally {
            return mqSendResult;
        }

    }

    @Override
    public void start() {

    }

    @Override
    public void close() {

        this.kafkaTemplate = null;
    }
}

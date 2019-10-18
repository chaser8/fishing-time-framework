package com.tydic.mq.api.kafka.consumer;

import com.tydic.mq.configure.KafkaConsumerConfiguration;
import com.tydic.mq.service.MqConsumerTemplate;
import com.tydic.mq.util.concurrent.EventCallback;
import com.tydic.mq.util.support.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-26.
 */
@Slf4j
public class ConsumerHandler implements MqConsumerTemplate {
    private final KafkaConsumer<String, String> consumer;
    private ExecutorService executors;
    private  EventCallback eventEventCallback;
    private boolean enableAutoCommit;

    public ConsumerHandler(KafkaConsumerConfiguration kafkaConsumerConfiguration){
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaConsumerConfiguration.getBrokerList());
        props.put("group.id", kafkaConsumerConfiguration.getGroupId());
        props.put("enable.auto.commit", kafkaConsumerConfiguration.getEnableAutoCommit());
        this.enableAutoCommit = Boolean.getBoolean(kafkaConsumerConfiguration.getEnableAutoCommit());
        props.put("auto.commit.interval.ms", kafkaConsumerConfiguration.getAutoCommitInterval());
        props.put("session.timeout.ms", kafkaConsumerConfiguration.getSessionTimeout());
        props.put("key.deserializer", kafkaConsumerConfiguration.getKeySerializer());
        props.put("value.deserializer", kafkaConsumerConfiguration.getValueSerializer());
        consumer = new KafkaConsumer<String, String>(props);

    }

    public void execute(int workerNum) {
        executors = new ThreadPoolExecutor(workerNum, workerNum, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
        Thread t = new Thread(new Runnable(){//启动一个子线程来监听kafka消息
            @Override
            public void run(){
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(200);
                    for (final ConsumerRecord record : records) {
                        log.debug("receive message :",record.value(),record.offset(),record.key());
                        executors.submit(new ConsumerWorker(record,eventEventCallback));
                    }
                }
            }});
        t.start();

    }

    @Override
    public void consumerMessage(String topic,EventCallback eventEventCallback) {

        this.eventEventCallback = eventEventCallback;
        List topics = new ArrayList();
        topics.add(topic);
        consumer.subscribe(topics);

    }

    @Override
    public void start() {
        execute(1);

    }

    private void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
        if (executors != null) {
            executors.shutdown();
        }
        try {
            if (!executors.awaitTermination(10, TimeUnit.SECONDS)) {
                log.info("Timeout.... Ignore for this case");
            }
        } catch (InterruptedException e) {
            log.error("Other thread interrupted this shutdown, ignore for this case.",e);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        shutdown();
    }

    @Override
    public void offset(Object object) {

        if (this.enableAutoCommit == false) {

            consumer.commitSync();
        }
    }
}

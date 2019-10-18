package com.tydic.mq.api.ctgmq.producer;

import com.ctg.mq.api.CTGMQFactory;
import com.ctg.mq.api.IMQProducer;
import com.ctg.mq.api.PropertyKeyConst;
import com.ctg.mq.api.bean.MQMessage;
import com.ctg.mq.api.bean.MQSendCallback;
import com.ctg.mq.api.bean.MQSendResult;
import com.ctg.mq.api.exception.MQException;
import com.tydic.mq.configure.CtgMqProductConfiguration;
import com.tydic.mq.service.MqProductTemplate;
import com.tydic.mq.util.concurrent.FailureCallback;
import com.tydic.mq.util.concurrent.SuccessCallback;
import com.tydic.mq.util.support.MqSendResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Properties;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
@Slf4j
public class CtgMqProducer implements MqProductTemplate {
    private IMQProducer mqProducer;


    public CtgMqProducer(CtgMqProductConfiguration ctgMqProductConfiguration) {

        super();
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.ProducerGroupName, ctgMqProductConfiguration.getProducerGroupName());
        properties.setProperty(PropertyKeyConst.InstanceName, ctgMqProductConfiguration.getInstanceName());
        properties.setProperty(PropertyKeyConst.NamesrvAddr, ctgMqProductConfiguration.getNamesrvAddr());
        properties.setProperty(PropertyKeyConst.NamesrvAuthID, ctgMqProductConfiguration.getAuthId());
        properties.setProperty(PropertyKeyConst.NamesrvAuthPwd, ctgMqProductConfiguration.getAuthPwd());
        properties.setProperty(PropertyKeyConst.MaxMessageSize, ctgMqProductConfiguration.getMaxMessageSize());
        properties.setProperty(PropertyKeyConst.SendMaxRetryTimes, ctgMqProductConfiguration.getSendMaxRetryTimes());
        properties.setProperty(PropertyKeyConst.SendMsgTimeout, ctgMqProductConfiguration.getSendMsgTimeout());
        properties.setProperty(PropertyKeyConst.CompressMsgBodyOverHowmuch, ctgMqProductConfiguration.getCompressMsgBodyOverHowmuch());
        properties.setProperty(PropertyKeyConst.ClusterName, ctgMqProductConfiguration.getClusterName());
        properties.setProperty(PropertyKeyConst.TenantID, ctgMqProductConfiguration.getTenantID());
        if (properties != null && properties.size() >0 ) {
             mqProducer = CTGMQFactory.createProducer(properties);

        }else {
            log.error("fail create mqProducer,props is null");

        }
    }


    @Override
    public <K, V> void sendAsyncMessage(String topic, Integer partition, Long timestamp, K key, V data, SuccessCallback<MqSendResult> successCallback, FailureCallback failureCallback) {
        ProducerRecord<K, V> producerRecord = new ProducerRecord<>(topic,partition,timestamp,key,data);

        try {

            /**
             * 发送消息
             */
            MQMessage message = new MQMessage(
                    //topic或者queue的名字
                    producerRecord.topic(),
                    //key字段,一般为业务的关键词，可以将其设置成唯一的标识字段，如订单ID
                    producerRecord.key() == null ? "10000":producerRecord.key().toString(),
                    //标签字段，方便过滤
                    "tag",
                    //消息体
                    producerRecord.value().toString().getBytes(),
                    //发往指定分区的关键词，可以为空，如果设置将发往其hash值的分区
                    producerRecord.partition());

            mqProducer.sendAsync(message, new Callback(successCallback,failureCallback));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <K, V> MqSendResult sendsyncMessage(String topic, Integer partition, Long timestamp, K key, V data) {
        ProducerRecord<K, V> producerRecord = new ProducerRecord<>(topic,partition,timestamp,key,data);

        /**
         * 发送消息
         */
        MQMessage message = new MQMessage(
                //topic或者queue的名字
                producerRecord.topic(),
                //key字段,一般为业务的关键词，可以将其设置成唯一的标识字段，如订单ID
                producerRecord.key() == null ? "10000":producerRecord.key().toString(),
                //标签字段，方便过滤
                "tag",
                //消息体
                producerRecord.value().toString().getBytes(),
                //发往指定分区的关键词，可以为空，如果设置将发往其hash值的分区
                producerRecord.partition());
        MQSendResult sendResult = null;
        try {
            sendResult = mqProducer.send(message);
            log.debug("success push : "+sendResult.getMessageID());

        } catch (MQException e) {
            e.printStackTrace();
            log.error("ProducerTest.send exception, desc: ",e);
        }

        TopicPartition topicPartition = new TopicPartition(sendResult.getMessageQueue().getTopic(),sendResult.getMessageQueue().getQueueId());
        RecordMetadata recordMetadata = new RecordMetadata(topicPartition,sendResult.getQueueOffset(),sendResult.getQueueOffset());

        MqSendResult mqSendResult = new MqSendResult(producerRecord,recordMetadata,sendResult.getMessageID());
        return mqSendResult;
    }

    @Override
    public void start(){
        int result = -1;
        try {
            result = mqProducer.connect();

        } catch (MQException e) {
            // Process connect exception
            e.printStackTrace();
            log.error("Producer.connect exception, desc: ",e);
        }

        if (result != 0) {
            // Process connect error
            log.error("Producer.connect fail return: " + result);
        }

    }

    @Override
    public void close() {
        try {
            mqProducer.close();
        } catch (MQException e) {
            e.printStackTrace();
            log.error("Producer.close fail return: " ,e);
        }
    }

    class Callback implements MQSendCallback {
        private SuccessCallback successCallback;
        private FailureCallback failureCallback;
        private long startTime;
        public Callback(SuccessCallback successCallback,FailureCallback failureCallback) {
            this.startTime = System.currentTimeMillis();
            this.successCallback = successCallback;
            this.failureCallback = failureCallback;
        }
        @Override
        public void onSuccess(MQSendResult mqSendResult) {
             TopicPartition topicPartition = new TopicPartition(mqSendResult.getMessageQueue().getTopic(),mqSendResult.getMessageQueue().getQueueId());
             RecordMetadata recordMetadata = new RecordMetadata(topicPartition,mqSendResult.getQueueOffset(),mqSendResult.getQueueOffset());
             ProducerRecord producerRecord = new ProducerRecord<>(mqSendResult.getMessageQueue().getTopic(),mqSendResult.getMessageQueue().getQueueId(),null,null,null);
             MqSendResult SendResult = new MqSendResult(producerRecord,recordMetadata,mqSendResult.getMessageID());
             this.successCallback.onSuccess(SendResult);
        }

        @Override
        public void onException(Throwable throwable) {

            this.failureCallback.onFailure(throwable);
        }
    }

}

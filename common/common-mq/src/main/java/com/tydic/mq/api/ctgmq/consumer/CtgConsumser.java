package com.tydic.mq.api.ctgmq.consumer;

import cn.hutool.json.JSONUtil;
import com.ctg.mq.api.*;
import com.ctg.mq.api.bean.MQMessageTimeoutStrategy;
import com.ctg.mq.api.bean.MQResult;
import com.ctg.mq.api.exception.MQAckException;
import com.ctg.mq.api.exception.MQException;
import com.ctg.mq.api.listener.ConsumeListener;
import com.ctg.mq.api.listener.ConsumerQueueListener;
import com.ctg.mq.api.listener.ConsumerQueueStatus;
import com.tydic.mq.configure.CtgMqConsumerConfiguration;
import com.tydic.mq.service.MqConsumerTemplate;
import com.tydic.mq.util.concurrent.EventCallback;
import com.tydic.mq.util.support.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

import java.util.List;
import java.util.Properties;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-26.
 */
@Slf4j
public class CtgConsumser<K, V> implements MqConsumerTemplate {

    private IMQBDBPushConsumer imqbdbPushConsumer = null;
    private IMQPushConsumer consumer = null;

    public CtgConsumser(CtgMqConsumerConfiguration ctgMqConsumerConfiguration) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.ConsumerGroupName, ctgMqConsumerConfiguration.getConsumerGroupId());
        properties.setProperty(PropertyKeyConst.InstanceName, Long.toString(System.currentTimeMillis()));
        properties.setProperty(PropertyKeyConst.NamesrvAddr, ctgMqConsumerConfiguration.getBootstrapServers());
        properties.setProperty(PropertyKeyConst.NamesrvAuthID, ctgMqConsumerConfiguration.getUsername());
        properties.setProperty(PropertyKeyConst.NamesrvAuthPwd, ctgMqConsumerConfiguration.getPassword());
        properties.setProperty(PropertyKeyConst.ClusterName, ctgMqConsumerConfiguration.getClustername());
        properties.setProperty(PropertyKeyConst.TenantID, ctgMqConsumerConfiguration.getTenantid());
        properties.setProperty(PropertyKeyConst.TimeoutStrategy, MQMessageTimeoutStrategy.ALARM_AND_SKIP.name());
        properties.setProperty(PropertyKeyConst.ConsumeTimeoutInSec, String.valueOf(3));
        properties.setProperty(PropertyKeyConst.ConsumeOrderly, "true");
        properties.setProperty(PropertyKeyConst.ConsumeMessageBatchMaxSize, String.valueOf(1));
        properties.setProperty(PropertyKeyConst.ConsumeFromWhere, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET.name());

        properties.setProperty(PropertyKeyConst.ConsumeOrderly, "true");//有序消费必须
        if (ctgMqConsumerConfiguration.getEnableBdb()) {
            imqbdbPushConsumer = CTGMQFactory.createBDBPushConsumer(properties);
            try {
                int connect = imqbdbPushConsumer.connect();
                if (connect != 0) {
                    throw new RuntimeException("连接失败:" + JSONUtil.toJsonStr(properties));
                }
            } catch (MQException e) {
                throw new RuntimeException(e);
            }
        } else {
            consumer = CTGMQFactory.createPushConsumer(properties);
            try {
                int connect = consumer.connect();
                if (connect != 0) {
                    throw new RuntimeException("连接失败:" + JSONUtil.toJsonStr(properties));
                }
            } catch (MQException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void consumerMessage(String topic, EventCallback eventEventCallback) {
        if (imqbdbPushConsumer != null) {
            try {
                imqbdbPushConsumer.listenQueue(topic, null, new ConsumeListener() {
                    @Override
                    public void onMessage(List<MQResult> list, IMQAckHandler imqAckHandler) {
                        for (MQResult result : list) {
                            MessageEvent messageEvent = new MessageEvent();
                            messageEvent.setKey(result.getMessage().getKey());
                            messageEvent.setValue(result.getMessage().getBody());
                            messageEvent.setTopic(result.getMessage().getSourceName());
                            messageEvent.setTimestamp(result.getMessage().getBornTimestamp());
                            messageEvent.setPartition(result.getMessage().getQueueId());
                            messageEvent.setOffset(result.getMessage().getCommitLogOffset());
                            eventEventCallback.onEvent(messageEvent);
                            try {
                                imqAckHandler.ackMessageSuccess(result);//业务成功，签收成功
                            } catch (MQAckException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
            } catch (MQException e) {
                e.printStackTrace();
                log.error("message consumer fail", e);
            }

        } else if (consumer != null) {
            try {
                consumer.listenQueue(topic, null, new ConsumerQueueListener() {
                    @Override
                    public ConsumerQueueStatus onMessage(List<MQResult> list) {
                        for (MQResult result : list) {
                            MessageEvent messageEvent = new MessageEvent();
                            messageEvent.setKey(result.getMessage().getKey());
                            messageEvent.setValue(result.getMessage().getBody());
                            messageEvent.setTopic(result.getMessage().getSourceName());
                            messageEvent.setTimestamp(result.getMessage().getBornTimestamp());
                            messageEvent.setPartition(result.getMessage().getQueueId());
                            messageEvent.setOffset(result.getMessage().getCommitLogOffset());
                            eventEventCallback.onEvent(messageEvent);
                        }
                        return ConsumerQueueStatus.SUCCESS;
                    }
                });
            } catch (MQException e) {
                e.printStackTrace();
                log.error("message consumer fail", e);
            }
        }


    }

    @Override
    public void start() {
        try {
            int connect = -1;
            if (imqbdbPushConsumer != null) {
                connect = imqbdbPushConsumer.connect();
            } else if (consumer != null) {
                connect = consumer.connect();
            }
            if (connect != 0) {
                throw new RuntimeException("连接失败");
            }
        } catch (MQException e) {
            e.printStackTrace();
            log.error("consumer connect fail", e);
        }
    }

    @Override
    public void close() {

        if (imqbdbPushConsumer != null) {

            try {
                imqbdbPushConsumer.close();
            } catch (MQException e) {
                e.printStackTrace();
            }

        } else if (consumer != null) {
            try {
                consumer.close();
            } catch (MQException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void offset(Object object) {
        MQResult result = (MQResult) object;
        if (imqbdbPushConsumer != null) {
            try {
                imqbdbPushConsumer.ackMessageSuccess(result);
            } catch (MQAckException e) {
                e.printStackTrace();
            }
        }
    }
}

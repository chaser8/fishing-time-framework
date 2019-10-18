package com.tydic.mq.service;

import com.tydic.mq.api.ctgmq.consumer.CtgConsumser;
import com.tydic.mq.api.kafka.consumer.ConsumerHandler;
import com.tydic.mq.configure.CtgMqConsumerConfiguration;
import com.tydic.mq.configure.KafkaConsumerConfiguration;
import com.tydic.mq.configure.MqConsumerConfiguration;
import com.tydic.mq.util.support.EntityUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-29.
 */
@Slf4j
public class MqConsumerFactory {

    private CtgMqConsumerConfiguration ctgMqConsumerConfiguration;
    private KafkaConsumerConfiguration kafkaConsumerConfiguration;
    private String type;
    public MqConsumerFactory(MqConsumerConfiguration mqConsumerConfiguration){
        super();
        this.type = mqConsumerConfiguration.getType();
        if ("ctg".equals(type)) {
            this.ctgMqConsumerConfiguration = EntityUtils.copyData(mqConsumerConfiguration,CtgMqConsumerConfiguration.class);
        }else if ("kafka".equals(type)) {
            this.kafkaConsumerConfiguration = EntityUtils.copyData(mqConsumerConfiguration,KafkaConsumerConfiguration.class);
        } else {
            log.error("this mq type is not ture : "+ mqConsumerConfiguration.getType());
        }
    }

    public MqConsumerTemplate createComsumer(){
        if ("ctg".equals(type)) {

            return new CtgConsumser<>(this.ctgMqConsumerConfiguration);


        }else if ("kafka".equals(type)) {
            return new ConsumerHandler(this.kafkaConsumerConfiguration);
        }else {
            log.error("MqProductTemplate is create fail ");
            return null;
        }
    }
}

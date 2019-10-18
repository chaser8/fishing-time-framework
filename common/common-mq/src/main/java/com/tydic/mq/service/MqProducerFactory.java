package com.tydic.mq.service;

import com.tydic.mq.api.ctgmq.producer.CtgMqProducer;
import com.tydic.mq.api.kafka.producer.KafkaProducer;
import com.tydic.mq.configure.CtgMqProductConfiguration;
import com.tydic.mq.configure.KafkaProductConfiguration;
import com.tydic.mq.configure.MqProductConfiguration;
import com.tydic.mq.util.support.EntityUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-08.
 */
@Slf4j
public class MqProducerFactory {

    private CtgMqProductConfiguration ctgMqProductConfiguration;
    private KafkaProductConfiguration kafkaProductConfiguration;
    private String type;

    public MqProducerFactory(MqProductConfiguration mqProductConfiguration){
        super();
        this.type = mqProductConfiguration.getType();
        if ("ctg".equals(type)) {

            this.ctgMqProductConfiguration = EntityUtils.copyData(mqProductConfiguration,CtgMqProductConfiguration.class);

        }else if ("kafka".equals(type)) {
            this.kafkaProductConfiguration = EntityUtils.copyData(mqProductConfiguration,KafkaProductConfiguration.class);
            KafkaProducer.createProducerFactory(this.kafkaProductConfiguration);
        }else {

            log.error("this mq type is not ture : "+ mqProductConfiguration.getType());
        }
    }

    public MqProductTemplate createProducer(){
        if ("ctg".equals(type)) {

            return new CtgMqProducer(this.ctgMqProductConfiguration);


        }else if ("kafka".equals(type)) {
            return new KafkaProducer(this.kafkaProductConfiguration);
        }else {
            log.error("MqProductTemplate is create fail ");
            return null;
        }
    }
}

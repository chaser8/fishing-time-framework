package com.tydic.mq.autoconfigure;

import com.tydic.mq.service.MqConsumerFactory;
import com.tydic.mq.service.MqConsumerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-29.
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(MqConsumerProperties.class)
@ConditionalOnClass(MqConsumerTemplate.class)
public class MqConsumerAutoConfiguration {
    @Bean
    @Autowired
    public MqConsumerFactory mqConsumerFactory(MqConsumerProperties mqConsumerProperties) {
        return new MqConsumerFactory(mqConsumerProperties);
    }

    @Bean
    @ConditionalOnMissingBean(MqConsumerTemplate.class)
    public MqConsumerTemplate  mqConsumerTemplate(@Autowired MqConsumerFactory mqConsumerFactory) {
        return mqConsumerFactory.createComsumer();
    }
}

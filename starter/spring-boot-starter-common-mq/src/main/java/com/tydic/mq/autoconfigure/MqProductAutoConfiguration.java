package com.tydic.mq.autoconfigure;

import com.tydic.mq.service.MqProducerFactory;
import com.tydic.mq.service.MqProductTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-08.
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(MqProducetProperties.class)
@ConditionalOnClass(MqProductTemplate.class)
public class MqProductAutoConfiguration {
    @Bean
    @Autowired
    public MqProducerFactory mqProducerFactory(MqProducetProperties mqProducetProperties) {

        return new MqProducerFactory(mqProducetProperties);
    }

    @Bean
    @ConditionalOnMissingBean(MqProductTemplate.class)
    public MqProductTemplate mqProductTemplate(@Autowired MqProducerFactory mqProducerFactory) {
        return mqProducerFactory.createProducer();
    }
}

package com.tydic.dev1.spring.mq.example;

import com.tydic.dev1.common.base.util.SpringBeanHelper;
import com.tydic.mq.api.ctgmq.consumer.CtgConsumser;
import com.tydic.mq.service.MqConsumerFactory;
import com.tydic.mq.service.MqConsumerTemplate;
import com.tydic.mq.service.MqProductTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.UnsupportedEncodingException;

/**
 * @program: framework
 * @description:
 * @author: yzb
 * @create: 2019-08-06 11:29
 **/
@SpringBootApplication
@Slf4j
public class MQApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(MQApplication.class, args);
        MqConsumerTemplate bean = run.getBean(MqConsumerTemplate.class);
        bean.consumerMessage("yzb_test_2x", event -> {
            try {
                log.info("" + event.hashCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

//    @Autowired
//    MqConsumerTemplate ctgConsumser;

//    @Override
//    public void run(String... args) throws Exception {
//
//    }
}
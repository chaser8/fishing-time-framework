package com.tydic.mq.autoconfigure;

import com.tydic.mq.configure.MqConsumerConfiguration;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-29.
 */
@ConfigurationProperties(prefix="spring.data.mq.consumer")
@Getter
@Setter
public class MqConsumerProperties extends MqConsumerConfiguration {

    /**
     *
     * 消息队列类型 ctg kafka
     * @author guomenghao
     * @date 2019-07-08
     * @param
     * @return
     */
    private String type;

    //ctg
    private String consumerGroupId;
    private String bootstrapServers;
    private String username;
    private String password;
    private String clustername;
    private String tenantid;
    /**
     * 是否开启强制一致性消费
     * */
    private Boolean enableBdb;

    //kafka
    private String brokerList;

    private String groupId;

    private  String enableAutoCommit="true";

    private String autoCommitInterval="1000";

    private String sessionTimeout="30000";

    private String keySerializer="org.apache.kafka.common.serialization.StringDeserializer";

    private String valueSerializer="org.apache.kafka.common.serialization.StringDeserializer";
}

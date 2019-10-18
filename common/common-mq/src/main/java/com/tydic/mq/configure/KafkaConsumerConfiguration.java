package com.tydic.mq.configure;

import com.tydic.mq.api.kafka.consumer.MessageContainer;
import lombok.Data;

import java.util.List;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-26.
 */
@Data
public class KafkaConsumerConfiguration {
    private String brokerList;

    private String groupId;

    private  String enableAutoCommit="true";

    private String autoCommitInterval="1000";

    private String sessionTimeout="30000";

    private String keySerializer="org.apache.kafka.common.serialization.StringDeserializer";

    private String valueSerializer="org.apache.kafka.common.serialization.StringDeserializer";

}

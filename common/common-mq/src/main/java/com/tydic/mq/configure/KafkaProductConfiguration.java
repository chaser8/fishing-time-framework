package com.tydic.mq.configure;

import lombok.Data;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
@Data
public class KafkaProductConfiguration {

    private String bootstrapServer;

    private String acks;

    private String groupId="0";

    private String retries="10";

    private String batchSize="16384";

    private String lingerMs="1";

    private String bufferMemory = "33554432";


}

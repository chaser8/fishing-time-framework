package com.tydic.mq.configure;

import lombok.Data;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-08.
 */
@Data
public class MqProductConfiguration {

    /**
     *
     * 消息队列类型 ctg kafka
     * @author guomenghao
     * @date 2019-07-08
     * @param
     * @return
     */
    private String type;

//    ctg-mq

    private String producerGroupName;

    private String instanceName;

    private String namesrvAddr;

    private String authId;

    private String authPwd;

    private String maxMessageSize = "5242880";

    private String sendMaxRetryTimes = "5";

    private String sendMsgTimeout = String.valueOf(3000);

    private String compressMsgBodyOverHowmuch = String.valueOf(4096);

    private String clusterName;

    private String tenantID;

//    kafka

    private String bootstrapServer;

    private String acks;

    private String groupId="0";

    private String retries="10";

    private String batchSize="16384";

    private String lingerMs="1";

    private String bufferMemory = "33554432";
}

package com.tydic.mq.configure;

import lombok.Data;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
@Data
public class CtgMqProductConfiguration {

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


}

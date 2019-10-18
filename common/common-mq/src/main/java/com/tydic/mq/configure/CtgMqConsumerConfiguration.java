package com.tydic.mq.configure;

import lombok.Data;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-26.
 */
@Data
public class CtgMqConsumerConfiguration {
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
}

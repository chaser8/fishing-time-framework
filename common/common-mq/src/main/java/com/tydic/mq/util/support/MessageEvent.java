package com.tydic.mq.util.support;

import lombok.Data;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
@Data
public class MessageEvent<K, V> {
    private  K key;
    private  V value;
    private String topic;
    private int partition;
    private long offset;
    private long timestamp;
}

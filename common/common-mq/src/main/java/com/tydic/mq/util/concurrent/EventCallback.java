package com.tydic.mq.util.concurrent;

import com.tydic.mq.util.support.MessageEvent;

import java.io.UnsupportedEncodingException;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
public interface EventCallback {

    void onEvent(MessageEvent event);
}

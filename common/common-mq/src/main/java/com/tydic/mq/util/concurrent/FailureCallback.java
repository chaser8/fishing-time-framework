package com.tydic.mq.util.concurrent;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
public interface FailureCallback {
    void onFailure(Throwable ex);
}

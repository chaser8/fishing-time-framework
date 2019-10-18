package com.tydic.mq.util.concurrent;

import org.springframework.lang.Nullable;

/**
 * @auther guomenghao.
 * Created by guomenghao on 2019-07-05.
 */
public interface SuccessCallback<T> {

    void onSuccess(@Nullable T result);

}

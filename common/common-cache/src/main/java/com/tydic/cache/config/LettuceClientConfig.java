package com.tydic.cache.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by guomenghao on 2018/8/30.
 */
@Setter
@Getter
@Accessors(chain = true)
public class LettuceClientConfig extends CacheClientConfig{
    public LettuceClientConfig(){
        this.setMode(CacheMode.LETTUCE);
    }
    /**
     *必填 IP和端口
     */
    private String ipPort;
    /**
     *密码
     */
    private String password;
}

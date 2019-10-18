package com.tydic.cache.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: framework
 * @description:
 * @author: yzb
 * @create: 2019-06-10 17:18
 **/
@Getter
@Setter
public class HostAndPort {
    public HostAndPort() {
    }
    public HostAndPort(String host, int port) {
        this.host=host;
        this.port=port;
    }
    private String host;
    private int port;
}

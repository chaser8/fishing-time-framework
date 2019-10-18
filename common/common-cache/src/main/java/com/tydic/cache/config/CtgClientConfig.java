package com.tydic.cache.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guomenghao on 2018/8/30.
 */
@Setter
@Getter
@Accessors(chain = true)
public class CtgClientConfig extends CacheClientConfig {

    public CtgClientConfig() {
        this.setMode(CacheMode.CTG);
    }

    /**
     * 连接超时时间，单位毫秒，默认2秒
     */
    private int connectionTimeout=2000;
    /**
     * 操作超时时间，单位毫秒，默认2
     */
    private int soTimeout;
    /**
     *密码，必填，格式为“用户#密码”
     */
    private String password;
    /**
     *桶位，必填
     */
    private int database;
    /**
     *客户端名，默认null
     */
    private String clientName = null;
    /**
     *后台监控执行周期，毫秒，默认3秒
     */
    private long period=3000;
    /**
     *监控命令超时时间,毫秒，默认2000毫秒
     */
    private int monitorTimeout=2000;
    /**
     * 监控连续出错几次，将接入机节点判断为失效，默认3次
     */
    private int monitorErrorNum=3;
    /**
     * 接入机地址列表，zk string类型
     */
    private String hosts;
    /**
     * 接入机地址列表，zk
     */
    private List<HostAndPort> nodes;
    /**
     * 是否开启后台监控日志，默认开启
     */
    private boolean monitorLog=true;
    /**
     * Apache pool 配置，默认为JedisPoolConfig
     */
    private JedisPoolConfig poolConfig;


    public void setHosts(String hosts){
        List<HostAndPort> hostAndPortList = new ArrayList();
        List<String> listsParams = java.util.Arrays.asList(hosts.split(","));
        for (String lists:listsParams
        ) {
            String host ;
            String port;
            List<String> hostsParams = java.util.Arrays.asList(lists.split(":"));
            host = hostsParams.get(0);
            port = hostsParams.get(1);
            HostAndPort host1 = new HostAndPort(host ,Integer.valueOf(port));
            hostAndPortList.add(host1);
        }


        this.nodes = hostAndPortList;
        this.hosts = hosts;

    }
}


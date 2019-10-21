package com.fishingtime.framework.hbase.configure;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: framework
 * @description:
 * @author:
 * @create: 2019-06-05 17:18
 **/
@Getter
@Setter
public class HbaseConfiguration {
    /**
     *  ZooKeeper 链接地址 如：hdp6.fishingtime.com:2181,hdp7.fishingtime.com:2181,hdp8.fishingtime.com:2181
     */
    private String quorum;
    /**
     *  hdfs hbase数据存放目录如：hdfs://hdptest2/apps/hbase/data
     */
    private String rootDir;
    /**
     *  hbase ZooKeeper 根目录 默认：/hbase-unsecure
     */
    private String nodeParent="";

    /**
     *kerberos 认证 Keytab文件路径
     */
    private String userKeytab;

    /**
     *kerberos 认证 principal
     */
    private String userPrincipal;

    /**
     *kerberos 认证 realm
     */
    private String realm;

    /**
     *kerberos 认证 kdc
     */
    private String kdc;
}

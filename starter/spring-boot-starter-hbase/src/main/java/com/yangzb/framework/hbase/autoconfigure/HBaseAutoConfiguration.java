package com.yangzb.framework.hbase.autoconfigure;

import com.yangzb.framework.common.base.util.HadoopUtil;
import com.yangzb.framework.hbase.api.HBaseConnectionFactory;
import com.yangzb.framework.hbase.api.HBaseTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Description:
 *
 * @return
 * @author yzb
 * @date 2019/2/25 17:10
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(HBaseProperties.class)
@ConditionalOnClass(HBaseTemplate.class)
public class HBaseAutoConfiguration {

    private static final String HBASE_QUORUM = "hbase.zookeeper.quorum";
    private static final String HBASE_ROOTDIR = "hbase.rootdir";
    private static final String HBASE_ZNODE_PARENT = "zookeeper.znode.parent";


    @Bean
    @Autowired
    public HBaseConnectionFactory hbaseConnectionFactory(HBaseProperties hbaseProperties){

        Configuration configuration = HBaseConfiguration.create();

        configuration.set(HBASE_QUORUM, hbaseProperties.getQuorum());
        configuration.set(HBASE_ROOTDIR, hbaseProperties.getRootDir());
        configuration.set(HBASE_ZNODE_PARENT, hbaseProperties.getNodeParent());

        configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        configuration.set("hbase.rpc.timeout", "600000");//单个连接处理超时时间，设定为600秒（默认60）
        configuration.set("hbase.client.retries.number", "100");//在周期内重试次数，100次
        configuration.set("hbase.client.pause", "50");//重试等待时间，50毫秒

        if(null != hbaseProperties.getUserKeytab() && hbaseProperties.getUserKeytab().length()>0){
            HadoopUtil.kerberosAuthentication(configuration,hbaseProperties.getUserKeytab(),hbaseProperties.getUserPrincipal(),hbaseProperties.getRealm(),hbaseProperties.getKdc());
        }
        return new HBaseConnectionFactory(configuration);
    }

    @Bean
    @ConditionalOnMissingBean(HBaseTemplate.class)
    public HBaseTemplate hbaseTemplate(@Autowired HBaseConnectionFactory hbaseConnectionFactory) {
        return new HBaseTemplate(hbaseConnectionFactory);
    }
}


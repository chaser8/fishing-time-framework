package com.fishingtime.framework.hbase.api;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-02-26 10:21
 **/
@Slf4j
public class HBaseConnectionFactory{
    private Configuration configuration;

    private volatile Connection connection;
    private ThreadPoolExecutor poolExecutor;
    private AtomicInteger count = new AtomicInteger(0);

    public void closeConnection(){
        count.decrementAndGet();
    }
//    private

//    private

    public HBaseConnectionFactory(Configuration configuration) {
        Assert.notNull(configuration, " a valid configuration is required");
        this.configuration = configuration;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnection() throws IOException {
        synchronized (this) {
            if (null == this.connection || this.connection.isClosed()) {
                createConnection();
            }
            log.info(connection.toString());
            count.incrementAndGet();
            return this.connection;
        }
    }

    public void destroy()throws Exception{
        synchronized (this) {
            while (true){
                if(count.get()<=0){
                    break;
                }
                Thread.sleep(100);
            }

            log.info("connection destroy start");
            if(null != this.connection&&!this.connection.isClosed()){
                this.connection.close();
            }

//            this.connection = null;
//            if(!poolExecutor.isShutdown()){
//                poolExecutor.shutdown();
//            }
//            while(true){
//                if(poolExecutor.isTerminated()){
//                    log.info("所有的子线程都结束了！");
//                    break;
//                }
//                Thread.sleep(100);
//            }
            log.info("connection destroyed");
        }
    }

    public void createConnection()throws IOException{
        synchronized (this) {
            log.info("create connection start");
            if (null == this.connection||this.connection.isClosed()) {
                try {
                    poolExecutor = new ThreadPoolExecutor(200, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
                    // init pool
                    poolExecutor.prestartCoreThread();
                    this.connection = ConnectionFactory.createConnection(configuration, poolExecutor);
                } catch (IOException e) {
                    log.error("hbase connection资源池创建失败!", e);
                    throw new IOException("hbase connection资源池创建失败!", e);
                }
                log.info("created connection");
            }
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}

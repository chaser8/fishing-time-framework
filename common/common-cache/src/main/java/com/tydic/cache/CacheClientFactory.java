package com.tydic.cache;

import com.tydic.cache.config.CacheClientConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by guomenghao on 2018/8/30.
 */
@Slf4j
public abstract class CacheClientFactory {
    /**
     * 数据库链接配置
     */
    @Getter
    protected static CacheClientConfig cacheClientConfig;
    public CacheClientFactory(CacheClientConfig cacheClientConfig) {
        this.cacheClientConfig = cacheClientConfig;
    }

    /***
     * 初始化链接
     * @Description:  
     * @param 
     * @return void
     * @author yzb
     * @date 2019-06-06 17:58
     * @throws
     */
    public abstract void init ();

    /***
     * 重新初始化
     * @Description:  
     * @param 
     * @return void
     * @author yzb
     * @date 2019-06-06 18:01
     * @throws
     */
    public void reset(){
        destroy();
        init();
    }

    /**
     * getClient
     * 获取链接
     * 使用方法
     * <p>
     *     try (ICacheClient cacheClient = cacheClientFactory.getClient()){
     *             cacheClient.set("test","111");
     *             Assert.assertEquals(cacheClient.get("test"),"111");
     *     }
     * <p/>
     * @param
     * @return com.tydic.cache.ICacheClient
     * @author yzb
     * @date 2019-06-06 17:56
     * @throws
     */
    public abstract ICacheClient getClient();
    /***
     * 销毁连接池 
     * @Description:  
     * @param 
     * @return void
     * @author yzb
     * @date 2019-06-06 17:58
     * @throws
     */
    public abstract void destroy();
}

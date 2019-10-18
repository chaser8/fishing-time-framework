package com.tydic;

import com.tydic.cache.CacheClientFactory;
import com.tydic.cache.IJedisRedisClient;
import com.tydic.cache.Impl.CtgCacheClientFactory;
import com.tydic.cache.config.CtgClientConfig;
import com.tydic.cache.config.HostAndPort;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by guomenghao on 2018/8/30.
 */
public class CacheTest {
    public static void main(String[] args) {
        CtgClientConfig ctgClientConfig = new CtgClientConfig();
        ctgClientConfig.setPassword("DEV_CACHE#1qaz@wsX")
                .setDatabase(205)
                .setNodes(new ArrayList<HostAndPort>(){{
                    add(new HostAndPort("192.168.10.152",19999));
                }});

        CacheClientFactory cacheClientFactory = new CtgCacheClientFactory(ctgClientConfig);
        try  {
            //分布式锁中分组的key，自定义；同一把锁，key必须唯一
            String lockName = "lock";
            //分布式锁中分组的key对应value的值，每次获取锁，用随机算法，随机产生一个value，作用是api通过value自动检测是否锁成功
            String lockValue = "lockValue";
            //分布式锁中分组的key的过期时间，过期后锁将失效;示例中，10秒后，锁将自动失效
            long lockExpireTime = 10000;
            //获取锁的超时时间，5秒获取不到锁，抛出异常
            long timeout = 5000;

            for (int i = 0; i <10000 ; i++) {
                IJedisRedisClient cacheClient = (IJedisRedisClient) cacheClientFactory.getClient();
//            cacheClient.lock(lockName,lockValue,lockExpireTime,timeout);
                HashMap itemMap = new HashMap(16);
                itemMap.put("key",String.valueOf(i));
                int result =  cacheClient.hmset("COMMON_REGION1:", itemMap);

                String result1=  cacheClient.hget("COMMON_REGION1:","key");
                System.out.println(result1);
//            cacheClient.unlock(lockName,lockValue);
                cacheClient.close();
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

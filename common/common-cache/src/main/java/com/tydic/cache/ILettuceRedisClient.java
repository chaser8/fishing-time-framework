package com.tydic.cache;
import com.lambdaworks.redis.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import com.lambdaworks.redis.cluster.api.sync.RedisAdvancedClusterCommands;
/**
 * Created by guomenghao on 2018/8/30.
 */
public interface ILettuceRedisClient extends IRedisClient {

    Long linsert(String key, boolean before, String pivot, String value);
    public RedisAdvancedClusterCommands<String, String> getSynCommonds();
    public RedisAdvancedClusterAsyncCommands<String, String> getAsynCommonds();
}

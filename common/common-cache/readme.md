### 同时支持原生redis集群以及电信集团分布式缓存
1. 依赖包需要根据需要自行引入  

原生redis依赖
```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version></version>
</dependency>
```
电信集团分布式缓存组件 
```xml
<dependency>
    <groupId>commons-collections</groupId>
    <artifactId>commons-collections</artifactId>
    <version>3.2.2</version>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
    <version></version>
</dependency>
<dependency>
    <groupId>com.tydic</groupId>
    <artifactId>ctg-cache-nclient</artifactId>
    <version></version>
</dependency>
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version></version>
</dependency>
```
2. 使用方法(使用完毕后一定要关闭)
```java
CacheClientFactory cacheClientFactory = new JedisCacheClientFactory(cacheProperties.getConfig());
try(ICacheClient cacheClient = cacheClientFactory.getClient();){
    String rs= cacheClient.get("key");
}
```
3. 配置说明

原生集群
ipPort：ip和端口

## 自动依赖
```xml
<dependency>
    <groupId>com.yangzb</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>com.yangzb</groupId>
    <artifactId>spring-boot-starter-springfox-swagger-lastest</artifactId>
</dependency>
<dependency>
    <groupId>com.yangzb</groupId>
    <artifactId>spring-boot-starter-mybatis-plus</artifactId>
</dependency>
<dependency>
    <groupId>com.yangzb</groupId>
    <artifactId>common-web</artifactId>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
</dependency>
```
## 改造点
1. 自动扫描  com.tydic.dev1包
2. 通用web异常处理 ResponseBodyAdvice
3. 自定义DefaultErrorAttributes，解决springmv 处理消息404等返回不统一问题
4. 重写RequestMappingHandlerMapping，支持重复路径定义，优先使用子类实现
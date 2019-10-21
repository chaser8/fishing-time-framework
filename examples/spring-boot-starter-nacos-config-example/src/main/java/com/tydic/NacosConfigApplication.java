package com.fishingtime;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
//@NacosPropertySources(value ={
//        @NacosPropertySource(dataId = "springbootNacosConfigExample.yaml", groupId = "example", autoRefreshed = true),
//        @NacosPropertySource(dataId = "database.yaml", groupId = "example", autoRefreshed = true,before = "")
//})

//@EnableSwagger2
@MapperScan("com.fishingtime.**.mapper*")
//@NacosConfigurationProperties(dataId = "test", groupId = "example",autoRefreshed = true)
//@NacosPropertySource(dataId = "mysql.properties",groupId = "example",autoRefreshed = true)
@NacosPropertySource(dataId = "testp",groupId = "example",autoRefreshed = true)
//@NacosConfigurationProperties(properties = )
//@NacosConfigurationProperties(properties = )
public class NacosConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }
}
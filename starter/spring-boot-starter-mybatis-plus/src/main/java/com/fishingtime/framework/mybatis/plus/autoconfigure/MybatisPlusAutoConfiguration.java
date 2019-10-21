package com.fishingtime.framework.mybatis.plus.autoconfigure;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.metadata.FieldFilterSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-03-05 10:56
 **/
@Configuration
@MapperScan("com.tydic.**.mapper")
@EnableConfigurationProperties(MybatisPlusProperties.class)
@AutoConfigureBefore(com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration.class)
public class MybatisPlusAutoConfiguration {
    private static String BASE_MAPPER_LOCATION = "classpath*:com/tydic/**/xml/*Mapper.xml";
    private static String TYPE_ALIASES_PACKAGE = "com.tydic.**.entity";

    /**
     * MybatisPlusAutoConfiguration 
     * @Description:   添加默认加载策略
     * @param mybatisPlusProperties
     * @return 
     * @author
     * @date 2019/3/25 12:23
     */
    @Autowired
    public  MybatisPlusAutoConfiguration(MybatisPlusProperties mybatisPlusProperties){
        String [] originalMapperLocations = mybatisPlusProperties.getMapperLocations();
        String typeAliasesPackage = mybatisPlusProperties.getTypeAliasesPackage();
        if(typeAliasesPackage==null||typeAliasesPackage.trim().length()==0){
            typeAliasesPackage = TYPE_ALIASES_PACKAGE;
        }else {
            typeAliasesPackage += ","+TYPE_ALIASES_PACKAGE;
        }
        mybatisPlusProperties.setTypeAliasesPackage(typeAliasesPackage);
        mybatisPlusProperties.setMapperLocations(ArrayUtil.append(originalMapperLocations,BASE_MAPPER_LOCATION));
    }
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     *
     * 改造必须加了 TableField 注解的才解析成 sql
     *
     * @param 
     * @return com.tydic.dev1.mybatis.plus.autoconfigure.FieldFilterSqlInjector
     * @author
     * @date 2019-07-05 09:19
     */
    @Bean
    public FieldFilterSqlInjector fieldFilterSqlInjector() {
        return new FieldFilterSqlInjector();
    }


    @Bean
    @ConditionalOnProperty(value = "application.hintSql",havingValue = "true",matchIfMissing = false)
    public HintSqlInterceptor hintSqlInterceptor(){
        return new HintSqlInterceptor();
    }
}
package com.yangzb.framework.common.codegenerator.autoconfigure;

import com.yangzb.framework.common.codegenerator.CodeInterceptor;
import com.yangzb.framework.common.codegenerator.SequenceCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-15 13:49
 **/
@Configuration
public class CodeGeneratorAutoConfiguration {
    @Bean
    public CodeInterceptor codeInterceptor(){
        return new CodeInterceptor();
    }
    @Bean
    public SequenceCodeGenerator sequenceCodeGenerator(@Autowired JdbcTemplate jdbcTemplate) {
        SequenceCodeGenerator sequenceCodeGenerator = new SequenceCodeGenerator(jdbcTemplate);
        return sequenceCodeGenerator;
    }
}

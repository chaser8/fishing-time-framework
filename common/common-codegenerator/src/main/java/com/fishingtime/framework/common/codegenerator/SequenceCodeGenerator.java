package com.fishingtime.framework.common.codegenerator;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-15 13:13
 **/
public class SequenceCodeGenerator implements ICodeGenerator {
    private JdbcTemplate jdbcTemplate;
    public SequenceCodeGenerator(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Object generate(String[] params) {
        String sequence=null;
        if(null!=params&&params.length>0){
            sequence = params[0];
        }
        Assert.notEmpty(sequence,"序列不能为空，请在CodeGenerator注解中配置。");
        String sql = "select "+sequence+".nextval";
//        JdbcTemplate jdbcTemplate = SpringBeanHelper.getBean(JdbcTemplate.class);
        String code = jdbcTemplate.queryForObject(sql,String.class);
        code = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN)+""+code;
        return code;
    }
}

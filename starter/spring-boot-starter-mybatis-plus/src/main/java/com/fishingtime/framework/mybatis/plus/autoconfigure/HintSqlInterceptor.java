package com.fishingtime.framework.mybatis.plus.autoconfigure;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.sql.Connection;
import java.util.Properties;

/**
 * 集团组件要求有hint信息
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-15 11:38
 **/
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@Slf4j
public class HintSqlInterceptor implements Interceptor {
    @Resource
    private Environment environment;

    public static final String HINT_SQL_SQL_ID = "&sqlId&";
    public static final String HINT_SQL_APP_NAME = "&appName&";
    public static final String HINT_SQL = " /* [\"sqlId\":\"" + HINT_SQL_SQL_ID + "\",\"appName\":\"" + HINT_SQL_APP_NAME + "\",\"Mode\":\"生产\"] */";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
            MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

            String sql = statementHandler.getBoundSql().getSql();
            String sql1 = sql.replaceAll("\t"," ").replaceAll("\n"," ").trim();
            String sqlType = sql1.split(" ", 0)[0].trim();
            Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");

            String dataSource = ((DruidDataSource)configuration.getEnvironment().getDataSource()).getDriverClassName();

            //判断dml语句，并且不是序列查询
            if (((sqlType.equalsIgnoreCase("select")&&sql.toLowerCase().indexOf("from")!=-1) || sqlType.equalsIgnoreCase("update") || sqlType.equalsIgnoreCase("delete")||sqlType.equalsIgnoreCase("insert"))&&dataSource.indexOf("com.mysql.jdbc.Driver")!=-1) {
                String id = (String) metaObject.getValue("delegate.mappedStatement.id");
                String appName = environment.getProperty("@appId");

                appName = StrUtil.isBlank(appName)?"mkt":appName;

                String hintSql = sqlType + HintSqlInterceptor.HINT_SQL;
                hintSql = hintSql.replaceFirst(HINT_SQL_SQL_ID, id).replaceFirst(HINT_SQL_APP_NAME, appName);
                sql = sql.replaceFirst(sqlType, hintSql);

                metaObject.setValue("delegate.boundSql.sql", sql);
            }
        } catch (Exception e) {
            log.error("添加 sql 标签出错！",e);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
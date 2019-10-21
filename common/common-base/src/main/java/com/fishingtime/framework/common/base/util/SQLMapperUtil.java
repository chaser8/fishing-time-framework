package com.fishingtime.framework.common.base.util;

import com.fishingtime.framework.common.base.map.MapEntry;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SQLMapper
 * @Description: sqlmapper
 */

@SuppressWarnings("all")
public class SQLMapperUtil {
    public static void addMappedStatement(Configuration configuration, String id, String sql, SqlCommandType type,Class<?> parameterType,Class<?> resultType) {
        if (configuration.hasStatement(id)) {
            return;
        }
        List<MapEntry<String, Object>> sqls = new ArrayList<MapEntry<String, Object>>();
        MapEntry<String, Object> sqlm = new MapEntry<String, Object>();
        sqlm.put("id", id);
        sqlm.put("sql", sql);
        sqlm.put("type", type);
        sqlm.put("resultType", resultType);
        sqlm.put("parameterType", parameterType);
        sqls.add(sqlm);
        addMappedStatement(configuration, sqls);
    }

    public static void addMappedStatement(Configuration configuration, List<MapEntry<String, Object>> sqls) {
        SqlSourceBuilder ssb = new SqlSourceBuilder(configuration);
        LanguageDriver languageDriver = configuration.getDefaultScriptingLanuageInstance();
        for (MapEntry<String, Object> map : sqls) {
            String sql = map.getString("sql");
            String id = map.getString("id");
            Class<?> resultType = map.getClazz("resultType");
            Class<?> parameterType = map.getClazz("parameterType");
            SqlCommandType type = (SqlCommandType) map.get("type");
            configuration.addMappedStatement(newMappedStatement(configuration,id,sql,parameterType,resultType));
        }
    }

    public static void removeStatement(Configuration configuration, String id) {
        if (configuration.hasStatement(id)) {
            try {
                Field mappedStatementsField = configuration.getClass().getDeclaredField("mappedStatements");
                mappedStatementsField.setAccessible(true);
                Map mappedStatements = (Map) mappedStatementsField.get(configuration);
                mappedStatements.remove(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建一个查询的MS
     *
     * @param msId
     * @param sqlSource  执行的sqlSource
     * @param resultType 返回的结果类型
     */
    private static MappedStatement newMappedStatement(Configuration configuration,String msId, String sql, Class<?> parameterType, final Class<?> resultType) {
        LanguageDriver languageDriver = configuration.getDefaultScriptingLanuageInstance();
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, parameterType);

//        StaticSqlSource sqlSource = new StaticSqlSource(configuration, sql);
        MappedStatement ms = new MappedStatement.Builder(configuration, msId, sqlSource, SqlCommandType.SELECT)
                .resultMaps(new ArrayList<ResultMap>() {
                    {
                        add(new ResultMap.Builder(configuration, "defaultResultMap", resultType, new ArrayList<ResultMapping>(0)).build());
                    }
                })
                .build();
        //缓存
        return ms;
    }
}

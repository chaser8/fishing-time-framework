package com.fishingtime.framework.hbase.api;

import java.util.Map;

/**
 * @author
 * @Description:
 * @return
 * @date 2019/2/26 09:20
 */
public interface HBaseOperations {
    /**
     * insert
     *
     * @param tableName
     * @param obj
     * @return void
     * @throws Exception
     * @Description:
     * @author
     * @date 2019/2/26 17:21
     */
    <T> void update(String tableName, T obj) throws Exception;

    /**
     * insert
     *
     * @param tableName
     * @param family
     * @param obj
     * @return void
     * @throws Exception
     * @Description:
     * @author
     * @date 2019/2/26 17:24
     */
    <T> void update(final String tableName, String family, T obj) throws Exception;

    /**
     * selectOne
     *
     * @param hBaseGet
     * @return java.util.Map<java.lang.String   ,   java.lang.String>
     * @throws
     * @Description:
     * @author
     * @date 2019/3/4 15:52
     */
    Map<String, String> selectOne(HBaseGet hBaseGet) throws Exception;

    /**
     * selectOne
     *
     * @param hBaseGet
     * @param resultType
     * @return T
     * @throws
     * @Description:
     * @author
     * @date 2019/3/4 15:52
     */
    <T> T selectOne(HBaseGet hBaseGet, Class<T> resultType) throws Exception;

    /**
     * scan
     *
     * @param hBaseScan
     * @param resultType
     * @return void
     * @throws
     * @Description:
     * @author
     * @date 2019/3/4 15:52
     */
    <T> void scan(HBaseScan hBaseScan, Class<T> resultType) throws Exception;

    /**
     * scan
     *
     * @param hBaseScan
     * @return void
     * @throws
     * @Description:
     * @author
     * @date 2019/3/4 15:52
     */
    <T> void scan(HBaseScan hBaseScan) throws Exception;

    /**
     * delete
     *
     * @param hBaseDelete
     * @return void
     * @throws
     * @Description:
     * @author
     * @date 2019/3/4 15:52
     */
    void delete(HBaseDelete hBaseDelete) throws Exception;

    /**
     * deleteColumn
     *
     * @param tableName     表名
     * @param family        列簇名
     * @param qualifier     列名
     * @param commitRecords 每次批量提交条数
     * @return void
     * @throws
     * @Description:
     * @author
     * @date 2019/3/4 15:38
     */
    void deleteColumn(String tableName, String family, String qualifier, int commitRecords) throws Exception;

    /**
     * batchUpdate 批量更新
     *
     * @param hBasePut
     * @return void
     * @throws
     * @Description:
     * @author
     * @date 2019/2/28 10:23
     */
    void batchUpdate(HBasePut hBasePut) throws Exception;
}
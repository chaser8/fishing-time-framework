package com.fishingtime.framework.hbase.api;

/**
 * @author
 * @Description:
 * @return
 * @date 2019/2/26 09:20
 */
public interface HBaseAdmin {
    void createTable(String tabName, String... family) throws Exception;

    void createTable(String tabName, String[] splits, String... family) throws Exception;
    boolean tableExists(String tabName) throws Exception;

    void disableTable(String tableName) throws Exception;

    void enableTable(String tableName) throws Exception;

    void dropTable(String tableName) throws Exception;

    void truncateTable(String tableName, boolean preserveSplits) throws Exception;

    void deleteColumn(String tableName, String familyName) throws Exception;
    void addColumn(String tableName, String... familys) throws Exception;

}
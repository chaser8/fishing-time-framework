package com.fishingtime.framework.hbase.api;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author
 * @Description:
 * @return
 * @date 2019/2/26 09:42
 */
@Slf4j
public class HBaseTemplate implements HBaseOperations, HBaseAdmin {
    private HBaseConnectionFactory hbaseConnectionFactory;

    public HBaseTemplate(HBaseConnectionFactory hbaseConnectionFactory) {
        Assert.notNull(hbaseConnectionFactory, " a valid hbaseConnectionFactory is required");
        this.hbaseConnectionFactory = hbaseConnectionFactory;
    }

    @Override
    public void createTable(String tableName, String... family) throws Exception {
        this.createTable(tableName, null, family);
    }

    @Override
    public void createTable(String tableName, String[] splits, String... familys) throws Exception {
        Assert.notNull(tableName, "a valid tableName is required");
        try (Admin admin = hbaseConnectionFactory.getConnection().getAdmin()) {
            TableName tab = TableName.valueOf(tableName);
            if (admin.tableExists(tab)) {
                log.error(StrUtil.format("表 {} 已存在无法创建！", tableName));
            } else {
                HTableDescriptor htd = new HTableDescriptor(tab);

                byte[][] splitKyes = null;
                if (splits != null && splits.length > 0) {
                    List<byte[]> bls = new ArrayList<>();
                    for (String s : splits) {
                        if (!"".equals(s)) {
                            bls.add(Bytes.toBytes(s));
                        }
                    }
                    splitKyes = new byte[bls.size()][];
                    bls.toArray(splitKyes);
                }

                for (String f : familys) {
                    if (!StrUtil.isEmpty(f)) {
                        HBaseUtil.addFamily(htd, f, false);
                    }
                }
                admin.createTable(htd, splitKyes);
            }
        } catch (Exception e) {
            throw new Exception(StrUtil.format("创建表{}出错!", tableName), e);
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }


    @Override
    public void disableTable(String tableName) throws Exception {
        try (Admin admin = hbaseConnectionFactory.getConnection().getAdmin()) {
            admin.disableTable(TableName.valueOf(tableName));
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public void deleteColumn(String tableName, String familyName) throws Exception {
        try (Admin admin = hbaseConnectionFactory.getConnection().getAdmin()) {
            admin.deleteColumn(TableName.valueOf(tableName), Bytes.toBytes(familyName));
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public void addColumn(String tableName, String... familys) throws Exception {
        try (Admin admin = hbaseConnectionFactory.getConnection().getAdmin()) {
            for (String f : familys) {
                if (!StrUtil.isEmpty(f)) {
                    admin.addColumn(TableName.valueOf(tableName), HBaseUtil.createHCDesc(f, BloomType.ROWCOL));
                }
            }
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public void enableTable(String tableName) throws Exception {
        try (Admin admin = hbaseConnectionFactory.getConnection().getAdmin()) {
            admin.enableTable(TableName.valueOf(tableName));
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public void truncateTable(String tableName, boolean preserveSplits) throws Exception {
        try (Admin admin = hbaseConnectionFactory.getConnection().getAdmin()) {
            TableName table = TableName.valueOf(tableName);
            admin.disableTable(table);
            admin.truncateTable(table, preserveSplits);
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public void dropTable(String tableName) throws Exception {
        try (Admin admin = hbaseConnectionFactory.getConnection().getAdmin()) {
            this.disableTable(tableName);
            admin.deleteTable(TableName.valueOf(tableName));
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public <T> void update(String tableName, String family, T obj) throws Exception {
        try (Table table = hbaseConnectionFactory.getConnection().getTable(TableName.valueOf(tableName))) {
            table.put(HBaseUtil.getPut(tableName, family, obj));
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public void batchUpdate(HBasePut hBasePut) throws Exception {
        Assert.notEmpty(hBasePut.getDatas(), "插入数据不能为空！HBasePut.getDatas");
        try (Table table = hbaseConnectionFactory.getConnection().getTable(TableName.valueOf(hBasePut.getTableName()))) {
            if (StrUtil.isEmpty(hBasePut.getFamily())) {
                HColumnDescriptor[] hColumnDescriptors = table.getTableDescriptor().getColumnFamilies();
                if (1 == hColumnDescriptors.length) {
                    hBasePut.setFamily(hColumnDescriptors[0].getNameAsString());
                } else {
                    throw new Exception("列簇数量不为1，请调指定列簇。");
                }
            }
            table.put(HBaseUtil.getPuts(hBasePut.getTableName(), hBasePut.getFamily(), hBasePut.getDatas()));
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }


    @Override
    public <T> void update(String tableName, T obj) throws Exception {
        try (Table table = hbaseConnectionFactory.getConnection().getTable(TableName.valueOf(tableName))) {
            HColumnDescriptor[] hColumnDescriptors = table.getTableDescriptor().getColumnFamilies();
            if (1 == hColumnDescriptors.length) {
                table.put(HBaseUtil.getPut(tableName, hColumnDescriptors[0].getNameAsString(), obj));
            } else {
                throw new Exception("列簇数量不为1，请调指定列簇。");
            }
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public Map<String, String> selectOne(HBaseGet hBaseGet) throws Exception {
        return selectOne(hBaseGet, Map.class);
    }

    @Override
    public <T> T selectOne(HBaseGet hBaseGet, Class<T> resultType) throws Exception {
        Object obj;
        try (Table table = hbaseConnectionFactory.getConnection().getTable(TableName.valueOf(hBaseGet.getTableName()))) {
            Result result = table.get(hBaseGet.getGet());
            obj = HBaseUtil.getResultValue(result, resultType, hBaseGet.isKeyContainsFamily(), hBaseGet.isKeyContainsVersion());
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
        return (T) obj;
    }

    @Override
    public void delete(HBaseDelete hBaseDelete) throws Exception {
        try (Table table = hbaseConnectionFactory.getConnection().getTable(TableName.valueOf(hBaseDelete.getTableName()))) {
            table.delete(hBaseDelete.getDelete());
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public void deleteColumn(String tableName, String family, String qualifier,int commitRecords) throws Exception {
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes(family),Bytes.toBytes(qualifier));
        try (Table table = hbaseConnectionFactory.getConnection().getTable(TableName.valueOf(tableName))) {
            final ArrayList<Delete> deletes = new ArrayList<Delete>();
            try (ResultScanner resultScanner = table.getScanner(scan)) {
                for (Result result : resultScanner) {
                    if(result.containsColumn(Bytes.toBytes(family),Bytes.toBytes(qualifier))){
                        Delete delete =new Delete(result.getRow());
                        delete.addColumn(Bytes.toBytes(family),Bytes.toBytes(qualifier),Long.MAX_VALUE);
                        deletes.add(delete);
                    }
                    if(deletes.size()>=commitRecords){
                        table.delete(deletes);
                        deletes.clear();
                    }
                }
                if(deletes.size()>0){
                    table.delete(deletes);
                    deletes.clear();
                }
            }
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public <T> void scan(HBaseScan hBaseScan) throws Exception {
        scan(hBaseScan, Map.class);
    }

    @Override
    public <T> void scan(HBaseScan hBaseScan, Class<T> resultType) throws Exception {
        Assert.notNull(hBaseScan, "HBaseScan 不能为空");
        Assert.notNull(hBaseScan.getCall(), "scan 方法 HBaseScan.call 不能为空");
        try (Table table = hbaseConnectionFactory.getConnection().getTable(TableName.valueOf(hBaseScan.getTableName()))) {
            try (ResultScanner resultScanner = table.getScanner(hBaseScan.getScan());) {
                for (Result result : resultScanner) {
                    try {
                        Object data = HBaseUtil.getResultValue(result, resultType, hBaseScan.isKeyContainsFamily(), hBaseScan.isKeyContainsVersion());
                        hBaseScan.getCall().next(data);
                    } catch (Exception e) {
                        log.debug("", e);
                        Map value = null;
                        if (resultType != Map.class) {
                            try {
                                value = HBaseUtil.getResultValue(result, Map.class, hBaseScan.isKeyContainsFamily(), hBaseScan.isKeyContainsVersion());
                            } catch (Exception e1) {
                                log.debug("", e1);
                            }
                        }
                        hBaseScan.getCall().typeConvertErrorHandler(result, value, e);
                    }
                }
            }
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }

    @Override
    public boolean tableExists(String tabName) throws Exception {
        try (Admin admin = hbaseConnectionFactory.getConnection().getAdmin()) {
            return admin.tableExists(TableName.valueOf(tabName));
        }finally {
            hbaseConnectionFactory.closeConnection();
        }
    }
}

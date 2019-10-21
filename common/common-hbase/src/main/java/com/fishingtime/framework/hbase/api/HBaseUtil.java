package com.fishingtime.framework.hbase.api;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-02-26 14:13
 **/
@Slf4j
public class HBaseUtil {
    public static final String ROW_KEY = "id";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static <T> List<Put> getPuts(String tableName, String family, List<Object> datas) throws Exception {
        List<Put> puts = new ArrayList<>();
        for (Object data : datas) {
            puts.add(HBaseUtil.getPut(tableName, family, data));
        }
        return puts;
    }


    public static <T> Put getPut(String tableName, String family, T obj) throws Exception {
        Put put = null;
        String rowKey = null;
        Map mapObj = null;
        if (obj instanceof Map) {
            mapObj = (Map) obj;
        } else {
            mapObj = OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(obj), Map.class);
        }
        rowKey = Convert.toStr(mapObj.remove(ROW_KEY));
        put = new Put(Bytes.toBytes(rowKey));
        HBaseUtil.addPutColumns(put, family, mapObj);
        return put;
    }

    public static HColumnDescriptor[] getHColumnDescriptors(Table table) throws Exception {
        return table.getTableDescriptor().getColumnFamilies();
    }

    public static <T> void addPutColumns(Put put, String family, Map data) {
        for (Object key : data.keySet()) {
            Object value = data.get(key);
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(Convert.toStr(key)), Bytes.toBytes(Convert.toStr(value)));
        }
    }

    /**
     * 给表添加列,此时不带列族
     *
     * @param htdesc   htdesc
     * @param colName  colName
     * @param readonly readonly
     *                 是否只读
     */
    public static void addFamily(HTableDescriptor htdesc, String colName,
                                 final boolean readonly) {
        htdesc.addFamily(HBaseUtil.createHCDesc(colName, BloomType.ROWCOL));
        htdesc.setReadOnly(readonly);
    }

    /**
     * 创建列的描述,添加后，该列会有一个冒号的后缀，用于存储(列)族, 将来如果需要扩展，那么就在该列后加入(列)族
     * 所有列公用一个列簇
     *
     * @param colName colName
     * @return HColumnDescriptor
     */
    public static HColumnDescriptor createHCDesc(String colName, BloomType bloomType) {
        HColumnDescriptor hcd = new HColumnDescriptor(colName);
        hcd.setBloomFilterType(bloomType);
        return hcd;
    }

    /**
     * getResultValue
     *
     * @param result
     * @param keyContainsVersion key是否包含 version 时间戳 只有当返回为 map 时生效
     * @param keyContainsFamily  key是否包含 列簇信息，只有当返回为 map 时生效
     * @return java.util.Map<java.lang.String               ,               java.lang.String>
     * @throws
     * @Description:
     * @author
     * @date 2019/2/27 14:16
     */
    public static <T> T getResultValue(Result result, Class<T> clazz, boolean keyContainsFamily, boolean keyContainsVersion) throws Exception {
        Object obj = null;
        Map map = CollectionUtil.newHashMap();
        boolean returnMap = false;
        if (clazz != null && clazz != map.getClass()) {
            returnMap = true;
        }
        List<Cell> cells = result.listCells();
        if (!CollUtil.isEmpty(cells)) {
            map.put(HBaseUtil.ROW_KEY, Bytes.toString(result.getRow()));
            for (Cell cell : cells) {
                StringBuffer k = new StringBuffer();
                if (keyContainsFamily && returnMap) {
                    String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
                    k.append(family).append(":");
                }

                String qualifier = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                k.append(qualifier);
                if (keyContainsVersion && returnMap) {
                    k.append(":").append(cell.getTimestamp());
                }
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                map.put(k.toString(), value);
            }
            if (returnMap) {
                obj = OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(map), clazz);
//           obj= BeanUtil.mapToBean(map,clazz,false);
            } else {
                obj = map;
            }
        }
        return (T) obj;
    }


    /**
     * 封装 get 查询时返回的列信息
     *
     * @param get
     * @param family
     * @param qualifiers
     * @return void
     * @throws
     * @Description:
     * @author
     * @date 2019/2/27 14:17
     */
    public static void addGetFamily(Get get, String family, String... qualifiers) throws IOException {
        HBaseUtil.addGetFamily(get, family, CollUtil.toList(qualifiers));
    }

    /**
     * addGetFamily
     *
     * @param get
     * @param family
     * @param qualifiers
     * @return void
     * @throws
     * @Description:
     * @author
     * @date 2019/2/28 10:43
     */
    public static void addGetFamily(Get get, String family, Collection<String> qualifiers) throws IOException {
        byte[] familyByte = Bytes.toBytes(family);
        if (!CollUtil.isEmpty(qualifiers)) {
            for (String qualifier : qualifiers) {
                get.addColumn(familyByte, Bytes.toBytes(qualifier));
            }
        } else {
            get.addFamily(familyByte);
        }
    }
}

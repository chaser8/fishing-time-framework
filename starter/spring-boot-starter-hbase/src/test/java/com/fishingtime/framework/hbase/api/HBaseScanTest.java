package com.fishingtime.framework.hbase.api;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HBaseScanTest {
    @Autowired
    HBaseOperations hBaseOperations;

    public static final String TABLE_NAME="_test2";
    public static final String FAMILY="cf1";
    public static final String FAMILY1="cf2";



    @Test
    public void scan() throws Exception{
        HBaseScan hBaseQuery = new HBaseScan(TABLE_NAME);
        hBaseQuery.addColumn(Bytes.toBytes(FAMILY),Bytes.toBytes("huo1"));
        hBaseQuery.addColumn(Bytes.toBytes(FAMILY),Bytes.toBytes("huo2"));
        hBaseQuery.setCall(new Call<Map>() {
            @Override
            public void next(Map obj) {
                log.info(JSONUtil.toJsonStr(obj));
            }
        });
        hBaseOperations.scan(hBaseQuery);
    }
}
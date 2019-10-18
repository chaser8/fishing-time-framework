package com.yangzb.framework.hbase.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HBaseDeleteTest {
    @Autowired
    HBaseOperations hBaseOperations;

    public static final String TABLE_NAME="yzb_test2";
    public static final String FAMILY="cf1";
    public static final String FAMILY1="cf2";



    @Test
    public void delete() throws Exception{
        HBaseDelete hBaseDelete = new HBaseDelete(TABLE_NAME,"1");
        hBaseOperations.delete(hBaseDelete);
    }
    @Test
    public void deleteColunm() throws Exception{
        hBaseOperations.deleteColumn(TABLE_NAME,FAMILY,"name1",88);
    }
}
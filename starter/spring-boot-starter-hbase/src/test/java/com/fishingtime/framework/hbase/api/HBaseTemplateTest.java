package com.fishingtime.framework.hbase.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class HBaseTemplateTest {
    @Autowired
    HBaseAdmin hBaseAdmin;

    @Test
    public void createTable() throws Exception {
        hBaseAdmin.createTable("_test1","0");
    }

    @Test
    public void createTable1()throws Exception {
        hBaseAdmin.createTable("_test2",new String[]{"1|","2|","3|","4|","5|","6|","7|","8|","9|"},"cf1");
    }

    @Test
    public void disableTable() throws Exception {
        hBaseAdmin.disableTable("_test1");
        hBaseAdmin.disableTable("_test2");

    }

    @Test
    public void enableTable() throws Exception {
        hBaseAdmin.enableTable("_test1");
        hBaseAdmin.enableTable("_test2");
    }

    @Test
    public void dropTable() throws Exception {
//        hBaseAdmin.dropTable("_test1");
        hBaseAdmin.dropTable("_test2");
    }
    @Test
    public void truncateTable() throws Exception {
//        hBaseAdmin.dropTable("_test1");
        hBaseAdmin.truncateTable("_test2",true);
    }

    @Test
    public void deleteColumn() throws Exception {
//        hBaseAdmin.addColumn("_test2","cf2");
        hBaseAdmin.deleteColumn("_test2","cf2");
    }

}
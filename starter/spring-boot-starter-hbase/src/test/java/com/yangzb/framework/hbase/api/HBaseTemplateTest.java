package com.yangzb.framework.hbase.api;

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
        hBaseAdmin.createTable("yzb_test1","0");
    }

    @Test
    public void createTable1()throws Exception {
        hBaseAdmin.createTable("yzb_test2",new String[]{"1|","2|","3|","4|","5|","6|","7|","8|","9|"},"cf1");
    }

    @Test
    public void disableTable() throws Exception {
        hBaseAdmin.disableTable("yzb_test1");
        hBaseAdmin.disableTable("yzb_test2");

    }

    @Test
    public void enableTable() throws Exception {
        hBaseAdmin.enableTable("yzb_test1");
        hBaseAdmin.enableTable("yzb_test2");
    }

    @Test
    public void dropTable() throws Exception {
//        hBaseAdmin.dropTable("yzb_test1");
        hBaseAdmin.dropTable("yzb_test2");
    }
    @Test
    public void truncateTable() throws Exception {
//        hBaseAdmin.dropTable("yzb_test1");
        hBaseAdmin.truncateTable("yzb_test2",true);
    }

    @Test
    public void deleteColumn() throws Exception {
//        hBaseAdmin.addColumn("yzb_test2","cf2");
        hBaseAdmin.deleteColumn("yzb_test2","cf2");
    }

}
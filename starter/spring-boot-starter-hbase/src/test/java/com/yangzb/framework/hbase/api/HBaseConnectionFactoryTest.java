package com.yangzb.framework.hbase.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HBaseConnectionFactoryTest {

    @Autowired
    HBaseOperations hBaseOperations;


    public static final String TABLE_NAME="yzb_test1";


    @Autowired
    HBaseConnectionFactory hBaseConnectionFactory;
    @Test
    public void getConnection()throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 500; j++) {
                    try {
                        Map data = new HashMap() {{
                            put("id", 3);
                            put("huo1", "1");
                            put("huo2", "1");
                        }};
                        hBaseOperations.update(TABLE_NAME, data);
                        Thread.sleep(10);
                        log.error("--------------------------updated");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 10000; i++) {
            Thread.sleep(1000);
            hBaseConnectionFactory.destroy();
        }

        Thread.sleep(100000000);
    }
}
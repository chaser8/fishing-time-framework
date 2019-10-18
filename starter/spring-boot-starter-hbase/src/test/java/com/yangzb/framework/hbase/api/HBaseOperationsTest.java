package com.yangzb.framework.hbase.api;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HBaseOperationsTest {
    @Autowired
    HBaseOperations hBaseOperations;

    public static final String TABLE_NAME="yzb_test1";
    public static final String FAMILY="cf1";
    public static final String FAMILY1="cf2";


    @Autowired
    HBaseAdmin hBaseAdmin;
    Map data = new HashMap() {{
        put("id", 3);
        put("huo1", "1");
        put("huo2", "1");
    }};

    @Test
    public void insert() throws Exception{
//        hBaseAdmin.createTable(TABLE_NAME,new String[]{"1","2","3"},FAMILY);
        hBaseOperations.update(TABLE_NAME, data);
//        hBaseAdmin.dropTable(TABLE_NAME);
    }

    @Test
    public void insert1() throws Exception{
        hBaseOperations.update(TABLE_NAME,FAMILY, data);
//        Map data2 = new HashMap() {{
//            put("id", 1);
//            put("name", "yzb1");
//        }};
//        hBaseOperations.insert(TABLE_NAME,FAMILY1, data2);
    }

    @Test
    public void selectOne() throws Exception{
        HBaseGet hBaseGet = new HBaseGet(TABLE_NAME,FAMILY,"1");
        User rs = hBaseOperations.selectOne(hBaseGet,User.class);
        log.info(JSONUtil.toJsonStr(rs));
//        Assert.assertEquals(rs.get("name"),"yzb1");
    }
    @Test
    public void selectOneFilter() throws Exception{
        HBaseGet hBaseGet = new HBaseGet(TABLE_NAME,FAMILY,"1");
        ValueFilter valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes(1)));
        hBaseGet.setFilter(valueFilter);
        Map rs = hBaseOperations.selectOne(hBaseGet);
        log.info(JSONUtil.toJsonStr(rs));
//        Assert.assertEquals(rs.get("name"),"yzb1");
    }


    @Test
    public void updates()throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        StopWatch sw = new StopWatch();
        sw.start();
        ThreadPoolExecutor threadPoolExecutor = ThreadUtil.newExecutor(50,50);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(()->{
                StopWatch sw1 = new StopWatch();
                sw1.start();
                HBasePut<Map> hBasePut = new HBasePut(TABLE_NAME,FAMILY);
                List<Map> datas = new ArrayList<>();
                int index=0;
                for (int j = 0; j < 1000; j++) {
                    index = atomicInteger.getAndIncrement();
                    Map data = new HashMap() {{
                        for (int i = 0; i < 2 ; i++) {
                            put("name"+i, RandomUtil.randomInt(3)%2);
                        }
//                        put("name", "yzb111");
//                        put("age", 12);

                    }};
                    data.put("id", index);
                    datas.add(data);
                }
                hBasePut.setDatas(datas);
                try {
                    hBaseOperations.batchUpdate(hBasePut);
                } catch (Exception e) {
                    log.error("",e);
                }
                sw1.stop();
                log.info("{}:{}",index,sw1.getTotalTimeSeconds());
            });
        }

        threadPoolExecutor.shutdown();
        while(true){
            if(threadPoolExecutor.isTerminated()){
                System.out.println("所有的子线程都结束了！");
                break;
            }
            Thread.sleep(10);
        }
        sw.stop();
        log.info(sw.prettyPrint());
    }

    @Test
    public void updatesBean()throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        StopWatch sw = new StopWatch();
        sw.start();
        ThreadPoolExecutor threadPoolExecutor = ThreadUtil.newExecutor(50,50);
        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.execute(()->{
                StopWatch sw1 = new StopWatch();
                sw1.start();
                HBasePut<User> hBasePut = new HBasePut(TABLE_NAME,FAMILY);
                List<User> datas = new ArrayList<>();
                int index=0;
                for (int j = 0; j < 2000; j++) {
                    index = atomicInteger.getAndIncrement();

                    User user =new User(""+index,"yzb111",12);
                    datas.add(user);
                }
                hBasePut.setDatas(datas);
                try {
                    hBaseOperations.batchUpdate(hBasePut);
                } catch (Exception e) {
                    log.error("",e);
                }
                sw1.stop();
                log.info("{}:{}",index,sw1.getTotalTimeSeconds());
            });
        }

        threadPoolExecutor.shutdown();
        while(true){
            if(threadPoolExecutor.isTerminated()){
                System.out.println("所有的子线程都结束了！");
                break;
            }
            Thread.sleep(10);
        }
        sw.stop();
        log.info(sw.prettyPrint());
    }
}
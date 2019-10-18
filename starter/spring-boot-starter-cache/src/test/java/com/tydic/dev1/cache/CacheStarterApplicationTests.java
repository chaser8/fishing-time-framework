package com.tydic.dev1.cache;

import com.tydic.cache.CacheClientFactory;
import com.tydic.cache.ICacheClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheStarterApplicationTests {
    @Autowired
    CacheClientFactory cacheClientFactory;

    @Test
    public void ctgTest() throws Exception{
        try (ICacheClient cacheClient = cacheClientFactory.getClient()){
            cacheClient.set("test","111");
            String test = cacheClient.get("test");
            Assert.assertEquals(test,"111");
        }
    }
}

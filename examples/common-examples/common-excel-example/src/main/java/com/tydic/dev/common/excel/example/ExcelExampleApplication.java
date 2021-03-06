package com.fishingtime.dev.common.excel.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-03-28 16:03
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ExcelExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExcelExampleApplication.class, args);
    }
}
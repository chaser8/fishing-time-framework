package com.tydic.dev1.common.ftp.example;

import cn.hutool.extra.ftp.AbstractFtp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-03-22 14:10
 **/
@SpringBootApplication
@Slf4j
public class FtpApplication implements CommandLineRunner {
    public static void main(String[] args){
        SpringApplication.run(FtpApplication.class, args);
    }
    @Autowired
    private AbstractFtp abstractFtp;
    @Override
    public void run(String... args) throws Exception {
//        System.out.println(abstractFtp.cd("aaa"));
//        abstractFtp.reconnectIfTimeout();
        String pwd = abstractFtp.pwd();
        log.info(pwd);
        List<String> ls = abstractFtp.ls("");
        log.info(""+ls);

//        log.info(""+abstractFtp.exist("/pub/ftpdirs/UNI076_LABEL_CATRGORY.20190716.821.001.DAT"));
        boolean upload = abstractFtp.upload("targrp", new File("/Users/yzb/Downloads/智慧BSS3.0系统集成方案v3.0.doc"));

        log.info(""+upload);
//        abstractFtp.download("/pub/ftpdirs/UNI076_LABEL_CATRGORY.20190721.821.001.DAT",new File("/Users/yzb/Downloads/sfz/UNI076_LABEL_CATRGORY.20190721.821.001.DAT"));
    }
}
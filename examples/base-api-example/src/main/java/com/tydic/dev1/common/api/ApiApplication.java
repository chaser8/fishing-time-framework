package com.fishingtime.dev1.common.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-03-22 14:10
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ApiApplication implements ServletContextInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        String relativePath="/Users//02.Subversion/framework/examples/base-api-example/src/main/resources/sso.properties";
        servletContext.setInitParameter("configurationStrategy", "PROPERTY_FILE");
        File docBaseFile=new File(relativePath);
//        if(!docBaseFile.exists()){
//            relativePath = "mkt-cpc-web/src/main/resources/sso.properties";
//            docBaseFile = new File(relativePath);
//        }
//        if(!docBaseFile.exists()){
//            relativePath = "src/main/resources/sso.properties";
//            docBaseFile = new File(relativePath);
//        }
        servletContext.setInitParameter("configFileLocation", docBaseFile.getAbsolutePath());
    }
}
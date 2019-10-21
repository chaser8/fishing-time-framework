package com.fishingtime.dev1.example.tomcat.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-03-22 14:10
 **/
@SpringBootApplication
public class TomcatDeployApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TomcatDeployApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TomcatDeployApplication.class);
    }
}
package com.fishingtime.dev1.springcloud.uaa.server.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudUaaServerApplication {
	public static void main(String[] args) {
	    SpringApplication.run(SpringCloudUaaServerApplication.class, args);
	}
}
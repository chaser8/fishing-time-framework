package com.tydic.dev1.spring.cloud.gateway.controller;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-04-25 15:58
 **/
@RestController
public class UAAController {

    @GetMapping("test/{id}")
    public String test(@PathVariable("id") Integer id, HttpRequest request){
        int r= 1/id;
        return "test111";
    }

    @GetMapping("no/{id}")
    public String test1(@PathVariable("id") Integer id){
        int r= 1/id;
        return "test111";
    }

    @GetMapping("error")
    public String error(){
        return "test111";
    }
}
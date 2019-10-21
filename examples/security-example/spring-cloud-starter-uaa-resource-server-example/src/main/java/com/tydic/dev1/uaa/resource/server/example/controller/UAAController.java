package com.fishingtime.dev1.uaa.resource.server.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-04-25 15:58
 **/
@RestController
public class UAAController {

    @GetMapping("test/{id}")
    public String test(@PathVariable("id") Integer id){
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
package com.yangzb.framework.uaa.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-04-25 15:58
 **/
@RestController
public class UAAController {

    /**
     * 认证后获取用户信息
     * @Description:
     * @param user
     * @return java.security.Principal
     * @author yzb
     * @date 2019/4/26 17:03
     * @throws
     */
    @GetMapping("user")
    public Principal user(Principal user){
        return user;
    }
}
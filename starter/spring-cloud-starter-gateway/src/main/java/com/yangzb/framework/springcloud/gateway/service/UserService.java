package com.yangzb.framework.springcloud.gateway.service;

import com.yangzb.framework.uaa.bean.SystemUser;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-09 10:00
 **/
public interface UserService {
    SystemUser getUserByToken(String token);
}
package com.fishingtime.framework.springcloud.gateway.service;

import com.fishingtime.framework.uaa.bean.SystemUser;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-09 10:00
 **/
public interface UserService {
    SystemUser getUserByToken(String token);
}
package com.tydic.dev1.spring.cloud.gateway;

import com.tydic.dev1.springcloud.gateway.service.ApplicationInterfaceService;
import com.tydic.dev1.springcloud.gateway.service.UserService;
import com.tydic.dev1.uaa.bean.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-09 10:08
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ApplicationInterfaceService applicationInterfaceService;


    @Override
    public SystemUser getUserByToken(String token) {
        return new SystemUser();
    }
}

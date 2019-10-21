package com.fishingtime.framework.springcloud.gateway.service.impl;

import com.fishingtime.framework.springcloud.gateway.entity.ApplicationInterface;
import com.fishingtime.framework.springcloud.gateway.service.ApplicationInterfaceService;
import com.fishingtime.framework.springboot.starter.tk.mybatis.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用api信息 服务实现类
 * </p>
 *
 * @author
 * @since 2019-05-22
 */
@Service
public class ApplicationInterfaceServiceImpl extends ServiceImpl<ApplicationInterface> implements ApplicationInterfaceService {
}
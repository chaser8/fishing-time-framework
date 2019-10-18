package com.yangzb.framework.springcloud.gateway.service.impl;

import com.yangzb.framework.common.base.service.ServiceImpl;
import com.yangzb.framework.springcloud.gateway.entity.ApplicationInterface;
import com.yangzb.framework.springcloud.gateway.mapper.ApplicationInterfaceMapper;
import com.yangzb.framework.springcloud.gateway.service.ApplicationInterfaceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用api信息 服务实现类
 * </p>
 *
 * @author yzb
 * @since 2019-05-22
 */
@Service
public class ApplicationInterfaceServiceImpl extends ServiceImpl<ApplicationInterfaceMapper, ApplicationInterface> implements ApplicationInterfaceService {
}
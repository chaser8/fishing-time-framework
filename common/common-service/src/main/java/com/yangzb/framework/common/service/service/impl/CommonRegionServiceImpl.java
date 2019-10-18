package com.yangzb.framework.common.service.service.impl;

import com.yangzb.framework.common.service.entity.CommonRegion;
import com.yangzb.framework.common.service.mapper.CommonRegionMapper;
import com.yangzb.framework.common.service.service.CommonRegionService;
import com.yangzb.framework.common.base.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 指对于各种专业电信管理区域的共性管理区域信息的抽象表达，包括省公司、本地网、营业区。因为使用目的不同，可以定义不同使用类 服务实现类
 * </p>
 *
 * @author yzb
 * @since 2019-04-15
 */
@Service
public class CommonRegionServiceImpl extends ServiceImpl<CommonRegionMapper, CommonRegion> implements CommonRegionService {

}

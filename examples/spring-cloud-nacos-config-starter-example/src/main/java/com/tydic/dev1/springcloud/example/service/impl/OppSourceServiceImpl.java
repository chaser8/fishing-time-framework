package com.tydic.dev1.springcloud.example.service.impl;

import com.tydic.dev1.common.base.service.ServiceImpl;
import com.tydic.dev1.springcloud.example.entity.OppSourceEntity;
import com.tydic.dev1.springcloud.example.mapper.OppSourceMapper;
import com.tydic.dev1.springcloud.example.service.OppSourceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 记录每一个优惠券实例信息 服务实现类
 * </p>
 *
 * @author yzb
 * @since 2019-03-08
 */
@Service
public class OppSourceServiceImpl extends ServiceImpl<OppSourceMapper, OppSourceEntity> implements OppSourceService {

}

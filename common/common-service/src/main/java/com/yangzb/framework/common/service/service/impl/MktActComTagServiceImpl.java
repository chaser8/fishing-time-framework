package com.yangzb.framework.common.service.service.impl;

import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangzb.framework.common.base.entity.StatusCd;
import com.yangzb.framework.common.base.util.SQLMapperUtil;
import com.yangzb.framework.common.service.entity.MktActComTag;
import com.yangzb.framework.common.service.mapper.MktActComTagMapper;
import com.yangzb.framework.common.service.service.MktActComTagService;
import com.yangzb.framework.common.base.service.ServiceImpl;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzb
 * @since 2019-04-15
 */
@Service
public class MktActComTagServiceImpl extends ServiceImpl<MktActComTagMapper, MktActComTag> implements MktActComTagService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Map> selectComTagDataList(String tagCode,Map params) {
        MktActComTag mktActComTag = new MktActComTag();
        mktActComTag.setStatusCd(StatusCd.CD_1000.getCode());
        mktActComTag.setTagCode(tagCode);
        mktActComTag = baseMapper.selectOne(new QueryWrapper<>(mktActComTag));
        if(null==mktActComTag){
            return new LinkedList<>();
        }
        String sqlId = ClassUtil.getClassName(MktActComTagMapper.class,false)+".selectComTagDataList."+tagCode;
        SQLMapperUtil.addMappedStatement(sqlSessionFactory.getConfiguration(),sqlId,mktActComTag.getTagSql(), SqlCommandType.SELECT,Map.class,Map.class);
        return sqlSessionTemplate.selectList(sqlId,params);
    }
}

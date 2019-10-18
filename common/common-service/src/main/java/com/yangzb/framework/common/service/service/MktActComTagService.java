package com.yangzb.framework.common.service.service;

import com.yangzb.framework.common.service.entity.MktActComTag;
import com.yangzb.framework.common.base.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzb
 * @since 2019-04-15
 */
public interface MktActComTagService extends IService<MktActComTag> {
    List<Map> selectComTagDataList(String tagCode,Map params);
}
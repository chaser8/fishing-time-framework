package com.yangzb.framework.common.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.yangzb.framework.common.base.page.PageInfo;
import com.yangzb.framework.common.base.page.PageRequestBody;
import com.yangzb.framework.common.base.page.PageResponseBody;
import com.yangzb.framework.common.base.page.PageResult;

import java.io.IOException;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-03-28 10:28
 **/
public interface IService<T> extends com.baomidou.mybatisplus.extension.service.IService<T> {
    /**
     * 废弃，调用 public PageResult<T> page(PageInfo pageInfo,T queryParams)
     *
     * @param entity
     * @return PageResponseBody<T>
     * @throws
     * @author yzb
     * @date 2019-06-19 09:45
     */
    @Deprecated
    PageResponseBody<T> page(PageRequestBody entity);

    /**
     * 弃用，请使用  public PageResult<T> page(PageInfo pageInfo,Wrapper wrapper)
     *
     * @param entity
     * @param wrapper
     * @return PageResponseBody<T>
     * @throws
     * @author yzb
     * @date 2019-06-19 09:48
     */
    @Deprecated
    PageResponseBody<T> page(PageRequestBody entity, Wrapper wrapper);

    /**
     * page 查询
     *
     * @param pageInfo
     * @param queryParams
     * @return PageResult<T>
     * @throws
     * @author yzb
     * @date
     */
    PageResult<T> page(PageInfo pageInfo, T queryParams);

    /**
     *
     * page
     *
     * @param pageInfo
     * @param wrapper
     * @return PageResult<T>
     * @author yzb
     * @date 2019-06-19 10:13        
     */
    PageResult<T> page(PageInfo pageInfo, Wrapper wrapper) throws IOException;
}
package com.yangzb.framework.common.base.page;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-03-27 15:05
 **/
@Deprecated
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {
    public Page(PageRequestBody pageBody){
        this.setAsc(pageBody.getAscs());
        this.setCurrent(pageBody.getPageIndex());
        this.setDesc(pageBody.getDescs());
        this.setSize(pageBody.getPageSize());
        this.setSearchCount(pageBody.isSearchCount());
        this.setOptimizeCountSql(pageBody.isOptimizeCountSql());
    }

    public PageResponseBody getPageResponseBody(){
        return new PageResponseBody(this);
    }
}

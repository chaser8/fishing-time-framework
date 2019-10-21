package com.fishingtime.framework.common.base.page;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: framework
 * @description:
 * @author:
 * @create: 2019-06-18 09:05
 **/
@Getter
@Setter
@Accessors(chain = true)
public class PageInfo implements Serializable {
//    private static final long serialVersionUID = 1L;
//    protected long pageIndex = 1L;
//    protected long pageSize = 10L;
//    @JsonIgnore
//    protected boolean searchCount = true;
//    @JsonIgnore
//    protected boolean optimizeCountSql = true;
//    protected String[] ascs = new String[0];
//    protected String[] descs = new String[0];
//    private long pageCount;
//    private long rowCount;
//
//    @JsonIgnore
//    public IPage getPage(){
//        return  new Page().setAsc(this.getAscs())
//                .setDesc(this.getDescs())
//                .setCurrent(this.getPageIndex())
//                .setOptimizeCountSql(this.optimizeCountSql)
//                .setSearchCount(this.searchCount)
//                .setSize(this.pageSize);
//    }
}

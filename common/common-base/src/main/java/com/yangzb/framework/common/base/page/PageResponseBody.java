package com.yangzb.framework.common.base.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @program: dic-framework-v3
 * @description:
 * @author: yzb
 * @create: 2019-03-06 14:05
 **/
@Getter
@Setter
@Deprecated
public class PageResponseBody<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public PageResponseBody(){

    }
    public PageResponseBody(IPage page) {
        pageInfo = new PageInfo(page);
        datas = page.getRecords();
    }

    private List<T> datas;

    private PageInfo pageInfo;

    @Setter
    @Getter
    public class PageInfo implements Serializable{
        public PageInfo(){

        }
        private static final long serialVersionUID = 1L;
        public PageInfo(IPage page) {
            this.pageCount = page.getPages();
            this.pageIndex = page.getCurrent();
            this.pageSize = page.getSize();
            this.rowCount = page.getTotal();
        }
        private long pageIndex;
        private long pageSize;
        private long pageCount;
        private long rowCount;
    }

}

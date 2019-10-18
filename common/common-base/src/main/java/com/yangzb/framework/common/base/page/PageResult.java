package com.yangzb.framework.common.base.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: framework
 * @description:
 * @author: yzb
 * @create: 2019-06-18 09:07
 **/
@Getter
@Setter
public class PageResult<T> {
    private List<T> datas;
    private PageInfo pageInfo;
    public PageResult(){

    }
    public PageResult(IPage page) {
        pageInfo = new PageInfo().setPageCount(page.getPages())
                .setPageIndex(page.getCurrent())
                .setRowCount(page.getTotal())
                .setPageSize(page.getSize());

        datas = page.getRecords();
    }
}

package com.yangzb.framework.common.base.page;

/**
 * @program: dic-framework-v3
 * @description:
 * @author: yzb
 * @create: 2019-03-06 14:05
 **/
@Deprecated
public interface PageRequestBody {

    /**
     * 默认第一页
     */
    public static final Long DEFAULT_PAGE_INDEX = 1L;
    /**
     * 默认每页10条
     */
    public static final Long DEFAULT_PAGE_SIZE = 10L;
    /**
     * 默认分页统计
     */
    public static final Boolean DEFAULT_SEARCH_COUNT = true;
    /**
     * 默认SQL优化
     */
    public static final Boolean DEFAULT_OPTIMIZE_COUNT_SQL = true;

    long getPageIndex();

    void setPageIndex(long pageIndex);

    long getPageSize();

    void setPageSize(long pageSize);

    boolean isSearchCount();

    void setSearchCount(boolean searchCount);

    boolean isOptimizeCountSql();

    void setOptimizeCountSql(boolean optimizeCountSql);

    String[] getAscs();

    void setAscs(String[] ascs);

    String[] getDescs();

    void setDescs(String[] descs);
}
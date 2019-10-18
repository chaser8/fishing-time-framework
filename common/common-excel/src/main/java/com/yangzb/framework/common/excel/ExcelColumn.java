package com.yangzb.framework.common.excel;

import java.util.List;
import java.util.Map;

/**
 * Excel配置数据实体
 * @auther litong
 * @date 20190703
 */
public class ExcelColumn {

    private String key; //列可以值
    private String text;  //列标题

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

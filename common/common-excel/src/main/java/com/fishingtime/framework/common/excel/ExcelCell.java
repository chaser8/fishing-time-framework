package com.fishingtime.framework.common.excel;

/**
 * Excel配置单元
 * @auther litong
 * @date 20190703
 */
public class ExcelCell {

    private int firstRow; //单元行开始
    private int lastRow;  //单元行结束
    private int firstColumn;
    private int lastColumn;
    private String text; //单元格填充内容
    private boolean style; //单元样式 true标题样式  false 默认样式

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public int getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(int firstColumn) {
        this.firstColumn = firstColumn;
    }

    public int getLastColumn() {
        return lastColumn;
    }

    public void setLastColumn(int lastColumn) {
        this.lastColumn = lastColumn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStyle() {
        return style;
    }

    public void setStyle(boolean style) {
        this.style = style;
    }
}

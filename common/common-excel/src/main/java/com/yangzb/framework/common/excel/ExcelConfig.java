package com.yangzb.framework.common.excel;

import java.util.List;
import java.util.Map;

/**
 * Excel配置数据实体
 * @auther litong
 * @date 20190703
 */
public class ExcelConfig {

    private String titleName; //文件名
    private String formDate="yyyy-MM-dd HH:mm:ss";  //文件名时间格式
    private Boolean passRows = true;   //是否只加载 配置的数据列

    private int lineNum=1;  //表头空行数

    private List<ExcelColumn> columns; //数据列信息

    private List<ExcelCell> Cells; //表格合并信息

    private String sheetName;    //sheet命名

    // 设置列宽（单位为一个字符的宽度，例如传入width为10，表示10个字符的宽度）
    Map<Integer,Integer> 	columnWidth;

    public Boolean getPassRows() {
        return passRows;
    }

    public void setPassRows(Boolean passRows) {
        this.passRows = passRows;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public List<ExcelColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<ExcelColumn> columns) {
        this.columns = columns;
    }

    public List<ExcelCell> getCells() {
        return Cells;
    }

    public void setCells(List<ExcelCell> cells) {
        Cells = cells;
    }
}

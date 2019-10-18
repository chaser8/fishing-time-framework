package com.yangzb.framework.common.excel;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 类 ,解析XML文件 获取ExcelData
 */
@Component
public class ExcelConfigXml {

    /**
     * 解析xml信息获取配置信息
     * @param pathxml xml文件地址
     * @return
     */
    public ExcelConfig solveXmlData(String pathxml) throws JDOMException, IOException {
        ExcelConfig excelConfig =  new ExcelConfig();

        SAXBuilder builder = new SAXBuilder();
        Document root = builder.build(this.getClass().getResourceAsStream(pathxml));
        Element rootElemenet = root.getRootElement();


        //获取columns 数据
        Element header= rootElemenet.getChild("columns");
        List<Element> columns = header.getChildren();
        List<ExcelColumn> ExcelColumns= new ArrayList<>();
        for(Element ele:columns) {
            ExcelColumn excelColumn =new ExcelColumn();
            excelColumn.setKey(ele.getAttributeValue("key"));
            excelColumn.setText(ele.getAttributeValue("text"));
            ExcelColumns.add(excelColumn);
        }
        excelConfig.setColumns(ExcelColumns);

        //获取表头配置
        Element merges =rootElemenet.getChild("headers");
        List<Element> merge = merges.getChildren();
        List<ExcelCell> cells = new ArrayList<>();
        for(Element ele:merge) {
            ExcelCell excelCell = new ExcelCell();
            excelCell.setFirstRow(Integer.parseInt(ele.getAttributeValue("firstRow")));
            excelCell.setLastRow(Integer.parseInt(ele.getAttributeValue("lastRow")));
            excelCell.setFirstColumn(Integer.parseInt(ele.getAttributeValue("firstColumn")));
            excelCell.setFirstColumn(Integer.parseInt(ele.getAttributeValue("lastColumn")));
            excelCell.setText(ele.getAttributeValue("text"));
            excelCell.setStyle(Boolean.parseBoolean(ele.getAttributeValue("style")));
            cells.add(excelCell);
        }
        excelConfig.setCells(cells);

        //获取文件名
        excelConfig.setTitleName(rootElemenet.getChild("title").getAttributeValue("name"));
        String formData=rootElemenet.getChild("title").getAttributeValue("dateform");
        if(formData!=null&&!"".equals(formData)){
            excelConfig.setFormDate(formData);
        }
        return excelConfig;
    }

    //获取XML配置的 文件名
    public String getXmlTitle(String pathxml) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document root = builder.build(this.getClass().getResourceAsStream(pathxml));
        Element rootElemenet = root.getRootElement();

        return  rootElemenet.getChild("title").getAttributeValue("name");
    }

    /**
     *xml添加动态列
     * @param pathxml
     * @param reUnloums
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public ExcelConfig solveXmlDataUn(String pathxml, List<ExcelColumn> reUnloums) throws JDOMException, IOException {
        ExcelConfig excelConfig =  new ExcelConfig();

        SAXBuilder builder = new SAXBuilder();
        Document root = builder.build(this.getClass().getResourceAsStream(pathxml));
        Element rootElemenet = root.getRootElement();


        //获取columns 数据
        Element header= rootElemenet.getChild("columns");
        List<Element> columns = header.getChildren();
        List<ExcelColumn> ExcelColumns= new ArrayList<>();
        for(Element ele:columns) {
            ExcelColumn excelColumn =new ExcelColumn();
            excelColumn.setKey(ele.getAttributeValue("key"));
            excelColumn.setText(ele.getAttributeValue("text"));
            ExcelColumns.add(excelColumn);
        }
        excelConfig.setColumns(ExcelColumns);
        ExcelColumns.addAll(reUnloums);
        excelConfig.setColumns(ExcelColumns);

        //获取表头配置
        Element merges =rootElemenet.getChild("headers");
        List<Element> merge = merges.getChildren();
        List<ExcelCell> cells = new ArrayList<>();
        for(Element ele:merge) {
            ExcelCell excelCell = new ExcelCell();
            excelCell.setFirstRow(Integer.parseInt(ele.getAttributeValue("firstRow")));
            excelCell.setLastRow(Integer.parseInt(ele.getAttributeValue("lastRow")));
            excelCell.setFirstColumn(Integer.parseInt(ele.getAttributeValue("firstColumn")));
            excelCell.setFirstColumn(Integer.parseInt(ele.getAttributeValue("lastColumn")));
            excelCell.setText(ele.getAttributeValue("text"));
            excelCell.setStyle(Boolean.parseBoolean(ele.getAttributeValue("style")));
            cells.add(excelCell);
        }
        excelConfig.setCells(cells);

        //获取文件名
        excelConfig.setTitleName(rootElemenet.getChild("title").getAttributeValue("name"));
        String formData=rootElemenet.getChild("title").getAttributeValue("dateform");
        if(formData!=null&&!"".equals(formData)){
            excelConfig.setFormDate(formData);
        }
        return excelConfig;
    }

}

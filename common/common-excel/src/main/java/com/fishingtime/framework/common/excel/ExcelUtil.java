package com.fishingtime.framework.common.excel;

import cn.hutool.poi.excel.BigExcelWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

/**
 * Excel 工具类 ,解析Excle的bean配置信息 用于生产Excel
 */
@Component
public class ExcelUtil {

    /**
     * 解析Bean配置数据 生成Excel文件输出字节流 (常用)
     * @param excelConfig
     * @return
     */
    public static ByteArrayOutputStream getExcelOutStream(ExcelConfig excelConfig, Iterable<?>  datalist){
        BigExcelWriter writer = (BigExcelWriter) cn.hutool.poi.excel.ExcelUtil.getBigWriter(); // 通过工具类创建writer，默认创建xls格式
        //columns 设置数据
        for(ExcelColumn ele: excelConfig.getColumns()) {
            writer.addHeaderAlias(ele.getKey(),ele.getText());
        }
        writer.setOnlyAlias(excelConfig.getPassRows()); //仅显示 设置表头列

        //设置表头行数
        writer.passRows(excelConfig.getLineNum());
        writer.write(datalist, true);

        for(ExcelCell ele: excelConfig.getCells()) {
            writer.merge(ele.getFirstRow(),
                    ele.getLastRow(),
                    ele.getFirstColumn(),
                    ele.getLastColumn(),
                    ele.getText(),
                    ele.isStyle()
            );
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writer.flush(out);
        // 关闭writer，释放内存
        writer.close();

        return  out;
    }

    /**
     * 解析Bean配置数据 写入Excel文件输出字节流 (常用)
     * @param excelConfig
     * @return
     */
    public static void writeExcelFile(ExcelConfig excelConfig, Iterable<?>  datalist, File destFile){
        BigExcelWriter writer = (BigExcelWriter) cn.hutool.poi.excel.ExcelUtil.getBigWriter(destFile); // 通过工具类创建writer
        //columns 设置数据
        for(ExcelColumn ele: excelConfig.getColumns()) {
            writer.addHeaderAlias(ele.getKey(),ele.getText());
        }
        writer.setOnlyAlias(excelConfig.getPassRows()); //仅显示 设置表头列

        //设置表头行数
        writer.passRows(excelConfig.getLineNum());
        writer.write(datalist, true);

        for(ExcelCell ele: excelConfig.getCells()) {
            writer.merge(ele.getFirstRow(),
                    ele.getLastRow(),
                    ele.getFirstColumn(),
                    ele.getLastColumn(),
                    ele.getText(),
                    ele.isStyle()
            );
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writer.flush();
        // 关闭writer，释放内存
        writer.close();

    }

    /**
     * 解析Bean配置数据 生成 BigExcelWriter (BigExcelWriter 可配置)
     * @param excelConfig
     * @return
     */
    public static BigExcelWriter getExcelWriter(ExcelConfig excelConfig, Iterable<?>  datalist){
        BigExcelWriter writer = (BigExcelWriter) cn.hutool.poi.excel.ExcelUtil.getBigWriter(); // 通过工具类创建writer，默认创建xls格式
        //columns 设置数据
        for(ExcelColumn ele: excelConfig.getColumns()) {
            writer.addHeaderAlias(ele.getKey(),ele.getText());
        }
        writer.setOnlyAlias(excelConfig.getPassRows()); //仅显示 设置表头列

        //设置表头行数
        writer.passRows(excelConfig.getLineNum());
        writer.write(datalist, true);

        for(ExcelCell ele: excelConfig.getCells()) {
            writer.merge(ele.getFirstRow(),
                    ele.getLastRow(),
                    ele.getFirstColumn(),
                    ele.getLastColumn(),
                    ele.getText(),
                    ele.isStyle()
            );
        }
        return  writer;
    }

    /**
     * 数据追加
     * @param writer
     * @return
     */
    public static BigExcelWriter addData(BigExcelWriter writer,List<Iterable<?>> datalist){
        for(int i=0;i<datalist.size();i++){
            writer.writeRow(datalist.get(i));
        }
        return writer;
    }

    /**
     * 添加标题行数据
     * @param writer
     * @return
     */
    public static BigExcelWriter addHeadRow(BigExcelWriter writer,Iterable<?> rowData){
        writer.writeHeadRow(rowData);
        return writer;
    }

    /**
     * 给指定单元格赋值
     * @param writer
     * @return
     */
    public static BigExcelWriter writeCell(BigExcelWriter writer,int x, int y, Object value){
        writer.writeCellValue( x,  y,  value);
        return  writer;
    }
}

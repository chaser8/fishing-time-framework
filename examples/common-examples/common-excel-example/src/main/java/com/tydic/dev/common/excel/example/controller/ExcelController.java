package com.fishingtime.dev.common.excel.example.controller;

import com.fishingtime.dev1.common.excel.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-14 17:17
 **/
@RestController
public class ExcelController {
    @Resource
    private ExcelHelper excelHelper;

    @GetMapping("excel")
    public void export(HttpServletResponse response){
        try {
//            excelHelper.export("1","1",null,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

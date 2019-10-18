package com.yangzb.framework.common.api.controller;

import com.yangzb.framework.common.base.map.IMapEntry;
import com.yangzb.framework.common.base.map.MapEntry;
import com.yangzb.framework.common.web.Response;
import com.yangzb.framework.common.web.ResponseBodyError;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zjy
 * @since 2019-05-07
 */
@Api(value = "DocumentApi" ,description = "指纳入系统管理的非结构化文件信息。如照片、图片、扫描件等。")
@RestController
@RequestMapping("/document")
@Slf4j
public class DocumentController {
    private final Logger logger = LoggerFactory.getLogger(DocumentController.class);


    @ApiOperation(value = "获取下载地址")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询失败", response = ResponseBodyError.class),
            @ApiResponse(code = 200, message = "查询失败", response = Void.class)
    })
    @RequestMapping(value = "/getDownloadURL", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE},method = RequestMethod.GET)
    public Response<String> getDownloadURL(@ApiParam(value = "document") Void document) {
        MapEntry param = new MapEntry();
//        String downloadUrl = documentService.getDownloadURL(document);
        Response responseEntity = Response.ok("");
        return responseEntity;
    }
    @ApiOperation(value = "获取文件信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询失败", response = ResponseBodyError.class),
            @ApiResponse(code = 200, message = "查询失败", response = Void.class)
    })
    @RequestMapping(value = "/getDocumentInfo", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE},method = RequestMethod.GET)
    public Response<Void> getDocumentInfo(@ApiParam(value = "document") Void document) {
//        Document docBack = documentService.getDocumentInfo(document);
        Response responseEntity = Response.ok("");
        return responseEntity;
    }

    @ApiOperation(value = "上传文件")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "上传失败", response = ResponseBodyError.class),
            @ApiResponse(code = 200, message = "上传", response = Void.class)
    })
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Response<IMapEntry> upload(HttpServletRequest request) {
        logger.info("处理附件上传信息");
        long start = System.currentTimeMillis();

        logger.info("附件上传完成,用时:-->" + (start - System.currentTimeMillis()) + "ms，处理结果：");
        Response responseEntity = Response.ok("");
        return responseEntity;
    }


}

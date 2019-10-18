package com.yangzb.framework.common.api.controller;

import com.yangzb.framework.common.base.page.PageInfo;
import com.yangzb.framework.common.base.page.PageResult;
import com.yangzb.framework.common.web.ControllerBase;
import com.yangzb.framework.common.web.Response;
import com.yangzb.framework.common.web.ResponseBodyError;
import com.yangzb.framework.common.base.validation.ValidGoup;
import com.yangzb.framework.common.service.entity.CommonRegion;
import com.yangzb.framework.common.service.service.CommonRegionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;
import java.io.Serializable;

/**
* @author yzb
* @since 2019-04-15
*/
@Api(value = "CommonRegionApi" ,description = "指对于各种专业电信管理区域的共性管理区域信息的抽象表达，包括省公司、本地网、营业区。因为使用目的不同，可以定义不同使用类")
@RestController
@RequestMapping("/commonRegion")
public class CommonRegionController extends ControllerBase {
    @Autowired
    protected CommonRegionService commonRegionService;

    @ApiOperation(value = "根据多种条件查询符合条件的列表，支持分页，输出基本信息列表")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "列表查询", response = PageResult.class),
        @ApiResponse(code = 200, message = "查询失败", response = ResponseBodyError.class)
    })
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE},method = RequestMethod.GET)
    public Response<CommonRegion> page(@ApiParam(value = "CommonRegionPage") @Validated(ValidGoup.Page.class) CommonRegion body, PageInfo pageInfo) {
        PageResult<CommonRegion> pageResponseBody = commonRegionService.page(pageInfo,body);
        Response response = Response.ok(pageResponseBody);
        return response;
    }



    @ApiOperation(value = "新增")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "新增失败", response = ResponseBodyError.class),
        @ApiResponse(code = 201, message = "新增成功", response = CommonRegion.class)
    })
    @RequestMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE}, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE }, method = RequestMethod.PUT)
    public Response<CommonRegion> add(@ApiParam(value = "")  @Validated(ValidGoup.Add.class) @RequestBody CommonRegion body){
        commonRegionService.save(body);
        Response response = Response.create(body);
        return response;
    }


    @ApiOperation(value = "删除")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "删除失败", response = ResponseBodyError.class),
        @ApiResponse(code = 204, message = "删除成功")
    })
    @RequestMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE},method = RequestMethod.DELETE)
    public Response<Void> delete(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id){
        commonRegionService.removeById(id);
        return Response.empty();
    }


    @ApiOperation(value = "查询详情")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "查询失败", response = ResponseBodyError.class),
        @ApiResponse(code = 200, message = "详情", response = CommonRegion.class)
    })
    @RequestMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE },method = RequestMethod.GET)
    public Response<CommonRegion> get(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id){
        CommonRegion commonRegion=commonRegionService.getById(id);
        Response response = Response.ok(commonRegion);
        return response;
    }


    @ApiOperation(value = "修改")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "修改失败", response = ResponseBodyError.class),
        @ApiResponse(code = 201, message = "修改成功", response = CommonRegion.class) })
    @RequestMapping(value = "/{id}",
    produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE},consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.PATCH)
    public Response<CommonRegion> patch(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id, @ApiParam(value = ""  )  @Validated(ValidGoup.Update.class) @RequestBody CommonRegion body){
        body.setId(id);
        commonRegionService.updateById(body);
        Response response = Response.ok(body);
        return response;
    }
}

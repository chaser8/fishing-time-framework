package com.yangzb.framework.common.api.controller;

import com.yangzb.framework.common.base.page.PageInfo;
import com.yangzb.framework.common.base.page.PageResult;
import com.yangzb.framework.common.base.validation.ValidGoup;
import com.yangzb.framework.common.service.entity.AttrSpec;
import com.yangzb.framework.common.service.service.AttrSpecService;
import com.yangzb.framework.common.web.ControllerBase;
import com.yangzb.framework.common.web.Response;
import com.yangzb.framework.common.web.ResponseBodyError;
import com.tydic.dev1.common.web.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
* @author yzb
* @since 2019-04-15
*/
@Api(value = "AttrSpecApi" ,description = "属性规格")
@RestController
@RequestMapping("/attrSpec")
public class AttrSpecController extends ControllerBase {
    @Autowired
    protected AttrSpecService attrSpecService;

    @ApiOperation(value = "根据多种条件查询符合条件的列表，支持分页，输出基本信息列表")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "列表查询", response = PageResult.class),
        @ApiResponse(code = 200, message = "查询失败", response = ResponseBodyError.class)
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public Response<AttrSpec> page(@ApiParam(value = "AttrSpecPage") @Validated(ValidGoup.Page.class) AttrSpec body, PageInfo pageInfo) {
        PageResult<AttrSpec> pageResponseBody = attrSpecService.page(pageInfo,body);
        Response response = Response.ok(pageResponseBody);
        return response;
    }

    @ApiOperation(value = "新增")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "新增失败", response = ResponseBodyError.class),
        @ApiResponse(code = 201, message = "新增成功", response = AttrSpec.class)
    })
    @PutMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE}, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE })
    public Response<AttrSpec> add(@ApiParam(value = "")  @Validated(ValidGoup.Add.class) @RequestBody AttrSpec body){
        attrSpecService.save(body);
        Response response = Response.create(body);
        return response;
    }


    @ApiOperation(value = "删除")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "删除失败", response = ResponseBodyError.class),
        @ApiResponse(code = 204, message = "删除成功")
    })
    @DeleteMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public Response<Void> delete(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id){
        attrSpecService.removeById(id);
        return Response.empty();
    }


    @ApiOperation(value = "查询详情")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "查询失败", response = ResponseBodyError.class),
        @ApiResponse(code = 200, message = "详情", response = AttrSpec.class)
    })
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE })
    public Response<AttrSpec> get(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id){
        AttrSpec attrSpec=attrSpecService.getById(id);
        Response response = Response.ok(attrSpec);
        return response;
    }


    @ApiOperation(value = "修改")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "修改失败", response = ResponseBodyError.class),
        @ApiResponse(code = 201, message = "修改成功", response = AttrSpec.class) })
    @PatchMapping(value = "/{id}",
    produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE},consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response<AttrSpec> patch(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id, @ApiParam(value = ""  )  @Validated(ValidGoup.Update.class) @RequestBody AttrSpec body){
        body.setId(id);
        attrSpecService.updateById(body);
        Response response = Response.ok(body);
        return response;
    }
}

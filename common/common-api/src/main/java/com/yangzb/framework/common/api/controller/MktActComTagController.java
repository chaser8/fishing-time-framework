package com.yangzb.framework.common.api.controller;

import com.yangzb.framework.common.base.page.PageInfo;
import com.yangzb.framework.common.base.page.PageResult;
import com.yangzb.framework.common.web.ControllerBase;
import com.yangzb.framework.common.web.Response;
import com.yangzb.framework.common.web.ResponseBodyError;
import com.yangzb.framework.common.base.validation.ValidGoup;
import com.yangzb.framework.common.service.entity.MktActComTag;
import com.yangzb.framework.common.service.service.MktActComTagService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
* @author yzb
* @since 2019-04-15
*/
@Api(value = "MktActComTagApi" ,description = "")
@RestController
@RequestMapping("/mktActComTag")
public class MktActComTagController extends ControllerBase {
    @Autowired
    protected MktActComTagService mktActComTagService;

    @ApiOperation(value = "根据多种条件查询符合条件的列表，支持分页，输出基本信息列表")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "列表查询", response = PageResult.class),
        @ApiResponse(code = 200, message = "查询失败", response = ResponseBodyError.class)
    })
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE},method = RequestMethod.GET)
    public Response<MktActComTag> page(@ApiParam(value = "MktActComTagPage") @Validated(ValidGoup.Page.class) MktActComTag body, PageInfo pageInfo) {
        PageResult<MktActComTag> pageResponseBody = mktActComTagService.page(pageInfo,body);
        Response response = Response.ok(pageResponseBody);
        return response;
    }

    @ApiOperation(value = "通过 tagcode 查询数据", response = Map.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "列表查询", response = Map.class, responseContainer = "List")})
    @RequestMapping(value = "/dataList/{tagCode}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @ResponseBody
    public Response<Map> dataList(@ApiParam(value = "tagCode",required = true) @PathVariable("tagCode") String tagCode,@RequestParam Map<String,Object> params) {
        params.put("tagCode",tagCode);
        List<Map> rs =  mktActComTagService.selectComTagDataList(tagCode,params);
        Response response = Response.ok(rs);
        return response;
    }



    @ApiOperation(value = "新增")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "新增失败", response = ResponseBodyError.class),
        @ApiResponse(code = 201, message = "新增成功", response = MktActComTag.class)
    })
    @RequestMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE}, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE }, method = RequestMethod.PUT)
    public Response<MktActComTag> add(@ApiParam(value = "")  @Validated(ValidGoup.Add.class) @RequestBody MktActComTag body){
        mktActComTagService.save(body);
        return Response.create(body);
    }


    @ApiOperation(value = "删除")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "删除失败", response = ResponseBodyError.class),
        @ApiResponse(code = 204, message = "删除成功")
    })
    @RequestMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE},method = RequestMethod.DELETE)
    public Response<Void> delete(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id){
        mktActComTagService.removeById(id);
        return Response.empty();
    }


    @ApiOperation(value = "查询详情")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "查询失败", response = ResponseBodyError.class),
        @ApiResponse(code = 200, message = "详情", response = MktActComTag.class)
    })
    @RequestMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE },method = RequestMethod.GET)
    public Response<MktActComTag> get(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id){
        MktActComTag mktActComTag=mktActComTagService.getById(id);
        Response response = Response.ok(mktActComTag);
        return response;
    }


    @ApiOperation(value = "修改")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "修改失败", response = ResponseBodyError.class),
        @ApiResponse(code = 201, message = "修改成功", response = MktActComTag.class) })
    @RequestMapping(value = "/{id}",
    produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE},consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.PATCH)
    public Response<MktActComTag> patch(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id, @ApiParam(value = ""  )  @Validated(ValidGoup.Update.class) @RequestBody MktActComTag body){
        body.setId(id);
        mktActComTagService.updateById(body);
        Response response = Response.ok(body);
        return response;
    }
}

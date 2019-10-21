package com.tydic.dev1.springcloud.example.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tydic.dev1.common.base.api.ResponseEntity;
import com.tydic.dev1.common.base.page.Page;
import com.tydic.dev1.common.base.page.PageRequestBody;
import com.tydic.dev1.springcloud.example.controller.vo.OppSource;
import com.tydic.dev1.springcloud.example.entity.OppSourceEntity;
import com.tydic.dev1.springcloud.example.service.OppSourceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;


/**
* @author
* @since 2019-03-08
*/
@Api(value = "OppSourceApi")
@RestController
@RequestMapping("/oppSource")
public class OppSourceController {
    @Autowired
    private OppSourceService oppSourceService;

    @ApiOperation(value = "根据多种条件查询符合条件的列表，支持分页，输出基本信息列表，", response = OppSource.class, responseContainer = "List")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "列表查询", response = OppSource.class, responseContainer = "List")})
    @RequestMapping(value = "/list",
    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
    method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<IPage> list(@ApiParam(value = "PageRequestBody") @Valid @RequestBody PageRequestBody<OppSource> body) {
        IPage poPage = oppSourceService.page(body, OppSource.class,OppSourceEntity.class);
        ResponseEntity responseEntity = ResponseEntity.build(poPage, HttpStatus.OK);
        return responseEntity;
    }



    @ApiOperation(value = "新增", notes = "", response = OppSource.class)
    @ApiResponses(value = {
    @ApiResponse(code = 201, message = "新增服务提交成功", response = OppSource.class) })
    @RequestMapping(
    produces = { MediaType.APPLICATION_JSON_UTF8_VALUE },
    consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE },
    method = RequestMethod.POST)
    public ResponseEntity<OppSource> add(@ApiParam(value = "")  @Valid @RequestBody OppSource body){
        oppSourceService.save(BeanUtil.toBean(body,OppSourceEntity.class));
        ResponseEntity responseEntity = ResponseEntity.build(body,HttpStatus.CREATED);
        return responseEntity;
    }


    @ApiOperation(value = "删除")
    @ApiResponses(value = {
    @ApiResponse(code = 204, message = "删除服务提交成功") })
    @RequestMapping(value = "/{id}",
    method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id){
        oppSourceService.removeById(id);
        return ResponseEntity.empty();
    }


    @ApiOperation(value = "查询详情")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "返回详情", response = OppSource.class) })
    @RequestMapping(value = "/{id}",
    produces = { MediaType.APPLICATION_JSON_UTF8_VALUE },
    method = RequestMethod.GET)
    public ResponseEntity<OppSource> get(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id){
        OppSourceEntity oppSourceEntity=oppSourceService.getById(id);
        ResponseEntity responseEntity = ResponseEntity.build(BeanUtil.toBean(oppSourceEntity,OppSource.class));
        return responseEntity;
    }

    @ApiOperation(value = "修改")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "修改成功", response = OppSource.class) })
    @RequestMapping(value = "/{id}",
    produces = { MediaType.APPLICATION_JSON_UTF8_VALUE },
    consumes = { "application/json-patch+json" },
    method = RequestMethod.PATCH)
    public ResponseEntity<OppSource> patch(@ApiParam(value = "标识",required=true) @PathVariable("id") Serializable id, @ApiParam(value = ""  )  @Valid @RequestBody OppSource body){
        oppSourceService.updateById(BeanUtil.toBean(body,OppSourceEntity.class));
        ResponseEntity responseEntity = ResponseEntity.build(body);
        return responseEntity;
    }
}

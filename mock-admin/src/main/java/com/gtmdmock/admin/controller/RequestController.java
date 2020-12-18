package com.gtmdmock.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ExpectationService;
import com.gtmdmock.admin.service.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "request相关操作")
@RequestMapping("/request")
@RestController
public class RequestController {

    @Autowired
    RequestService requestService;
    
    @ApiOperation(value = "获取某个ExpectationsId下的所有request")
    @GetMapping("/list")
    public BaseResponseVO getRequests(@RequestParam(value = "expectationsId") Integer expectationsId,
                                      @RequestParam(value = "pn",defaultValue = "1") Integer pageNumber){

        List<Request> requests = requestService.getRequestByExpectationsId(expectationsId);

        PageHelper.startPage(pageNumber,5);

        PageInfo<Request> pageInfo = new PageInfo<>(requests,5);

        return BaseResponseVO.success(pageInfo);
    }

    @ApiOperation(value = "根据requestId获取某个request")
    @GetMapping("/get")
    public BaseResponseVO getRequest(@RequestParam(value = "requestId") Integer requestId){

        Request request = requestService.getRequestById(requestId);
        return BaseResponseVO.success(request);
    }

    @ApiOperation(value = "添加一个request，如果isOpen为1，在添加的同时，也会同步至core")
    @PostMapping("/add")
    public BaseResponseVO addProject(@RequestBody Request request){

        requestService.insertRequestToCore(request);
        return BaseResponseVO.success("添加成功");
    }

    @ApiOperation(value = "更新一个request，可以通过更改isOpen字段，来开启/关闭request所属的期望是否生效")
    @PostMapping("/upd")
    public BaseResponseVO updateProject(@RequestBody Request request){

        requestService.updateRequestOfCore(request);
        return BaseResponseVO.success("添加成功");
    }

    @ApiOperation(value = "根据requestId删除一个request")
    @GetMapping("/del")
    public BaseResponseVO deleteProject(@RequestParam(value = "requestId") Integer requestId){
        requestService.deleteRequestOfCore(requestId);
        return BaseResponseVO.success("success");
    }
}

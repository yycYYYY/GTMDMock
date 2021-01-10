package com.gtmdmock.admin.controller;

import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
                                      @RequestParam(value = "pn", defaultValue = "1") Integer pageNumber,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit){

        List<Request> requests = requestService.getRequestByExpectationsId(expectationsId, pageNumber, limit);
        return BaseResponseVO.success(new PageInfo<>(requests));
    }

    @ApiOperation(value = "根据requestId获取某个request")
    @GetMapping("/get")
    public BaseResponseVO getRequest(@RequestParam(value = "requestId") Integer requestId){

        Request request = requestService.getRequestById(requestId);
        return BaseResponseVO.success(request);
    }

    @ApiOperation(value = "添加一个request，如果isOpen为1，在添加的同时，也会同步至core")
    @PostMapping("/add")
    public BaseResponseVO addRequest(@RequestBody Request request){
        if (request.getIsOpen() == 1){
            requestService.insertRequestToCore(request);
        }else {
            requestService.insertRequest(request);
        }
        return BaseResponseVO.success("添加成功");
    }

    @ApiOperation(value = "更新一个request")
    @PostMapping("/upd")
    public BaseResponseVO updateRequest(@RequestBody Request request){

        requestService.updateRequestOfCore(request);
        return BaseResponseVO.success("添加成功");
    }

    @ApiOperation(value = "根据requestId删除一个request")
    @GetMapping("/del")
    public BaseResponseVO deleteRequest(@RequestParam(value = "requestId") Integer requestId){
        requestService.deleteRequestOfCore(requestId);
        return BaseResponseVO.success("success");
    }

    @ApiOperation("开启/关闭一个期望")
    @GetMapping("/switch")
    public BaseResponseVO switchProject(@RequestParam(value = "requestId") Integer requestId,
                                        @RequestParam(value = "isOpen") Integer isOpen){
        requestService.switchRequest(requestId,isOpen);
        return BaseResponseVO.success("success");
    }
}

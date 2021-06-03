package com.gtmdmock.admin.controller;

import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ExpectationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "expectations相关操作")
@RequestMapping("/expectations")
@RestController
public class ExpectationsController {

    @Autowired
    ExpectationsService expectationsService;

    @ApiOperation("获取某个project下的所有期望集")
    @GetMapping("/list")
    public BaseResponseVO getAllExpectations(@RequestParam(value = "projectId") Integer projectId,
                                             @RequestParam(value = "pn",defaultValue = "1") Integer pageNumber,
                                             @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        List<Expectations> expectations = expectationsService.getAllExpectationsOfAdminByProjectId(projectId,pageNumber,limit);
        return BaseResponseVO.success(new PageInfo<>(expectations));
    }

    @ApiOperation("新增一个期望集")
    @PostMapping("/add")
    public BaseResponseVO addExpectations(@RequestBody Expectations expectations){

        expectationsService.insertExpectations(expectations);
        return BaseResponseVO.success("success");
    }

    @ApiOperation("更新一个期望集")
    @PostMapping("/upd")
    public BaseResponseVO updateExpectations(@RequestBody Expectations expectations){

        expectationsService.updateExpectations(expectations);
        return BaseResponseVO.success("success");
    }

    @ApiOperation("删除一个工程下的所有期望集")
    @GetMapping("/delAll")
    public BaseResponseVO deleteAllExpectations(@RequestParam(value = "projectId") Integer projectId){
        expectationsService.deleteExpectationsByProjectId(projectId);
        return BaseResponseVO.success("success");
    }

    @ApiOperation("通过expectationsId删除一个期望集")
    @GetMapping("/del")
    public BaseResponseVO deleteExpectations(@RequestParam(value = "expectationsId") Integer expectationsId){
        expectationsService.deleteExpectationsOfCoreById(expectationsId);
        return BaseResponseVO.success("success");
    }

    @ApiOperation("开启/关闭一个期望集")
    @GetMapping("/switch")
    public BaseResponseVO switchExpectations(@RequestParam(value = "expectationsId") Integer expectationsId){
        expectationsService.switchExpectations(expectationsId);
        return BaseResponseVO.success("success");
    }
}

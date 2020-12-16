package com.gtmdmock.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ExpectationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "expectations")
@RequestMapping("/expectations")
@RestController
public class ExpectationsController {

    @Autowired
    ExpectationsService expectationsService;

    @ApiOperation("获取某个project下的所有期望集")
    @GetMapping("/list")
    public BaseResponseVO getAllExpectations(@RequestParam(value = "pn",defaultValue = "1") Integer pageNumber,
                                             @RequestParam(value = "projectId") Integer projectId){
        PageHelper.startPage(pageNumber,5);
        List<Expectations> expectations = expectationsService.getAllExpectationsOfAdminByProjectId(projectId);
        PageInfo<Expectations> pageInfo = new PageInfo<>(expectations);
        return BaseResponseVO.success(pageInfo);
    }

    @ApiOperation("新增一个期望集，并同步至core")
    @PostMapping("/add")
    public BaseResponseVO addExpectations(@RequestBody Expectations expectations){

        expectationsService.insertExpectationsToCore(expectations);
        return BaseResponseVO.success("success");
    }

    @ApiOperation("更新一个期望集，并同步至core")
    @PostMapping("/update")
    public BaseResponseVO updateExpectations(@RequestBody Expectations expectations){

        expectationsService.updateExpectationsOfCore(expectations);
        return BaseResponseVO.success("success");
    }

    @ApiOperation("删除一个工程下的所有期望集")
    @GetMapping("/delAll")
    public BaseResponseVO deleteAllExpectations(@RequestParam(value = "projectId") Integer projectId){

        return BaseResponseVO.success("success");
    }

    @ApiOperation("删除一个期望集")
    @GetMapping("/del")
    public BaseResponseVO deleteExpectations(@RequestParam(value = "expectations") Integer expectationsId){
        expectationsService.deleteExpectationsOfCoreById(expectationsId);
        return BaseResponseVO.success("success");
    }
}

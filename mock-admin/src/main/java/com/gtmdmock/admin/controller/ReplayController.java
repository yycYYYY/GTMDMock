package com.gtmdmock.admin.controller;

import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ExpectationsService;
import com.gtmdmock.admin.service.ReplayService;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.admin.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "录制回放相关操作")
@RequestMapping("/record")
@RestController
public class ReplayController {

    @Autowired
    ReplayService replayService;

    @ApiOperation(value = "回放当前所有的请求",notes = "回放此项目所有的接口，但不记录到数据库内（也就是下次项目初始化时，不会声明这些期望）")
    @ApiImplicitParam(name = "projectId",dataType = "Integer",example = "1",value = "项目id")
    @GetMapping("/replayAll")
    public BaseResponseVO replayAllExpectations(@RequestParam(value = "projectId") Integer projectId){
        replayService.replayAll(projectId);
        return BaseResponseVO.success("操作成功");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId",dataType = "Integer", example = "1", value = "项目id"),
            @ApiImplicitParam(name = "path",dataType = "String", example = "/hk", value = "录制路径，递归录制该路径下所有接口")
    })
    @ApiOperation(value = "回放指定路径下的所有请求",notes = "回放此项目某path下所有的接口，但不记录到数据库内（也就是下次项目初始化时，不会声明这些期望）")
    @GetMapping("/replay")
    public BaseResponseVO replayExpectations(@RequestParam(value = "projectId") Integer projectId,
                                             @RequestParam(value = "path")String path){
        replayService.replay(projectId,path);
        return BaseResponseVO.success("操作成功");
    }

    @ApiOperation(value = "回放所有请求，并将请求转化成期望，同步至数据库",notes = "回放此项目所有的接口，且记录到数据库内（也就是下次项目初始化时，会声明这些期望）")
    @GetMapping("/replayAllAndSave")
    public BaseResponseVO replayAllExpectationsAndSave(@RequestParam(value = "projectId") Integer projectId){
        replayService.replayAllAndSave(projectId);
        return BaseResponseVO.success("操作成功");
    }

    @ApiOperation(value = "回放指定路径下所有请求，并将请求转化成期望，同步至数据库",notes = "回放此项目某path下所有的接口，，且记录到数据库内（也就是下次项目初始化时，会声明这些期望）")
    @GetMapping("/replayAndSave")
    public BaseResponseVO replayExpectationsAndSave(@RequestParam(value = "projectId") Integer projectId,
                                             @RequestParam(value = "path")String path){
        replayService.replayAndSave(projectId,path);
        return BaseResponseVO.success("操作成功");
    }


}

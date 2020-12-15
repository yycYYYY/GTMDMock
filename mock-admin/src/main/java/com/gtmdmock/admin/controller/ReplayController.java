package com.gtmdmock.admin.controller;

import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ReplayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("record")
@RequestMapping("/replay")
@RestController
public class ReplayController {

    @Autowired
    ReplayService replayService;

    @ApiOperation("回放当前所有的请求")
    @GetMapping("/replayAll")
    public BaseResponseVO replayAllExpectations(@RequestParam(value = "projectId") Integer projectId){
        replayService.replayAll(projectId);
        return BaseResponseVO.success("操作成功");
    }

    @ApiOperation("回放指定路径下的所有请求")
    @GetMapping("/replay")
    public BaseResponseVO replayExpectations(@RequestParam(value = "projectId") Integer projectId,
                                             @RequestParam(value = "path")String path){
        replayService.replay(projectId,path);
        return BaseResponseVO.success("操作成功");
    }

    @ApiOperation("回放所有请求，并将请求转化成期望，同步至数据库")
    @GetMapping("/replayAllAndSave")
    public BaseResponseVO replayAllExpectationsAndSave(@RequestParam(value = "projectId") Integer projectId){
        replayService.replayAllAndSave(projectId);
        return BaseResponseVO.success("操作成功");
    }

    @ApiOperation("回放指定路径下所有请求，并将请求转化成期望，同步至数据库")
    @GetMapping("/replayAndSave")
    public BaseResponseVO replayExpectationsAndSave(@RequestParam(value = "projectId") Integer projectId,
                                             @RequestParam(value = "path")String path){
        replayService.replayAndSave(projectId,path);
        return BaseResponseVO.success("操作成功");
    }


}

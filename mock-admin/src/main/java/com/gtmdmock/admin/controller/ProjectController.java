package com.gtmdmock.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "project相关操作")
@RequestMapping("/project")
@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @ApiOperation(value = "获取所有的项目")
    @GetMapping("/list")
    public BaseResponseVO getAllProjects(@RequestParam(value = "pn", defaultValue = "1") Integer pageNumber,
                                         @RequestParam(value = "limit", defaultValue = "5") Integer limit){

        List<Project> projects = projectService.getAllProjects(pageNumber,limit);
        return BaseResponseVO.success(new PageInfo<>(projects));
    }

    @ApiOperation(value = "根据projectId获取项目")
    @GetMapping("/get")
    public BaseResponseVO getProjectById(@RequestParam(value = "projectId") Integer projectId){
        Project project = projectService.getProjectById(projectId);
        return BaseResponseVO.success(project);
    }

    @ApiOperation(value = "添加一个项目，如果isOpen为1，在添加的同时，也会同步至core")
    @PostMapping("/add")
    public BaseResponseVO addProject(@RequestBody Project project){

        if (project.getIsOpen() == 1){
            projectService.insertProjectToCore(project);
        }else {
            projectService.insertProject(project);
        }
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "更新一个项目，可以通过更改isOpen字段，来开启/关闭项目是否生效")
    @PostMapping("/upd")
    public BaseResponseVO updateProject(@RequestBody Project project){
        projectService.updateProjectOfCore(project);
        return BaseResponseVO.success("更新成功");
    }

    @ApiOperation(value = "删除一个项目，同时删除core中已经生效的MockServerClient")
    @GetMapping("/del")
    public BaseResponseVO deleteProject(@RequestParam(value = "projectId") Integer projectId){
        projectService.deleteProjectOfCore(projectId);
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "回放某个路径下，所有的请求响应。注意入参path不能以/结尾")
    @GetMapping("/replay")
    public BaseResponseVO replay(@RequestParam(value = "projectId") Integer projectId,
                                 @RequestParam(value = "path",defaultValue = "")String path,
                                 @RequestParam(value = "save",defaultValue = "0")Integer save){
        projectService.replay(projectId,path,save);
        return BaseResponseVO.success("操作成功");
    }

    public BaseResponseVO switchProject(@RequestParam(value = "projectId") Integer projectId,
                                        @RequestParam(value = "isOpen") Integer isOpen){
        projectService.switchProject(projectId,isOpen);
        return BaseResponseVO.success("success");
    }
}

package com.gtmdmock.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.vo.BaseResponseVO;

import com.gtmdmock.admin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/project")
@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/getAll")
    public BaseResponseVO getAllProjects(@RequestParam(value = "pn",defaultValue = "1") Integer pageNumber){
        //TODO:分页这里有点问题，需要再改下
        PageHelper.startPage(pageNumber,5);
        List<Project> projects = projectService.getAllProjects();
        PageInfo<Project> pageInfo = new PageInfo<>(projects,5);
        return BaseResponseVO.success(pageInfo);
    }

    @PostMapping("/add")
    public BaseResponseVO addProject(@RequestParam(value = "pn",defaultValue = "1") Integer pageNumber){
//      TODO: CRUD有点烦，后续等到放松大脑，休息的时候再写
        return BaseResponseVO.success("success");
    }
}

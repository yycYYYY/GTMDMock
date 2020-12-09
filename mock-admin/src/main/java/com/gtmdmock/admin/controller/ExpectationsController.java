package com.gtmdmock.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ExpectationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/expectations")
@RestController
public class ExpectationsController {

    @Autowired
    ExpectationsService expectationsService;

    @GetMapping("/list")
    public BaseResponseVO getAllExpectations(@RequestParam(value = "pn",defaultValue = "1") Integer pageNumber){
        //TODO:待补充
        return null;
    }


    @PostMapping("/add")
    public BaseResponseVO addResponse(@RequestBody Expectations expectations){

        expectationsService.insertExpectationsToCore(expectations);
        return BaseResponseVO.success("success");
    }

    @GetMapping("/del")
    public BaseResponseVO deleteResponse(@RequestParam(value = "projectId") Integer requestId){

        return BaseResponseVO.success("success");
    }
}

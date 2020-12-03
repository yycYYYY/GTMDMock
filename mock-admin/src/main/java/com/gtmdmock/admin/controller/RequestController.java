package com.gtmdmock.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ExpectationService;
import com.gtmdmock.admin.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller("/request")
public class RequestController {

    @Autowired
    RequestService requestService;

    @Autowired
    ExpectationService expectationService;

    @GetMapping("/list")
    public BaseResponseVO getRequests(@RequestParam(value = "projectId") Integer projectId,
                                      @RequestParam(value = "pn",defaultValue = "1") Integer pageNumber){

        List<Request> requests = new ArrayList<>();

        PageHelper.startPage(pageNumber,5);
        List<Expectations> expectationsList = expectationService.getExpectationsByProjectId(projectId);

        for (Expectations expectations: expectationsList){
            requests.addAll(requestService.getRequestByExpectationsId(expectations.getId()));
        }
        PageInfo<Request> pageInfo = new PageInfo<>(requests,5);

        return BaseResponseVO.success(pageInfo);
    }

    @PostMapping("/add")
    public BaseResponseVO addProject(@RequestBody Request request){

        requestService.insertRequestToCore(request);
//      TODO: CRUD有点烦，后续等到放松大脑，休息的时候再写
        return BaseResponseVO.success("success");
    }

    @GetMapping("/del")
    public BaseResponseVO deleteProject(@RequestParam(value = "requestId") Integer requestId){
        requestService.deleteRequest(requestId);
        return BaseResponseVO.success("success");
    }
}

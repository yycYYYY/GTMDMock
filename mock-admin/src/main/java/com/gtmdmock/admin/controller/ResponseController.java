package com.gtmdmock.admin.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/response")
@RestController
public class ResponseController {

    @Autowired
    ResponseService responseService;

    @GetMapping("/get")
    public BaseResponseVO getResponseByRequestId(@RequestParam(value = "requestId") Integer requestId){
        Response response = responseService.getResponsesByRequestId(requestId);

        return BaseResponseVO.success(response);
    }

    @PostMapping("/add")
    public BaseResponseVO addResponse(@RequestBody Response response){

        responseService.insertResponseToCOre(response);
        return BaseResponseVO.success("success");
    }

    @GetMapping("/del")
    public BaseResponseVO deleteResponse(@RequestParam(value = "projectId") Integer requestId){
        responseService.deleteResponseOfCore(requestId);
        return BaseResponseVO.success("success");
    }

}

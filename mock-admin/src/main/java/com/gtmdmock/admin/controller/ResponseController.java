package com.gtmdmock.admin.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "response相关操作")
@RequestMapping("/response")
@RestController
public class ResponseController {

    @Autowired
    ResponseService responseService;

    @ApiOperation(value = "获取某个requestId的response")
    @GetMapping("/get")
    public BaseResponseVO getResponseByRequestId(@RequestParam(value = "requestId") Integer requestId){
        Response response = responseService.getResponsesByRequestId(requestId);

        return BaseResponseVO.success(response);
    }

    @ApiOperation(value = "新增一个response,并同步至core")
    @PostMapping("/add")
    public BaseResponseVO addResponse(@RequestBody Response response){

        responseService.insertResponseToCOre(response);
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "更新一个response,并同步至core")
    @PostMapping("/upd")
    public BaseResponseVO updateResponse(@RequestBody Response response){

        responseService.updateResponseOfCore(response);
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "删除一个response,并同步至core")
    @GetMapping("/del")
    public BaseResponseVO deleteResponse(@RequestParam(value = "requestId") Integer requestId){
        responseService.deleteResponseOfCore(requestId);
        return BaseResponseVO.success("success");
    }

}

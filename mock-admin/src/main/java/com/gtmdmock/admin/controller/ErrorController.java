package com.gtmdmock.admin.controller;

import com.gtmdmock.admin.model.entity.Error;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ErrorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "error")
@RequestMapping("/error")
@RestController
public class ErrorController {

    //TODO:同步至core的部分还没写

    @Autowired
    ErrorService errorService;


    @ApiOperation(value = "获取某个requestId的error")
    @GetMapping("/get")
    public BaseResponseVO getErrorByRequestId(@RequestParam(value = "requestId") Integer requestId){

        Error error = errorService.getErrorByRequestId(requestId);
        return BaseResponseVO.success(error);
    }

    @ApiOperation(value = "新增一个error")
    @PostMapping("/add")
    public BaseResponseVO addError(@RequestBody Error error){

        errorService.insertError(error);
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "更新一个error")
    @PostMapping("/upd")
    public BaseResponseVO updateError(@RequestBody Error error){

        errorService.updateError(error);
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "删除一个error")
    @GetMapping("/del")
    public BaseResponseVO deleteError(@RequestParam(value = "requestId") Integer requestId){
        errorService.deleteErrorByRequestId(requestId);
        return BaseResponseVO.success("success");
    }
}

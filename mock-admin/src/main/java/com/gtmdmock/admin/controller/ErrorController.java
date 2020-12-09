package com.gtmdmock.admin.controller;

import com.gtmdmock.admin.model.entity.Error;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/error")
@RestController
public class ErrorController {

    @Autowired
    ErrorService errorService;

    @GetMapping("/get")
    public BaseResponseVO getErrorByRequestId(@RequestParam(value = "requestId") Integer requestId){

        Error error = errorService.getErrorByRequestId(requestId);
        return BaseResponseVO.success(error);
    }

    @PostMapping("/add")
    public BaseResponseVO addError(@RequestBody Error error){

        errorService.insertError(error);
        return BaseResponseVO.success("success");
    }

    @GetMapping("/del")
    public BaseResponseVO deleteError(@RequestParam(value = "requestId") Integer requestId){
        errorService.deleteErrorByRequestId(requestId);
        return BaseResponseVO.success("success");
    }
}

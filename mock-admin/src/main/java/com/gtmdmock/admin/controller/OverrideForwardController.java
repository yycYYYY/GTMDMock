package com.gtmdmock.admin.controller;

import com.gtmdmock.admin.model.entity.OverrideForward;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.OverrideForwardService;
import com.gtmdmock.admin.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api( tags = "overrideForward相关操作")
@RequestMapping("/overrideMapping")
@RestController
public class OverrideForwardController {

    @Autowired
    OverrideForwardService overrideForwardService;

    @ApiOperation(value = "获取某个requestId的overrideForward")
    @GetMapping("/get")
    public BaseResponseVO getOverrideForwardByRequestId(@RequestParam(value = "requestId") Integer requestId){

        OverrideForward overrideForward = overrideForwardService.getOverrideForwardByRequestId(requestId);
        return BaseResponseVO.success(overrideForward);

    }

    @ApiOperation(value = "新增一个overrideForward,并同步至core")
    @PostMapping("/add")
    public BaseResponseVO addOverrideForward(@RequestBody OverrideForward overrideForward){

        overrideForwardService.insertOverrideForwardToCore(overrideForward);
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "更新一个overrideForward,并同步至core")
    @PostMapping("/upd")
    public BaseResponseVO updateOverrideForward(@RequestBody OverrideForward overrideForward){

        overrideForwardService.updateOverrideForwardOfCore(overrideForward);
        return BaseResponseVO.success("success");
    }


    @ApiOperation(value = "删除一个overrideForward,并同步至core")
    @GetMapping("/del")
    public BaseResponseVO deleteOverrideForward(@RequestParam(value = "projectId") Integer requestId){
        overrideForwardService.deleteOverrideForwardOfCoreById(requestId);
        return BaseResponseVO.success("success");
    }
}

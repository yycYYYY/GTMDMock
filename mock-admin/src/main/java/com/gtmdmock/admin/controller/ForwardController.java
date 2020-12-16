package com.gtmdmock.admin.controller;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ForwardService;
import com.gtmdmock.admin.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "forward")
@RequestMapping("/forward")
@RestController
public class ForwardController {

    @Autowired
    ForwardService forwardService;

    @ApiOperation(value = "获取某个requestId的forward")
    @GetMapping("/get")
    public BaseResponseVO getForwardByRequestId(@RequestParam(value = "requestId") Integer requestId){
        Forward forward = forwardService.getForwardByRequestId(requestId);

        return BaseResponseVO.success(forward);
    }

    @ApiOperation(value = "新增一个forward,并同步至core")
    @PostMapping("/add")
    public BaseResponseVO addForward(@RequestBody Forward forward){

        forwardService.insertForwardToCore(forward);
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "更新一个forward,并同步至core")
    @PostMapping("/upd")
    public BaseResponseVO updateForward(@RequestBody Forward forward){

        forwardService.updateForwardOfCore(forward);
        return BaseResponseVO.success("success");
    }

    @ApiOperation(value = "删除一个forward,并同步至core")
    @GetMapping("/del")
    public BaseResponseVO deleteForward(@RequestParam(value = "requestId") Integer requestId){
        forwardService.deleteForwardOfCore(requestId);
        return BaseResponseVO.success("success");
    }
}

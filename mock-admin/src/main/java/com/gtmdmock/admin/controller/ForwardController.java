package com.gtmdmock.admin.controller;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.ForwardService;
import com.gtmdmock.admin.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/forward")
@RestController
public class ForwardController {
    @Autowired
    ForwardService forwardService;

    @GetMapping("/get")
    public BaseResponseVO getForwardByRequestId(@RequestParam(value = "requestId") Integer requestId){
        Forward forward = forwardService.getForwardByRequestId(requestId);

        return BaseResponseVO.success(forward);
    }

    @PostMapping("/add")
    public BaseResponseVO addForward(@RequestBody Forward forward){

        forwardService.insertForwardToCore(forward);
        return BaseResponseVO.success("success");
    }

    @GetMapping("/del")
    public BaseResponseVO deleteForward(@RequestParam(value = "projectId") Integer requestId){
        forwardService.deleteForwardByRequestId(requestId);
        return BaseResponseVO.success("success");
    }
}

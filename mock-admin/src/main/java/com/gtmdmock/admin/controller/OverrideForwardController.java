package com.gtmdmock.admin.controller;

import com.gtmdmock.admin.model.entity.OverrideForward;
import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.vo.BaseResponseVO;
import com.gtmdmock.admin.service.OverrideForwardService;
import com.gtmdmock.admin.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/overrideMapping")
@RestController
public class OverrideForwardController {

    @Autowired
    OverrideForwardService overrideForwardService;

    @GetMapping("/get")
    public BaseResponseVO getOverrideForwardByRequestId(@RequestParam(value = "requestId") Integer requestId){

        OverrideForward overrideForward = overrideForwardService.getOverrideForwardByRequestId(requestId);
        return BaseResponseVO.success(overrideForward);

    }

    @PostMapping("/add")
    public BaseResponseVO addOverrideForward(@RequestBody OverrideForward overrideForward){

        overrideForwardService.insertOverrideForwardToCore(overrideForward);
//      TODO: CRUD有点烦，后续等到放松大脑，休息的时候再写
        return BaseResponseVO.success("success");
    }

    @GetMapping("/del")
    public BaseResponseVO deleteOverrideForward(@RequestParam(value = "projectId") Integer requestId){
        overrideForwardService.deleteOverrideForwardOfCoreById(requestId);
        return BaseResponseVO.success("success");
    }
}

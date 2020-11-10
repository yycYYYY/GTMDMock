package com.gtmdmock.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthCheck {

    @GetMapping("/healthCheck")
    @ResponseBody
    public String healthCheck(){
        return "hello world";
    }
}

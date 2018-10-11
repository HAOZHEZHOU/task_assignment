package com.yc.tmwk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="staff/index")
public class StaffIndexController {

    @RequestMapping(value="index")
    public String index(){
        return "staff/index";
    }



}

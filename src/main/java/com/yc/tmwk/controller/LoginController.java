package com.yc.tmwk.controller;

import com.yc.tmwk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping(value="login")
public class LoginController {

    static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String index(){
        return "login/index";
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(){
        return "login/index";
    }

}

package com.yc.tmwk.controller;

import com.yc.tmwk.model.*;
import com.yc.tmwk.service.AttachmentService;
import com.yc.tmwk.service.ChatService;
import com.yc.tmwk.service.UserRoleService;
import com.yc.tmwk.service.UserService;
import com.yc.tmwk.util.JsonResults;
import com.yc.tmwk.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RequestMapping(value="welcome")
@Controller
public class WelcomeController {

    private final static Logger log = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    ChatService chatService;

    @RequestMapping(value="index")
    public String index(){
        return "welcome/index";
    }

    @RequestMapping(value="grant")
    @ResponseBody
    public JsonResults t(String id, HttpSession session){
        JsonResults results = new JsonResults();
        session.setAttribute("id",id);
        results.setResult_msg("id:"+(String)session.getAttribute("id"));
        log.info("role list：" + results.toString());
        return results;
    }

    @RequestMapping(value="demo")
    @ResponseBody
    public String demo(HttpSession session){

        return (String)session.getAttribute("id");
    }



    @RequestMapping(value="test")
    @ResponseBody
    public String test(){
        //List<Attachment> lst = attachmentService.findByFk("a",null,null);
        Chat chat = new Chat();
        chat.setState(Short.valueOf("0"));
        chat.setChatFrom(2);
        chat.setChatTo(3);
        chat.setText("sdf");
        chatService.insert(chat);
        return "test" ;
    }



    @RequestMapping(value="dispatcher")
    public String  dispatcher(HttpSession session){

        JsonResults results  = new JsonResults();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());

        if(roles.contains("1")){
            log.debug("Auto redirect, admin index");
            return "redirect:admin/index/index";
        }/*else if(roles.contains("2")){
            return "redirect:customer/index/index";
        }*/else if(roles.contains("2")){
            log.debug("Auto redirect, staff index");
            return "redirect:staff/index/index";
        }else{
            return "redirect:index";
        }
    }

    @RequestMapping(value="upload")
    public String toUpload(){
        return "welcome/upload";
    }

    @RequestMapping(value="do_upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){
        log.error("Upload file：" + file);

        log.error("file is empty:" + file.isEmpty());


        if(file != null){
            String path = UploadUtil.upload(file);
            return "dsf"+ path;
        }else{
            return "";
        }


    }

}

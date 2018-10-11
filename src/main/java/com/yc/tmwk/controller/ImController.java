package com.yc.tmwk.controller;

import com.yc.tmwk.model.Chat;
import com.yc.tmwk.model.Users;
import com.yc.tmwk.service.ChatService;
import com.yc.tmwk.service.UserService;
import com.yc.tmwk.util.JsonResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value="staff/im")
public class ImController {

    private static final Logger log = LoggerFactory.getLogger(ImController.class);

    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;



    @RequestMapping(value="index")
    public String index(Model model, HttpSession session){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Users user = userService.queryByName(userDetails.getUsername());
        session.setAttribute("user",user);

        Users leader = userService.queryOneByKey(user.getPid());
        List<Users> lst = userService.queryByPid(user.getUserId());

        model.addAttribute("leader",leader);
        model.addAttribute("lst",lst);
        model.addAttribute("user",user);

        return "staff/im/index";
    }


    @RequestMapping(value="record")
    @ResponseBody
    public JsonResults record(Integer id,Model model,HttpSession session){
        JsonResults results = new JsonResults();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Users user = userService.queryByName(userDetails.getUsername());
        log.debug("InfoList");
        log.debug("Param ID：" + id);
        log.debug("param user：" + user.toString());
        //Get latest news
        List<Chat> latest = chatService.getLatestRecord(id,user.getUserId(),0,20);
        //Update has been read
        chatService.setRead(id,user.getUserId());

        model.addAttribute("latest",latest);
        HashMap<String,Object> arr = new HashMap<String,Object>();
        arr.put("msg",latest);
        results.setResult_arr(arr);
        return results;
    }



}

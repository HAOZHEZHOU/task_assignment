package com.yc.tmwk.controller;

import com.yc.tmwk.model.*;
import com.yc.tmwk.service.AttachmentService;
import com.yc.tmwk.service.TaskRecordService;
import com.yc.tmwk.service.TaskService;
import com.yc.tmwk.service.UserService;
import com.yc.tmwk.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="staff/task")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    TaskRecordService taskRecordService;
    @Autowired
    AttachmentService attachmentService;

    public String index(){

        return "staff/task/index";
    }

    // publish task

    @RequestMapping(value="pub_lst")
    public String pubLst(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Users user = userService.queryByName(userDetails.getUsername());

        List<Task> lst = taskService.queryByFrom(user.getUserId());
        model.addAttribute("list",lst);
        return "staff/task/pub_lst";
    }

    // Personal received task
    @RequestMapping(value="recv_lst")
    public String recvLst(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Users user = userService.queryByName(userDetails.getUsername());

        List<Task> lst = taskService.queryByTo(user.getUserId());
        model.addAttribute("list",lst);
        return "staff/task/recv_lst";
    }

    // Task insert

    @RequestMapping(value="add")
    public String add(Integer id, Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Users user = userService.queryByName(userDetails.getUsername());
        List<Users> userList = userService.queryByPid(user.getUserId());
        model.addAttribute("userList",userList);
        model.addAttribute("id",id);
        return "staff/task/add";
    }

    @RequestMapping(value="do_add")
    public String doAdd(Task task){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Users user = userService.queryByName(userDetails.getUsername());



        task.setCreateDate(new Date());
        task.setState(0);
        task.setTaskFrom(user.getUserId());
        taskService.insert(task);
        return "redirect:pub_lst";
    }

    // Task edit
    @RequestMapping(value="edit")
    public String edit(Integer id,Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Users user = userService.queryByName(userDetails.getUsername());
        List<Users> userList = userService.queryByPid(user.getUserId());
        model.addAttribute("userList",userList);

        Task item = taskService.queryOneByKey(id);
        model.addAttribute("item",item);
        return "staff/task/edit";
    }

    @RequestMapping(value="do_edit")
    public String doEdit(Task task,Model model){
        taskService.update(task);
        return "redirect:pub_lst";
    }

    // Task delete
    @RequestMapping(value="delete")
    public String delete(Integer id){
        taskService.delete(id);
        return "redirect:pub_lst";
    }

    @RequestMapping(value="detail")
    public String detail(Integer id, Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Users user = userService.queryByName(userDetails.getUsername());


        Task item = taskService.queryOneByKey(id);
        List<UserTaskRecord>  list = taskRecordService.queryByTask(id);

        for(int  x = 0;x<list.size();x++){
            UserTaskRecord temp = list.get(x);
            String path = "";
            List<Attachment> files = attachmentService.findByFk("task_record","record_id",temp.getRecordId());
            for(int y = 0 ; y < files.size(); y++){
                path += files.get(y).getPath() + ",";
            }
            temp.setPath(path);
            list.set(x,temp);
            log.error("Task info [" + x + "] :" + temp);
            log.error("Attachments counts：" + files.size());
        }

        model.addAttribute("user",user);
        model.addAttribute("item",item);
        model.addAttribute("list",list);

        log.error("Task Detal：" + item.toString());
        log.error("Record counts："+list.size());
        return "staff/task/detail";
    }

    @RequestMapping(value="add_record")
    public String addRecord(Integer id,Model model){
        model.addAttribute("id",id);
        return "staff/task/record/add";
    }

    @RequestMapping(value="do_add_record")
    public String doAddRecord(@RequestParam("file")MultipartFile file, TaskRecord taskRecord, Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Users user = userService.queryByName(userDetails.getUsername());

        taskRecord.setCreateDate(new Date());
        taskRecord.setUserId(user.getUserId());
        log.debug("记录增加：" + taskRecord.toString());
        taskRecordService.insert(taskRecord);


        if(file != null && !file.isEmpty()){
            TaskRecord latestRecord = taskRecordService.queryLatest(taskRecord.getUserId(),taskRecord.getTaskId());
            String filePath = UploadUtil.upload(file);
            Attachment attachment = new Attachment();

            attachment.setCreateDate(new Date());
            attachment.setFkTable("task_record");
            attachment.setFkName("record_id");
            attachment.setFkId(latestRecord.getRecordId());
            attachment.setPath(filePath);

            attachmentService.insert(attachment);
            log.debug("文件上传：" + attachment);
        }


        return "redirect:detail?id="+taskRecord.getTaskId();
    }


    @RequestMapping(value="download")
    @ResponseBody
    public void download(String path,HttpServletResponse response){
        UploadUtil.download(path,response);
    }

    @RequestMapping(value="edit_tag")
    public String editTag(Integer id,Model model){

        return "staff/task/tag/edit_tag";
    }

}

package com.yc.tmwk.controller;


import com.yc.tmwk.model.Attachment;
import com.yc.tmwk.model.Task;
import com.yc.tmwk.model.UserTaskRecord;
import com.yc.tmwk.model.Users;
import com.yc.tmwk.service.AttachmentService;
import com.yc.tmwk.service.TaskRecordService;
import com.yc.tmwk.service.TaskService;
import com.yc.tmwk.service.UserService;
import com.yc.tmwk.util.JsonResults;
import com.yc.tmwk.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RequestMapping(value="admin/task")
@Controller
public class AdminTaskController {

    private static final Logger log = LoggerFactory.getLogger(AdminTaskController.class);

    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    TaskRecordService taskRecordService;

    @RequestMapping(value="user_tree")
    public String userTree(Model model){

        List<Users> list = userService.queryByPid(0);

        model.addAttribute("list",list);
        return "admin/task/user_tree";
    }

    @RequestMapping(value="user_child")
    @ResponseBody
    public HashMap<String,Object> userChild(Integer id,Model model){
        List<Users> lst = userService.queryByPid(id);
        HashMap<String,Object> result = new HashMap<String,Object>();
        result.put("arr",lst);
        return result;
    }

    @RequestMapping(value="child")
    @ResponseBody
    public JsonResults child(Integer id,Model model){
        JsonResults jsonResults = new JsonResults();
        HashMap<String,List<Task>> map ;

        return jsonResults;
    }

    @RequestMapping(value="user_task")
    @ResponseBody
    public HashMap<String,Object> userTask(Integer id,Model model){
        List<Task> list = taskService.queryByTo(id);
        HashMap<String,Object> result = new HashMap<String,Object>();
        result.put("arr",list);
        return result;
    }


    @RequestMapping(value="task_file_list")
    @ResponseBody
    public HashMap<String,Object> taskFileList(Integer id,Model mode){
        HashMap<String,Object> result = new HashMap<String,Object>();
        List<Attachment> list = attachmentService.getTaskAttachment(id);
        // todo add taskAttachment
        result.put("arr",list);
        return result;
    }


    @RequestMapping(value="download")
    @ResponseBody
    public void download(String path, HttpServletResponse response){
        UploadUtil.download(path,response);
    }


    @RequestMapping(value="task_search")
    @ResponseBody
    public List<Task> taskSearch(String q){
        HashMap<String,Object> result = new HashMap<String,Object>();
        List<Task> list = taskService.queryByTaskName(q);
        result.put("arr",list);
        return list;
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
            log.error("Record [" + x + "] :" + temp);
            log.error("Attachment counts：" + files.size());
        }

        model.addAttribute("user",user);
        model.addAttribute("item",item);
        model.addAttribute("list",list);

        log.error("Task detail：" + item.toString());
        log.error("Record counts："+list.size());
        return "admin/task/detail";
    }

}

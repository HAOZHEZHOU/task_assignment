package com.yc.tmwk.controller;

import com.yc.tmwk.model.Tag;
import com.yc.tmwk.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="admin/tag")
public class AdminTagController {

    private static final Logger log = LoggerFactory.getLogger(AdminTagController.class);

    @Autowired
    TagService tagService;

    @RequestMapping(value="lst")
    public String lst(Model model){
        List<Tag> lst = tagService.lst();

        model.addAttribute("list",lst);
        return "admin/tag/lst";
    }


    @RequestMapping(value="add")
    public String add(){
        return "admin/tag/add";
    }

    @RequestMapping(value="do_add")
    public String doAdd(Tag tag){
        tagService.insert(tag);
        return "redirect:lst";
    }

    @RequestMapping(value="edit")
    public String edit(Integer id,Model model){
        Tag item = tagService.queryOneByKey(id);
        model.addAttribute("item",item);
        return "admin/tag/edit";
    }

    @RequestMapping(value="do_edit")
    public String doEdit(Tag tag){
        tagService.update(tag);
        return "redirect:lst";
    }

    @RequestMapping(value="delete")
    public String delete(Integer id){
        tagService.delete(id);
        return "redirect:lst";
    }

}

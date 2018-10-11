package com.yc.tmwk.controller;

import com.yc.tmwk.model.Role;
import com.yc.tmwk.model.UserRole;
import com.yc.tmwk.model.UserRoleKey;
import com.yc.tmwk.model.Users;
import com.yc.tmwk.service.RoleService;
import com.yc.tmwk.service.UserRoleService;
import com.yc.tmwk.service.UserService;
import com.yc.tmwk.util.JsonResults;
import com.yc.tmwk.util.QrcodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="admin")
public class AdminController {

    final static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;
    @Autowired
    QrcodeService qrcodeService;


    @RequestMapping(value="index/index")
    public String index(){
        logger.error("View admin/index");
        return "admin/index";
    }





    @RequestMapping(value="user/child")
    public String userChild(Integer id,Model model){

        List<Users> lst = userService.queryByPid(id);
        model.addAttribute("list",lst);
        model.addAttribute("id",id);

        Users user = userService.queryOneByKey(id);
        model.addAttribute("user",user);
        return "admin/user/child";
    }

    @RequestMapping(value="user/lst")
    public String userLst(Integer r, Model model){

        r = r==null ? 3:r;
        UserRoleKey key = new UserRoleKey();
        key.setRoleId(r);
        List<Users> lst = userService.queryByR(r);
        model.addAttribute("list",lst);
        model.addAttribute("r",r);

        return "admin/user/lst";
    }

    @RequestMapping(value="user/add")
    public String userAdd(Integer pid,Model model){
        model.addAttribute("pid",pid);
        return "admin/user/add";
    }

    @RequestMapping(value="user/do_add")
    public String userDoAdd(Users user){
        logger.warn("Insert Staff:" + user.toString());

        user.setLoginPwd(new BCryptPasswordEncoder().encode(user.getLoginPwd()));
        userService.insert(user);
        int id = userService.queryByName(user.getLoginName()).getUserId();
        logger.warn("New staff Id:" + id);
        UserRole userRole = new UserRole();
        userRole.setUserId(id);
        userRole.setRoleId(2);
        userRoleService.insert(userRole);
        logger.warn("Set User role:" + userRole.toString());
        if(user.getPid() == null){
            return "redirect:lst?r=2";
        }else{
            return "redirect:child?id="+user.getPid();
        }

    }

    @RequestMapping(value="user/edit")
    public String userEdit(Integer id,Model model){
        Users user = userService.queryOneByKey(id);
        model.addAttribute("item", user);
        return "admin/user/edit";
    }

    @RequestMapping(value="user/update")
    public String userUpdate(Users user, Model model){
        if(user.getLoginPwd().length() == 0){
            user.setLoginPwd(null);
        }else{
            user.setLoginPwd(new BCryptPasswordEncoder().encode(user.getLoginPwd()));
        }
        logger.debug("[user]：" + user.toString());
        userService.update(user);
        return "redirect:lst?r=3";
    }

    @RequestMapping(value="user/delete")
    public String userDelete(Integer id, Model mode){
        userService.delete(id);
        return "redirect:lst?r=3";
    }



    // 角色增删改查
    @RequestMapping(value="role/lst")
    public String roleLst(Model model){
        List<Role> lst = roleService.lst();
        model.addAttribute("list",lst);
        return "admin/role/lst";
    }

    @RequestMapping(value="role/add")
    public String roleAdd(){
        return "admin/role/add";
    }

    @RequestMapping(value="role/do_add")
    public String roleDoAdd(Role role){
        roleService.insert(role);
        return "redirect:lst";
    }

    @RequestMapping(value="role/delete")
    public String roleDelete(Integer id){
        if(id < 3){
            logger.error("Warning, could not delete system role");
        }else{
            roleService.delete(id);
        }
        return "redirect:lst";
    }

    @RequestMapping(value="role/edit")
    public String roleEdit(Integer id,Model model){
        if(id < 3){
            logger.error("warning，could not delete system role");
            return "redirect:lst";
        }else{
            Role role = roleService.queryByKey(id);
            model.addAttribute("item", role);
            return "admin/role/edit";
        }
    }

    @RequestMapping(value="role/update")
    public String roleUpdate(Role role){
        if( role.getRoleId() < 2){
            logger.error("warning，could not delete system role");
        }else{
            roleService.update(role);
        }
        return "redirect:lst";
    }


    // 用户角色编辑
    @RequestMapping(value="user/role_list")
    public String userOfRoles(Integer id,Model model){

        //user's role
        List<Role> userRole = userRoleService.findByUserId(id);

        //all role
        List<Role> all = roleService.lst();

        model.addAttribute("user_role",userRole);
        model.addAttribute("all",all);
        logger.debug("System Role list:" + all);
        logger.debug("User role:" + userRole);
        return "admin/user/role_list";
    }

    @RequestMapping(value="user/save_roles")
    @ResponseBody
    @Transactional
    public JsonResults saveUserRoles(Integer[] ids, Integer user_id){
        logger.info("Save User Roles" + ids.length + "\n" + user_id);
        JsonResults result = new JsonResults();
        try{
            // remove all user auth
            UserRoleKey key = new UserRoleKey();
            key.setUserId(user_id);
            userRoleService.deleteByUserId(key);
            logger.debug("delete all user's role");
            // insert new user auth
            UserRole userRole = new UserRole();
            userRole.setUserId(user_id);
            for(int x = 0 ; x < ids.length; x++){
                userRole.setRoleId(ids[x]);
                userRoleService.insert(userRole);
            }
            logger.debug("batch insert user'role");
        }catch(Exception e){
            result.setResult_code(JsonResults.FAILED);
            result.setResult_msg("Db insert role back");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return result;
        }
    }

}

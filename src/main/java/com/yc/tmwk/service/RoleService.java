package com.yc.tmwk.service;

import com.yc.tmwk.dao.RoleMapper;
import com.yc.tmwk.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public void insert(Role role){
        roleMapper.insert(role);
    }

    public void delete(Integer key){
        roleMapper.deleteByPrimaryKey(key);
    }

    public void update(Role role){
        roleMapper.updateByPrimaryKeySelective(role);
    }

    public Role queryByKey(Integer key){
        return roleMapper.selectByPrimaryKey(key);
    }

    public List<Role> lst(){
        return roleMapper.lst();
    }

}

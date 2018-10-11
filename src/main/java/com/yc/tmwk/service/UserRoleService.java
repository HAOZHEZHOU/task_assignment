package com.yc.tmwk.service;

import com.yc.tmwk.dao.UserRoleMapper;
import com.yc.tmwk.model.Role;
import com.yc.tmwk.model.UserRole;
import com.yc.tmwk.model.UserRoleKey;
import com.yc.tmwk.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    public UserRole queryOneByKey(UserRoleKey key){
        return userRoleMapper.selectByPrimaryKey(key);
    }

    public List<UserRole> queryAllByKey(UserRoleKey key){
        return userRoleMapper.queryAllByKey(key);
    }

    public void insert(UserRole item){
        userRoleMapper.insertSelective(item);
    }

    public void update(UserRole item){
        userRoleMapper.updateByPrimaryKeySelective(item);
    }

    public void delete(UserRoleKey key){
        userRoleMapper.deleteByPrimaryKey(key);
    }

    public List<Role> findByUserId(Integer id){
        return userRoleMapper.queryAllUserRole(id);
    }

    public void deleteByUserId(UserRoleKey key){
        userRoleMapper.deleteByUserId(key);
    }
}

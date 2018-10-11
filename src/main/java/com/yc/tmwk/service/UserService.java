package com.yc.tmwk.service;

import com.yc.tmwk.dao.UsersMapper;
import com.yc.tmwk.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersMapper usersMapper;

    public Users queryOneByKey(Integer key){
        return usersMapper.selectByPrimaryKey(key);
    }

    public void insert(Users item){
        usersMapper.insertSelective(item);
    }

    public void update(Users item){
        usersMapper.updateByPrimaryKeySelective(item);
    }

    public void delete(Integer key){
        usersMapper.deleteByPrimaryKey(key);
    }

    public Users queryByName(String name){
        return usersMapper.queryByName(name);
    }


    public List<Users> queryByR(Integer r){
        return usersMapper.queryByR(r);
    }

    public List<Users> queryByPid(Integer id){
        return usersMapper.queryByPid(id);
    }
}

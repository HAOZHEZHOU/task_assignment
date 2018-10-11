package com.yc.tmwk.service;

import com.yc.tmwk.dao.TaskMapper;
import com.yc.tmwk.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskMapper taskMappper;

    public Task queryOneByKey(Integer key){
        return taskMappper.selectByPrimaryKey(key);
    }

    public void insert(Task item){
        taskMappper.insertSelective(item);
    }

    public void update(Task item){
        taskMappper.updateByPrimaryKeySelective(item);
    }

    public void delete(Integer key){
        taskMappper.deleteByPrimaryKey(key);
    }

    public List<Task> queryByFrom(Integer from){
        return taskMappper.queryByFrom(from);
    }

    public List<Task> queryByTo(Integer from){
        return taskMappper.queryByTo(from);
    }

    public List<Task> queryByPid(Integer pid){
        return taskMappper.queryByPid(pid);
    }

    public List<Task> queryByTaskName(String name){
        return taskMappper.queryByKey(name);
    }

}

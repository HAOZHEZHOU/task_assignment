package com.yc.tmwk.service;

import com.yc.tmwk.dao.TaskRecordMapper;
import com.yc.tmwk.model.TaskRecord;
import com.yc.tmwk.model.UserTaskRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskRecordService {

    @Autowired
    TaskRecordMapper taskRecordMapper;

    public TaskRecord queryOneByKey(Integer key){
        return taskRecordMapper.selectByPrimaryKey(key);
    }


    public void insert(TaskRecord item){
        taskRecordMapper.insertSelective(item);
    }

    public void update(TaskRecord item){
        taskRecordMapper.updateByPrimaryKeySelective(item);
    }

    public void delete(Integer key){
        taskRecordMapper.deleteByPrimaryKey(key);
    }

    public List<UserTaskRecord> queryByTask(Integer id){
        return taskRecordMapper.queryByTask(id);
    }

    public TaskRecord queryLatest(Integer userId,Integer taskId){
        return taskRecordMapper.queryLatest(userId,taskId);
    }


}

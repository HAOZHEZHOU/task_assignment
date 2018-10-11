package com.yc.tmwk.service;


import com.yc.tmwk.dao.TagMapper;
import com.yc.tmwk.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagMapper tagMapper;

    public Tag queryOneByKey(Integer key){
        return tagMapper.selectByPrimaryKey(key);
    }


    public void insert(Tag item){
        tagMapper.insertSelective(item);
    }

    public void update(Tag item){
        tagMapper.updateByPrimaryKeySelective(item);
    }

    public void delete(Integer key){
        tagMapper.deleteByPrimaryKey(key);
    }

    public List<Tag> lst(){
        return tagMapper.lst();
    }

    public List<Tag> queryByFk(String table,String name,Integer id){
        return tagMapper.queryByFk(table,name,id);
    }

}

package com.yc.tmwk.service;

import com.yc.tmwk.dao.ChatMapper;
import com.yc.tmwk.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    ChatMapper chatMapper;

    public Chat queryOneByKey(Integer key){
        return chatMapper.selectByPrimaryKey(key);
    }


    public void insert(Chat item){
        chatMapper.insertSelective(item);
    }

    public void update(Chat item){
        chatMapper.updateByPrimaryKeySelective(item);
    }

    public void delete(Integer key){
        chatMapper.deleteByPrimaryKey(key);
    }


    public List<Chat> getLatestRecord(Integer from, Integer to,Integer pageStart,Integer pageSize){
        return chatMapper.getLatestRecord(from,to, pageStart, pageSize);
    }

    /**
     * update the Chat information between  two users, set the state=1
     * @param from
     * @param to
     */
    public void setRead(Integer from,Integer to){
        chatMapper.setRead(from,to);
    }

}

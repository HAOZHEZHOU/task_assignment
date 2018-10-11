package com.yc.tmwk.service;

import com.yc.tmwk.dao.AttachmentMapper;
import com.yc.tmwk.model.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentService {

    @Autowired
    AttachmentMapper attachmentMapper;
    
    public Attachment queryOneByKey(Integer key){
        return attachmentMapper.selectByPrimaryKey(key);
    }


    public void insert(Attachment item){
        attachmentMapper.insertSelective(item);
    }

    public void update(Attachment item){
        attachmentMapper.updateByPrimaryKeySelective(item);
    }

    public void delete(Integer key){
        attachmentMapper.deleteByPrimaryKey(key);
    }

    public List<Attachment> findByFk(String fkTable, String fkName, Integer fkId){
        return attachmentMapper.queryByFk(fkTable,fkName,fkId);
    }


    public List<Attachment> getTaskAttachment(Integer id){
        return attachmentMapper.getTaskAttachment(id);
    }

}

package com.yc.tmwk.dao;

import com.yc.tmwk.model.TaskTagKey;
import org.apache.ibatis.jdbc.SQL;

public class TaskTagSqlProvider {

    public String insertSelective(TaskTagKey record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("task_tag");
        
        if (record.getTaskId() != null) {
            sql.VALUES("task_id", "#{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getTagId() != null) {
            sql.VALUES("tag_id", "#{tagId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }
}
package com.yc.tmwk.dao;

import com.yc.tmwk.model.Task;
import org.apache.ibatis.jdbc.SQL;

public class TaskSqlProvider {

    public String insertSelective(Task record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("task");
        
        if (record.getTaskId() != null) {
            sql.VALUES("task_id", "#{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getTaskFrom() != null) {
            sql.VALUES("task_from", "#{taskFrom,jdbcType=INTEGER}");
        }
        
        if (record.getTaskTo() != null) {
            sql.VALUES("task_to", "#{taskTo,jdbcType=INTEGER}");
        }
        
        if (record.getpId() != null) {
            sql.VALUES("p_id", "#{pId,jdbcType=INTEGER}");
        }
        
        if (record.getTaskName() != null) {
            sql.VALUES("task_name", "#{taskName,jdbcType=VARCHAR}");
        }
        
        if (record.getTaskContent() != null) {
            sql.VALUES("task_content", "#{taskContent,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Task record) {
        SQL sql = new SQL();
        sql.UPDATE("task");
        
        if (record.getTaskFrom() != null) {
            sql.SET("task_from = #{taskFrom,jdbcType=INTEGER}");
        }
        
        if (record.getTaskTo() != null) {
            sql.SET("task_to = #{taskTo,jdbcType=INTEGER}");
        }
        
        if (record.getpId() != null) {
            sql.SET("p_id = #{pId,jdbcType=INTEGER}");
        }
        
        if (record.getTaskName() != null) {
            sql.SET("task_name = #{taskName,jdbcType=VARCHAR}");
        }
        
        if (record.getTaskContent() != null) {
            sql.SET("task_content = #{taskContent,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=INTEGER}");
        }
        
        sql.WHERE("task_id = #{taskId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}
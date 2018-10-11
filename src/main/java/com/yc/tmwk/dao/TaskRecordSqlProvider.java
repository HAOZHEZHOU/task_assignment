package com.yc.tmwk.dao;

import com.yc.tmwk.model.TaskRecord;
import org.apache.ibatis.jdbc.SQL;

public class TaskRecordSqlProvider {

    public String insertSelective(TaskRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("task_record");
        
        if (record.getRecordId() != null) {
            sql.VALUES("record_id", "#{recordId,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getTaskId() != null) {
            sql.VALUES("task_id", "#{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getText() != null) {
            sql.VALUES("text", "#{text,jdbcType=VARCHAR}");
        }

        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TaskRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("task_record");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getTaskId() != null) {
            sql.SET("task_id = #{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getText() != null) {
            sql.SET("text = #{text,jdbcType=VARCHAR}");
        }

        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("record_id = #{recordId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}
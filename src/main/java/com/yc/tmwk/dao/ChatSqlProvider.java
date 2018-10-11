package com.yc.tmwk.dao;

import com.yc.tmwk.model.Chat;
import org.apache.ibatis.jdbc.SQL;

public class ChatSqlProvider {

    public String insertSelective(Chat record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("chat");
        
        if (record.getChatId() != null) {
            sql.VALUES("chat_id", "#{chatId,jdbcType=INTEGER}");
        }
        
        if (record.getChatFrom() != null) {
            sql.VALUES("chat_from", "#{chatFrom,jdbcType=INTEGER}");
        }
        
        if (record.getChatTo() != null) {
            sql.VALUES("chat_to", "#{chatTo,jdbcType=INTEGER}");
        }
        
        if (record.getText() != null) {
            sql.VALUES("text", "#{text,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=SMALLINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Chat record) {
        SQL sql = new SQL();
        sql.UPDATE("chat");
        
        if (record.getChatFrom() != null) {
            sql.SET("chat_from = #{chatFrom,jdbcType=INTEGER}");
        }
        
        if (record.getChatTo() != null) {
            sql.SET("chat_to = #{chatTo,jdbcType=INTEGER}");
        }
        
        if (record.getText() != null) {
            sql.SET("text = #{text,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=SMALLINT}");
        }
        
        sql.WHERE("chat_id = #{chatId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}
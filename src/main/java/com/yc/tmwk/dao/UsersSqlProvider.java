package com.yc.tmwk.dao;

import com.yc.tmwk.model.Users;
import org.apache.ibatis.jdbc.SQL;

public class UsersSqlProvider {

    public String insertSelective(Users record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("users");
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getLoginName() != null) {
            sql.VALUES("login_name", "#{loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getNick() != null) {
            sql.VALUES("nick", "#{nick,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.VALUES("avatar", "#{avatar,jdbcType=VARCHAR}");
        }
        
        if (record.getPid() != null) {
            sql.VALUES("pid", "#{pid,jdbcType=INTEGER}");
        }
        
        if (record.getLoginPwd() != null) {
            sql.VALUES("login_pwd", "#{loginPwd,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Users record) {
        SQL sql = new SQL();
        sql.UPDATE("users");
        
        if (record.getLoginName() != null) {
            sql.SET("login_name = #{loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getNick() != null) {
            sql.SET("nick = #{nick,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.SET("avatar = #{avatar,jdbcType=VARCHAR}");
        }
        
        if (record.getPid() != null) {
            sql.SET("pid = #{pid,jdbcType=INTEGER}");
        }
        
        if (record.getLoginPwd() != null) {
            sql.SET("login_pwd = #{loginPwd,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("user_id = #{userId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}
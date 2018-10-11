package com.yc.tmwk.dao;

import com.yc.tmwk.model.UserRole;
import com.yc.tmwk.model.UserRoleKey;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRoleSqlProvider {

    private final static Logger log = LoggerFactory.getLogger(UserRoleSqlProvider.class);

    public String insertSelective(UserRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_role");
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getEditable() != null) {
            sql.VALUES("editable", "#{editable,jdbcType=SMALLINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserRole record) {
        SQL sql = new SQL();
        sql.UPDATE("user_role");
        
        if (record.getEditable() != null) {
            sql.SET("editable = #{editable,jdbcType=SMALLINT}");
        }
        
        sql.WHERE("user_id = #{userId,jdbcType=INTEGER}");
        sql.WHERE("role_id = #{roleId,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    public String queryAllByKey(UserRoleKey key) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("user_role");

        if (key.getUserId() != null) {
            sql.WHERE("user_id = #{userId,jdbcType=INTEGER}");
        }

        if(key.getRoleId() != null){
            sql.WHERE("role_id=#{roleId,jdbcType=INTEGER}");
        }

        return sql.toString();
    }
}
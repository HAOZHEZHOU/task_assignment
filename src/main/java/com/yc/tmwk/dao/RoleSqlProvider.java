package com.yc.tmwk.dao;

import com.yc.tmwk.model.Role;
import org.apache.ibatis.jdbc.SQL;

public class RoleSqlProvider {

    public String insertSelective(Role record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("role");
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleName() != null) {
            sql.VALUES("role_name", "#{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleDesc() != null) {
            sql.VALUES("role_desc", "#{roleDesc,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Role record) {
        SQL sql = new SQL();
        sql.UPDATE("role");
        
        if (record.getRoleName() != null) {
            sql.SET("role_name = #{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleDesc() != null) {
            sql.SET("role_desc = #{roleDesc,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("role_id = #{roleId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}
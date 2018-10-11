package com.yc.tmwk.dao;

import com.yc.tmwk.model.Tag;
import org.apache.ibatis.jdbc.SQL;

public class TagSqlProvider {

    public String insertSelective(Tag record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tag");
        
        if (record.getTagId() != null) {
            sql.VALUES("tag_id", "#{tagId,jdbcType=INTEGER}");
        }
        
        if (record.getTagName() != null) {
            sql.VALUES("tag_name", "#{tagName,jdbcType=VARCHAR}");
        }
        
        if (record.getFkName() != null) {
            sql.VALUES("fk_name", "#{fkName,jdbcType=VARCHAR}");
        }
        
        if (record.getFkId() != null) {
            sql.VALUES("fk_id", "#{fkId,jdbcType=INTEGER}");
        }
        
        if (record.getFkTable() != null) {
            sql.VALUES("fk_table", "#{fkTable,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Tag record) {
        SQL sql = new SQL();
        sql.UPDATE("tag");
        
        if (record.getTagName() != null) {
            sql.SET("tag_name = #{tagName,jdbcType=VARCHAR}");
        }
        
        if (record.getFkName() != null) {
            sql.SET("fk_name = #{fkName,jdbcType=VARCHAR}");
        }
        
        if (record.getFkId() != null) {
            sql.SET("fk_id = #{fkId,jdbcType=INTEGER}");
        }
        
        if (record.getFkTable() != null) {
            sql.SET("fk_table = #{fkTable,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("tag_id = #{tagId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}
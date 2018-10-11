package com.yc.tmwk.dao;

import com.yc.tmwk.model.Comments;
import org.apache.ibatis.jdbc.SQL;

public class CommentsSqlProvider {

    public String insertSelective(Comments record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("comments");
        
        if (record.getCtmId() != null) {
            sql.VALUES("ctm_id", "#{ctmId,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getCmtText() != null) {
            sql.VALUES("cmt_text", "#{cmtText,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFkType() != null) {
            sql.VALUES("fk_type", "#{fkType,jdbcType=VARCHAR}");
        }
        
        if (record.getFkId() != null) {
            sql.VALUES("fk_id", "#{fkId,jdbcType=INTEGER}");
        }
        
        if (record.getUserNick() != null) {
            sql.VALUES("user_nick", "#{userNick,jdbcType=VARCHAR}");
        }
        
        if (record.getUserAvatar() != null) {
            sql.VALUES("user_avatar", "#{userAvatar,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Comments record) {
        SQL sql = new SQL();
        sql.UPDATE("comments");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getCmtText() != null) {
            sql.SET("cmt_text = #{cmtText,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFkType() != null) {
            sql.SET("fk_type = #{fkType,jdbcType=VARCHAR}");
        }
        
        if (record.getFkId() != null) {
            sql.SET("fk_id = #{fkId,jdbcType=INTEGER}");
        }
        
        if (record.getUserNick() != null) {
            sql.SET("user_nick = #{userNick,jdbcType=VARCHAR}");
        }
        
        if (record.getUserAvatar() != null) {
            sql.SET("user_avatar = #{userAvatar,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ctm_id = #{ctmId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}
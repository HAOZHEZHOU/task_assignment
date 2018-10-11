package com.yc.tmwk.dao;

import com.yc.tmwk.model.Attachment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttachmentSqlProvider {

    private static final Logger log = LoggerFactory.getLogger(AttachmentSqlProvider.class);

    public String insertSelective(Attachment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("attachment");
        
        if (record.getAttchId() != null) {
            sql.VALUES("attch_id", "#{attchId,jdbcType=INTEGER}");
        }
        
        if (record.getPath() != null) {
            sql.VALUES("path", "#{path,jdbcType=VARCHAR}");
        }
        
        if (record.getFkTable() != null) {
            sql.VALUES("fk_table", "#{fkTable,jdbcType=VARCHAR}");
        }
        
        if (record.getFkName() != null) {
            sql.VALUES("fk_name", "#{fkName,jdbcType=VARCHAR}");
        }
        
        if (record.getFkId() != null) {
            sql.VALUES("fk_id", "#{fkId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Attachment record) {
        SQL sql = new SQL();
        sql.UPDATE("attachment");
        if (record.getPath() != null) {
            sql.SET("path = #{path,jdbcType=VARCHAR}");
        }
        
        if (record.getFkTable() != null) {
            sql.SET("fk_table = #{fkTable,jdbcType=VARCHAR}");
        }
        
        if (record.getFkName() != null) {
            sql.SET("fk_name = #{fkName,jdbcType=VARCHAR}");
        }
        
        if (record.getFkId() != null) {
            sql.SET("fk_id = #{fkId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("attch_id = #{attchId,jdbcType=INTEGER}");
        
        return sql.toString();
    }



    public String queryByFk(@Param("fkTable") String fkTable,@Param("fkName") String fkName,@Param("fkId") Integer fkId){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("attachment");

        if(fkTable != null){
            sql.WHERE("fk_table = #{fkTable,jdbcType=VARCHAR}");
        }

        if(fkName != null){
            sql.WHERE("fk_name = #{fkName,jdbcType=VARCHAR}");
        }

        if(fkId != null){
            sql.WHERE("fk_id = #{fkId,jdbcType=INTEGER}");
        }

        log.error("附件SQL:" + sql.toString());
        return sql.toString();
    }
}
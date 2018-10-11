package com.yc.tmwk.dao;

import com.yc.tmwk.model.Attachment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AttachmentMapper {
    @Delete({
        "delete from attachment",
        "where attch_id = #{attchId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer attchId);

    @Insert({
        "insert into attachment (attch_id, path, ",
        "fk_table, fk_name, ",
        "fk_id, create_date)",
        "values (#{attchId,jdbcType=INTEGER}, #{path,jdbcType=VARCHAR}, ",
        "#{fkTable,jdbcType=VARCHAR}, #{fkName,jdbcType=VARCHAR}, ",
        "#{fkId,jdbcType=INTEGER}, #{createDate,jdbcType=TIMESTAMP})"
    })
    int insert(Attachment record);

    @InsertProvider(type=AttachmentSqlProvider.class, method="insertSelective")
    int insertSelective(Attachment record);

    @Select({
        "select",
        "attch_id, path, fk_table, fk_name, fk_id, create_date",
        "from attachment",
        "where attch_id = #{attchId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="attch_id", property="attchId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="fk_table", property="fkTable", jdbcType=JdbcType.VARCHAR),
        @Result(column="fk_name", property="fkName", jdbcType=JdbcType.VARCHAR),
        @Result(column="fk_id", property="fkId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP)
    })
    Attachment selectByPrimaryKey(Integer attchId);

    @UpdateProvider(type=AttachmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Attachment record);

    @Update({
        "update attachment",
        "set path = #{path,jdbcType=VARCHAR},",
          "fk_table = #{fkTable,jdbcType=VARCHAR},",
          "fk_name = #{fkName,jdbcType=VARCHAR},",
          "fk_id = #{fkId,jdbcType=INTEGER},",
          "create_date = #{createDate,jdbcType=TIMESTAMP}",
        "where attch_id = #{attchId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Attachment record);

    @SelectProvider(type=AttachmentSqlProvider.class, method = "queryByFk")
    @Results({
            @Result(column="attch_id", property="attchId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_table", property="fkTable", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_name", property="fkName", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_id", property="fkId", jdbcType=JdbcType.INTEGER),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Attachment> queryByFk(@Param("fkTable") String fkTable,@Param("fkName") String fkName,@Param("fkId") Integer fkId);


    @Select({
            "select * from attachment",
                    "where ",
                    "fk_table='task_record' and",
                    "fk_name='record_id' and",
                    "fk_id in",
                    "(select record_id",
                    "from task_record",
                    "where task_id= #{id,jdbcType=INTEGER} )",
    })
    @Results({
            @Result(column="attch_id", property="attchId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_table", property="fkTable", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_name", property="fkName", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_id", property="fkId", jdbcType=JdbcType.INTEGER),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Attachment> getTaskAttachment(Integer id);
}
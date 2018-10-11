package com.yc.tmwk.dao;

import com.yc.tmwk.model.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TagMapper {
    @Delete({
        "delete from tag",
        "where tag_id = #{tagId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer tagId);

    @Insert({
        "insert into tag (tag_id, tag_name, ",
        "fk_name, fk_id, fk_table)",
        "values (#{tagId,jdbcType=INTEGER}, #{tagName,jdbcType=VARCHAR}, ",
        "#{fkName,jdbcType=VARCHAR}, #{fkId,jdbcType=INTEGER}, #{fkTable,jdbcType=VARCHAR})"
    })
    int insert(Tag record);

    @InsertProvider(type=TagSqlProvider.class, method="insertSelective")
    int insertSelective(Tag record);

    @Select({
        "select",
        "tag_id, tag_name, fk_name, fk_id, fk_table",
        "from tag",
        "where tag_id = #{tagId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="tag_id", property="tagId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tag_name", property="tagName", jdbcType=JdbcType.VARCHAR),
        @Result(column="fk_name", property="fkName", jdbcType=JdbcType.VARCHAR),
        @Result(column="fk_id", property="fkId", jdbcType=JdbcType.INTEGER),
        @Result(column="fk_table", property="fkTable", jdbcType=JdbcType.VARCHAR)
    })
    Tag selectByPrimaryKey(Integer tagId);

    @UpdateProvider(type=TagSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Tag record);

    @Update({
        "update tag",
        "set tag_name = #{tagName,jdbcType=VARCHAR},",
          "fk_name = #{fkName,jdbcType=VARCHAR},",
          "fk_id = #{fkId,jdbcType=INTEGER},",
          "fk_table = #{fkTable,jdbcType=VARCHAR}",
        "where tag_id = #{tagId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Tag record);


    @Select({
            "select",
            "tag_id, tag_name, fk_name, fk_id, fk_table",
            "from tag",
            "where fk_table = #{arg0,jdbcType=VARCHAR}",
            "and fk_name = #{arg1,jdbcType=VARCHAR}",
            "and fk_id = #{arg2,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="tag_id", property="tagId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="tag_name", property="tagName", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_name", property="fkName", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_id", property="fkId", jdbcType=JdbcType.INTEGER),
            @Result(column="fk_table", property="fkTable", jdbcType=JdbcType.VARCHAR)
    })
    List<Tag> queryByFk(String fkTable,String fkName,Integer fkId);

    @Select({
            "select",
            "tag_id, tag_name, fk_name, fk_id, fk_table",
            "from tag"
    })
    @Results({
            @Result(column="tag_id", property="tagId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="tag_name", property="tagName", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_name", property="fkName", jdbcType=JdbcType.VARCHAR),
            @Result(column="fk_id", property="fkId", jdbcType=JdbcType.INTEGER),
            @Result(column="fk_table", property="fkTable", jdbcType=JdbcType.VARCHAR)
    })
    List<Tag> lst();
}
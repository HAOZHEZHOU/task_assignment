package com.yc.tmwk.dao;

import com.yc.tmwk.model.Comments;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CommentsMapper {
    @Delete({
        "delete from comments",
        "where ctm_id = #{ctmId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer ctmId);

    @Insert({
        "insert into comments (ctm_id, user_id, ",
        "cmt_text, create_date, ",
        "fk_type, fk_id, user_nick, ",
        "user_avatar)",
        "values (#{ctmId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{cmtText,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, ",
        "#{fkType,jdbcType=VARCHAR}, #{fkId,jdbcType=INTEGER}, #{userNick,jdbcType=VARCHAR}, ",
        "#{userAvatar,jdbcType=VARCHAR})"
    })
    int insert(Comments record);

    @InsertProvider(type=CommentsSqlProvider.class, method="insertSelective")
    int insertSelective(Comments record);

    @Select({
        "select",
        "ctm_id, user_id, cmt_text, create_date, fk_type, fk_id, user_nick, user_avatar",
        "from comments",
        "where ctm_id = #{ctmId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ctm_id", property="ctmId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="cmt_text", property="cmtText", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="fk_type", property="fkType", jdbcType=JdbcType.VARCHAR),
        @Result(column="fk_id", property="fkId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_nick", property="userNick", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_avatar", property="userAvatar", jdbcType=JdbcType.VARCHAR)
    })
    Comments selectByPrimaryKey(Integer ctmId);

    @UpdateProvider(type=CommentsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Comments record);

    @Update({
        "update comments",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "cmt_text = #{cmtText,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "fk_type = #{fkType,jdbcType=VARCHAR},",
          "fk_id = #{fkId,jdbcType=INTEGER},",
          "user_nick = #{userNick,jdbcType=VARCHAR},",
          "user_avatar = #{userAvatar,jdbcType=VARCHAR}",
        "where ctm_id = #{ctmId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Comments record);
}
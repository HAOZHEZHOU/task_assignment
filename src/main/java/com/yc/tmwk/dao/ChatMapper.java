package com.yc.tmwk.dao;

import com.yc.tmwk.model.Chat;
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

public interface ChatMapper {
    @Delete({
        "delete from chat",
        "where chat_id = #{chatId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer chatId);

    @Insert({
        "insert into chat (chat_id, chat_from, ",
        "chat_to, text, create_date, ",
        "state)",
        "values (#{chatId,jdbcType=INTEGER}, #{chatFrom,jdbcType=INTEGER}, ",
        "#{chatTo,jdbcType=INTEGER}, #{text,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, ",
        "#{state,jdbcType=SMALLINT})"
    })
    int insert(Chat record);

    @InsertProvider(type=ChatSqlProvider.class, method="insertSelective")
    int insertSelective(Chat record);

    @Select({
        "select",
        "chat_id, chat_from, chat_to, text, create_date, state",
        "from chat",
        "where chat_id = #{chatId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="chat_id", property="chatId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="chat_from", property="chatFrom", jdbcType=JdbcType.INTEGER),
        @Result(column="chat_to", property="chatTo", jdbcType=JdbcType.INTEGER),
        @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.SMALLINT)
    })
    Chat selectByPrimaryKey(Integer chatId);

    @UpdateProvider(type=ChatSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Chat record);

    @Update({
        "update chat",
        "set chat_from = #{chatFrom,jdbcType=INTEGER},",
          "chat_to = #{chatTo,jdbcType=INTEGER},",
          "text = #{text,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "state = #{state,jdbcType=SMALLINT}",
        "where chat_id = #{chatId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Chat record);


    @Select({
            "select",
            "chat_id, chat_from, chat_to, text, create_date, state",
            "from chat",
            "where chat_to = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="chat_id", property="chatId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="chat_from", property="chatFrom", jdbcType=JdbcType.INTEGER),
            @Result(column="chat_to", property="chatTo", jdbcType=JdbcType.INTEGER),
            @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="state", property="state", jdbcType=JdbcType.SMALLINT)
    })
    List<Chat> listByTo(Integer id);

    @Select({
            "select",
            "chat_id, chat_from, chat_to, text, create_date, state",
            "from chat",
            "where chat_from = #{chatFrom,jdbcType=INTEGER}",
            "and chat_to=#{chatTo,jdbcType=INTEGER}",
            "limit(#{pageStart,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER})"
    })
    @Results({
            @Result(column="chat_id", property="chatId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="chat_from", property="chatFrom", jdbcType=JdbcType.INTEGER),
            @Result(column="chat_to", property="chatTo", jdbcType=JdbcType.INTEGER),
            @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="state", property="state", jdbcType=JdbcType.SMALLINT)
    })
    List<Chat> listByFrom(Integer chatFrom,Integer chatTo,Integer pageStart,Integer pageSize);

    @Update({
            "update chat",
            "set ",
            "state = 1",
            "where chat_from = #{from,jdbcType=INTEGER}",
            "and chat_to = #{to,jdbcType=INTEGER}",
            "and state = 0",
    })
    int updateStateByTo(Integer from,Integer to);

    @Select({
            "select",
            "chat_id, chat_from, chat_to, text, create_date, state",
            "from chat",
            "where chat_from = #{from,jdbcType=INTEGER}",
            "and chat_to = #{to,jdbcType=INTEGER}",
            "and state = 0",
            "order by create_Date asc"
    })
    @Results({
            @Result(column="chat_id", property="chatId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="chat_from", property="chatFrom", jdbcType=JdbcType.INTEGER),
            @Result(column="chat_to", property="chatTo", jdbcType=JdbcType.INTEGER),
            @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="state", property="state", jdbcType=JdbcType.SMALLINT)
    })
    List<Chat> getUnReadFromTo(Integer from,Integer to);

    @Select({
            "select",
            "chat_id, chat_from, chat_to, text, create_date, state",
            "from chat",
            "where chat_from in (#{arg0,jdbcType=INTEGER},#{arg1,jdbcType=INTEGER})",
            "and chat_to in (#{arg0,jdbcType=INTEGER},#{arg1,jdbcType=INTEGER})",
            "order by create_date asc",
            "limit #{arg2,jdbcType=INTEGER},#{arg3,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="chat_id", property="chatId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="chat_from", property="chatFrom", jdbcType=JdbcType.INTEGER),
            @Result(column="chat_to", property="chatTo", jdbcType=JdbcType.INTEGER),
            @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="state", property="state", jdbcType=JdbcType.SMALLINT)
    })
    List<Chat> getLatestRecord(Integer id1,Integer id2,Integer pageStart,Integer pageSize);


    @Update({
            "update chat",
            "set ",
            "state = 1",
            "where chat_from in (#{arg0,jdbcType=INTEGER},#{arg1,jdbcType=INTEGER})",
            "and chat_to in (#{arg0,jdbcType=INTEGER},#{arg1,jdbcType=INTEGER})"
    })
    int setRead(Integer id1,Integer id2);
}
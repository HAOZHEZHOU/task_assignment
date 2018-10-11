package com.yc.tmwk.dao;

import com.yc.tmwk.model.TaskRecord;
import com.yc.tmwk.model.UserTaskRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TaskRecordMapper {
    @Delete({
        "delete from task_record",
        "where record_id = #{recordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer recordId);

    @Insert({
        "insert into task_record (record_id, user_id, ",
        "task_id, text,create_date)",
        "values (#{recordId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{taskId,jdbcType=INTEGER}, #{text,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP})"
    })
    int insert(TaskRecord record);

    @InsertProvider(type=TaskRecordSqlProvider.class, method="insertSelective")
    int insertSelective(TaskRecord record);

    @Select({
        "select",
        "record_id, user_id, task_id, text, create_date",
        "from task_record",
        "where record_id = #{recordId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="record_id", property="recordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP)
    })
    TaskRecord selectByPrimaryKey(Integer recordId);

    @UpdateProvider(type=TaskRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TaskRecord record);

    @Update({
        "update task_record",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "task_id = #{taskId,jdbcType=INTEGER},",
          "text = #{text,jdbcType=VARCHAR}",
        "where record_id = #{recordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TaskRecord record);




    @Select({
            "select",
            "task_record.*, users.login_name,users.avatar",
            "from task_record",
            "left join users on users.user_id=task_record.user_id",
            "where task_id = #{taskId,jdbcType=INTEGER}",
            "order by create_date desc"
    })
    @Results({
            @Result(column="record_id", property="recordId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
            @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
            @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserTaskRecord> queryByTask(Integer taskId);



    @Select({
            "select",
            "record_id, user_id, task_id, text, create_date",
            "from task_record",
            "where user_id = #{userId,jdbcType=INTEGER}",
            "and  task_id = #{taskId,jdbcType=INTEGER}",
            "order by record_id desc",
            "limit 0,1"
    })
    @Results({
            @Result(column="record_id", property="recordId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
            @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP)
    })
    TaskRecord queryLatest(@Param("userId") Integer userId,@Param("taskId") Integer taskId);





}
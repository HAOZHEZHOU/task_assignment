package com.yc.tmwk.dao;

import com.yc.tmwk.model.Task;
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

public interface TaskMapper {
    @Delete({
        "delete from task",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer taskId);

    @Insert({
        "insert into task (task_id, task_from, ",
        "task_to, p_id, task_name, ",
        "task_content, create_date, ",
        "state)",
        "values (#{taskId,jdbcType=INTEGER}, #{taskFrom,jdbcType=INTEGER}, ",
        "#{taskTo,jdbcType=INTEGER}, #{pId,jdbcType=INTEGER}, #{taskName,jdbcType=VARCHAR}, ",
        "#{taskContent,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, ",
        "#{state,jdbcType=INTEGER})"
    })
    int insert(Task record);

    @InsertProvider(type=TaskSqlProvider.class, method="insertSelective")
    int insertSelective(Task record);

    @Select({
        "select",
        "task_id, task_from, task_to, p_id, task_name, task_content, create_date, state",
        "from task",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_from", property="taskFrom", jdbcType=JdbcType.INTEGER),
        @Result(column="task_to", property="taskTo", jdbcType=JdbcType.INTEGER),
        @Result(column="p_id", property="pId", jdbcType=JdbcType.INTEGER),
        @Result(column="task_name", property="taskName", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_content", property="taskContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    Task selectByPrimaryKey(Integer taskId);

    @UpdateProvider(type=TaskSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Task record);

    @Update({
        "update task",
        "set task_from = #{taskFrom,jdbcType=INTEGER},",
          "task_to = #{taskTo,jdbcType=INTEGER},",
          "p_id = #{pId,jdbcType=INTEGER},",
          "task_name = #{taskName,jdbcType=VARCHAR},",
          "task_content = #{taskContent,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "state = #{state,jdbcType=INTEGER}",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Task record);


    @Select({
            "select",
            "task_id, task_from, task_to, p_id, task_name, task_content, create_date, state",
            "from task",
            "where task_from = #{taskFrom,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="task_from", property="taskFrom", jdbcType=JdbcType.INTEGER),
            @Result(column="task_to", property="taskTo", jdbcType=JdbcType.INTEGER),
            @Result(column="p_id", property="pId", jdbcType=JdbcType.INTEGER),
            @Result(column="task_name", property="taskName", jdbcType=JdbcType.VARCHAR),
            @Result(column="task_content", property="taskContent", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    List<Task> queryByFrom(Integer taskFrom);

    @Select({
            "select",
            "task_id, task_from, task_to, p_id, task_name, task_content, create_date, state",
            "from task",
            "where task_to = #{taskTo,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="task_from", property="taskFrom", jdbcType=JdbcType.INTEGER),
            @Result(column="task_to", property="taskTo", jdbcType=JdbcType.INTEGER),
            @Result(column="p_id", property="pId", jdbcType=JdbcType.INTEGER),
            @Result(column="task_name", property="taskName", jdbcType=JdbcType.VARCHAR),
            @Result(column="task_content", property="taskContent", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    List<Task> queryByTo(Integer taskTo);




    @Select({
            "select",
            "task_id, task_from, task_to, p_id, task_name, task_content, create_date, state",
            "from task",
            "where p_id = #{pId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="task_from", property="taskFrom", jdbcType=JdbcType.INTEGER),
            @Result(column="task_to", property="taskTo", jdbcType=JdbcType.INTEGER),
            @Result(column="p_id", property="pId", jdbcType=JdbcType.INTEGER),
            @Result(column="task_name", property="taskName", jdbcType=JdbcType.VARCHAR),
            @Result(column="task_content", property="taskContent", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    List<Task> queryByPid(Integer pId);


    @Select({
            "select",
            "task_id, task_from, task_to, p_id, task_name, task_content, create_date, state",
            "from task",
            "where task_name like CONCAT('%',#{key,jdbcType=VARCHAR},'%')"
    })
    @Results({
            @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="task_from", property="taskFrom", jdbcType=JdbcType.INTEGER),
            @Result(column="task_to", property="taskTo", jdbcType=JdbcType.INTEGER),
            @Result(column="p_id", property="pId", jdbcType=JdbcType.INTEGER),
            @Result(column="task_name", property="taskName", jdbcType=JdbcType.VARCHAR),
            @Result(column="task_content", property="taskContent", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    List<Task> queryByKey(String key);

}
package com.yc.tmwk.dao;

import com.yc.tmwk.model.TaskTagKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface TaskTagMapper {
    @Delete({
        "delete from task_tag",
        "where task_id = #{taskId,jdbcType=INTEGER}",
          "and tag_id = #{tagId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(TaskTagKey key);

    @Insert({
        "insert into task_tag (task_id, tag_id)",
        "values (#{taskId,jdbcType=INTEGER}, #{tagId,jdbcType=INTEGER})"
    })
    int insert(TaskTagKey record);

    @InsertProvider(type=TaskTagSqlProvider.class, method="insertSelective")
    int insertSelective(TaskTagKey record);
}
package com.yc.tmwk.dao;

import com.yc.tmwk.model.Role;
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

public interface RoleMapper {
    @Delete({
        "delete from role",
        "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleId);

    @Insert({
        "insert into role (role_id, role_name, ",
        "role_desc)",
        "values (#{roleId,jdbcType=INTEGER}, #{roleName,jdbcType=VARCHAR}, ",
        "#{roleDesc,jdbcType=VARCHAR})"
    })
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @Select({
        "select",
        "role_id, role_name, role_desc",
        "from role",
        "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_desc", property="roleDesc", jdbcType=JdbcType.VARCHAR)
    })
    Role selectByPrimaryKey(Integer roleId);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update role",
        "set role_name = #{roleName,jdbcType=VARCHAR},",
          "role_desc = #{roleDesc,jdbcType=VARCHAR}",
        "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);

    @Select({
            "select",
            "role_id, role_name, role_desc",
            "from role"
    })
    @Results({
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="role_desc", property="roleDesc", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> lst();
}
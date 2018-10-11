package com.yc.tmwk.dao;

import com.yc.tmwk.model.Role;
import com.yc.tmwk.model.UserRole;
import com.yc.tmwk.model.UserRoleKey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface UserRoleMapper {
    @Delete({
        "delete from user_role",
        "where user_id = #{userId,jdbcType=INTEGER}",
          "and role_id = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(UserRoleKey key);

    @Insert({
        "insert into user_role (user_id, role_id, ",
        "editable)",
        "values (#{userId,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{editable,jdbcType=SMALLINT})"
    })
    int insert(UserRole record);

    @InsertProvider(type=UserRoleSqlProvider.class, method="insertSelective")
    int insertSelective(UserRole record);

    @Select({
        "select",
        "user_id, role_id, editable",
        "from user_role",
        "where user_id = #{userId,jdbcType=INTEGER}",
          "and role_id = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="editable", property="editable", jdbcType=JdbcType.SMALLINT)
    })
    UserRole selectByPrimaryKey(UserRoleKey key);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserRole record);

    @Update({
        "update user_role",
        "set editable = #{editable,jdbcType=SMALLINT}",
        "where user_id = #{userId,jdbcType=INTEGER}",
          "and role_id = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserRole record);


    @SelectProvider(type=UserRoleSqlProvider.class, method="queryAllByKey")
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="editable", property="editable", jdbcType=JdbcType.SMALLINT)
    })
    List<UserRole> queryAllByKey(UserRoleKey key);

    @Select({
            "select",
            "role.*",
            "from user_role",
            "left join role on role.role_id=user_role.role_id",
            "where user_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="editable", property="editable", jdbcType=JdbcType.SMALLINT)
    })
    List<Role> queryAllUserRole(Integer id);



    @Delete({
            "delete from user_role",
            "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByUserId(UserRoleKey key);
}
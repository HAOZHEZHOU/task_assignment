package com.yc.tmwk.dao;

import com.yc.tmwk.model.Users;
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

public interface UsersMapper {
    @Delete({
        "delete from users",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into users (user_id, login_name, ",
        "nick, avatar, pid, ",
        "login_pwd)",
        "values (#{userId,jdbcType=INTEGER}, #{loginName,jdbcType=VARCHAR}, ",
        "#{nick,jdbcType=VARCHAR}, #{avatar,jdbcType=VARCHAR}, #{pid,jdbcType=INTEGER}, ",
        "#{loginPwd,jdbcType=VARCHAR})"
    })
    int insert(Users record);

    @InsertProvider(type=UsersSqlProvider.class, method="insertSelective")
    int insertSelective(Users record);

    @Select({
        "select",
        "user_id, login_name, nick, avatar, pid, login_pwd",
        "from users",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
        @Result(column="nick", property="nick", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
        @Result(column="login_pwd", property="loginPwd", jdbcType=JdbcType.VARCHAR)
    })
    Users selectByPrimaryKey(Integer userId);

    @UpdateProvider(type=UsersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Users record);

    @Update({
        "update users",
        "set login_name = #{loginName,jdbcType=VARCHAR},",
          "nick = #{nick,jdbcType=VARCHAR},",
          "avatar = #{avatar,jdbcType=VARCHAR},",
          "pid = #{pid,jdbcType=INTEGER},",
          "login_pwd = #{loginPwd,jdbcType=VARCHAR}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Users record);


    @Select({
            "select",
            "user_id, login_name, nick, avatar, pid, login_pwd",
            "from users",
            "where login_name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="nick", property="nick", jdbcType=JdbcType.VARCHAR),
            @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
            @Result(column="login_pwd", property="loginPwd", jdbcType=JdbcType.VARCHAR)
    })
    Users queryByName(String name);

    @Select({
            "select",
            "users.*",
            "from user_role",
            "left join users on users.user_id = user_role.user_id",
            "where user_role.role_id= #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="nick", property="nick", jdbcType=JdbcType.VARCHAR),
            @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
            @Result(column="login_pwd", property="loginPwd", jdbcType=JdbcType.VARCHAR)
    })
    List<Users> queryByR(Integer id);

    @Select({
            "select",
            "user_id, login_name, nick, avatar, pid, login_pwd",
            "from users",
            "where pid = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="nick", property="nick", jdbcType=JdbcType.VARCHAR),
            @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
            @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
            @Result(column="login_pwd", property="loginPwd", jdbcType=JdbcType.VARCHAR)
    })
    List<Users> queryByPid(Integer id);
}
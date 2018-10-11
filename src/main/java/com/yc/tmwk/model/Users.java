package com.yc.tmwk.model;

public class Users {
    private Integer userId;

    private String loginName;

    private String nick;

    private String avatar;

    private Integer pid;

    private String loginPwd;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", nick='" + nick + '\'' +
                ", avatar='" + avatar + '\'' +
                ", pid=" + pid +
                ", loginPwd='" + loginPwd + '\'' +
                '}';
    }
}
package com.yc.tmwk.model;

public class UserTaskRecord extends TaskRecord {

    private Integer userId;
    private String loginName;
    private String avatar;
    private String path;

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public String toString() {
        return "UserTaskRecord{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

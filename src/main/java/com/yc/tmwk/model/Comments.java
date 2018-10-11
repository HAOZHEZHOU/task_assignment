package com.yc.tmwk.model;

import java.util.Date;

public class Comments {
    private Integer ctmId;

    private Integer userId;

    private String cmtText;

    private Date createDate;

    private String fkType;

    private Integer fkId;

    private String userNick;

    private String userAvatar;

    public Integer getCtmId() {
        return ctmId;
    }

    public void setCtmId(Integer ctmId) {
        this.ctmId = ctmId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCmtText() {
        return cmtText;
    }

    public void setCmtText(String cmtText) {
        this.cmtText = cmtText == null ? null : cmtText.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFkType() {
        return fkType;
    }

    public void setFkType(String fkType) {
        this.fkType = fkType == null ? null : fkType.trim();
    }

    public Integer getFkId() {
        return fkId;
    }

    public void setFkId(Integer fkId) {
        this.fkId = fkId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick == null ? null : userNick.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }
}
package com.yc.tmwk.model;

import java.util.Date;

public class Task {
    private Integer taskId;

    private Integer taskFrom;

    private Integer taskTo;

    private Integer pId;

    private String taskName;

    private String taskContent;

    private Date createDate;

    private Integer state;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskFrom() {
        return taskFrom;
    }

    public void setTaskFrom(Integer taskFrom) {
        this.taskFrom = taskFrom;
    }

    public Integer getTaskTo() {
        return taskTo;
    }

    public void setTaskTo(Integer taskTo) {
        this.taskTo = taskTo;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent == null ? null : taskContent.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
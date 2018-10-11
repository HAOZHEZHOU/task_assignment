package com.yc.tmwk.model;

import java.util.Date;

public class TaskRecord {
    private Integer recordId;

    private Integer userId;

    private Integer taskId;

    private String text;

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    @Override
    public String toString() {
        return "TaskRecord{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", taskId=" + taskId +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
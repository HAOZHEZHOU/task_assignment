package com.yc.tmwk.model;

import java.util.Date;

public class Chat {
    private Integer chatId;

    private Integer chatFrom;

    private Integer chatTo;

    private String text;

    private Date createDate;

    private Short state;

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getChatFrom() {
        return chatFrom;
    }

    public void setChatFrom(Integer chatFrom) {
        this.chatFrom = chatFrom;
    }

    public Integer getChatTo() {
        return chatTo;
    }

    public void setChatTo(Integer chatTo) {
        this.chatTo = chatTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", chatFrom=" + chatFrom +
                ", chatTo=" + chatTo +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                ", state=" + state +
                '}';
    }
}
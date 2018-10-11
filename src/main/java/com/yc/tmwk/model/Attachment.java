package com.yc.tmwk.model;

import java.util.Date;

public class Attachment {
    private Integer attchId;

    private String path;

    private String fkTable;

    private String fkName;

    private Integer fkId;

    private Date createDate;

    public Integer getAttchId() {
        return attchId;
    }

    public void setAttchId(Integer attchId) {
        this.attchId = attchId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getFkTable() {
        return fkTable;
    }

    public void setFkTable(String fkTable) {
        this.fkTable = fkTable == null ? null : fkTable.trim();
    }

    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName == null ? null : fkName.trim();
    }

    public Integer getFkId() {
        return fkId;
    }

    public void setFkId(Integer fkId) {
        this.fkId = fkId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
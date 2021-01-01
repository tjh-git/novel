package org.model;

import java.sql.Date;
import java.sql.Timestamp;

public class CommentInfo {
    private String context;
    private Date createdTime;
    private String readerName;
    private int novelId;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public int getNovelId() {
        return novelId;
    }

    public void setNovelId(int novelId) {
        this.novelId = novelId;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "context='" + context + '\'' +
                ", createdTime=" + createdTime +
                ", readerName='" + readerName + '\'' +
                ", novelId=" + novelId +
                ", id=" + id +
                '}';
    }
}

package com.example.redisdemo.domain;

import java.util.Date;

public class Announcement {
    private Long aId;
    private String title;
    private String detail;
    private Date publishDate;
    private Date updateTime;

    @Override
    public String toString() {
        return "Announcement{" +
                "aId=" + aId +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", publishDate=" + publishDate +
                ", updateTime=" + updateTime +
                '}';
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

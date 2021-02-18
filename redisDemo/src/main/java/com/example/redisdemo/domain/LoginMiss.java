package com.example.redisdemo.domain;

import java.util.Date;

public class LoginMiss {
    private Long id;
    private Long uId;
    private Integer missNumber;
    private Integer missFlag;
    private Date missTime;
    private Date createTime;
    private  Date updateTime;

    @Override
    public String toString() {
        return "LoginMiss{" +
                "id=" + id +
                ", uId=" + uId +
                ", missNumber=" + missNumber +
                ", missFlag=" + missFlag +
                ", missTime=" + missTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Integer getMissNumber() {
        return missNumber;
    }

    public void setMissNumber(Integer missNumber) {
        this.missNumber = missNumber;
    }

    public Integer getMissFlag() {
        return missFlag;
    }

    public void setMissFlag(Integer missFlag) {
        this.missFlag = missFlag;
    }

    public Date getMissTime() {
        return missTime;
    }

    public void setMissTime(Date missTime) {
        this.missTime = missTime;
    }
}

package com.example.redisdemo.domain;

import java.util.Date;

public class BorrowCard {
    private Long bcId;
    private String reader;
    private Integer borrowStatus;
    private Long ruleId;
    private Long loginAccountId;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "borrowCard{" +
                "bcId=" + bcId +
                ", reader='" + reader + '\'' +
                ", borrowStatus=" + borrowStatus +
                ", ruleId=" + ruleId +
                ", loginAccountId=" + loginAccountId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getLoginAccountId() {
        return loginAccountId;
    }

    public void setLoginAccountId(Long loginAccountId) {
        this.loginAccountId = loginAccountId;
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

    public Long getBcId() {
        return bcId;
    }

    public void setBcId(Long bcId) {
        this.bcId = bcId;
    }


    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public Integer getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(Integer borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }
}

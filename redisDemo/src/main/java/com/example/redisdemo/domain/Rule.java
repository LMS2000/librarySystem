package com.example.redisdemo.domain;

import java.util.Date;

public class Rule {
    private Long rId;
    private Long borrowNum;
    private Long limitDay;
    private String borrowLibrary;
    private Double overTimeFee;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "rule{" +
                "rId=" + rId +
                ", borrowNum=" + borrowNum +
                ", limitDay=" + limitDay +
                ", borrowLibrary='" + borrowLibrary + '\'' +
                ", overTimeFee=" + overTimeFee +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Long getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(Long borrowNum) {
        this.borrowNum = borrowNum;
    }

    public Long getLimitDay() {
        return limitDay;
    }

    public void setLimitDay(Long limitDay) {
        this.limitDay = limitDay;
    }

    public String getBorrowLibrary() {
        return borrowLibrary;
    }

    public void setBorrowLibrary(String borrowLibrary) {
        this.borrowLibrary = borrowLibrary;
    }

    public Double getOverTimeFee() {
        return overTimeFee;
    }

    public void setOverTimeFee(Double overTimeFee) {
        this.overTimeFee = overTimeFee;
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
}

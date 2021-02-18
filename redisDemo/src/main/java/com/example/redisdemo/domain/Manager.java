package com.example.redisdemo.domain;

import java.util.Date;

public class Manager {
    private Long mId;
    private String managerName;
    private String email;
    private Long loginAccountId;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "manager{" +
                "mId=" + mId +
                ", managerName='" + managerName + '\'' +
                ", email='" + email + '\'' +
                ", loginAccountId=" + loginAccountId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}

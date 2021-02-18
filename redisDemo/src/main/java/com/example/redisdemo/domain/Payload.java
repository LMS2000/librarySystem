package com.example.redisdemo.domain;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

public class Payload<T> {

        private String id;
        private T userInfo;
        private Date expiration;
    public Payload() {}
    public Payload(String id, T userInfo, Date expiration) {
        this.id = id;
        this.userInfo = userInfo;
        this.expiration = expiration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}

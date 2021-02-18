package com.example.redisdemo.domain;

import java.util.Date;

public class Message {
    private Long  messageId;
    private Long  cardId;
    private String detail;
    private Date publicDate;
    private Date updateTime;

    @Override
    public String toString() {
        return "message{" +
                "messageId=" + messageId +
                ", cardId=" + cardId +
                ", detail='" + detail + '\'' +
                ", publicDate=" + publicDate +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

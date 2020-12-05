package com.example.demo.po;

import java.io.Serializable;
import java.util.Date;

public class FriendsRequest implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 发送人id
     */
    private String sendUserId;

    /**
     * 接收人id
     */
    private String acceptUserId;

    /**
     * 发送时间
     */
    private Date requestDataTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public Date getRequestDataTime() {
        return requestDataTime;
    }

    public void setRequestDataTime(Date requestDataTime) {
        this.requestDataTime = requestDataTime;
    }

    @Override
    public String toString() {
        return "FriendsRequest{" +
                "id='" + id + '\'' +
                ", sendUserId='" + sendUserId + '\'' +
                ", acceptUserId='" + acceptUserId + '\'' +
                ", requestDataTime=" + requestDataTime +
                '}';
    }
}
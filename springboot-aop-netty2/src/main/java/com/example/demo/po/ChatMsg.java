package com.example.demo.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库的pojo类
 */

//@Table(name = "chat_msg")
public class ChatMsg implements Serializable {
    /**
     * ID
     */
    private String id;

    //消息类型
    private String type;

    /**
     * 发送人id
     */
    private String sendUserId;

    /**
     * 接收人id
     */
    private String acceptUserId;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 是否已读
     */
    private Boolean signFlag;

    /**
     * 消息创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(Boolean signFlag) {
        this.signFlag = signFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ChatMsg{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", sendUserId='" + sendUserId + '\'' +
                ", acceptUserId='" + acceptUserId + '\'' +
                ", msg='" + msg + '\'' +
                ", signFlag=" + signFlag +
                ", createTime=" + createTime +
                '}';
    }
}
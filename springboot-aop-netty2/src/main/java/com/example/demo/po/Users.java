package com.example.demo.po;

import java.io.Serializable;

public class Users implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码，已加密
     */
    private String password;

    /**
     * 用户头像
     */
    private String faceImage;

    /**
     * 用户大头像
     */
    private String faceImageBig;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户二维码
     */
    private String qrcode;

    /**
     * 用户客户端id
     */
    private String cid;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getFaceImageBig() {
        return faceImageBig;
    }

    public void setFaceImageBig(String faceImageBig) {
        this.faceImageBig = faceImageBig;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", faceImage='" + faceImage + '\'' +
                ", faceImageBig='" + faceImageBig + '\'' +
                ", nickname='" + nickname + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
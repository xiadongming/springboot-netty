package com.example.demo.po;

import java.io.Serializable;

public class MyFriends implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 当前用户id
     */
    private String myUserId;

    /**
     * 添加朋友的id
     */
    private String myFriendUserId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public String getMyFriendUserId() {
        return myFriendUserId;
    }

    public void setMyFriendUserId(String myFriendUserId) {
        this.myFriendUserId = myFriendUserId;
    }

    @Override
    public String toString() {
        return "MyFriends{" +
                "id='" + id + '\'' +
                ", myUserId='" + myUserId + '\'' +
                ", myFriendUserId='" + myFriendUserId + '\'' +
                '}';
    }
}
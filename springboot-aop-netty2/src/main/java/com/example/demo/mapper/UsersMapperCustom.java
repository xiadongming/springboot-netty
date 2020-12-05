package com.example.demo.mapper;


import com.example.demo.po.FriendsRequest;
import com.example.demo.po.MyFriends;
import com.example.demo.po.Users;

import java.util.List;

/**
 * 用于接收添加好友者的信息
 */

public interface UsersMapperCustom extends MyMapper<Users> {

    List<FriendsRequest> queryFriendRequestList(String acceptId);

    List<MyFriends> queryMyFriends(String myUserId);

    void updateSignMsg(List<String> msgIdList);
}
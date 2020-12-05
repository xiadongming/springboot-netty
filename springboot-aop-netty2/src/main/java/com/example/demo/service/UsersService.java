package com.example.demo.service;


import com.example.demo.po.ChatMsg;
import com.example.demo.po.Users;

import java.util.List;

public interface UsersService{


    /**
     * 用户注册接口
     * @param user
     * @return
     */
    Users registerUser(Users user);

}

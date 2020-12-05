package com.example.demo.service.impl;

import com.example.demo.mapper.*;
import com.example.demo.po.ChatMsg;
import com.example.demo.po.FriendsRequest;
import com.example.demo.po.MyFriends;
import com.example.demo.po.Users;
import com.example.demo.service.UsersService;
import com.example.demo.utils.QRCodeUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private QRCodeUtils qrCodeUtils;


    //用户注册,将用户输入的账号和密码,当做新用户信息注册
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users registerUser(Users user) {
        //1. 生成userId
        String id = UUID.randomUUID().toString();
        //2. 填充用户信息
        user.setId(id);
        //为用户设置默认头像
        user.setFaceImage("M00/00/00/wKgABF0isKOAMcn1AAYEqr08yNY440_80x80.png");
        user.setFaceImageBig("M00/00/00/wKgABF0isKOAMcn1AAYEqr08yNY440.png");
        //3. 注册时为用户生成唯一的二维码
        //3.1 定义二维码生成路径
        //win下的临时目录
        String urlLocalPath = "d:\\" + user.getId() + "qrcode.png";
        //String urlLocalPath = File.separator + "home" + File.separator + "tempImg" + File.separator + user.getId() + "qrcode.png";
        String qrcodeContent = "easyChat_qrcode:" + user.getUsername();
        //3.2 通过工具生成二维码
        qrCodeUtils.createQRCode(urlLocalPath,qrcodeContent);
        //传入数据库（转换为Multipart）
     /*   MultipartFile file = FileUtils.fileToMultipart(urlLocalPath);
        String qrcodeUrl = "";
        try {
            qrcodeUrl = fastDFSClient.uploadQRCode(file);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //3.3 写入数据库
        user.setQrcode("12121");

        //4. 写入数据库
        int insert = usersMapper.insert(user);
        return insert == 1 ? user : null;
    }


}

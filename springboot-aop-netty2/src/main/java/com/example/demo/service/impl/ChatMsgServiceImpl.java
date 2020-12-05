package com.example.demo.service.impl;

import com.example.demo.mapper.ChatMsgMapper;
import com.example.demo.po.ChatMsg;
import com.example.demo.service.ChatMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: xiadongming
 * @Date: 2020/12/5 15:52
 * @描述:
 */
@Service("chatMsgService")
@Transactional
public class ChatMsgServiceImpl implements ChatMsgService {
    @Autowired
    private ChatMsgMapper chatMsgMapper;

    @Override
    public void insertMsg(String msg1,String type) {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setMsg(msg1);
        chatMsg.setType(type);
        chatMsg.setId(UUID.randomUUID().toString());
        chatMsg.setCreateTime(new Date());
        chatMsg.setAcceptUserId("from");
        chatMsg.setSendUserId("send");
        chatMsg.setSignFlag(true);
        chatMsgMapper.insert(chatMsg);
    }
}

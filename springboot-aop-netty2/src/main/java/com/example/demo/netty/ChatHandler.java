package com.example.demo.netty;

import com.alibaba.fastjson.JSON;
import com.example.demo.po.ChatMsg;
import com.example.demo.service.ChatMsgService;
import com.example.demo.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xiadongming
 * @Date: 2020/12/1 22:08
 * @描述:
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //保存所有的客户端连接
    private static DefaultChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //接收到数据后，会自动调用该方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        System.out.println("接收到的消息 text= " + text);

        ChatMsg chatMsg = JSON.parseObject(text, ChatMsg.class);
        try {
            switch (chatMsg.getType()) {
                //建立用户和通道的关联，类似用户刚刚打开微信的操作
                case "0":
                    String sendUserId = chatMsg.getSendUserId();
                    UserChannelMap.put(sendUserId, ctx.channel());
                    System.out.println("建立用户：" + sendUserId + "  与通道  " + ctx.channel().id().asLongText() + " 的关联");
                    UserChannelMap.output();
                    break;
                //处理好友的聊天消息
                case "1":
                    //将消息保存到数据库
                    String msg1 = chatMsg.getMsg();
                   /* ChatMsgService chatMsgService = (ChatMsgService)SpringUtil.getBean("chatMsgService");
                    chatMsgService.insertMsg(msg1, "1");*/
                    //如果好友在线，则直接将消息发送给好友，如果不在线则不发送
                    String acceptUserId = chatMsg.getAcceptUserId();
                    Channel channel = UserChannelMap.get(acceptUserId);
                    //好友在线
                    if (null != channel) {
                        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msg1)));
                        //不在线
                    } else {
                        System.out.println("用户：" + acceptUserId + " 不在线");
                    }
                    break;
                 //心跳消息
                case "2":
                    System.out.println("心跳消息");
                    break;
            }
            //广播的形式，不是一对一聊天的形式
       /* for (Channel client : clients) {
            //将消息发送到所有的客户端
            client.writeAndFlush(new TextWebSocketFrame(simpleDateFormat.format(new Date()) + ": " + text));
        }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //当新的客户端连接服务器之后，会自动调用该方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //将新的channel添加到clients中
        clients.add(ctx.channel());
    }

    //发生异常，调用此方法
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //移除用户id和channel的关联关系
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        //关闭channel
        ctx.channel().close();

    }

    //正常关闭会话。类似关闭微信的操作
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //移除用户id和channel的关联关系
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        UserChannelMap.output();
        System.out.println("正常关闭通道(会话)");
    }
}

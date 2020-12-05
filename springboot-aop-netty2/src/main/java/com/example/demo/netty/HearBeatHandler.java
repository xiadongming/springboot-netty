package com.example.demo.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @Author: xiadongming
 * @Date: 2020/12/5 16:54
 * @描述:
 */
public class HearBeatHandler extends ChannelInboundHandlerAdapter {
    //用户事件触发，用于捕获空闲超时的事件
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
             //读空闲
            if(idleStateEvent.state() == IdleState.READER_IDLE){
             //写空闲
            }else if(idleStateEvent.state() == IdleState.WRITER_IDLE){

             //读写空闲
            }else if(idleStateEvent.state() == IdleState.ALL_IDLE){
                System.out.println("读写空闲");
                System.out.println("关闭资源");
                ctx.channel().close();
            }
        }

    }
}

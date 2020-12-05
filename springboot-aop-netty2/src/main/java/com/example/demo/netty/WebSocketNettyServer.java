package com.example.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @Author: xiadongming
 * @Date: 2020/11/29 23:04
 * @描述:
 */
@Component
public class WebSocketNettyServer {

    //静态内部类声明
    private static class SingletonWSSever{
        static final WebSocketNettyServer instance = new WebSocketNettyServer();
    }

    //公开获取的静态方法
    public static WebSocketNettyServer getInstance(){
        return SingletonWSSever.instance;
    }

    private NioEventLoopGroup bossGroup ;
    private NioEventLoopGroup subGroup ;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;

    //构造器私有
    private WebSocketNettyServer(){
        //1. 两个线程组
        bossGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        //2. 启动类
        serverBootstrap = new ServerBootstrap();
        //3. 定义启动的线程组,channel和初始化器
        serverBootstrap.group(bossGroup,subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketChannelInitializer());
    }


    public void start(){
        //绑定服务器端口，以同步的方式启动服务器
        try {
            channelFuture  = serverBootstrap.bind(9090).sync();
            System.err.println("Netty Websocket Server 启动完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

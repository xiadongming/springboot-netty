package com.example.demo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: xiadongming
 * @Date: 2020/12/1 18:44
 * @描述: 用于加载通道处理器类
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    //初始化通道，在这个方法中加载对应的channelHandler
    @Override   //数据会通过socketChannel过来
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //获取管道，将一个一个channelHadler添加到管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        //添加一个http的编解码器
        pipeline.addLast(new HttpServerCodec());
        // 添加一个用于支持大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //添加一个聚合器，主要用于将HttpMessage聚合成FullHttpRequest/FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        // 需要指定接收请求的路由，必须使用以"/ws"结尾的url才能访问
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //netty心跳，空闲超时检查(通过自定义心跳handler实现)
        //第一个参数：读空闲超时：超过4秒，会发送对应的读事件消息
        //第二个参数：写空闲超时：超过8秒，会发送对应的写事件消息
        //第三个参数：读写空闲超时：超过12秒，会发送对应的读写事件消息
        pipeline.addLast(new IdleStateHandler(4,8,12));
        //自定义心跳检查handler
        pipeline.addLast(new HearBeatHandler());

        //添加自定义handler
        pipeline.addLast(new ChatHandler());
    }
}

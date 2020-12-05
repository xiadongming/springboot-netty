package com.example.demo.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: xiadongming
 * @Date: 2020/12/5 11:51
 * @描述: 保证netty在spring容器启动的时候，netty就启动
 */
@Component
public class NettyListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //事件获得上下文对象化后启动服务器
        if (event.getApplicationContext().getParent() == null) {
            try {
                WebSocketNettyServer.getInstance().start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

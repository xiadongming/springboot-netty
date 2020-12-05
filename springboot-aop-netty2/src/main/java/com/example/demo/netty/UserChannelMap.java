package com.example.demo.netty;

import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiadongming
 * @Date: 2020/12/5 14:55
 * @描述: 用户id和channel的关联
 */
public class UserChannelMap {


    private static Map<String, Channel> userChannelMap;

    static {
        userChannelMap = new HashMap<>();
    }

    //建立连接
    public static void put(String userId, Channel channel) {
        userChannelMap.put(userId, channel);
    }

    //断开连接，根据用户id，移除关联
    public static void remove(String userId) {
        userChannelMap.remove(userId);
    }

    //断开连接，根据channelId移除关联关系
    public static void removeByChannelId(String channelId) {
        if (StringUtils.isBlank(channelId)) {
            return;
        }
        for (Map.Entry<String, Channel> stringChannelEntry : userChannelMap.entrySet()) {
            if (channelId.equals(stringChannelEntry.getValue().id().asLongText())) {
                remove(stringChannelEntry.getKey());
                System.out.println("移除用户id: " + stringChannelEntry.getKey() + " 和通道：" + stringChannelEntry.getValue() + " 的关联关系");
                break;
            }
        }
    }


    //定义获取方法
    public static Channel get(String userId) {
        //根据senderId获取对应的channel
        return userChannelMap.get(userId);
    }

    //输出所有的user和channel信息
    public static void output() {
        for (Map.Entry<String, Channel> channelEntry : userChannelMap.entrySet()) {
            System.out.println("当前的所有正在会话的用户列表：user: " + channelEntry.getKey() + ", channelId: " + channelEntry.getValue().id().asLongText());
        }
    }

}

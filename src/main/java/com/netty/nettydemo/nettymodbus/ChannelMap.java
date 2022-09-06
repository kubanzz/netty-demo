package com.netty.nettydemo.nettymodbus;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存和设备建立的channel连接
 */
public class ChannelMap {

    private static Map<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();

    public static void addTimeServerChannel(String id, Channel sc) {
        channelMap.put(id, sc);
    }

    public static Map<String, Channel> getAllChannels() {
        return channelMap;
    }

    public static Channel getTimeServerChannel(String id) {
        return channelMap.get(id);
    }

    public static void removeTimeServerChannel(String id) {
        channelMap.remove(id);
        System.out.println("size = " + channelMap.size());
    }
}

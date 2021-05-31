package com.dikar.api.ws;


import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.common.constants
 * @Author: yefei
 * @CreateTime: 2021-05-31 11:49
 * @Description:
 */
public class WSSocketHolder {
    private static final Map<Long, Channel> CHANNEL_MAP = new ConcurrentHashMap<>(16);

    public static void put(Long id, Channel channel) {
        CHANNEL_MAP.put(id, channel);
    }

    public static Channel get(Long id) {
        return CHANNEL_MAP.get(id);
    }

    public static Map<Long, Channel> getMap() {
        return CHANNEL_MAP;
    }

    public static void remove(Channel channel) {
        CHANNEL_MAP
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == channel)
                .forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));

    }
}

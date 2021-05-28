package com.dikar.api.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.ws
 * @Author: yefei
 * @CreateTime: 2021-05-28 16:24
 * @Description: websocket启动类
 */
@Slf4j
@Component
public class WSServer {

    @Value("${ws.port:4444}")
    private int wsPort;

    private EventLoopGroup work = new NioEventLoopGroup();
    private EventLoopGroup boss = new NioEventLoopGroup();

    @PostConstruct
    public void startWebsocketServer() {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(wsPort))
                // 保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new WSServerInitializer());
    }
}

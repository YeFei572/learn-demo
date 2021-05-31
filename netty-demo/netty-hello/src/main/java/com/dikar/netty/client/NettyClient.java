package com.dikar.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    public static String host = "127.0.0.1";
    public static int port = 9876;
    private EventLoopGroup group = new NioEventLoopGroup();

    // 唯一标记
    private boolean initFlag = true;

    public void run() {
        doConnect(new Bootstrap(), group);
    }

    public void doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
        ChannelFuture f = null;
        try {
            if (Objects.nonNull(bootstrap)) {
                bootstrap.group(eventLoopGroup)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.SO_KEEPALIVE, true)
                        .handler(new NettyClientFilter())
                        .remoteAddress(host, port);
                f = bootstrap.connect().addListener(
                        (ChannelFuture futureListener) -> {
                            final EventLoop eventLoop = futureListener.channel().eventLoop();
                            if (!futureListener.isSuccess()) {
                                System.out.println("与服务器断开连接！ 在10s之后准备尝试重连！");
                                eventLoop.schedule(() -> doConnect(new Bootstrap(), eventLoop), 10, TimeUnit.SECONDS);
                            }
                        }
                );
                if (initFlag) {
                    System.out.println("Netty客户端启动成功！");
                    initFlag = false;
                }
                f.channel().closeFuture().sync();
            }
        } catch (Exception e) {
            System.out.println("客户端连接失败。。。");
            e.printStackTrace();
        }
    }

}

package com.dikar.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty服务端
 */
public class NettyServer {

    private static final int port = 9876;
    private static EventLoopGroup boss = new NioEventLoopGroup();
    private static EventLoopGroup work = new NioEventLoopGroup();
    private static ServerBootstrap sb = new ServerBootstrap();

    public void run() {
        try {
            sb.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerFilter());
            ChannelFuture f = sb.bind(port).sync();
            System.out.println("服务端启动成功，端口是：" + port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}

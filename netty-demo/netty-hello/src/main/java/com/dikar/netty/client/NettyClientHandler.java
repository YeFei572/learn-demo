package com.dikar.netty.client;

import com.dikar.netty.protobuf.UserInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    // 循环次数
    private int fcount = 1;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立连接时：" + new Date());
        super.channelActive(ctx);
    }

    /**
     * 关闭连接时
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接关闭。。" + new Date());
        final EventLoop eventLoop = ctx.channel().eventLoop();
        NettyClient nettyClient = new NettyClient();
        nettyClient.doConnect(new Bootstrap(), eventLoop);
        super.channelInactive(ctx);
    }

    /**
     * 心跳请求处理， 每4s请求发一次心跳
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        System.out.println("循环请求时间：" + new Date() + ",次数" + fcount);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleState.WRITER_IDLE.equals(event.state())) {
                System.out.println("write_idle");
                UserInfo.UserMsg.Builder userState = UserInfo.UserMsg.newBuilder().setState(2);
                ctx.channel().writeAndFlush(userState);
                fcount++;
            }
        }
    }
}

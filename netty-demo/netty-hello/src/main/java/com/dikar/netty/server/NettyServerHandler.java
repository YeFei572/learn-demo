package com.dikar.netty.server;

import com.dikar.netty.protobuf.UserInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 空闲次数
    private int idle_count = 1;
    // 发送次数
    private int count = 1;

    /**
     * 建立连接时，发送给一条消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接客户端地址：" + ctx.channel().remoteAddress());
        UserInfo.UserMsg msg = UserInfo.UserMsg.newBuilder().setId(1).setAge(20).setName("张三").setState(0).build();
        ctx.writeAndFlush(msg);
        super.channelActive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            // 如果读通道处于空闲时间表示没有消息等待处理
            if (IdleState.READER_IDLE.equals(event.state())) {
                System.out.println("已经有5s没有收到心跳");
                if (idle_count > 1) {
                    System.out.println("正要准备关闭这个不活跃的通道");
                    ctx.channel().close();
                }
                idle_count++;
            }
        } else {
            super.userEventTriggered(ctx, obj);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("第" + count + "次" + ",服务端接受的消息:" + msg);
        try {
            if (msg instanceof UserInfo.UserMsg) {
                UserInfo.UserMsg userMsg = (UserInfo.UserMsg) msg;
                switch (userMsg.getState()) {
                    case 1:
                        System.out.println("客户端业务处理成功！");
                        break;
                    case 2:
                        System.out.println("处理客户端的心跳程序。。。。");
                        break;
                    default:
                        System.out.println("未知命令。。");
                }
            } else {
                System.out.println("未知数据！");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
        count++;
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.channel();
    }
}

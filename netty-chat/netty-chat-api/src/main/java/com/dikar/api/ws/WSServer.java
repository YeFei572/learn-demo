package com.dikar.api.ws;

import com.dikar.api.vo.req.WSBaseReqVO;
import com.dikar.api.vo.req.WSMessageReqVO;
import com.dikar.api.vo.req.WSUserReqVO;
import com.dikar.common.protobuf.WSBaseResProtoOuterClass;
import com.dikar.common.protobuf.WSMessageResProtoOuterClass;
import com.dikar.common.protobuf.WSUserResProtoOuterClass;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Objects;

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
    public void startWebsocketServer() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(wsPort))
                // 保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new WSServerInitializer());
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            log.info("服务器已经启动成功！");
        }
    }

    @PreDestroy
    public void destroy() {
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        log.info("服务器关闭成功！");
    }

    public Boolean sendMsg(Long fromId, WSBaseReqVO wsBaseReqVO) {
        Channel channel = WSSocketHolder.get(fromId);
        if (Objects.isNull(channel)) {
            log.info("用户 [{}] 不在线", fromId);
            return Boolean.FALSE;
        }
        WSMessageReqVO wsMessageReqVO = wsBaseReqVO.getMessage();
        WSBaseResProtoOuterClass.WSMessageResProto wsMessageResProto = WSBaseResProtoOuterClass.WSMessageResProto.newBuilder()
                .setReceiveId(wsMessageReqVO.getReceiveId())
                .setMsgType(wsMessageReqVO.getMsgType())
                .setMsgContent(wsMessageReqVO.getMsgContent())
                .build();

        WSUserReqVO wsUserReqVO = wsBaseReqVO.getUser();
        WSBaseResProtoOuterClass.WSUserResProto wsUserResProto = WSBaseResProtoOuterClass.WSUserResProto.newBuilder()
                .setUid(wsUserReqVO.getUid())
                .setName(wsUserReqVO.getName())
                .setAvatar(wsUserReqVO.getAvatar())
                .build();

        WSBaseResProtoOuterClass.WSBaseResProto wsBaseResProto = WSBaseResProtoOuterClass.WSBaseResProto.newBuilder()
                .setType(wsBaseReqVO.getType())
                .setMessage(wsMessageResProto)
                .setUser(wsUserResProto)
                .setCreateTime(new Date().toString())
                .build();

        channel.writeAndFlush(wsBaseResProto);
        return Boolean.TRUE;
    }
}

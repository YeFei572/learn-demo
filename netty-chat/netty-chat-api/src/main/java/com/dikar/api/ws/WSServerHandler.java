package com.dikar.api.ws;

import com.dikar.api.utils.UserLoginUtils;
import com.dikar.common.constants.WSReqTypeConstants;
import com.dikar.common.constants.WSResTypeConstant;
import com.dikar.common.protobuf.WSBaseReqProtoOuterClass;
import com.dikar.common.protobuf.WSBaseResProtoOuterClass;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Objects;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.ws
 * @Author: yefei
 * @CreateTime: 2021-05-28 17:27
 * @Description:
 */
@Slf4j
@ChannelHandler.Sharable
public class WSServerHandler extends SimpleChannelInboundHandler<WSBaseReqProtoOuterClass.WSBaseReqProto> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WSBaseReqProtoOuterClass.WSBaseReqProto msg) throws Exception {
        // 对消息进行分类处理
        switch (msg.getType()) {
            case WSReqTypeConstants.LOGIN:
                log.info("用户登陆。。。");
                userLogin(ctx, msg.getUid(), msg.getSid());
                break;
            case WSReqTypeConstants.PING:
                log.info("客户端心跳。。。");
                break;
            default:
                log.info("未知。。");
        }
    }

    private void userLogin(ChannelHandlerContext ctx, Long uid, String sid) {
        if (!UserLoginUtils.checkToken(uid, sid)) {
            log.info("非法登陆： {}， {}", uid, sid);
            // 登陆异常， 发送下线通知
            WSBaseResProtoOuterClass.WSBaseResProto wsBaseReqProto = WSBaseResProtoOuterClass.WSBaseResProto.newBuilder()
                    .setType(WSResTypeConstant.WS_OUT)
                    .setCreateTime(new Date().toString())
                    .build();
            ctx.channel().writeAndFlush(wsBaseReqProto);
            return;
        }
        // 判断是否在线，如果在线，踢出当前在线用户
        Channel channel = WSSocketHolder.get(uid);
        // 如果不是第一次登陆，并且客户端id和当前不匹配，则通知之前的客户端下线
        if (Objects.nonNull(channel) && !Objects.equals(ctx.channel().id(), channel.id())) {
            WSBaseResProtoOuterClass.WSBaseResProto wsBaseResProto = WSBaseResProtoOuterClass.WSBaseResProto.newBuilder()
                    .setType(WSResTypeConstant.WS_OUT)
                    .setCreateTime(new Date().toString())
                    .build();
            // 发送下线信息
            channel.writeAndFlush(wsBaseResProto);
        }
        // 加入在线map中
        WSSocketHolder.put(uid, ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (Objects.equals("Connection reset by peer", cause.getMessage())) {
            log.error("连接出现问题。。");
            return;
        }
        log.error(cause.getMessage(), cause);
    }
}

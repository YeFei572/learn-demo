package com.dikar.api.ws;

import com.dikar.common.constants.WSReqTypeConstants;
import com.dikar.common.protobuf.WSBaseReqProtoOuterClass;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

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
            case WSReqTypeConstants.PING:
        }
    }

    private void userLogin(ChannelHandlerContext ctx, Long uid, String sid) {

    }
}

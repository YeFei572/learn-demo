package com.dikar.api.ws;

import com.dikar.common.protobuf.WSBaseReqProtoOuterClass;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.ws
 * @Author: yefei
 * @CreateTime: 2021-05-28 16:31
 * @Description:
 */
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline()
                // 15秒客户端没有向服务器发送心跳则关闭连接
                .addLast(new IdleStateHandler(15, 0, 0))
                // HTTP请求的解码和编码
                .addLast(new HttpServerCodec())
                // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
                // 原因是HTTP解码器会在每个HTTP消息中生成多个消息对象HttpRequest/HttpResponse,HttpContent,LastHttpContent
                .addLast(new HttpObjectAggregator(66536))
                // 主要用于处理大数据流，比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的; 增加之后就不用考虑这个问题了
                .addLast(new ChunkedWriteHandler())
                // WebSocket数据压缩
                .addLast(new WebSocketServerCompressionHandler())
                // 协议包长度限制
                .addLast(new WebSocketServerProtocolHandler("/ws", null, true))
                // 协议解码
                .addLast(new MessageToMessageDecoder<WebSocketFrame>() {
                    @Override
                    protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> objects) {
                        ByteBuf buf = frame.content();
                        objects.add(buf);
                        buf.retain();
                    }
                })
                // 协议编码
                .addLast(new MessageToMessageEncoder<MessageLiteOrBuilder>() {
                    @Override
                    protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> objects) {
                        ByteBuf result = null;
                        if (msg instanceof MessageLite) {
                            result = wrappedBuffer(((MessageLite) msg).toByteArray());
                        }
                        if (msg instanceof MessageLite.Builder) {
                            result = wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
                        }
                        // ==== 上面代码片段是拷贝自TCP ProtobufEncoder 源码 ====
                        // 然后下面再转成websocket二进制流，因为客户端不能直接解析protobuf编码生成的
                        WebSocketFrame frame = new BinaryWebSocketFrame(result);
                        objects.add(frame);
                    }
                })
                // 协议包解码时指定Protobuf字节数实例化为CommonProtocol类型
                .addLast(new ProtobufDecoder(WSBaseReqProtoOuterClass.WSBaseReqProto.getDefaultInstance()))
                // websocket定义了传递数据的6中frame类型
                .addLast(new WSServerHandler());
    }
}

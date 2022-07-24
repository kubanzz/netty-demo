package com.netty.nettydemo.netty.server;

import com.netty.nettydemo.netty.OperateDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.util.SerializationUtils;

import java.util.List;

public class ServerByteDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] con = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(con);

        OperateDto operateDto = (OperateDto) SerializationUtils.deserialize(con);
        System.out.println("服务端decoder收到消息：" + operateDto);
        list.add(operateDto);
    }
}

package com.netty.nettydemo.netty.client;

import com.netty.nettydemo.netty.OperateDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.util.SerializationUtils;

import java.util.Arrays;

public class ClientByteEncoder extends MessageToByteEncoder<OperateDto> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, OperateDto operateDto, ByteBuf byteBuf) throws Exception {
        byte[] serialize = SerializationUtils.serialize(operateDto);
        System.out.println("客户端encoder发送：" + Arrays.toString(serialize));
        byteBuf.writeBytes(serialize);
    }
}

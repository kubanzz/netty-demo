package com.netty.nettydemo.netty.server;

import com.netty.nettydemo.netty.ResultDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.util.SerializationUtils;

public class ServerByteEncoder extends MessageToByteEncoder<ResultDto> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResultDto resultDto, ByteBuf byteBuf) throws Exception {

        System.out.println("服务端响应处理结果：" + resultDto);
        byteBuf.writeBytes(SerializationUtils.serialize(resultDto));
    }
}

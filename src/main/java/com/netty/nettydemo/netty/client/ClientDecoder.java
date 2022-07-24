package com.netty.nettydemo.netty.client;

import com.netty.nettydemo.netty.ResultDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.util.SerializationUtils;

import java.util.List;

public class ClientDecoder extends ByteToMessageDecoder {

    // 第二个参数为in（from server），第三个参数为out（out to next handler）
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // byteBuf用于存储通信内容
        byte[] resp = new byte[byteBuf.readableBytes()];
        // 将byteBuf的内容读取到resp数组中
        byteBuf.readBytes(resp);
        // 将读取到到byte数组进行反序列化操作
        ResultDto resultDto = (ResultDto) SerializationUtils.deserialize(resp);
        System.out.println("接收到服务器响应：" + resultDto);

        // list即为out，加入后会传送给下一个handler
        list.add(resultDto);
    }
}

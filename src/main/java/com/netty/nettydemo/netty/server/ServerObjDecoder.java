package com.netty.nettydemo.netty.server;

import com.netty.nettydemo.netty.OperateDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class ServerObjDecoder extends MessageToMessageDecoder<OperateDto> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, OperateDto operateDto, List<Object> list) {
        System.out.println("接收到client的消息：" + operateDto);
        list.add(operateDto);
    }

}

package com.netty.nettydemo.netty.client;

import com.netty.nettydemo.netty.OperateDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class ClientObjEncoder extends MessageToMessageEncoder<OperateDto> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, OperateDto operateDto, List<Object> list) {
        System.out.println("client obj encoder " + operateDto);
        OperateDto newOp = new OperateDto();
        list.add(newOp);
    }


//    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, OperateDto operateDto, ByteBuf byteBuf) throws Exception {
//        ByteBuf buffer = channelHandlerContext.alloc().buffer();
//        byte[] con1 = new byte[buffer.readableBytes()];
//        buffer.readBytes(con1);
//
//        byte[] con2 = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(con2);
//
//        System.out.println("client encoder");
//        System.out.println(new String(con1) + " - " + new String(con2));
////        byteBuf.writeBytes(bytes);
//    }

//    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, OperateDto operateDto, List<Object> list) throws Exception {
//        System.out.println("client encoder: " + operateDto);
//
//        ByteBuf buffer = channelHandlerContext.alloc().buffer();
//        list.add(operateDto);
//    }
}

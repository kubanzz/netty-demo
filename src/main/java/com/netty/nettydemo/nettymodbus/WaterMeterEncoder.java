package com.netty.nettydemo.nettymodbus;

import com.netty.nettydemo.nettymodbus.dto.WaterMeterSendDto;
import com.netty.nettydemo.nettymodbus.util.Crc16Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Arrays;

public class WaterMeterEncoder extends MessageToByteEncoder<WaterMeterSendDto> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, WaterMeterSendDto waterMeterSendDto, ByteBuf byteBuf) throws Exception {
        System.out.println("进入encoder");
        String address = waterMeterSendDto.getAddress();
        String func = waterMeterSendDto.getFunction();
        String[] start = waterMeterSendDto.getStart();
        String[] length = waterMeterSendDto.getNums();

        byte[] sendData = Crc16Util.getData(address, func, start[0], start[1], length[0], length[1]);
        byteBuf.writeBytes(sendData);
        System.out.println("encoder结束，发送数据为：" + Arrays.toString(sendData));
    }
}

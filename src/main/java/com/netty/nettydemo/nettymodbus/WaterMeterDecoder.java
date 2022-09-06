package com.netty.nettydemo.nettymodbus;

import com.netty.nettydemo.nettymodbus.dto.WaterMeterRecvDto;
import com.netty.nettydemo.nettymodbus.util.Crc16Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class WaterMeterDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        WaterMeterRecvDto waterMeterRecvDto = parseRespData(byteBuf);
        System.out.println("decoder接收到的响应数据为：" + waterMeterRecvDto);

        list.add(waterMeterRecvDto);
    }

    private WaterMeterRecvDto parseRespData(ByteBuf byteBuf) {
        byte[] data = new byte[4];
        byte[] crc = new byte[2];

        byte address = byteBuf.readByte();
        byte func = byteBuf.readByte();
        byte length = byteBuf.readByte();
        byteBuf.readBytes(data);
        byteBuf.readBytes(crc);

        return WaterMeterRecvDto.builder()
                .addressCode(Crc16Util.byteTo16String(address))
                .functionCode(Crc16Util.byteTo16String(func))
                .length((int) length)
                .data(Crc16Util.bytesToInt(data, 0, true) * 0.01)
                .build();
    }
}

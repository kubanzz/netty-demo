package com.netty.nettydemo.nettymodbus;

import com.netty.nettydemo.nettymodbus.dto.WaterMeterRecvDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class WaterMeterHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端已连接到服务器：{}", ctx);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof WaterMeterRecvDto)) {
            return;
        }
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = socketAddress.getAddress().getHostAddress();

        WaterMeterRecvDto waterMeterRecvDto = (WaterMeterRecvDto) msg;

        // 存储数据至缓存中
        Optional<List<WaterMeterRecvDto>> waterMeterRecvDtos = Optional.ofNullable(DataCacheMap.waterMeterCache.get(clientIp));
        if (!waterMeterRecvDtos.isPresent()) {
                DataCacheMap.waterMeterCache.put(clientIp, new CopyOnWriteArrayList<>());
        }
        waterMeterRecvDtos.ifPresent(waterMeterRecvDtos1 -> waterMeterRecvDtos1.add(waterMeterRecvDto));

    }

}

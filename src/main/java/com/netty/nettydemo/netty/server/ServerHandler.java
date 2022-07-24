package com.netty.nettydemo.netty.server;

import com.netty.nettydemo.netty.OperateDto;
import com.netty.nettydemo.netty.ResultDto;
import com.netty.nettydemo.netty.SpringContextUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<OperateDto> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, OperateDto operateDto) {
        System.out.println("server handler 接收到：" + operateDto);

        ServerService serverService = SpringContextUtil.getBean(ServerService.class);
        Channel channel = channelHandlerContext.channel();

        ResultDto resultDto = serverService.operateClientReq(operateDto);

        // 服务端可以通过channel跟客户端进行通信，每个客户端与服务端的连接都会有一个channel
        channel.writeAndFlush(resultDto);
    }
}

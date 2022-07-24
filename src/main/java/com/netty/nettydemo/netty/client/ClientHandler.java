package com.netty.nettydemo.netty.client;

import com.netty.nettydemo.netty.OperateDto;
import com.netty.nettydemo.netty.ResultDto;
import com.netty.nettydemo.netty.SpringContextUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;

public class ClientHandler extends SimpleChannelInboundHandler<ResultDto> {

    /**
     * 接收来自客户端的promise，当业务达到结束条件后，setSuccess或者setFailure，
     * 客户端监听到结果后后进行相关处理，例如通过addListening异步监听，或者通过sync同步阻塞等待结果
     */
    private final Promise<Integer> promise;

    public ClientHandler(Promise<Integer> promise) {
        this.promise = promise;
    }

    /**
     *  将channel和promise的操作放在handler中，与service解耦
     *  promise：用于确定业务的执行结果，并将结果返回到client
     *  channel：用于服务端和客户端间到通信，向channel写入数据即可向对方通信
     *  ByteBuf：用于存储接收到的通信内容，通常需要从中读取内容并进行接下来的业务操作
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ResultDto resultDto) throws Exception {
        ClientService clientService = SpringContextUtil.getBean(ClientService.class);
        Channel channel = channelHandlerContext.channel();

        // 将业务逻辑抽离到service层，获取业务执行结果
        OperateDto operateDto = clientService.handleResult(resultDto);
        channel.writeAndFlush(operateDto);

        // 根据结果判定是否达到业务结束条件，将结果添加到promise中
        String operate = operateDto.getOperate();
        if ("3".equals(operate)) {
            promise.setSuccess(10000);
//            promise.setFailure(new Exception("出错啦"));
        }

    }
}

package com.netty.nettydemo.netty.client;

import com.netty.nettydemo.netty.OperateDto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Promise;

import java.util.concurrent.ExecutionException;

public class NettyClient {

    public static void start() throws InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();

        // promise用于获取业务的执行结果
        EventExecutor eventExecutor = new DefaultEventExecutor();
        Promise<Integer> promise = new DefaultPromise<>(eventExecutor);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new ClientDecoder());
                        pipeline.addLast(new ClientByteEncoder());
                        pipeline.addLast(new ClientHandler(promise));
                    }
                });

        // channel无法序列化，所以不能跨服务传输
        Channel channel = bootstrap.connect("127.0.0.1", 8090).await().channel();

        OperateDto operateDto = OperateDto.builder()
                .operate("1")
                .content("这是client的1")
                .build();
        channel.writeAndFlush(operateDto);

        System.out.println("发送内容：" + operateDto);

        try {
            Integer integer = promise.await().get();

            System.out.println("最终执行结果：" + integer);
        } catch (ExecutionException e) {
            System.out.println("最终执行结果：" + e.getCause().getMessage());
        }

    }
}

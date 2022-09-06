package com.netty.nettydemo.nettymodbus;

import com.netty.nettydemo.nettymodbus.dto.WaterMeterSendDto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Benjamin
 * @date 2022-08-26
 */
@Slf4j
@Component
public class NettyClient {

    private final NioEventLoopGroup bossExecutors = new NioEventLoopGroup();

    private Bootstrap bootstrap;

    @PostConstruct
    public void init() {
        bootstrap = new Bootstrap();
        bootstrap
                .group(bossExecutors)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new WaterMeterEncoder());
                        pipeline.addLast(new WaterMeterDecoder());
                        pipeline.addLast(new WaterMeterHandler());
                    }
                });
    }

    public void sendWaterMeterReadReq(String ip, int port, String address) {

        // 获取缓存中的channel连接
        Channel channel;
        if (!ChannelMap.getAllChannels().containsKey(ip + port)) {
            channel = bootstrap.connect(ip, port).channel();
            ChannelMap.getAllChannels().put(ip + port, channel);
        }else {
            channel = ChannelMap.getAllChannels().get(ip + port);
        }
        // 读取操作
        String func = "03";
        // 开始读取数据区的下标
        String[] start = {"00", "00"};
        // 数据长度，读取4位，即2个寄存器的数量
        String[] nums = {"00", "02"};

        WaterMeterSendDto waterMeterSendDto = WaterMeterSendDto.builder()
                .address(address)
                .function(func)
                .start(start)
                .nums(nums).build();

        // 发送采集请求到水表
        channel.writeAndFlush(waterMeterSendDto);
    }

    @PreDestroy
    public void destroy() throws InterruptedException {
        bossExecutors.shutdownGracefully().sync();
        System.out.println("Netty Server关闭成功");
    }
}

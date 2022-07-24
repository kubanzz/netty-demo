package com.netty.nettydemo.netty;

import com.netty.nettydemo.netty.client.NettyClient;
import com.netty.nettydemo.netty.server.NettyServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/netty")
public class NettyController {

    @GetMapping("/server")
    public String startServer() {
        NettyServer.start();
        return "服务启动成功";
    }

    @GetMapping("/client")
    public String startClient() throws InterruptedException {
        NettyClient.start();
        return "客户端连接并发送成功";
    }
}

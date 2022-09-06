package com.netty.nettydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NettyDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyDemoApplication.class, args);
	}

}

package com.netty.nettydemo.netty;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Promise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperateDto implements Serializable {
    private String operate;
    private String content;
}

package com.netty.nettydemo.netty.server;


import com.netty.nettydemo.netty.OperateDto;
import com.netty.nettydemo.netty.ResultDto;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Promise;
import org.springframework.stereotype.Service;


@Service
public class ServerService {

    public ResultDto operateClientReq(OperateDto operateDto) {
        String operate = operateDto.getOperate();
        ResultDto resultDto = ResultDto.builder().build();

        switch (operate) {
            case "1":
                resultDto.setFlag("1");
                resultDto.setResult("这是server对你1的回复");
                break;
            case "2":
                resultDto.setFlag("2");
                resultDto.setResult("这是server回复你2的回复");
                break;
        }

        return resultDto;
    }
}

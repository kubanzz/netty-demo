package com.netty.nettydemo.netty.client;

import com.netty.nettydemo.netty.OperateDto;
import com.netty.nettydemo.netty.ResultDto;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public OperateDto handleResult(ResultDto resultDto) {
        String flag = resultDto.getFlag();
        OperateDto operateDto = OperateDto.builder().build();

        switch (flag) {
            case "1":
                operateDto.setOperate("2");
                operateDto.setContent("接收到1，开始执行2");
                break;
            case "2":
                operateDto.setOperate("3");
                operateDto.setContent("接收到2，开始执行3");
                break;
        }

        return operateDto;
    }
}

package com.netty.nettydemo.nettymodbus.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaterMeterRecvDto {

    /**
     * 地址码，可以认为是设备Id
     */
    @JSONField(name = "deviceId")
    private String addressCode;

    /**
     * 功能码
     */
    private String functionCode;

    /**
     * 数据长度
     */
    private int length;

    /**
     * 数据区内容
      */
    private double data;

}

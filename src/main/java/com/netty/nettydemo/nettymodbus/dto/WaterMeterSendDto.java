package com.netty.nettydemo.nettymodbus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterMeterSendDto {

    /**
     * 地址码
     */
    private String address;

    /**
     * 功能码
     */
    private String function;

    /**
     * 起始地址
     */
    private String[] start;

    /**
     * 获取的寄存器数量，水表获取的是2个寄存器数据
     */
    private String[] nums;
}

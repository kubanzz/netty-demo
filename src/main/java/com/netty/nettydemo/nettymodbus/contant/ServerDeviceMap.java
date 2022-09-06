package com.netty.nettydemo.nettymodbus.contant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于存储各个串口服务器对应的设备地址码
 *
 * @author Benjamin
 */
public class ServerDeviceMap {

    public final static Map<String, List<String>> deviceMap = new HashMap<>();

    static {
        // 圣堂南区水表设备地址码
        String stServerIp = "127.0.0.1";
        List<String> deviceList = Arrays.asList("01", "02", "03");
        deviceMap.put(stServerIp, deviceList);
    }
}

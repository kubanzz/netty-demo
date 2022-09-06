package com.netty.nettydemo.nettymodbus;

import com.netty.nettydemo.nettymodbus.dto.WaterMeterRecvDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataCacheMap {

    public static Map<String, List<WaterMeterRecvDto>> waterMeterCache = new ConcurrentHashMap<>();
}

package com.netty.nettydemo.nettymodbus.cron;

import com.netty.nettydemo.nettymodbus.WaterMeterNettyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WaterMeterScheduled {

    @Autowired
    WaterMeterNettyService waterMeterNettyService;

    /**
     * 每十分钟检查一次缓存的采集到的水表数据并批量发送至kafka
     */
    @Scheduled(cron = "0 */10 * * * *")
    public void waterDataSync() {
        waterMeterNettyService.dataSync();
    }


    /**
     * 每30分钟获取一次水表数据
     */
    @Scheduled(cron = "0 */30 * * * *")
    public void waterDataRead() {
        waterMeterNettyService.dataRead();
    }
}

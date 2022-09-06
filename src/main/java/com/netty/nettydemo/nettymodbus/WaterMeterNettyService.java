package com.netty.nettydemo.nettymodbus;

import com.netty.nettydemo.nettymodbus.contant.ServerDeviceMap;
import com.netty.nettydemo.nettymodbus.dto.WaterMeterRecvDto;
import com.netty.nettydemo.nettymodbus.model.Header;
import com.netty.nettydemo.nettymodbus.model.Package;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
@Log
public class WaterMeterNettyService {

    @Autowired
    NettyClient nettyClient;

    @Autowired
//    private KafkaMessageSender kafkaMessageSender;

    public void dataRead() {
        int port = 502;
        ServerDeviceMap.deviceMap.forEach((key, value) -> {
            for (String deviceAddress : value) {
                nettyClient.sendWaterMeterReadReq(key, port, deviceAddress);
            }
        });
    }

    public void dataSync() {
        DataCacheMap.waterMeterCache.forEach((serverIp, waterMeterRecvDtoList) -> {
            Header header = new Header();
            header.setRoadNo("01");
            header.setSourceModuleNo(serverIp);
            header.setSourceModuleName("智能水表监测");
            header.setRecordTime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));

            Package<Void, WaterMeterRecvDto> pkg = new Package<>();
            pkg.setHeader(header);
            pkg.setVisionData(new ArrayList<>());

            for (WaterMeterRecvDto waterMeterRecvDto : waterMeterRecvDtoList) {
                pkg.getVisionData().add(waterMeterRecvDto);
            }

            if (!pkg.getVisionData().isEmpty()) {
             // 将采集数据发送至kafka
//              kafkaMessageSender.sendKafkaMessage(DeviceKafkaTopicEnum.WATER_VALUE_READ_TOPIC.getTopic(), JSONObject.toJSONString(pkg));
            }

            // 清空缓存
            waterMeterRecvDtoList.clear();
        });
    }
}

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\ = /O
 * ____/`---'\____
 * .   ' \\| |// `.
 * / \\||| : |||// \
 * / _||||| -:- |||||- \
 * | | \\\ - /// | |
 * | \_| ''\---/'' | |
 * \ .-\__ `-` ___/-. /
 * ___`. .' /--.--\ `. . __
 * ."" '< `.___\_<|>_/___.' >'"".
 * | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * <p>
 * .............................................
 * 佛祖保佑             永无BUG
 *
 * @Author：jianzhenluo
 * @Description：
 * @Date：create in 2022/4/20 09:53
 */
package com.netty.nettydemo.nettymodbus.kafka;

public enum DeviceKafkaTopicEnum {
    /**
     * 情报板控制
     */
    CMS_CONTROL_TOPIC("kt_cms_control"),
    /**
     * 情报板控制结果
     */
    CMS_CONTROL_RESULT_TOPIC("kt_cms_control_result"),

    /**
     * 诱导屏控制
     */
    PARKING_GUIDE_CONTROL_TOPIC("kt_parking_num_control"),

    /**
     * 诱导屏控制结果
     */
    PARKING_GUIDE_CONTROL_RESULT_TOPIC("kt_parking_num_control_result"),

    /**
     * 智能电表、电力监控系统对接结果
     */
    ELECTRIC_VALUE_READ_TOPIC("kt_electric_value_read"),

    /**
     * 智能水表读取结果
     */
    WATER_VALUE_READ_TOPIC("kt_water_value_read"),

    /**
     * 卡口数据推送
     */
    BAYONET_VALUE_TOPIC("kt_bayonet_value"),

    /**
     * 污水排污数据推送
     */
    SEWAGE_DISPOSAL_TOPIC("kt_sewage_disposal_value"),

    /**
     * 智慧厕所接入数据
     */
    TOILET_ACCESS_TOPIC("kt_from_device_toilet_status")
    ;


    private final String topic;

    DeviceKafkaTopicEnum(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}

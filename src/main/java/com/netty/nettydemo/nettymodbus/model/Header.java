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
 * @Date：create in 2022/4/19 20:33
 */
package com.netty.nettydemo.nettymodbus.model;

import lombok.Data;

@Data
public class Header {

    /**
     * 消息id
     */
    public String messageCode;

    public String roadNo;

    public String sourceModuleNo;

    public String sourceModuleName;

    public String recordTime;

}

package com.netty.nettydemo.nettymodbus.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @author Benjamin
 * @date 2022-08-26
 */
public class Crc16Util {
    /**
     * 获取源数据和验证码的组合byte数组
     *
     * @param strings 可变长度的十六进制字符串
     */
    public static byte[] getData(String... strings) {
        byte[] data = new byte[]{};
        for (String string : strings) {
            int x = Integer.parseInt(string, 16);
            byte n = (byte) x;
            byte[] buffer = new byte[data.length + 1];
            byte[] aa = {n};
            System.arraycopy(data, 0, buffer, 0, data.length);
            System.arraycopy(aa, 0, buffer, data.length, aa.length);
            data = buffer;
        }
        return getData(data);
    }

    /**
     * 获取源数据和验证码的组合byte数组
     *
     * @param sourceData 字节数组
     */
    private static byte[] getData(byte[] sourceData) {
        byte[] crc = getCrc16(sourceData);
        byte[] res = new byte[sourceData.length + crc.length];
        System.arraycopy(sourceData, 0, res, 0, sourceData.length);
        System.arraycopy(crc, 0, res, sourceData.length, crc.length);
        return res;
    }

    /**
     * 获取验证码byte数组，基于Modbus CRC16的校验算法
     */
    private static byte[] getCrc16(byte[] arrBuff) {
        int len = arrBuff.length;

        // 预置 1 个 16 位的寄存器为十六进制FFFF, 称此寄存器为 CRC寄存器。
        int crc = 0xFFFF;
        int i, j;
        for (i = 0; i < len; i++) {
            // 把第一个 8 位二进制数据 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器
            crc = ((crc & 0xFF00) | (crc & 0x00FF) ^ (arrBuff[i] & 0xFF));
            for (j = 0; j < 8; j++) {
                // 把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位
                if ((crc & 0x0001) > 0) {
                    // 如果移出位为 1, CRC寄存器与多项式A001进行异或
                    crc = crc >> 1;
                    crc = crc ^ 0xA001;
                } else {
                    // 如果移出位为 0,再次右移一位
                    crc = crc >> 1;
                }
            }
        }
        return intToBytes(crc);
    }

    /**
     * 将int转换成byte数组，低位在前，高位在后
     * 改变高低位顺序只需调换数组序号
     */
    private static byte[] intToBytes(int value) {
        byte[] src = new byte[2];
        src[1] = (byte) ((value >> 8) & 0xFF);
        src[0] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * 将字节数组转换成十六进制字符串
     */
    public static String byteTo16String(byte[] data) {
        StringBuilder buffer = new StringBuilder();
        for (byte b : data) {
            buffer.append(byteTo16String(b));
        }
        return buffer.toString();
    }

    /**
     * 将字节转换成十六进制字符串
     * int转byte对照表
     * [128,255],0,[1,128)
     * [-128,-1],0,[1,128)
     */
    public static String byteTo16String(byte b) {
        StringBuilder buffer = new StringBuilder();
        int aa = (int) b;
        if (aa < 0) {
            buffer.append(Integer.toString(aa + 256, 16)).append(" ");
        } else if (aa == 0) {
            buffer.append("00 ");
        } else if (aa > 0 && aa <= 15) {
            buffer.append("0" + Integer.toString(aa, 16) + " ");
        } else if (aa > 15) {
            buffer.append(Integer.toString(aa, 16)).append(" ");
        }
        return buffer.toString();
    }

    /**
     * @apiNote  https://cloud.tencent.com/developer/article/1011941
     * @param littleEndian 小端模式即低位在后，高位在前的模式
     */
    public static long bytesToInt(byte[] input, int offset, boolean littleEndian) {
        // 将byte[] 封装为 ByteBuffer
        ByteBuffer buffer = ByteBuffer.wrap(input,offset,4);
        if(littleEndian){
            // ByteBuffer 默认为大端(BIG_ENDIAN)模式
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer.getInt();
    }

    public boolean validCrc(byte[] data, byte[] sourceCrc) {
        byte[] crc16Generate = getCrc16(data);
        return Arrays.equals(crc16Generate, sourceCrc);
    }
}

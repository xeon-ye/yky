package com.deloitte.services.project.common.util;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/18 15:30
 * @Description : 生成业务编码单号工具类
 * @Modified:
 */
public class GeneralBuisnessNoUtils {


    /**
     * 生成业务单号
     * 生成规则： 类型+单位编码+年份+流水
     *
     * @param busCode
     * @return
     */
    public static String generalBusNO(String busCode) {
        StringBuilder sb = new StringBuilder()
                .append(busCode)
                .append("100")
                .append(getCurrentYear())
                .append(generalRandomReqNO());
        return sb.toString();
    }

    /**
     * 生成6位流水号
     *
     * @return
     */
    private static String generalRandomReqNO() {
        int length = 6;
        String base = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取当前系统年
     * @return
     */
    private static String getCurrentYear() {
        int year = LocalDateTime.now().getYear();
        return String.valueOf(year);
    }

}

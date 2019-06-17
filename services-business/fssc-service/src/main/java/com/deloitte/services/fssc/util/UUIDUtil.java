package com.deloitte.services.fssc.util;


import java.util.UUID;

/**
 * @author Created by jackliu on 09/11/2017.
 * @Description 全局UUID生成工具类
 */
public class UUIDUtil {

    private UUIDUtil() {

    }

    /***
     * 随机产生32位16进制字符串
     *
     * @return
     */
    public static String getRandom32PK() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获得12个长度的十六进制的UUID
     *
     * @return UUID
     */
    public static String get12UUID() {
        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        return idd[0] + idd[1];
    }

}

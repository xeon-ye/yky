package com.deloitte.services.srpmp.common.util;

import org.apache.commons.lang3.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by lixin on 26/02/2019.
 */
public class ProjectNumberGenerator {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");

    //生成项目申请编号
    public static String generatProjectApplyNum(){
        String pre = "AP";
        String dstr = sdf.format(new Date());
        String radow = String.valueOf(RandomUtils.nextInt(100,999));
        return pre+dstr+radow;
    }

    //生成项目编号
    public static String generatProjectNum(){
        String dstr = sdf.format(new Date());
        String radow = String.valueOf(RandomUtils.nextInt(100,999));
        return dstr+radow;
    }

}

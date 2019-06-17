package com.deloitte.services.srpmp.common.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class GeneratorSQL {

    public static void main(String[] args) {
        String tempStr = "科研 院校级 创新工程  __" +
                "  院基科费  __" +
                "  校基科费  __" +
                "  改革经费  __" +
                " 国家级 国家科技重大专项  __" +
                "  国家重点研发计划项目  __" +
                "  技术创新引导专项  __" +
                "  重大科学计划  __" +
                "  科技支撑计划  __" +
                "  973计划  __" +
                "  863计划  __" +
                "  基地和人才专项  __" +
                "  基础条件平台  __" +
                "  国际合作项目(科技部)  __" +
                "  其他计划项目  __" +
                "  国家自然科学基金项目  __" +
                "  国家社会科学基金项目  __" +
                " 省部级 国家卫生健康委项目  __" +
                "  教育部项目  __" +
                "  国家发改委项目  __" +
                "  国家药监局项目  __" +
                "  国家中医药局项目  __" +
                "  中国科协  __" +
                "  北京市自然科学基金  __" +
                "  北京市教委  __" +
                "  北京市科协  __" +
                "  其他部委项目  __" +
                " 其他 其他  __" +
                " 横向 与企业联合项目  __";
        Integer workId = 1;
        

        long rootkey = IdWorker.getId();
        System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + rootkey + "','" + "SRCAT" + "','" + "项目分类" + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");

        String[] strArr = tempStr.split(" __");

        String c1= null;
        int countC1 = 100;
        long c1key =0l;
        long c2key =0l;
        long c3key =0l;
        String c2= null;
        int countC2 = 1000;
        String c3= null;
        int countC3 = 10000;
        for (int i = 0;i < strArr.length; i ++) {
            String temp = strArr[i].trim();
            String[] tempArr = temp.split(" ");
           // System.out.println(tempArr.length);
            if (tempArr.length == 3) {
                c1 = tempArr[0];
                c2 = tempArr[1];
                c3 = tempArr[2];
                c1key = IdWorker.getId();
                c2key = IdWorker.getId();
                c3key = IdWorker.getId();
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c1key + "','" + ++ countC1  + "','" + c1 + "','" +rootkey + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + ++ countC2 + "','" + c2 + "','" + c1key+ "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c3key + "','" + ++ countC3 + "','" + c3 + "','" + c2key+ "'," +  "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
            }
            if (tempArr.length == 2) {
                c2 = tempArr[0];
                c3 = tempArr[1];
                c2key = IdWorker.getId();
                c3key = IdWorker.getId();
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + ++ countC2 + "','" + c2 + "','" + c1key+ "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c3key + "','" + ++ countC3 + "','" + c3 + "','" + c2key+ "'," +  "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");

            }
            if (tempArr.length == 1) {
                c3 = tempArr[0];
                c3key = IdWorker.getId();
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c3key + "','" + ++ countC3 + "','" + c3 + "','" + c2key+ "'," +  "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");

            }
        }
    }
}

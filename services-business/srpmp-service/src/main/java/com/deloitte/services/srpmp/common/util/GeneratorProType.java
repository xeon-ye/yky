package com.deloitte.services.srpmp.common.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class GeneratorProType {

    static String countC1 = "00";
    static String countC2 = "00";
    static String countC3 = "00";
    public static void main(String[] args) {
        String tempStr = "院校级,创新工程,重大协同创新项目/协同创新团队/先导专项/院外研发机构---" +
                ",院基科费,科技创新/前沿培育/学科发展/基地平台/创新体系/人才团队/重大任务及行业支撑/国际合作/基础应急/科学传播/战略研究---" +
                ",校基科费,青年教师/学生项目/滚动支持---" +
                ",改革经费,改革经费---" +
                "国家级,国家科技重大专项,新药创制专项/传染病专项---" +
                ",国家重点研发计划项目,---" +
                ",技术创新引导专项,---" +
                ",重大科学计划,---" +
                ",科技支撑计划,---" +
                ",973计划,---" +
                ",863计划,---" +
                ",基地和人才专项,---" +
                ",基础条件平台,---" +
                ",国际合作项目（科技部）,---" +
                ",其他计划项目,---" +
                ",国家自然科学基金项目,面上项目/重点项目/重大项目/重大研究计划项目/青年科学基金项目/地区科学基金项目/优秀青年科学基金项目/国家杰出青年科学基金项目/创新群体项目/国际（地区）合作研究项目/海外及港澳学者合作研究基金项目/国家重大科研仪器研制项目/联合基金项目/应急管理项目/基础科学中心---" +
                ",国家社会科学基金项目,青年基金项目/一般项目/重大项目---" +
                "省部级,国家卫生健康委项目,行业专项/其他项目---" +
                ",教育部项目,项目（项）/人文社科---" +
                ",国家发改委项目,---" +
                ",国家药监局项目,---" +
                ",国家中医药局项目,---" +
                ",中国科协项目,---" +
                ",北京市自然科学基金项目,---" +
                ",北京市教委项目,---" +
                ",北京市科协项目,---" +
                ",其他部委项目,---" +
                "其他,其他,地方项目/国际合作项目（非科技部）---" +
                "横向,与企业联合项目,---";

        String[] strArr = tempStr.split("---");

        long rootKey =IdWorker.getId();
        System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + rootKey + "','" + "PRO_CATEGORY" + "','" + "项目分类" + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");

        long c1key =IdWorker.getId();
        long c2key =IdWorker.getId();
        long c3key =IdWorker.getId();

        String c1= null;
        String c2= null;
        String c3= null;

        // System.out.println(strArr.length);
        for (int i = 0;i < strArr.length; i ++) {
            // System.out.println(strArr[i]);

            String[] tempArr = strArr[i].split(",");
            // System.out.println(tempArr.length);

            if (tempArr.length == 3) {
                countC3 = "00";
                if (!"".equals(tempArr[0])) {
                    c1key =IdWorker.getId();
                    countC2 = "00";
                    System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c1key + "','" + getNextCount1() + "','" + tempArr[0] + "','"  +  rootKey + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
                }
                c2key =IdWorker.getId();
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + ("" + getCount1() + getNextCount2()) + "','" + tempArr[1] + "','" + c1key+ "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
                String[] tempArr2 = tempArr[2].split("/");
                for (int j = 0; j < tempArr2.length; j ++) {
                    c3key =IdWorker.getId();
                    System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c3key + "','" + ("" + getCount1() + countC2 + getNextCount3()) + "','" + tempArr2[j] + "','" + c2key+ "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
                }
            } else {
                if (!"".equals(tempArr[0])) {
                    c1key =IdWorker.getId();
                    countC2 = "00";
                    System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c1key + "','" + getNextCount1() + "','"  + tempArr[0] + "','" +  rootKey + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
                }
                c2key =IdWorker.getId();
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + ("" + getCount1() + getNextCount2()) + "','" + tempArr[1] + "','" + c1key+ "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");

            }
//            for (int j = 0; j < tempArr.length; j ++) {
//                System.out.println(tempArr[j]);
//            }
        }
//        for (int i = 0;i < strArr.length; i ++) {
//            String temp = strArr[i].trim();
//            String[] tempArr = temp.split(",");
//            // System.out.println(tempArr.length);
//            if (tempArr.length == 3) {
//                c1 = tempArr[0];
//                c2 = tempArr[1];
//                c3 = tempArr[2];
//                c1key = IdWorker.getId();
//                c2key = IdWorker.getId();
//                c3key = IdWorker.getId();
//                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c1key + "','" + ++ countC1  + "','" + c1 + "','" +rootkey + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
//                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + ++ countC2 + "','" + c2 + "','" + c1key+ "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
//                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c3key + "','" + ++ countC3 + "','" + c3 + "','" + c2key+ "'," +  "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
//            }
//            if (tempArr.length == 2) {
//                c2 = tempArr[0];
//                c3 = tempArr[1];
//                c2key = IdWorker.getId();
//                c3key = IdWorker.getId();
//                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + ++ countC2 + "','" + c2 + "','" + c1key+ "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
//                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c3key + "','" + ++ countC3 + "','" + c3 + "','" + c2key+ "'," +  "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
//
//            }
//            if (tempArr.length == 1) {
//                c3 = tempArr[0];
//                c3key = IdWorker.getId();
//                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c3key + "','" + ++ countC3 + "','" + c3 + "','" + c2key+ "'," +  "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')"+ "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" +",'" + "system" + "','" + "system" + "');");
//
//            }
//        }
    }

    private static String getCount1() {

        return "10" + countC1;
    }

    private static String getNextCount1() {
        int i = Integer.parseInt(countC1);
        i ++;
        if (i < 10) {
            countC1 =  "0" + i;
        } else {
            countC1 =  "" + i;
        }

        return "10" + countC1;
    }

    private static String getNextCount2() {
        int i = Integer.parseInt(countC2);
        i ++;
        if (i < 10) {
            countC2 =  "0" + i;
        } else {
            countC2 =  "" + i;
        }
        return countC2;
    }

    private static String getNextCount3() {
        int i = Integer.parseInt(countC3);
        i ++;
        if (i < 10) {
            countC3 =  "0" + i;
        } else {
            countC3 =  "" + i;
        }
        return countC3;
    }
}

package com.deloitte.services.srpmp.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Author:LIJUN
 * Date:30/04/2019
 * Description: 报告类型
 */
public enum ReportTypeEnum {

    YEAR_REPORT("10", "年度报告"),
    MID_REPORT("20", "中期报告"),
    MPR_REPORT("30", "中期绩效报告"),
    OTHER("99", "其他");

    ReportTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ReportTypeEnum getEnumByCode(String code) {
        for (ReportTypeEnum typeEnum : ReportTypeEnum.values()) {
            if (StringUtils.equals(code, typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }

    public static String getDescByCode(String code) {
        for (ReportTypeEnum typeEnum : ReportTypeEnum.values()) {
            if (StringUtils.equals(code, typeEnum.getCode())) {
                return typeEnum.getDesc();
            }
        }
        return "";
    }
}

package com.deloitte.services.srpmp.common.enums;

/**
 * 项目类型枚举
 * Created by lixin on 20/02/2019.
 */
public enum ProjectCategoryEnums {

    INNOVATE_BCOO("['10','1001','100101','10010101']", "10010101","创新工程-重大协同创新"),
    INNOVATE_COO("['10','1001','100101','10010102']","10010102", "创新工程-协同创新团队"),
    INNOVATE_PRE("['10','1001','100101','10010103']","10010103", "创新工程-先导专项"),
    INNOVATE_YOU("['10','1001','100101','10010104']","10010104", "创新工程-青年创新团队项目"),

    ACADEMY("['10','1001','100102']","10010201", "院基科费"),
    STRUCTURAL_REFORM("['10','1001','100104']","10010401", "科技体制改革"),
    SCHOOL_TEACH("['10','1001','100103','10010301']","10010301", "校基科费-青年教师"),
    SCHOOL_STU("['10','1001','100103','10010302']", "10010302","校基科费-学生项目"),
    ACADEMY_UNIT("['10','1001','100105']","100105", "院校级-创新单元项目"),

    TRAN_LONG_TASK("", "","横纵向项目");

    ProjectCategoryEnums(String code,String header, String desc) {
        this.code = code;
        this.header = header;
        this.desc = desc;
    }

    private String code;
    private String header;
    private String desc;

    public String getHeader() {
        return header;
    }
    public void setHeader(String lastCode) {
        this.header = lastCode;
    }

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
    }}

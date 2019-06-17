package com.deloitte.services.notice.biz;

public enum OaProcessStatusEnum {

    OA_PS_NORMAL("normal", "正常"),

    OA_PS_NOTREAD("notRead", "待审阅"),

    OA_PS_DRAFT("draft", "草稿");

    public String type;

    public String name;

    private OaProcessStatusEnum(String type, String name){
        this.type = type;
        this.name = name;
    }

}

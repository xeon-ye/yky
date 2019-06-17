package com.deloitte.services.notice.biz;

public enum OaNoticeOtherEnum {

    OA_TYPE_INFOCHANGE("oa_type_infochange", "信息交流"),
    OA_TYPE_MEETING_ARRGE("oa_type_meeting_arrge", "会议安排"),
    OA_TYPE_MEETING_RECORD("oa_type_meeting_record", "会议纪要"),
    OA_TYPE_CALENDER("oa_type_calender", "校历"),
    OA_TYPE_RESOURCE("oa_type_resource", "资源下载");

    public String code;

    public String name;

    OaNoticeOtherEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCodeByName(String name){
        for(OaNoticeOtherEnum e : OaNoticeOtherEnum.values()) {
            if(e.name.equals(name))
                return e.code;
        }

        return null;
    }

}

package com.deloitte.services.notice.biz;

public enum OaBpmEnum {

    OA_RULESYSTEM_APPROVAL_URL("APPROVAL_RULESYSTEM","规章制度审批", "oaRulesystemApproval"),
    OA_RULESYSTEM_APPROVAL_START_URL("APPROVAL_RULESYSTEM_START", "规章制度审批开始URL", "addRuleSystem"),
    OA_NOTICE_APPROVAL_URL("APPROVAL_NOTICE", "公告审批", "oaAnnouncementApproval"),
    OA_NOTICE_APPROVAL_START_URL("APPROVAL_NOTICE_START", "公告审批开始URL", "announcementAdd"),
    OA_CALENDAR_APPROVAL_URL("APPROVAL_CALENDAR", "校历审批", "oaCalendarApproval"),
    OA_CALENDAR_APPROVAL_START_URL("APPROVAL_CALENDAR_START", "校历审批开始URL", "calenderAdd"),
    OA_MEETING_APPROVAL_URL("APPROVAL_MEETING", "会议记录审批", "oaMeetingApproval"),
    OA_MEETING_APPROVAL_START_URL("APPROVAL_MEETING_START", "会议记录审批开始URL", "meetingRecordAdd"),
    OA_RESOURCE_APPROVAL_URL("APPROVAL_RESOURCE", "校历审批", "oaResourceApproval"),
    OA_RESOURCE_APPROVAL_START_URL("APPROVAL_RESOURCE_START", "校历审批开始URL", "resourceAdd"),
    OA_INFOCHANGE_APPROVAL_URL("APPROVAL_INFOCHANGE", "校历审批", "oaInfochangeApproval"),
    OA_INFOCHANGE_APPROVAL_START_URL("APPROVAL_INFOCHANGE_START", "校历审批开始URL", "infochangeAdd"),
    OA_MEETINGARRGE_APPROVAL_URL("APPROVAL_MEETINGARRGE", "校历审批", "oaMeetingArrgeApproval"),
    OA_MEETINGARRGE_APPROVAL_START_URL("APPROVAL_MEETINGARRGE_START", "校历审批开始URL", "meetingArrgeAdd"),;

    public String type;

    private String name;

    private String url;

    OaBpmEnum(String type, String name, String url){
        this.type = type;
        this.name = name;
        this.url = url;
    }

    public static String getUrl(String type){
        for(OaBpmEnum e : OaBpmEnum.values()){
            if(e.type.equals(type)){
                return e.url;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
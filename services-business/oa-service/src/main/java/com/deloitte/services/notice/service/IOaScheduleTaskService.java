package com.deloitte.services.notice.service;

public interface IOaScheduleTaskService {

    /**
     * 将通知公告临时表数据格式化存储到数据库中
     */
    void parseNoticeTemp2Db();

}

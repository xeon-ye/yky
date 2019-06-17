package com.deloitte.services.oa.scheduletask;

import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.service.IOaScheduleTaskService;
import com.deloitte.services.oaservice.entity.OaMssSendInfo;
import com.deloitte.services.oaservice.entity.OaScheduleTable;
import com.deloitte.services.oaservice.service.IOaAsyncService;
import com.deloitte.services.oaservice.service.IOaScheduleTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公告定时器
 * 定时执行通知通告对接数据
 */
@Component
@Configuration
//@EnableScheduling // 定时开启定时任务
@Slf4j
public class NoticeScheduleTask {

    @Autowired
    private IOaScheduleTaskService scheduleTaskService;

    /**
     * 定时执行任务
     * 从通知公告对接接口的临时表中取数据，解析存储到OA门户的通知公告表中
     *
     */
    @Scheduled(cron = "${scheduletask.notice}")
    private void parseTempNotice2Db(){
        scheduleTaskService.parseNoticeTemp2Db();
    }

    @Autowired
    public IOaScheduleTableService oaScheduleTableService;

    @Autowired
    UserFeignService userService;

    @Autowired
    IOaAsyncService sendMssService;
    /**
     * 定时执行任务
     * 根据提醒通知对应的人员，每天的早上8
     *
     */
    @Scheduled(cron = "${scheduletask.person.notice}")
    private void sendNoticeToUser(){
        //两时间点，早上8点和下午1点半
        OaScheduleTableQueryForm queryForm = new OaScheduleTableQueryForm();
        queryForm.setWorkType("个人日程");
        //短信通知
        queryForm.setNoticeType("1");
        LocalDateTime time = LocalDateTime.now();
        int hour = time.getHour();
        if(hour < 12){
            //表示上午发的
            time = time.withHour(8).withMinute(30).withSecond(0);
            queryForm.setStartTime(time);
            time = time.withHour(13).withMinute(29).withSecond(59);
            queryForm.setEndTime(time);
        }else{
            //表示下午发的
            time = time.withHour(13).withMinute(30).withSecond(0);
            queryForm.setStartTime(time);
            time = time.plusDays(1).withHour(8).withMinute(29).withSecond(59);
            queryForm.setEndTime(time);
        }
        List<OaScheduleTable> list =  oaScheduleTableService.selectList(queryForm);
        for(OaScheduleTable vo : list){
            Result<UserVo> rs = userService.get(Long.valueOf(vo.getUserId()));
            if(rs.isSuccess()){
                UserVo user = rs.getData();
                OaMssSendInfo oaMssSendInfo = new OaMssSendInfo();
                oaMssSendInfo.setReceiveId(user.getId());
                oaMssSendInfo.setReceiveName(user.getName());
                oaMssSendInfo.setReceiveTelephone(user.getPhone());
                oaMssSendInfo.setMssTitle("个人日程提醒");
                oaMssSendInfo.setMssType("remindMsg");
                //sendMssService.sendMsg(oaMssSendInfo,"您有日程需要处理："+vo.getWorkDesc());
                log.info("发送短信的提醒");
            }
        }
        //邮件通知
        queryForm.setNoticeType("2");
        List<OaScheduleTable> list2 =  oaScheduleTableService.selectList(queryForm);
        for(OaScheduleTable vo : list2){
            Result<UserVo> rs = userService.get(Long.valueOf(vo.getUserId()));
            if(rs.isSuccess()){
                UserVo user = rs.getData();
                log.info("发送邮件的提醒");
                //sendMssService.sendMail(user.getEmail(),"个人日程提醒",vo.getWorkDesc());
            }
        }
    }

}

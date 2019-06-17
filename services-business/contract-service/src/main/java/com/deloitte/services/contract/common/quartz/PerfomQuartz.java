package com.deloitte.services.contract.common.quartz;

import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.service.IBasicInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 合同履行对应定时器
 */
@Component
@EnableScheduling
@Slf4j
public class PerfomQuartz {

    @Autowired
    private IBasicInfoService basicInfoService;

//    @Scheduled(cron = "0/10 * * * * ?")
//    public void testQuartz(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String dateStr = sdf.format(new Date());
//        System.out.println("当前时间"+dateStr);
//    }

    /**
     * 每天4点运行发送履行预警信息
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void sendExecuteWaringQuartz(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        log.info("每天4点运行发送履行预警信息:",  dateStr);
        Result result = basicInfoService.sendExecuteWaring();
        if (result.isSuccess()){
            log.info("每天4点运行发送履行计划预警信息:发生成功");
        }
        result = basicInfoService.sendContractWaring();
        if (result.isSuccess()){
            log.info("每天4点运行发送履行预警信息:发生成功");
        }
    }
}

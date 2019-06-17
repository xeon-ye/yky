package com.deloitte.platform.common.core.monitor;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * @Author : jackliu
 * @Date : Create in 14:05 11/03/2019
 * @Description : 监控系统状态
 * @Modified :
 */
@Slf4j
public class ApplicationEventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        // 在这里可以监听到Spring Boot的生命周期
        // 初始化环境变量
        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent){

        }
        // 初始化完成
        else if (applicationEvent instanceof ApplicationPreparedEvent){

        }
        // 应用刷新
        else if (applicationEvent instanceof ContextRefreshedEvent){

        }
        // 应用已启动完成
        else if (applicationEvent instanceof ApplicationReadyEvent){
            ClientStatus clientStatus=StatusUtil.getClientStatus(null,"");
            log.error("。。。。。。。。。。。。。应用已启动完成。。。。。。。。。。。");
            log.error(clientStatus.toString());
        }
        //应用启动，需要在代码动态添加监听器才可捕获
        else if (applicationEvent instanceof ContextStartedEvent){

        }
        // 应用停止
        else if (applicationEvent instanceof ContextStoppedEvent){

        }
        // 应用关闭
        else if(applicationEvent instanceof ContextClosedEvent){
            log.error("。。。。。。。。。。。。。应用关闭。。。。。。。。。。。");
            ClientStatus clientStatus=StatusUtil.getClientStatus(null,"");
            log.error(clientStatus.toString());
        }
        else{

        }
    }
}

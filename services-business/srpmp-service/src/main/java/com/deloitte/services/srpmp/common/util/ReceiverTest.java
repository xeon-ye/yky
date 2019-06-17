package com.deloitte.services.srpmp.common.util;

import com.alibaba.fastjson.JSON;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.SrpmsUpdateSyncFsscVo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Author:LIJUN
 * Date:17/04/2019
 * Description:
 */
@Component
public class ReceiverTest {

//    @RabbitListener(queues = "QUEUE.SRPMP.FSSC")
    public void process(Message msg){
        String s = new String(msg.getBody());
        System.out.println("QUEUE.SRPMP.FSSC 消费消息：------------------->" + s);
        SrpmsUpdateSyncFsscVo syncFsscVo = JSON.parseObject(s, SrpmsUpdateSyncFsscVo.class);
    }

}

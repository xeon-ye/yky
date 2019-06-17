package com.deloitte.services.fssc.budget.mq;

import com.alibaba.fastjson.JSON;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.SrpmsUpdateSyncFsscVo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectSender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send(){
        SrpmsUpdateSyncFsscVo vo=new SrpmsUpdateSyncFsscVo();
        vo.setMsgType("2323232");
        MessageBuilder messageBuilder = MessageBuilder.withBody(JSON.toJSONBytes(vo));

        amqpTemplate.convertAndSend(MqConfig.que,  messageBuilder.build());
    }
}

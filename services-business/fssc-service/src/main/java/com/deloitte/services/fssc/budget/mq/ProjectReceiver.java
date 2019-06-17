package com.deloitte.services.fssc.budget.mq;

import com.alibaba.fastjson.JSON;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.oaservice.applycenter.feign.SendProcessTaskFeignService;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.SrpmsUpdateSyncFsscVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.budget.mq.entity.SyncMsg;
import com.deloitte.services.fssc.budget.mq.service.ISyncMsgService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.bpm.vo.SendToOaVo;
import com.deloitte.services.fssc.business.rep.entity.RecievePayment;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Map;

@Component
@Slf4j
public class ProjectReceiver {

    @Autowired
    private IBudgetProjectService budgetProjectService;

    @Autowired
    private SendProcessTaskFeignService sendProcessTaskFeignService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private IRecievePaymentService paymentService;

    @Autowired
    private ISyncMsgService syncMsgService;

    @Value("${rep.cliam.url}")
    private String cliamUrl;

    @RabbitListener(queues = MqConfig.que)
    public void receiveProject(Message message) {
        byte[] body = message.getBody();
        String bodyStr = new String(body);
        log.info("接收到科研项目信息:{}", new String(body));
        if (StringUtil.isNotEmpty(bodyStr)) {
            SrpmsUpdateSyncFsscVo vo = JSON.parseObject(bodyStr, SrpmsUpdateSyncFsscVo.class);
            SyncMsg msg = new SyncMsg();
            msg.setRelationId(vo.getSrpmsProjectSyncVo() != null ? vo.getSrpmsProjectSyncVo().getId() : null);
            msg.setMsgStatus(FsscEums.MQ_MESSAGE_STATUS_BEGIN.getValue());
            msg.setMsgType(vo.getMsgType());
            msg.setMsgBody(JSON.toJSONString(vo));
            try {
                log.info("项目接口开始执行保存.....,时间:{}", Calendar.getInstance().getTime());
                budgetProjectService.saveBudgetProject(vo);
                msg.setMsgStatus(FsscEums.MQ_MESSAGE_STATUS_SUCCESS.getValue());
            }catch (Exception e){
                msg.setMsgStatus(FsscEums.MQ_MESSAGE_STATUS_FAILED.getValue());
                msg.setMsgErrorInfo(e.getMessage());
            }finally {
                syncMsgService.saveOrUpdate(msg);
            }
        }
    }


    @RabbitListener(queues = MqConfig.sendToOa)
    public void recieveSendToOa(Message message) {
        byte[] body = message.getBody();
        String bodyStr = new String(body);
        log.info("MQ收到消息:{}", new String(body));
        if (StringUtil.isNotEmpty(bodyStr)) {
            SendToOaVo vo = JSON.parseObject(bodyStr, SendToOaVo.class);

            for (Map<String, String> em : vo.getCopyEmpNos()) {
                copyMessageTo(vo.getProcessInstanceId(), vo.getProcessDefKey(), vo.getDocumentType(),
                        vo.getDocumentNum(), em, vo.getDocumentId());
            }
        }

    }


    private void copyMessageTo(String processInstanceId
            , String processDefKey
            , String documentType
            , String documentNum
            , Map<String, String> em
            , Long documentId) {

         SendProcessTaskForm form = new SendProcessTaskForm();

        form.setProcessInstanceId(processInstanceId);
        form.setProcessDefineKey(processDefKey);
        form.setSourceSystem(FsscConstants.SOURCE_SYSTEM_NAME);

        form.setEmergency("0");
        form.setProcessCreateTime(LocalDateTime.now());
        String message = "现有单据编号" + documentNum + "的收款需要您认领！";
        form.setObjectDescription(message);
        FsscTableNameEums eums = FsscTableNameEums.getByValue(documentType);
        if (eums != null) {
            form.setObjectType(eums.getName());
        }
        form.setObjectUrl(cliamUrl);
        form.setTaskStauts("待阅");
        form.setTaskId(String.valueOf(documentId));

        if(documentId!=null){
            RecievePayment payment = paymentService.getById(documentId);
            if(payment!=null){
                form.setCreateByName(payment.getCreateUserName());
                //金额准备设置
                form.setMoney(BigDecimalUtil.convert(payment.getSkTotal()).toString());
            }
        }

        String empNo = em.get("copyEmpNo");
        Result no = userFeignService.getByEmpNo(empNo);
        if (no.isSuccess()) {
            Object data = no.getData();
            if (data != null) {
                Map<String, Object> userInfo = (Map<String, Object>) data;
                form.setApproverAcount(StringUtil.objectToString(userInfo.get("id")));
                form.setApproverName(StringUtil.objectToString(userInfo.get("name")));
                form.setApproverDescription("认领人");
            }
        }

        form.setObjectId(StringUtil.objectToString(documentId));
        form.setTaskKey("start");
        form.setTaskName(message);
        form.setTaskTitle(message);
        form.setObjectOrderNum(documentNum);
        form.setObjectStauts("已提交");
        log.info("抄送OA参数{}", JSON.toJSONString(form));
        Result result = sendProcessTaskFeignService.add(form);
        log.info("抄送结果{}", result.getMesg());
    }
}

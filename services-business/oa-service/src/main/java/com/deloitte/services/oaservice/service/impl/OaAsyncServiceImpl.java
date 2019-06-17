package com.deloitte.services.oaservice.service.impl;

import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.vo.OaSendMsgFrom;
import com.deloitte.platform.api.oaservice.vo.OaSendMsgReceiveFrom;
import com.deloitte.platform.api.push.feign.SendMssFeignService;
import com.deloitte.platform.api.push.form.SendMssInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.oa.common.server.WebSocketServer;
import com.deloitte.services.oa.util.OaBeanUtils;
import com.deloitte.services.oa.util.VisiableThreadPoolTaskExecutor;
import com.deloitte.services.oaservice.entity.OaMssInfo;
import com.deloitte.services.oaservice.entity.OaMssSendInfo;
import com.deloitte.services.oaservice.service.IOaAsyncService;
import com.deloitte.services.oaservice.service.IOaMssInfoService;
import com.deloitte.services.oaservice.service.IOaMssSendInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Service
@Transactional
@Slf4j
public class OaAsyncServiceImpl implements IOaAsyncService {

    //通过注解引入配置
    @Resource(name = "asyncServiceExecutor")
    private ThreadPoolTaskExecutor executor;

    @Autowired
    IOaMssInfoService oaMssInfoService;

    @Autowired
    IOaMssSendInfoService oaMssSendInfoService;

    @Override
    @Async("asyncServiceExecutor")
    public void sendMsgAsync(OaSendMsgFrom form, List<UserVo> userList) {
        String requestId = form.getRequestId();
        try {
            List<Future> futures = new ArrayList<Future>();
            int total = 0;
            if (userList != null) {
                total = userList.size();
            }
            if (form.getReceiveArr() != null) {
                total = form.getReceiveArr().size() + total;
            }

            VisiableThreadPoolTaskExecutor.executorStatus.put(requestId, true);
            Map<String, String> map = new HashMap<String, String>();
//            Future<?> monitoringFuture = executor.submit(() -> {
//                monitoring(requestId,total,false);
//            });
            OaMssInfo oaMssInfo = new OaMssInfo();
            oaMssInfo.setMssContent(form.getContent());
            oaMssInfo.setMssTitle(form.getTitle());
            oaMssInfo.setSendUserId(form.getSendUserId());
            oaMssInfo.setSendUserName(form.getSendUserName());
            oaMssInfo.setRequestId(form.getRequestId());
            oaMssInfo.setMssType(form.getMssType());
            oaMssInfo.setCreateBy(form.getSendUserId());
            oaMssInfo.setUpdateBy(form.getSendUserId());
//            oaMssInfoService.save(oaMssInfo);
            //按80个手机号码汇总
            StringBuffer phone = new StringBuffer();
            StringBuffer receiveId = new StringBuffer();
            StringBuffer receiveName = new StringBuffer();
            int k=0;
            for (int i = 0; form.getReceiveArr() != null && i < form.getReceiveArr().size(); i++) {
                //Thread.sleep(300);
                OaSendMsgReceiveFrom vo = form.getReceiveArr().get(i);
                if (map.get(vo.getReceiveId()) != null) {
                    //如果最后一个是重复，会跳出循环，攒着的组装电话不会发送
                    if(i == form.getReceiveArr().size()-1 && k!=0){

                    }else {
                        continue;
                    }
                } else {
                    map.put(vo.getReceiveId(), vo.getReceiveTelephone());
                }
                if(k<80){
                    //判断i是否为最后一个，如果为最后一个，则直接拼接完成后跳出循环
                    if(i == form.getReceiveArr().size()-1){
                        phone.append(vo.getReceiveTelephone());
                        receiveId.append(vo.getReceiveId());
                        receiveName.append(vo.getReceiveTelephone());
                    }else {
                        if (k == 79) {
                            phone.append(vo.getReceiveTelephone());
                            receiveId.append(vo.getReceiveId());
                            receiveName.append(vo.getReceiveTelephone());
                        } else {
                            phone.append(vo.getReceiveTelephone()).append(",");
                            receiveId.append(vo.getReceiveId()).append(",");
                            receiveName.append(vo.getReceiveName()).append(",");
                            k++;
                            continue;
                        }
                    }
                }
                //按照80个一组拼装后，数据还原
                k=0;
                vo.setReceiveId(receiveId.toString());
                vo.setReceiveName(receiveName.toString());
                vo.setReceiveTelephone(phone.toString());
                phone = new StringBuffer();
                receiveId = new StringBuffer();
                receiveName = new StringBuffer();
                Boolean executorStatus = VisiableThreadPoolTaskExecutor.executorStatus.get(requestId);
                if (executorStatus != null && executorStatus) {
                    Future<?> future = executor.submit(() -> {
                        sendMsg(requestId, oaMssInfo, vo, null);
                    });
                    futures.add(future);
                } else {
                    VisiableThreadPoolTaskExecutor.executorStatus.remove(requestId);
                    break;
                }
            }
            for (int i = 0; userList != null && i < userList.size(); i++) {
                //Thread.sleep(300);
                UserVo vo = userList.get(i);
                if (map.get(vo.getId()) != null) {
                    //如果最后一个是重复，会跳出循环，攒着的组装电话不会发送
                    if(i == form.getReceiveArr().size()-1 && k!=0){

                    }else {
                        continue;
                    }
                } else {
                    map.put(vo.getId(), vo.getPhone());
                }
                if(k<80){
                    //判断i是否为最后一个，如果为最后一个，则直接拼接完成后跳出循环
                    if(i == form.getReceiveArr().size()-1){
                        phone.append(vo.getPhone());
                        receiveId.append(vo.getId());
                        receiveName.append(vo.getName());
                    }else {

                        if (k == 79) {
                            phone.append(vo.getPhone());
                            receiveId.append(vo.getId());
                            receiveName.append(vo.getName());
                        } else {
                            phone.append(vo.getPhone()).append(",");
                            receiveId.append(vo.getId()).append(",");
                            receiveName.append(vo.getName()).append(",");
                            k++;
                            continue;
                        }
                    }
                }
                //按照80个一组拼装后，数据还原
                k=0;
                vo.setId(receiveId.toString());
                vo.setName(receiveName.toString());
                vo.setPhone(phone.toString());
                phone = new StringBuffer();
                receiveId = new StringBuffer();
                receiveName = new StringBuffer();
                Boolean executorStatus = VisiableThreadPoolTaskExecutor.executorStatus.get(requestId);
                if (executorStatus != null && executorStatus) {
                    Future<?> future = executor.submit(() -> {
                        sendMsg(requestId, oaMssInfo, null, vo);
                    });
                    futures.add(future);
                } else {
                    VisiableThreadPoolTaskExecutor.executorStatus.remove(requestId);
                    break;
                }
            }
            VisiableThreadPoolTaskExecutor.futuresMap.put(requestId, futures);
            log.info("执行全部的线程监控");
            monitoring(requestId, total, VisiableThreadPoolTaskExecutor.futuresMap.get(requestId));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            VisiableThreadPoolTaskExecutor.futuresMap.remove(requestId);
            VisiableThreadPoolTaskExecutor.executorStatus.remove(requestId);
        }
    }

    @Override
    @Async("asyncServiceExecutor")
    public void monitoring(String requestId, int total, List<Future> futures) {
        log.info("total:{}", total);
        int completeCount = 0;
        int sum = total;
        if (futures != null) {
            if (total > futures.size()) {
                sum = futures.size();
            }
            for (Future<?> future : futures) {
                while (true) {
                    if (!future.isCancelled()) {
                        //线程没取消，完成一个向前台发送一个通知
                        if (future.isDone()) {
                            try {
                                completeCount++;
                                log.info("total:{},completeCount:{}", total, completeCount);
                                String percentage = new BigDecimal(completeCount * 100).divide(new BigDecimal(sum), 3, BigDecimal.ROUND_HALF_UP).toString();
                                String message = "{\"percentage\":\"" + percentage + "\",\"total\":\""+total+"\",\"completeCount\":\""+completeCount+"\"}";
                                WebSocketServer.sendInfo(message, requestId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } else {
                            try {
                                Thread.sleep(1);//每次轮询休息1毫秒（CPU纳秒级），避免CPU高速轮循耗空CPU---》新手别忘记这个
                            } catch (Exception e) {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }


    public void sendMsg(String requestId, OaMssInfo oaMssInfo, OaSendMsgReceiveFrom receiveFrom, UserVo userVo) {
        OaMssSendInfo oaMssSendInfo = new OaBeanUtils<OaMssSendInfo>().copyObj(oaMssInfo, OaMssSendInfo.class);
        oaMssSendInfo.setMssId(oaMssInfo.getId()==null?null:oaMssInfo.getId().toString());
        oaMssSendInfo.setId(null);
        if (receiveFrom != null) {
            oaMssSendInfo.setReceiveId(receiveFrom.getReceiveId());
            oaMssSendInfo.setReceiveName(receiveFrom.getReceiveName());
            oaMssSendInfo.setReceiveTelephone(receiveFrom.getReceiveTelephone());
        }
        if (userVo != null) {
            oaMssSendInfo.setReceiveId(userVo.getId());
            oaMssSendInfo.setReceiveName(userVo.getName());
            oaMssSendInfo.setReceiveTelephone(userVo.getPhone());
        }
        //oaMssSendInfoService.save(oaMssSendInfo);
        sendMsg(oaMssSendInfo, oaMssInfo.getMssContent());

        log.info(requestId + ": send Msg :");
    }

    @Autowired
    SendMssFeignService sendMssFeignService;

    @Override
    public void sendMsg(OaMssSendInfo oaMssSendInfo, String content) {
        SendMssInfoForm sendMssInfoForm = new OaBeanUtils<SendMssInfoForm>().copyObj(oaMssSendInfo, SendMssInfoForm.class);
        sendMssInfoForm.setResourceSystem("oaservice");
        oaMssSendInfo.setResourceSystem("oaservice");
        sendMssInfoForm.setMssContent(content);
        Result rs = sendMssFeignService.sendMss(sendMssInfoForm);
//        try{
//            long count = Math.round((sendMssInfoForm.getMssContent().length() + 9) / 70 + 0.5);
//            sendMssInfoForm.setMssCount(count);
//            oaMssSendInfo.setMssCount(count);
//            Result rs = sendMssFeignService.sendMss(sendMssInfoForm);
//            if (rs.isSuccess()) {
//                oaMssSendInfo.setIsSend("是");
//            } else {
//                oaMssSendInfo.setIsSend("否");
//            }
//        }catch (Exception e){
//            oaMssSendInfo.setIsSend("否");
//            oaMssSendInfo.setMssCount(0l);
//        }
//        oaMssSendInfoService.saveOrUpdate(oaMssSendInfo);
    }

    @Autowired
    UserFeignService userService;

    public Result<List<UserVo>> getDiyOrgUsers(OaSendMsgReceiveFrom oaSendMsgReceiveFrom) {
        UserQueryForm userQueryForm = new UserQueryForm();
        userQueryForm.setState("1");
        try {
            if ("all".equalsIgnoreCase(oaSendMsgReceiveFrom.getReceiveId())) {
                //群组为所有人
                Result<List<UserVo>> listUserResult = userService.list(userQueryForm);
                return listUserResult;
            } else {
                Class clazz = userQueryForm.getClass();
                Field field = clazz.getDeclaredField(oaSendMsgReceiveFrom.getReceiveId());
                field.setAccessible(true);
                field.set(userQueryForm, oaSendMsgReceiveFrom.getReceiveName());
            }
            Result<List<UserVo>> listUserResult = userService.list(userQueryForm);
            return listUserResult;
        } catch (Exception e) {
            return Result.fail();
        }
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendMail(String to, String title, String content) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

package com.deloitte.services.push.utils;

import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.push.exception.PushErrorType;
import lombok.extern.slf4j.Slf4j;
import smscommon.SmsClient;
import smsmessage.sms.SMSclientSubmit;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DIYSmsUtil {
    /**
     * 发送短信
     * @param phone 电话
     * @param content 内容
     * @throws Exception
     */
    public static Map<String,String> send(String phone, String content) {
        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("content",content);
        //建立socket连接
        SmsClient smsClient =SmsClient.getInstance();
        try {
            //实例化用于下发短信的对象
            SMSclientSubmit mt = new SMSclientSubmit();
            //接入号码
            mt.setSrcID("106575020569");
            // 用户号码
            mt.setDestID(phone);
            mt.setMsgContent(content);
            //设置优先级，可选择字段，默认为1。
            mt.setPriority(1);
            //下发信息的返回值
            String retVal = "1";
            String result = "";
            int i = 0;
            while ("1".equals(retVal) || "2".equals(retVal)
                    || "3".equals(retVal) || "4".equals(retVal)
                    || "5".equals(retVal) || "6".equals(retVal)
                    || "7".equals(retVal) || "8".equals(retVal)
                    || "78".equals(retVal)) {
                //获得信息下发的标识，用于匹配状态报告
                retVal = mt.sendSMS();
                if ("2".equals(retVal)) {
                    result = "没有建立连接或连接断开";
                    log.error("没有建立连接或连接断开");
                    Thread.sleep(1000);
                } else if ("3".equals(retVal)) {
                    result = "流量超过设定值";
                    log.error(result);
                    Thread.sleep(500);
                } else if ("4".equals(retVal)) {
                    result = "短信长度超出限制";
                    log.error(result);
                    Thread.sleep(500);
                } else if ("5".equals(retVal)) {
                    result = "手机号为空";
                    log.error(result);
                    Thread.sleep(500);
                } else if ("7".equals(retVal)) {
                    result = "手机号为空";
                    log.error(result);
                    Thread.sleep(500);
                } else if ("8".equals(retVal)) {
                    result = "手机号超长";
                    log.error(result);
                    Thread.sleep(500);
                } else if ("78".equals(retVal)) {
                    result = "手机号为空或号码超长，没有正确的号码";
                    log.error(result);
                    Thread.sleep(500);
                } else {
                    log.info("企业下行消息标识 ["+retVal+"]，"
                            +"目的号码 ["+mt.getDestID()+"]，"
                            +"短信内容 ["+mt.getMsgContent()+"]");
                    map.put("flag","true");
                    map.put("msg","企业下行消息标识 ["+retVal+"]，"
                            +"目的号码 ["+mt.getDestID()+"]，");
                    break;
                }
                i ++;
                if(i>10){
                    //如果失败，重试10次
                    map.put("flag","false");
                    map.put("msg",result);
                    break;
                }
            }

           /* for (int j=0;j<100;j++){
                //获取信息
                DELIVER deliver = new DELIVER();
                deliver = ComFun.getSMS();
                if(deliver != null && deliver.getMsgFlag() == 1){
                    //下行内容
                    map.put("msgContent",deliver.getMsgContent());
                    //接受短信情况
                    map.put("reserve",deliver.getReserve());
                    //号码
                    map.put("destid",deliver.getDestID());
                    log.info("短信返回："+ JSON.toJSONString(map));
                    return map;
                }else{
                    log.info("短信上行信息："+JSON.toJSONString(deliver));
                }
            }*/
            return map;

        } catch (Exception e) {
            throw new ServiceException(PushErrorType.SYSTEM_ERROR,e.getMessage());
        }
    }

}

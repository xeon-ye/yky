package com.deloitte.services.push.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.feign.OaMssInfoFeignService;
import com.deloitte.platform.api.oaservice.vo.OaMssInfoForm;
import com.deloitte.platform.api.push.client.SendMssClient;
import com.deloitte.platform.api.push.form.OaMssSendInfoForm;
import com.deloitte.platform.api.push.form.SendMssInfoForm;
import com.deloitte.platform.api.push.param.PushMssSendInfoVO;
import com.deloitte.platform.api.push.param.PushMssSendInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.push.entity.PushMssInfo;
import com.deloitte.services.push.entity.PushMssSendInfo;
import com.deloitte.services.push.service.IPushMssInfoService;
import com.deloitte.services.push.service.IPushMssSendInfoService;
import com.deloitte.services.push.utils.DIYSmsUtil;
import com.deloitte.services.push.utils.MailUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SmsController implements SendMssClient {

    @Autowired
    OaMssInfoFeignService oaMssInfoFeignService;

    @Autowired
    IPushMssInfoService oaMssInfoService;

    @Autowired
    IPushMssSendInfoService oaMssSendInfoService;

    @Override
    @ApiOperation(value = "测试发送短信接口", notes = "测试发送短信接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", value = "电话号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "content", value = "内容", required = true, dataType = "String")
    })
    public Result send(@RequestParam("phone") String phone, @RequestParam("content") String content) {
        int length = 0;
        Map<String, String> map = new HashMap<>();
//        if (content != null && content.length() > 500) {
//            length = content.length();
//            int count = new BigDecimal(Math.round(length / 500 + 0.5)).intValue();
//            for (int i = 0; i < count; i++) {
//                if ((i + 1) * 500 > length) {
//                    map = DIYSmsUtil.send(phone, content.substring(i * 500));
//                } else {
//                    map = DIYSmsUtil.send(phone, content.substring(i * 500, (i + 1) * 500));
//                }
//            }
//        } else {
//            map = DIYSmsUtil.send(phone, content);
//        }
        map = DIYSmsUtil.send(phone, content);
        return new Result().sucess(map);
    }

    @Override
    @ApiOperation(value = "发送短信接口", notes = "发送短信接口")
    public Result sendMss(@Valid @RequestBody SendMssInfoForm sendMssInfoForm) {
        int length = 0;
        int maxLength = 2000;
        String content = sendMssInfoForm.getMssContent();
        Map<String, String> map = new HashMap<>();
        if (content != null && content.length() > maxLength) {
            length = content.length();
            int count = new BigDecimal(Math.round(length / maxLength + 0.5)).intValue();
            for (int i = 0; i < count; i++) {
                if ((i + 1) * maxLength > length) {
                    map = DIYSmsUtil.send(sendMssInfoForm.getReceiveTelephone(), content.substring(i * maxLength));
                } else {
                    map = DIYSmsUtil.send(sendMssInfoForm.getReceiveTelephone(), content.substring(i * maxLength, (i + 1) * maxLength));
                }
                //可能出现部分发送失败
            }
        } else {
            map = DIYSmsUtil.send(sendMssInfoForm.getReceiveTelephone(), content);
        }
        long count = Math.round((sendMssInfoForm.getMssContent().length() + 9) / 70 + 0.5);
        sendMssInfoForm.setMssCount(count);
        if (map != null && "true".equalsIgnoreCase(map.get("flag"))) {
            sendMssInfoForm.setIsSend("是");
        } else {
            sendMssInfoForm.setIsSend("否");
            if (map != null) {
                sendMssInfoForm.setExt1(map.get("msg"));
            }else{
                sendMssInfoForm.setExt1("未知错误");
            }
        }
        OaMssInfoForm oaMssInfoForm = new BeanUtils<OaMssInfoForm>().copyObj(sendMssInfoForm, OaMssInfoForm.class);
        OaMssSendInfoForm oaMssSendInfoForm = new BeanUtils<OaMssSendInfoForm>().copyObj(sendMssInfoForm, OaMssSendInfoForm.class);
        //调用保存记录的方法
        oaMssInfoForm.setId(sendMssInfoForm.getMssId());
        PushMssInfo oaMssInfo =  new BeanUtils<PushMssInfo>().copyObj(oaMssInfoForm, PushMssInfo.class);
        oaMssInfoService.save(oaMssInfo);
        oaMssInfoForm.setId(oaMssInfo.getId()+"");
//        Result rs = oaMssInfoFeignService.saveOaMssInfo(oaMssInfoForm);
//        if(rs.isSuccess()){
//            oaMssInfoForm.setId(rs.getData().toString());
//        }
        oaMssSendInfoForm.setMssId(oaMssInfoForm.getId());
        if (sendMssInfoForm.getReceiveTelephone().indexOf(",") > 0) {
            String[] phoneArr = sendMssInfoForm.getReceiveTelephone().split(",");
            for (int i = 0; i < phoneArr.length; i++) {
                //支持手机号码群发，多个号码以逗号分隔
                oaMssSendInfoForm.setReceiveTelephone(phoneArr[i]);
                if (sendMssInfoForm.getId() != null && sendMssInfoForm.getId().indexOf(",") > 0) {
                    //判断是否设置了id，如果设置了，需要和phone一样按照逗号分隔
                    String[] idsArr = sendMssInfoForm.getId().split(",");
                    oaMssSendInfoForm.setId(idsArr[i]);
                }
                if (sendMssInfoForm.getReceiveId() != null && sendMssInfoForm.getReceiveId().indexOf(",") > 0) {
                    //判断是否设置了id，如果设置了，需要和phone一样按照逗号分隔
                    String[] idsArr = sendMssInfoForm.getReceiveId().split(",");
                    oaMssSendInfoForm.setReceiveId(idsArr[i]);
                }
                if (sendMssInfoForm.getReceiveName() != null && sendMssInfoForm.getReceiveName().indexOf(",") > 0) {
                    //判断是否设置了id，如果设置了，需要和phone一样按照逗号分隔
                    String[] idsArr = sendMssInfoForm.getReceiveName().split(",");
                    oaMssSendInfoForm.setReceiveName(idsArr[i]);
                }
                PushMssSendInfo oaMssSendInfo =  new BeanUtils<PushMssSendInfo>().copyObj(oaMssSendInfoForm, PushMssSendInfo.class);
                oaMssSendInfoService.save(oaMssSendInfo);
//               oaMssInfoFeignService.saveOaMssSendInfo(oaMssSendInfoForm);
            }
        } else {
            //单个电话号码，直接调用保存
            PushMssSendInfo oaMssSendInfo =  new BeanUtils<PushMssSendInfo>().copyObj(oaMssSendInfoForm, PushMssSendInfo.class);
            oaMssSendInfoService.save(oaMssSendInfo);
//            oaMssInfoFeignService.saveOaMssSendInfo(oaMssSendInfoForm);
        }
        return Result.success(map);
    }

//    @ApiOperation(value = "获取短信内容", notes = "获取短信内容")
//    public Result<OaMssInfoForm> getPushMsgInfo(@PathVariable String id){
//        PushMssInfo info =  oaMssInfoService.getById(id);
//        return new Result<OaMssInfoForm>().sucess(new BeanUtils<OaMssInfoForm>().copyObj(info,OaMssInfoForm.class));
//    }

    @Override
    @ApiOperation(value = "获取本月发送的短信数", notes = "获取本月发送的短信数")
    public Result<Long> getPushMssTotal(@RequestParam(name = "date") @ApiParam(name="月份格式yyyy-MM",value="月份格式yyyy-MM",required=true)String date){
        Long total =  oaMssSendInfoService.getMssMonthTotal(date);
        return new Result<Long>().sucess(total);
    }
    @Override
    @ApiOperation(value = "分页查询OaMssSendInfoVO", notes = "根据条件查询OaMssSendInfoVO分页数据")
    public Result<IPage<PushMssSendInfoVO>> search(@Valid @RequestBody @ApiParam(name="oaAssistantMappingQueryForm",value="OaAssistantMapping查询参数",required=true) PushMssSendInfoForm queryForm) {
        IPage<PushMssSendInfo> oaAssistantMappingPage=oaMssSendInfoService.selectPage(queryForm);
        IPage<PushMssSendInfoVO> oaAssistantMappingVoPage=new BeanUtils<PushMssSendInfoVO>().copyPageObjs(oaAssistantMappingPage, PushMssSendInfoVO.class);
        return new Result<IPage<PushMssSendInfoVO>>().sucess(oaAssistantMappingVoPage);
    }

    @Override
    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mail", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "title", value = "标题", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "content", value = "内容", required = true, dataType = "String")
    })
    public Result sendMail(@RequestParam("mail") String mail,@RequestParam("title") String title,@RequestParam("content") String content){
        MailUtils.send(mail,title,content);
        return Result.success();
    }
}

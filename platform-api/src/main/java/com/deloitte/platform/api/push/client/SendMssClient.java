package com.deloitte.platform.api.push.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.vo.OaMssInfoForm;
import com.deloitte.platform.api.push.form.SendMssInfoForm;
import com.deloitte.platform.api.push.param.PushMssSendInfoVO;
import com.deloitte.platform.api.push.param.PushMssSendInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface SendMssClient {

    public static final String path="/push";

    @PostMapping(path = path+"/sms/send")
    Result send(@RequestParam("phone") String phone, @RequestParam("content") String content);

    @PostMapping(path = path+"/sms/sendMss")
    Result sendMss(@Valid @RequestBody SendMssInfoForm sendMssInfoForm);

//    @GetMapping(path=path+"/getSendMsg/{id}")
//    Result<OaMssInfoForm> getPushMsgInfo(@PathVariable String id);

    @GetMapping(path=path+"/getSendMsg/monthTotal")
    Result<Long> getPushMssTotal(@RequestParam(name = "date") String date);

    @PostMapping(path = path+"/sendMsg/page/search")
    Result<IPage<PushMssSendInfoVO>> search(@Valid @RequestBody PushMssSendInfoForm queryForm);

    @GetMapping(path=path+"/sendMail")
    Result sendMail(@RequestParam("mail") String mail,@RequestParam("title") String title,@RequestParam("content") String content);
}

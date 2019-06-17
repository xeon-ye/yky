package com.deloitte.services.oaservice.controller;

import com.deloitte.platform.api.oaservice.client.OaMssInfoClient;
import com.deloitte.platform.api.oaservice.vo.OaMssInfoForm;
import com.deloitte.platform.api.push.form.OaMssSendInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.oa.util.OaBeanUtils;
import com.deloitte.services.oaservice.entity.OaMssInfo;
import com.deloitte.services.oaservice.entity.OaMssSendInfo;
import com.deloitte.services.oaservice.service.IOaMssInfoService;
import com.deloitte.services.oaservice.service.IOaMssSendInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "OaMssInfoController操作接口")
@Slf4j
@RestController
public class OaMssInfoController implements OaMssInfoClient {

    @Autowired
    IOaMssInfoService oaMssInfoService;

    @Autowired
    IOaMssSendInfoService oaMssSendInfoService;

    @Override
    @ApiOperation(value = "记录发送短信内容", notes = "记录发送短信内容")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result saveOaMssInfo(@Valid @RequestBody @ApiParam(name="oaMssInfoForm",value="记录发送短信内容",required=true)OaMssInfoForm oaMssInfoForm) {
        OaMssInfo oaMssInfo =  new OaBeanUtils<OaMssInfo>().copyObj(oaMssInfoForm,OaMssInfo.class);
        oaMssInfoService.saveOrUpdate(oaMssInfo);
        return Result.success(oaMssInfo.getId());
    }

    @Override
    @ApiOperation(value = "记录发送短信对象及状态", notes = "记录发送短信对象及状态")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result saveOaMssSendInfo(@Valid @RequestBody @ApiParam(name="oaMssSendInfoForm",value="记录发送短信对象及状态",required=true)OaMssSendInfoForm oaMssSendInfoForm) {
        OaMssSendInfo oaMssSendInfo =  new OaBeanUtils<OaMssSendInfo>().copyObj(oaMssSendInfoForm,OaMssSendInfo.class);
        return Result.success(oaMssSendInfoService.saveOrUpdate(oaMssSendInfo));
    }
}

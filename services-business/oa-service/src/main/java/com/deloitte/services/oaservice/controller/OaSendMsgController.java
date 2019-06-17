package com.deloitte.services.oaservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaAssistantMappingQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaAssistantMappingVo;
import com.deloitte.platform.api.oaservice.vo.OaSendMsgFrom;
import com.deloitte.platform.api.oaservice.vo.OaSendMsgReceiveFrom;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.oa.common.server.WebSocketServer;
import com.deloitte.services.oa.util.OaBeanUtils;
import com.deloitte.services.oa.util.VisiableThreadPoolTaskExecutor;
import com.deloitte.services.oaservice.entity.*;
import com.deloitte.services.oaservice.service.IOaAsyncService;
import com.deloitte.services.oaservice.service.IOaMssInfoService;
import com.deloitte.services.oaservice.service.IOaMssSendInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Api(value = "OaSendMsgController", description = "短信中心API")
@Slf4j
@RequestMapping("/oaservice")
@RestController
public class OaSendMsgController {

    @Autowired
    IOaAsyncService service;

    @Autowired
    UserFeignService userService;

    @Autowired
    DeptFeignService deptService;

    @Autowired
    OrganizationFeignService organizationFeignService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IOaMssInfoService oaMssInfoService;

    @Autowired
    IOaMssSendInfoService oaMssSendInfoService;

    @PostMapping(path = "/sendMsg")
    public Result sendMsg(@Valid @RequestBody @ApiParam(name="form",value="发送短信内容",required=true) OaSendMsgFrom form){
        List<OaSendMsgReceiveFrom> list = form.getReceiveArr();
        OaSendMsgFrom oaSendMsgFrom = new OaSendMsgFrom();
        oaSendMsgFrom.setContent(form.getContent());
        oaSendMsgFrom.setMssType(form.getMssType());
        oaSendMsgFrom.setRequestId(form.getRequestId());
        oaSendMsgFrom.setSendUserId(form.getSendUserId());
        oaSendMsgFrom.setSendUserName(form.getSendUserName());
        oaSendMsgFrom.setTitle(form.getTitle());

        List<OaSendMsgReceiveFrom> receiverList = new ArrayList<>();
        List<UserVo> userList = new ArrayList<>();
        for(OaSendMsgReceiveFrom oaSendMsgReceiveFrom: list){
            String receiveType =  oaSendMsgReceiveFrom.getReceiveType();
            if("dept".equals(receiveType)){
               //按单位发送
                UserQueryForm userQueryForm = new UserQueryForm();
                userQueryForm.setState("1");
                Result<DeptVo> deptResult = deptService.get(Long.valueOf(oaSendMsgReceiveFrom.getReceiveId()));
                if (deptResult.isSuccess()) {
                    userQueryForm.setDept(deptResult.getData().getDeptCode());
                    Result<List<UserVo>> listUserResult = userService.list(userQueryForm);
                    if(listUserResult.isSuccess()){
                        userList.addAll(listUserResult.getData());
                    }else{
                        return Result.fail();
                    }
                }else{
                    return Result.fail();
                }
            }else if("org".equals(receiveType)){
                //按组织发送
                Result<OrganizationVo> orgResult = organizationFeignService.get(Long.valueOf(oaSendMsgReceiveFrom.getReceiveId()));
                if (orgResult.isSuccess()) {
                    OrganizationVo org = orgResult.getData();
                    Result<List<UserVo>> listUserResult = userService.getByOrgCodeList(org.getCode());
                    if (listUserResult.isSuccess()) {
                        userList.addAll(listUserResult.getData());
                    }else{
                        return Result.fail();
                    }
                }else{
                    return Result.fail();
                }
            }else if("diyOrg".equals(receiveType)){
                //按自定义群组发送
                Result<List<UserVo>> listUserResult = service.getDiyOrgUsers(oaSendMsgReceiveFrom);//userService.list(userQueryForm);
                if(listUserResult.isSuccess()){
                    userList.addAll(listUserResult.getData());
                }else{
                    return Result.fail();
                }
            }else{
                //按个人发送
                receiverList.add(oaSendMsgReceiveFrom);
            }
        }
        oaSendMsgFrom.setReceiveArr(receiverList);
        service.sendMsgAsync(oaSendMsgFrom,userList);
        return Result.success();
    }

    @GetMapping(path = "/sendMsg/process")
    public Result getProcess(@RequestParam(name = "requestId") String requestId){
        Object num = redisTemplate.opsForValue().get(requestId);
        return Result.success(num);
    }

    @GetMapping(path = "/ws/sendMsg/process")
    String sendOneMessage(@RequestParam(required=true) String message,@RequestParam(required=true) String id){
        try {
            WebSocketServer.sendInfo(message,id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @PostMapping(path = "/sendMsg/stop")
    public Result stopSendMsg(@RequestBody OaSendMsgFrom form){
        String requestId = form.getRequestId();
        List<Future> futures = VisiableThreadPoolTaskExecutor.futuresMap.get(requestId);
        VisiableThreadPoolTaskExecutor.executorStatus.put(requestId,false);
        if(futures!=null) {
            for (Future<?> future : futures) {
                future.cancel(true);
            }
//            String message = "{\"percentage\":\"-1\",\"error\":\"操作取消\"}";
//            try {
//                WebSocketServer.sendInfo(message, requestId);
//            } catch (IOException e) {
//                return Result.fail("取消操作失败");
//            }
        }
        return Result.success();
    }

    @GetMapping(path="/getSendMsg/{id}")
    public Result<OaMssInfo> getMsgInfo(@PathVariable String id){
        OaMssInfo info =  oaMssInfoService.getById(id);
        return new Result<OaMssInfo>().sucess(info);
    }

    @GetMapping(path="/getSendMsg/monthTotal")
    public Result<Long> getMssTotal(@RequestParam(name = "date") @ApiParam(name="月份格式yyyy-MM",value="月份格式yyyy-MM",required=true)String date){
        Long total =  oaMssSendInfoService.getMssMonthTotal(date);
        return new Result<Long>().sucess(total);
    }

    @PostMapping(path="/sendMsgAgain")
    public Result sendMsgAgain(@Valid @RequestBody @ApiParam(name="oaAssistantMappingQueryForm",value="OaAssistantMapping查询参数",required=true) OaMssSendInfoForm[] queryForm){
       for(OaMssSendInfoForm form : queryForm){
           OaMssSendInfo oaMssSendInfo =  new OaBeanUtils<OaMssSendInfo>().copyObj(form,OaMssSendInfo.class);
           OaMssInfo info =  oaMssInfoService.getById(oaMssSendInfo.getMssId());
           service.sendMsg(oaMssSendInfo,info.getMssContent());
       }
        return Result.success();
    }

    @ApiOperation(value = "分页查询OaAssistantMapping", notes = "根据条件查询OaAssistantMapping分页数据")
    @PostMapping(path = "/sendMsg/page/search")
    public Result<IPage<OaMssSendInfoVO>> search(@Valid @RequestBody @ApiParam(name="oaAssistantMappingQueryForm",value="OaAssistantMapping查询参数",required=true) OaMssSendInfoForm queryForm) {
        log.info("search with oaAssistantMappingQueryForm:", queryForm.toString());
        IPage<OaMssSendInfo> oaAssistantMappingPage=oaMssSendInfoService.selectPage(queryForm);
        IPage<OaMssSendInfoVO> oaAssistantMappingVoPage=new BeanUtils<OaMssSendInfoVO>().copyPageObjs(oaAssistantMappingPage,OaMssSendInfoVO.class);
        return new Result<IPage<OaMssSendInfoVO>>().sucess(oaAssistantMappingVoPage);
    }
}

package com.deloitte.services.isump.controller;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorApprove;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormApprove;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.isump.UserWorkFlowClient;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.param.bpm.ApprovalForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.isump.entity.DeputyAccount;
import com.deloitte.services.isump.entity.DeputyAccountRole;
import com.deloitte.services.isump.entity.Role;
import com.deloitte.services.isump.service.IBpmService;
import com.deloitte.services.isump.service.IDeputyAccountRoleService;
import com.deloitte.services.isump.service.IDeputyAccountService;
import com.deloitte.services.isump.service.IRoleService;
import com.deloitte.services.isump.util.ConfigPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   User控制器实现类
 * @Modified :
 */
@Api(tags = "UserWorkflow操作接口")
@Slf4j
@RestController
public class UserWorkFlowController implements UserWorkFlowClient {

    @Autowired
    private IBpmService bpmService;


    @Override
    @ApiOperation(value = "机构管理员审批-通过")
    public Result approvalUserCheck(@ApiParam(value = "审批对象", required = true) @RequestBody ApprovalForm approvalForm) {
        bpmService.approvalProcess(approvalForm);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "审批-终止")
    public Result stopProcess(@ApiParam(value = "审批对象", required = true) @RequestBody ApprovalForm approvalForm) {
        return bpmService.stopProcess(approvalForm);
    }

    @Override
    @ApiOperation(value = "机构注册审批-通过")
    public Result approvalOrgCheck(
            @ApiParam(value = "审批对象", required = true) @RequestBody ApprovalForm approvalForm) {
        return bpmService.orgApprovalCheck(approvalForm);
    }

}




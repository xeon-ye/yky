package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.feign.BpmApprovalMatrixFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.contract.vo.StandardTemplateVo;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.feign.SendProcessTaskFeignService;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskVo;
import com.deloitte.platform.api.project.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.project.vo.DeclarationsVO;
import com.deloitte.platform.api.project.vo.ProcessTaskApprovalVo;
import com.deloitte.platform.api.project.vo.TaskNodeActionVO;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.enums.ValueEnums;
import com.deloitte.services.project.common.enums.VoucherStatusEnums;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.entity.Application;
import com.deloitte.services.project.entity.ApprovalVouchers;
import com.deloitte.services.project.entity.Projects;
import com.deloitte.services.project.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.deloitte.platform.common.core.entity.vo.Result.success;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-08
 * @Description :   我的工作项
 * @Modified :
 */
@Api(tags = "1-我的工作台")
@Slf4j
@RestController
@RequestMapping("/work")
public class MyWorkController {

    @Autowired
    public IBPMService bpmService;

    @Autowired
    private IApprovalVouchersService approvalVoucherService;

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Autowired
    private BpmApprovalMatrixFeignService bpmApprovalMatrixFeignService;

    @Autowired
    public ICommonService commonService;

    @Autowired
    public IApplicationService applicationService;
    @Autowired
    public IAppDepartmentalBudgetService appDepartmentalBudgetService;

    @Autowired
    private ICarbonCopyService carbonCopyService;
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private SendProcessTaskFeignService sendProcessTaskFeignService;
    @Autowired
    private IProjectsService projectsService;

    @ApiOperation(value = "1-1-1查询待办", notes = "1-1-1查询待办")
    @PostMapping("/backLog")
    public Result backLog(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm) {
        UserVo userVo = commonService.getCurrentUser();
        return bpmService.backLog(processTaskQueryForm,userVo) ;
    }

    @ApiOperation(value = "1-1-2查询已办", notes = "1-1-2查询已办")
    @PostMapping("/done")
    public Result done(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm) {
        UserVo userVo = commonService.getCurrentUser();
        return bpmService.done(processTaskQueryForm,userVo) ;
    }

    @ApiOperation(value = "1-1-3审核流程", notes = "1-1-3审核流程")
    @PostMapping("/auditApprove")
    public Result auditApprove(@Valid @RequestBody TaskNodeActionVO actionVO ) {
        supplyTaskNodeActionVO(actionVO);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        Result result = new Result();

        //查询单据，获取合同id
        ApprovalVouchers approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        if (VoucherTypeEnums.PROJECT_APPLY_PROCESS.getCode().equals(approvalVoucher.getVoucherType())) {//合同审批流程的审批结束的业务代码
            Application application = applicationService.getById(approvalVoucher.getBusinessId());//申报书
            Projects projects = projectsService.getById(application.getProjectId());
            processVariables.put("projectType",projects.getProjectTypeCode());
            result = bpmService.auditProcess(actionVO, userVo, organizationVo, processVariables);
            if (result.isSuccess()) {
                //判断是否审批完成
                //****************************************************************对应的业务操作****************************************************************
                //****************************************************************对应的业务操作****************************************************************
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //更新申报书和项目的状态
                    //****************************************************************对应的业务操作****************************************************************
                    //****************************************************************对应的业务操作****************************************************************
                    // 项目状态
                    QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
                    projectsQueryWrapper.eq(Projects.PROJECT_ID, application.getProjectId());
                    Projects updateProject = projectsService.getOne(projectsQueryWrapper);
                    if (Objects.nonNull(updateProject)) {
                        // 项目审批结束，更改项目状态 已申报 --> 待批复
                        updateProject.setProjectStatusCode(ValueEnums.PROJECT_REPLIED.getCode());
                        updateProject.setProjectStatusName(ValueEnums.PROJECT_REPLIED.getValue());
                        projectsService.update(updateProject, projectsQueryWrapper);
                    }
                    // 申报书状态
                    QueryWrapper<Application> applicationQueryWrapper = new QueryWrapper<>();
                    applicationQueryWrapper.eq(Application.APPLICATION_ID, application.getApplicationId());
                    Application updateApplication = applicationService.getOne(applicationQueryWrapper);
                    if (Objects.nonNull(updateApplication)) {
                        // 申报书审批结束， 更改申报书状态 已申报 --> 待评审
                        updateApplication.setAppStateCode(ValueEnums.APPLICATION_PENDING_REVIEW.getCode());
                        updateApplication.setAppStateName(ValueEnums.APPLICATION_PENDING_REVIEW.getValue());
                        applicationService.update(updateApplication, applicationQueryWrapper);
                    }
                }
            }
        }
        return result;
    }

    @ApiOperation(value = "1-1-4驳回流程", notes = "1-1-4驳回流程")
    @PostMapping("/rejectProcess")
    public Result rejectProcess(@Valid @RequestBody TaskNodeActionVO actionVO) {
        supplyTaskNodeActionVO(actionVO);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        Result result = new Result();
        //查询单据，获取申报书
        ApprovalVouchers approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        if(VoucherTypeEnums.PROJECT_APPLY_PROCESS.getCode().equals(approvalVoucher.getVoucherType())){//合同审批流程
            actionVO.setTaskKey("start");//设置驳回的节点，驳回到start节点
            Application application = applicationService.getById(approvalVoucher.getBusinessId());
            UserVo acceptVo = new UserVo();
            acceptVo.setId("");
            acceptVo.setName("");
            result = bpmService.rejectProcess(actionVO,userVo,acceptVo);
            if(result.isSuccess()){
                //更新合同状态为退回
                //****************************************************************对应的业务操作****************************************************************
                //****************************************************************对应的业务操作****************************************************************
                applicationService.updateById(application);
                //更新单据的状态
                /*approvalVoucher.setVoucherStatus(VoucherStatusEnums.BACK.getCode());
                approvalVoucherService.updateById(approvalVoucher);*/

            }
        }
        return result;
    }

    @ApiOperation(value = "1-1-5撤回到发起人", notes = "1-1-5撤回到发起人")
    @PostMapping("/rollBackProcess")
    public Result rollBackProcess(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        supplyTaskNodeActionVO(actionVO);
        ApprovalVouchers approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        Result result = new Result();
        if(VoucherTypeEnums.PROJECT_APPLY_PROCESS.getCode().equals(approvalVoucher.getVoucherType())){//医院服务能力建设申报流程
            result =  bpmService.rollBackProcess(actionVO,userVo) ;
            if(result.isSuccess()){
                Application application = applicationService.getById(approvalVoucher.getBusinessId());
                //更新合同状态为撤回
                //****************************************************************对应的业务操作****************************************************************
                //****************************************************************对应的业务操作****************************************************************
                applicationService.updateById(application);

                //更新单据的状态 合同撤回也当做是审批中的状态
               /* approvalVoucher.setVoucherStatus(VoucherStatusEnums.ROLLBACK.getCode());
                approvalVoucherService.updateById(approvalVoucher);*/
            }

        }
        return result;
    }

    @ApiOperation(value = "1-1-6 取消流程", notes = "1-1-6 取消流程")
    @PostMapping("/cancel")
    public Result cancel(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        supplyTaskNodeActionVO(actionVO);
        Result result = new Result();
        ApprovalVouchers approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        if(VoucherTypeEnums.PROJECT_APPLY_PROCESS.getCode().equals(approvalVoucher.getVoucherType())){//医院服务能力建设申报流程
            result = bpmService.cancel(actionVO,userVo) ;
            if(result.isSuccess()){
                //更新合同状态为取消
                Application application = applicationService.getById(approvalVoucher.getBusinessId());
                //****************************************************************对应的业务操作****************************************************************
                //****************************************************************对应的业务操作****************************************************************
                applicationService.updateById(application);
                //更新单据的状态
                /*approvalVoucher.setVoucherStatus(VoucherStatusEnums.STOP.getCode());
                approvalVoucherService.updateById(approvalVoucher);*/
            }
        }
        return result;

    }
    @ApiOperation(value = "1-1-7 查询待办/已办 详情", notes = "1-1-7 查询待办/已办 详情")
    @PostMapping("/detail")
    public Result detail(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        supplyTaskNodeActionVO(actionVO);
        ApprovalVouchers approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        if(VoucherTypeEnums.PROJECT_APPLY_PROCESS.getCode().equals(approvalVoucher.getVoucherType())){//项目申报流程
            //查询项目和申报书的信息返回给前端 做审批页面的展示功能
            DeclarationsVO declarationsVO = appDepartmentalBudgetService.getDepartmentBudget(approvalVoucher.getBusinessId());
            //获取审批节点
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setObjectId(approvalVoucher.getId()+"");
            Result<List<BpmProcessTaskVo>>  resultBpmProcessTaskList = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
            if(resultBpmProcessTaskList.isSuccess() &&  null != resultBpmProcessTaskList.getData() && resultBpmProcessTaskList.getData().size() > 0 ){
                declarationsVO.setBpmProcessTaskVoList(resultBpmProcessTaskList.getData());
            }
            return new Result<DeclarationsVO>().success(declarationsVO);
        }
        return Result.fail(ProjectErrorType.DATA_IS_NULL);
    }

    @ApiOperation(value = "1-2-1 我的申请", notes = "1-2-1 我的申请")
    @PostMapping("/myApply")
    public Result myApply(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm) {
        UserVo userVo = commonService.getCurrentUser();
        Result<GdcPage<BpmProcessTaskVo>> result = bpmService.myApply(processTaskQueryForm,userVo);
        if(result.isSuccess()){
            List<BpmProcessTaskVo> bpmProcessTaskVoList = result.getData().getContent();
            List<ProcessTaskApprovalVo> processTaskApprovalVoList = new ArrayList<>();
            for(BpmProcessTaskVo bpmProcessTaskVo : bpmProcessTaskVoList ){
                processTaskQueryForm.setObjectId(bpmProcessTaskVo.getObjectId());
                Result<GdcPage<BpmProcessTaskVo>> backLogTaskVoGage = bpmService.backLog(processTaskQueryForm,null) ;

                if(null != backLogTaskVoGage &&  null != backLogTaskVoGage.getData() && null != backLogTaskVoGage.getData().getContent() && backLogTaskVoGage.getData().getContent().size() > 0){
                    ProcessTaskApprovalVo processTaskApprovalVo = new ProcessTaskApprovalVo();
                    processTaskApprovalVo.setId(backLogTaskVoGage.getData().getContent().get(0).getId());
                    processTaskApprovalVo.setApprovalNum(backLogTaskVoGage.getData().getContent().get(0).getApproverAcount());
                    processTaskApprovalVo.setApprovalName(backLogTaskVoGage.getData().getContent().get(0).getApproverName());
                    processTaskApprovalVo.setLastApprovalNum(backLogTaskVoGage.getData().getContent().get(0).getCreateBy());
                    processTaskApprovalVo.setLastApprovalName(backLogTaskVoGage.getData().getContent().get(0).getCreateByName());
                    processTaskApprovalVo.setCreateTime(backLogTaskVoGage.getData().getContent().get(0).getCreateTime());
                    processTaskApprovalVo.setLastApprovalTime(backLogTaskVoGage.getData().getContent().get(0).getUpdateTime());
                    processTaskApprovalVo.setTaskStauts(backLogTaskVoGage.getData().getContent().get(0).getTaskStauts());
                    processTaskApprovalVo.setProcessDefineKey(backLogTaskVoGage.getData().getContent().get(0).getProcessDefineKey());
                    processTaskApprovalVo.setProcessInstanceId(backLogTaskVoGage.getData().getContent().get(0).getProcessInstanceId());
                    processTaskApprovalVo.setTaskKey(backLogTaskVoGage.getData().getContent().get(0).getTaskKey());
                    processTaskApprovalVo.setTaskName(backLogTaskVoGage.getData().getContent().get(0).getTaskName());
                    processTaskApprovalVo.setCurrentTaskKey(backLogTaskVoGage.getData().getContent().get(0).getTaskKey());
                    processTaskApprovalVo.setCurrentTaskName(backLogTaskVoGage.getData().getContent().get(0).getTaskName());
                    processTaskApprovalVo.setSubmitterCode(backLogTaskVoGage.getData().getContent().get(0).getSubmitterCode());
                    processTaskApprovalVo.setSubmitterName(backLogTaskVoGage.getData().getContent().get(0).getSubmitterName());
                    ApprovalVouchers approvalVouchers = approvalVoucherService.getById(backLogTaskVoGage.getData().getContent().get(0).getObjectId());
                    if(null != approvalVouchers ){//判断单据是否为空， 不为空 填充项目名称
                        processTaskApprovalVo.setProjectId(approvalVouchers.getBusinessId());
                        processTaskApprovalVo.setProjectName(approvalVouchers.getBusinessName());
                    }
                    processTaskApprovalVoList.add(processTaskApprovalVo);
                    continue;
                }
                Result<GdcPage<BpmProcessTaskVo>> doneTaskVoGage = bpmService.done(processTaskQueryForm,null) ;
                if(null != doneTaskVoGage &&  null != doneTaskVoGage.getData() && null != doneTaskVoGage.getData().getContent() && doneTaskVoGage.getData().getContent().size() > 0){
                    ProcessTaskApprovalVo processTaskApprovalVo = new ProcessTaskApprovalVo();
                    processTaskApprovalVo.setId(doneTaskVoGage.getData().getContent().get(0).getId());
                    processTaskApprovalVo.setApprovalNum(doneTaskVoGage.getData().getContent().get(0).getApproverAcount());
                    processTaskApprovalVo.setApprovalName(doneTaskVoGage.getData().getContent().get(0).getApproverName());
                    processTaskApprovalVo.setLastApprovalNum(doneTaskVoGage.getData().getContent().get(0).getCreateBy());
                    processTaskApprovalVo.setLastApprovalName(doneTaskVoGage.getData().getContent().get(0).getCreateByName());
                    processTaskApprovalVo.setCreateTime(doneTaskVoGage.getData().getContent().get(0).getCreateTime());
                    processTaskApprovalVo.setLastApprovalTime(doneTaskVoGage.getData().getContent().get(0).getUpdateTime());
                    processTaskApprovalVo.setTaskStauts(doneTaskVoGage.getData().getContent().get(0).getTaskStauts());
                    processTaskApprovalVo.setProcessDefineKey(doneTaskVoGage.getData().getContent().get(0).getProcessDefineKey());
                    processTaskApprovalVo.setProcessInstanceId(doneTaskVoGage.getData().getContent().get(0).getProcessInstanceId());
                    processTaskApprovalVo.setTaskKey(doneTaskVoGage.getData().getContent().get(0).getTaskKey());
                    processTaskApprovalVo.setTaskName(doneTaskVoGage.getData().getContent().get(0).getTaskName());
                    processTaskApprovalVo.setCurrentTaskKey(doneTaskVoGage.getData().getContent().get(0).getTaskKey());
                    processTaskApprovalVo.setCurrentTaskName(doneTaskVoGage.getData().getContent().get(0).getTaskName());

                    processTaskApprovalVo.setSubmitterCode(doneTaskVoGage.getData().getContent().get(0).getSubmitterCode());
                    processTaskApprovalVo.setSubmitterName(doneTaskVoGage.getData().getContent().get(0).getSubmitterName());
                    ApprovalVouchers approvalVouchers = approvalVoucherService.getById(doneTaskVoGage.getData().getContent().get(0).getObjectId());
                    if(null != approvalVouchers ){//判断单据是否为空， 不为空 填充项目名称
                        processTaskApprovalVo.setProjectId(approvalVouchers.getBusinessId());
                        processTaskApprovalVo.setProjectName(approvalVouchers.getBusinessName());
                    }
                    processTaskApprovalVoList.add(processTaskApprovalVo);
                }
            }
            GdcPage<ProcessTaskApprovalVo> page = new GdcPage(processTaskApprovalVoList,result.getData().getTotal(),result.getData().getPageNo(),result.getData().getPageSize());
            return new Result< GdcPage<ProcessTaskApprovalVo>>().sucess(page);
        }
        return  result;
    }

    @ApiOperation(value = "1-3-1 我的审批", notes = "1-3-1 我的审批")
    @PostMapping("/myApproval")
    public Result myApproval(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm) {
        UserVo userVo = commonService.getCurrentUser();
        Result<GdcPage<BpmProcessTaskVo>>  result = bpmService.myApproval(processTaskQueryForm,userVo);
        if(result.isSuccess()){
            List<BpmProcessTaskVo> bpmProcessTaskVoList = result.getData().getContent();
            List<ProcessTaskApprovalVo> processTaskApprovalVoList = new ArrayList<>();
            for(BpmProcessTaskVo bpmProcessTaskVo : bpmProcessTaskVoList ){
                processTaskQueryForm.setObjectId(bpmProcessTaskVo.getObjectId());
                Result<GdcPage<BpmProcessTaskVo>> backLogTaskVoGage = bpmService.backLog(processTaskQueryForm,userVo) ;
                if(null != backLogTaskVoGage &&  null != backLogTaskVoGage.getData() && null != backLogTaskVoGage.getData().getContent() && backLogTaskVoGage.getData().getContent().size() > 0){
                    ProcessTaskApprovalVo processTaskApprovalVo = new ProcessTaskApprovalVo();
                    processTaskApprovalVo.setId(backLogTaskVoGage.getData().getContent().get(0).getId());
                    processTaskApprovalVo.setApprovalNum(backLogTaskVoGage.getData().getContent().get(0).getApproverAcount());
                    processTaskApprovalVo.setApprovalName(backLogTaskVoGage.getData().getContent().get(0).getApproverName());
                    processTaskApprovalVo.setLastApprovalNum(backLogTaskVoGage.getData().getContent().get(0).getCreateBy());
                    processTaskApprovalVo.setLastApprovalName(backLogTaskVoGage.getData().getContent().get(0).getCreateByName());
                    processTaskApprovalVo.setCreateTime(backLogTaskVoGage.getData().getContent().get(0).getCreateTime());
                    processTaskApprovalVo.setLastApprovalTime(backLogTaskVoGage.getData().getContent().get(0).getUpdateTime());
                    processTaskApprovalVo.setTaskStauts(backLogTaskVoGage.getData().getContent().get(0).getTaskStauts());
                    processTaskApprovalVo.setProcessDefineKey(backLogTaskVoGage.getData().getContent().get(0).getProcessDefineKey());
                    processTaskApprovalVo.setProcessInstanceId(backLogTaskVoGage.getData().getContent().get(0).getProcessInstanceId());
                    processTaskApprovalVo.setTaskKey(backLogTaskVoGage.getData().getContent().get(0).getTaskKey());
                    processTaskApprovalVo.setTaskName(backLogTaskVoGage.getData().getContent().get(0).getTaskName());
                    processTaskApprovalVo.setCurrentTaskKey(backLogTaskVoGage.getData().getContent().get(0).getTaskKey());
                    processTaskApprovalVo.setCurrentTaskName(backLogTaskVoGage.getData().getContent().get(0).getTaskName());

                    processTaskApprovalVo.setSubmitterCode(backLogTaskVoGage.getData().getContent().get(0).getSubmitterCode());
                    processTaskApprovalVo.setSubmitterName(backLogTaskVoGage.getData().getContent().get(0).getSubmitterName());
                    ApprovalVouchers approvalVouchers = approvalVoucherService.getById(backLogTaskVoGage.getData().getContent().get(0).getObjectId());
                    if(null != approvalVouchers ){//判断单据是否为空， 不为空 填充项目名称
                        processTaskApprovalVo.setProjectId(approvalVouchers.getBusinessId());
                        processTaskApprovalVo.setProjectName(approvalVouchers.getBusinessName());
                    }
                    processTaskApprovalVoList.add(processTaskApprovalVo);
                    continue;
                }
                Result<GdcPage<BpmProcessTaskVo>> doneTaskVoGage = bpmService.done(processTaskQueryForm,userVo) ;
                if(null != doneTaskVoGage &&  null != doneTaskVoGage.getData() && null != doneTaskVoGage.getData().getContent() && doneTaskVoGage.getData().getContent().size() > 0){
                    ProcessTaskApprovalVo processTaskApprovalVo = new ProcessTaskApprovalVo();
                    processTaskApprovalVo.setId(doneTaskVoGage.getData().getContent().get(0).getId());
                    processTaskApprovalVo.setApprovalNum(doneTaskVoGage.getData().getContent().get(0).getApproverAcount());
                    processTaskApprovalVo.setApprovalName(doneTaskVoGage.getData().getContent().get(0).getApproverName());
                    processTaskApprovalVo.setLastApprovalNum(doneTaskVoGage.getData().getContent().get(0).getCreateBy());
                    processTaskApprovalVo.setLastApprovalName(doneTaskVoGage.getData().getContent().get(0).getCreateByName());
                    processTaskApprovalVo.setCreateTime(doneTaskVoGage.getData().getContent().get(0).getCreateTime());
                    processTaskApprovalVo.setLastApprovalTime(doneTaskVoGage.getData().getContent().get(0).getUpdateTime());
                    processTaskApprovalVo.setTaskStauts(doneTaskVoGage.getData().getContent().get(0).getTaskStauts());
                    processTaskApprovalVo.setProcessDefineKey(doneTaskVoGage.getData().getContent().get(0).getProcessDefineKey());
                    processTaskApprovalVo.setProcessInstanceId(doneTaskVoGage.getData().getContent().get(0).getProcessInstanceId());
                    processTaskApprovalVo.setTaskKey(doneTaskVoGage.getData().getContent().get(0).getTaskKey());
                    processTaskApprovalVo.setTaskName(doneTaskVoGage.getData().getContent().get(0).getTaskName());
                    processTaskApprovalVo.setCurrentTaskKey(doneTaskVoGage.getData().getContent().get(0).getTaskKey());
                    processTaskApprovalVo.setCurrentTaskName(doneTaskVoGage.getData().getContent().get(0).getTaskName());
                    processTaskApprovalVo.setSubmitterCode(doneTaskVoGage.getData().getContent().get(0).getSubmitterCode());
                    processTaskApprovalVo.setSubmitterName(doneTaskVoGage.getData().getContent().get(0).getSubmitterName());
                    ApprovalVouchers approvalVouchers = approvalVoucherService.getById(doneTaskVoGage.getData().getContent().get(0).getObjectId());
                    if(null != approvalVouchers ){//判断单据是否为空， 不为空 填充项目名称
                        processTaskApprovalVo.setProjectId(approvalVouchers.getBusinessId());
                        processTaskApprovalVo.setProjectName(approvalVouchers.getBusinessName());
                    }
                    processTaskApprovalVoList.add(processTaskApprovalVo);
                }
            }
            GdcPage<ProcessTaskApprovalVo> page = new GdcPage(processTaskApprovalVoList,result.getData().getTotal(),result.getData().getPageNo(),result.getData().getPageSize());
            return new Result< GdcPage<ProcessTaskApprovalVo>>().sucess(page);
        }
        return result;
    }

    public  void supplyTaskNodeActionVO(TaskNodeActionVO actionVO){
        BpmProcessTaskVo processTask = bpmProcessTaskFeignService.get(Long.parseLong(actionVO.getId())).getData();
        actionVO.setId(processTask.getId());
        actionVO.setObjectId(processTask.getObjectId());
        actionVO.setTaskId(processTask.getTaskId());
        actionVO.setOpinion(processTask.getOpinion());
        actionVO.setProcessDefineKey(processTask.getProcessDefineKey());
        actionVO.setProcessInstanceId(processTask.getProcessInstanceId());
        actionVO.setTaskKey(processTask.getTaskKey());
        actionVO.setSubmitterOrg(processTask.getSubmitterOrg());
    }

    @ApiOperation(value = "1-1-6待阅列表", notes = "1-1-6待阅列表")
    @PostMapping("/noRead")
    public Result<GdcPage<SendProcessTaskVo>> noRead() {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        SendProcessTaskQueryForm sendProcessTaskQueryForm = new SendProcessTaskQueryForm();
        sendProcessTaskQueryForm.setApproverAcount(userVo.getId());
        sendProcessTaskQueryForm.setTaskStauts("待阅");
        return carbonCopyService.search(sendProcessTaskQueryForm);
    }

    @ApiOperation(value = "1-1-7已阅列表", notes = "1-1-7已阅列表")
    @PostMapping("/hasRead")
    public Result<GdcPage<SendProcessTaskVo>> hasRead() {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        SendProcessTaskQueryForm sendProcessTaskQueryForm = new SendProcessTaskQueryForm();
        sendProcessTaskQueryForm.setApproverAcount(userVo.getId());
        sendProcessTaskQueryForm.setTaskStauts("已阅");
        return carbonCopyService.search(sendProcessTaskQueryForm);
    }

    @ApiOperation(value = "1-1-8发送一条待阅", notes = "1-1-8发送一条待阅")
    @PostMapping("/beNoRead")
    public Result beNoRead(@Valid @RequestBody SendProcessTaskForm sendProcessTaskForm) {
        Result<UserVo>  result = userFeignService.get(Long.parseLong(sendProcessTaskForm.getApproverAcount()));
        if(result.isSuccess()){
            //给某个id 发送一条待阅
            UserVo userVo = result.getData();
            Result result1 = commonService.sendProcessTask(userVo,"1130778117188239362",VoucherTypeEnums.PROJECT_TEST);
            return result1;
        }
        return result;
    }
    @ApiOperation(value = "1-1-10待阅 已阅的详情", notes = "1-1-10待阅 已阅的详情")
        @PostMapping("/detailRead")
        public Result detailRead(@Valid @RequestBody com.deloitte.platform.api.contract.vo.TaskNodeActionVO actionVO) {
            UserVo userVo = commonService.getCurrentUser();
            OrganizationVo organizationVo =commonService.getOrganization();
            supplySendProcessTaskNodeActionVO(actionVO);
            ApprovalVouchers approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
            if(VoucherTypeEnums.PROJECT_TEST.getCode().equals(approvalVoucher.getVoucherType())) {
                //根据  approvalVoucher.getBusinessId() 获取对应的实体信息vo,然后返回
                //***************************************************************查询实体***************************************************************
                String applicationId = actionVO.getId();

                return new Result().sucess(null);
            }
            return Result.fail(false);
    }
    public  void supplySendProcessTaskNodeActionVO(com.deloitte.platform.api.contract.vo.TaskNodeActionVO actionVO){
        SendProcessTaskVo sendProcessTaskVo = sendProcessTaskFeignService.get(Long.parseLong(actionVO.getId())).getData();
        actionVO.setId(sendProcessTaskVo.getId());
        actionVO.setObjectId(sendProcessTaskVo.getObjectId());
        actionVO.setTaskId(sendProcessTaskVo.getTaskId());
        actionVO.setOpinion(sendProcessTaskVo.getOpinion());
        actionVO.setProcessDefineKey(sendProcessTaskVo.getProcessDefineKey());
        actionVO.setProcessInstanceId(sendProcessTaskVo.getProcessInstanceId());
        actionVO.setTaskKey(sendProcessTaskVo.getTaskKey());
        actionVO.setSubmitterOrg(sendProcessTaskVo.getSubmitterOrg());
    }

}

package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.vo.ApprovalOpinionVo;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.TaskNodeActionVO;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.common.enums.VoucherStatusEnums;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.entity.ApprovalOpinion;
import com.deloitte.services.contract.entity.ApprovalVoucher;
import com.deloitte.services.contract.entity.BasicInfo;
import com.deloitte.services.contract.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   BPM流程发起
 * @Modified :
 */
@Api(tags = "BPM流程发起接口")
@Slf4j
@RestController
@RequestMapping("/bpm")
public class BPMController {

    @Autowired
    public IBPMService bpmService;

    @Autowired
    public IBasicInfoService basicInfoService;

    @Autowired
    private IApprovalVoucherService approvalVoucherService;

    @Autowired
    private IApprovalOpinionService approvalOpinionService;
    @Autowired
    private ICommonService commonService;

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;


    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/startAuditProcess")
    public Result startAuditProcess(@Valid @RequestBody BasicInfoVo basicInfoVo) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setId(null);
        basicInfo.setContractSerialNumber("20190328011111111");
        basicInfo.setOperatorCode("2");
        basicInfo.setOperator("冯昊");
        basicInfo.setContractName("合同审批");
        basicInfoService.save(basicInfo);
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(basicInfo.getId(), VoucherTypeEnums.CONTRACT_BOOK);
        Map<String, String> processVariables = new HashMap<>();
        Result result = bpmService.startAuditProcess(voucher, userVo, organizationVo, basicInfoVo.getProcessDefineKey(), processVariables);
        if (result.isSuccess()) {
            //更新合同状态
            BasicInfo contract = basicInfoService.getById(basicInfo.getId());
            contract.setStatue("EXA2000");
            basicInfoService.updateById(contract);
        }
        return result;
    }

    @ApiOperation(value = "审核流程", notes = "审核流程")
    @PostMapping("/auditApprove9")
    public Result auditApprove9(@Valid @RequestBody TaskNodeActionVO actionVO) {
        supplyTaskNodeActionVO(actionVO);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();

        Map<String, String> processVariables = new HashMap<>();
        Result result = bpmService.auditProcess(actionVO, userVo, organizationVo, processVariables);
        if (result.isSuccess()) {
            //查询单据，获取合同id
            QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<ApprovalVoucher>();
            queryWrapper.eq(ApprovalVoucher.ID, actionVO.getObjectId());
            ApprovalVoucher approvalVoucher = approvalVoucherService.getOne(queryWrapper);
            //保存审批意见
            List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
            if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                    approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                    approvalOpinionVo.setProcessTaskId(actionVO.getId() + "");
                    ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                    approvalOpinionService.save(opinion);
                }
            }
            if (null != result.getData() && "审批结束".equals(result.getData())) {
                //更新合同状态
                BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                contract.setStatue("EXA1000");
                basicInfoService.updateById(contract);

                //更新单据状态
                approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                approvalVoucherService.updateById(approvalVoucher);
            }
        }

        return result;
    }

    @ApiOperation(value = "驳回流程", notes = "驳回流程")
    @PostMapping("/rejectProcess")
    public Result rejectProcess(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();

        Result result = bpmService.rejectProcess(actionVO, userVo, userVo);
        if (result.isSuccess()) {
            //查询单据，获取合同id
            QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<ApprovalVoucher>();
            queryWrapper.eq(ApprovalVoucher.ID, actionVO.getObjectId());
            ApprovalVoucher approvalVoucher = approvalVoucherService.getOne(queryWrapper);
            //保存审批意见
            List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
            if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                    approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                    approvalOpinionVo.setProcessTaskId(actionVO.getId() + "");
                    ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                    approvalOpinionService.save(opinion);
                }
            }
            //更新单据的状态
            approvalVoucher.setVoucherStatus(VoucherStatusEnums.BACK.getCode());
            approvalVoucherService.updateById(approvalVoucher);
        }
        return result;
    }

    @ApiOperation(value = "查询待办", notes = "查询待办")
    @PostMapping("/searchBackLog")
    public Result searchBackLog(@Valid @RequestBody BaseQueryForm pageForm) {
        UserVo userVo = commonService.getCurrentUser();
        return bpmService.searchBackLog(pageForm, userVo);
    }

    @ApiOperation(value = "查询已办", notes = "查询已办")
    @PostMapping("/searchDone")
    public Result searchDone(@Valid @RequestBody BaseQueryForm pageForm) {
        UserVo userVo = commonService.getCurrentUser();
        return bpmService.searchDone(pageForm, userVo);
    }

    @ApiOperation(value = "流程转办", notes = "流程转办")
    @PostMapping("/turnToProcess")
    public Result turnToProcess(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        return bpmService.turnToProcess(actionVO, userVo, organizationVo, actionVO.getProcessDefineKey());
    }

    @ApiOperation(value = "发起流程1-合同定稿", notes = "发起流程1-合同定稿")
    @PostMapping("/autoStartAuditProcess1")
    public Result autoStartAuditProcess1(@Valid @RequestBody BasicInfoVo basicInfoVo) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(basicInfoVo.getId()), VoucherTypeEnums.CONTRACT_SURE);
        Result result = bpmService.autoStartAuditProcess(voucher, userVo, organizationVo, userVo, basicInfoVo.getProcessDefineKey(), processVariables);
        return result;
    }

    @ApiOperation(value = "发起流程2-合同签署", notes = "发起流程2-合同签署")
    @PostMapping("/autoStartAuditProcess2")
    public Result autoStartAuditProcess2(@Valid @RequestBody BasicInfoVo basicInfoVo) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        processVariables.put("way", basicInfoVo.getWay());
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(basicInfoVo.getId()), VoucherTypeEnums.CONTRACT_SIGN_BOOK);
        Result result = bpmService.autoStartAuditProcess(voucher, userVo, organizationVo, userVo, basicInfoVo.getProcessDefineKey(), processVariables);
        return result;
    }

    @ApiOperation(value = "发起流程3-合同履行", notes = "发起流程3-合同履行")
    @PostMapping("/autoStartAuditProcess3")
    public Result autoStartAuditProcess3(@Valid @RequestBody BasicInfoVo basicInfoVo) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(basicInfoVo.getId()), VoucherTypeEnums.CONTRACT_EXECUTE);
        Result result = bpmService.autoStartAuditProcess(voucher, userVo, organizationVo, userVo, basicInfoVo.getProcessDefineKey(), processVariables);
        return result;
    }

    @ApiOperation(value = "发起流程4-合同办结", notes = "发起流程4-合同办结")
    @PostMapping("/autoStartAuditProcess4")
    public Result autoStartAuditProcess4(@Valid @RequestBody BasicInfoVo basicInfoVo) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(basicInfoVo.getId()), VoucherTypeEnums.CONTRACT_FINISH);
        Result result = bpmService.autoStartAuditProcess(voucher, userVo, organizationVo, userVo, basicInfoVo.getProcessDefineKey(), processVariables);
        return result;
    }

    @ApiOperation(value = "发起流程5-合同打印", notes = "发起流程5-合同打印")
    @PostMapping("/autoStartAuditProcess5")
    public Result autoStartAuditProcess5(@Valid @RequestBody BasicInfoVo basicInfoVo) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(basicInfoVo.getId()), VoucherTypeEnums.CONTRACT_PRINT);
        Result result = bpmService.autoStartAuditProcess(voucher, userVo, organizationVo, userVo, basicInfoVo.getProcessDefineKey(), processVariables);
        return result;
    }

    @ApiOperation(value = "发起流程6-合同审批最新流程", notes = "发起流程6-合同审批最新流程")
    @PostMapping("/autoStartAuditProcess6")
    public Result autoStartAuditProcess6(@Valid @RequestBody BasicInfoVo basicInfoVo) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(basicInfoVo.getId()), VoucherTypeEnums.CONTRACT_BOOK);
        Result result = bpmService.startAuditProcess(voucher, userVo, organizationVo, basicInfoVo.getProcessDefineKey(), processVariables);
        return result;
    }

    @ApiOperation(value = "发起会签流程测试", notes = "发起会签流程测试")
    @PostMapping("/startSignProcess")
    public Result startSignProcess(@Valid @RequestBody BasicInfoVo basicInfoVo) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(basicInfoVo.getId()), VoucherTypeEnums.TEST);
        Result result = bpmService.startAuditProcess(voucher, userVo, organizationVo, "C1000001000100", processVariables);
        return result;
    }

    @ApiOperation(value = "1-1-3审核流程", notes = "1-1-3审核流程")
    @PostMapping("/auditApprove")
    public Result auditApprove(@Valid @RequestBody TaskNodeActionVO actionVO) {
        supplyTaskNodeActionVO(actionVO);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        Result result = new Result();

        //查询单据，获取合同id
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        if (VoucherTypeEnums.TEST.getCode().equals(approvalVoucher.getVoucherType())) {//合同审批流程的审批结束的业务代码
            //审批路由参数
            String conditionText = actionVO.getNextNodeVO().getConditionText();
            if(!"".equals(conditionText) && conditionText.contains("==")){
                String key = conditionText.replaceAll("\\{","").replaceAll("\\}","").split("==")[0].trim();
                String value = conditionText.replaceAll("\\{","").replaceAll("\\}","").split("==")[1].trim();
                processVariables.put(key,value);
            }
            //用户选择的下一个节点处理人
            UserVo accepVo = new UserVo();
            List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList = actionVO.getNextNodeVO().getBpmApprovalMatrixVoList();
            ArrayList<NextNodeParamVo> nextNodeParamVoList = new ArrayList<>();
            if(null != bpmApprovalMatrixVoList && bpmApprovalMatrixVoList.size() > 0){
                for(BpmApprovalMatrixVo bpmApprovalMatrixVo :bpmApprovalMatrixVoList){
                    NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
                    nextNodeParamVo.setAcountId(bpmApprovalMatrixVo.getAccountNum());
                    nextNodeParamVo.setAcountName(bpmApprovalMatrixVo.getAccountName());
                    nextNodeParamVoList.add(nextNodeParamVo);
                }
            }
            result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,nextNodeParamVoList, processVariables);
            if (result.isSuccess()) {

            }
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

}

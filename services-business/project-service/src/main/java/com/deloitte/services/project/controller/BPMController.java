package com.deloitte.services.project.controller;

import com.deloitte.platform.api.contract.vo.ApprovalOpinionVo;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.vo.TaskNodeActionVO;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.entity.ApprovalVouchers;
import com.deloitte.services.project.service.IApprovalVouchersService;
import com.deloitte.services.project.service.IBPMService;
import com.deloitte.services.project.service.ICommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    private ICommonService commonService;
    @Autowired
    private IApprovalVouchersService approvalVouchersService;

    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_APPLY_PROCESS")
    public Result PROJECT_APPLY_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        Result result   = commonService.startAuditProcess("1138257208208531457",processVariables, VoucherTypeEnums.PROJECT_APPLY_PROCESS);
        return result;
    }


    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_CANCLE_PROCESS")
    public Result PROJECT_CANCLE_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        Result result   = commonService.startAuditProcess("1138257208208531457",processVariables, VoucherTypeEnums.PROJECT_CANCLE_PROCESS);
        return result;
    }

    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_CHANGE_PROCESS")
    public Result PROJECT_CHANGE_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        Result result   = commonService.startAuditProcess("1138257208208531457",processVariables, VoucherTypeEnums.PROJECT_CHANGE_PROCESS);
        return result;
    }

    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_OPERATE_PROCESS")
    public Result PROJECT_OPERATE_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        Result result   = commonService.startAuditProcess("1138257208208531457",processVariables, VoucherTypeEnums.PROJECT_OPERATE_PROCESS);
        return result;
    }

    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_ADJUST_PROCESS")
    public Result PROJECT_ADJUST_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        UserVo userVo = commonService.getCurrentUser();
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        Result result   = commonService.startAuditProcessByAccept("1138257208208531457",processVariables, VoucherTypeEnums.PROJECT_ADJUST_PROCESS,userVo);
        return result;
    }
/*

    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_APPLY_PROCESS")
    public Result PROJECT_APPLY_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        //新建一个单据
        ApprovalVouchers voucher = approvalVouchersService.generateNewVoucher("1138257208208531457", VoucherTypeEnums.PROJECT_APPLY_PROCESS);
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        Result result = bpmService.startAuditProcess(voucher,userVo,organizationVo,"PROJECT_APPLY_PROCESS",processVariables );
        if(result.isSuccess()){
            System.out.println("合同提交成功!");
        }
        return result;
    }


    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_CANCLE_PROCESS")
    public Result PROJECT_CANCLE_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        //新建一个单据
        ApprovalVouchers voucher = approvalVouchersService.generateNewVoucher("1138257208208531457", VoucherTypeEnums.PROJECT_CANCLE_PROCESS);
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        Result result = bpmService.startAuditProcess(voucher,userVo,organizationVo,"PROJECT_CANCLE_PROCESS",processVariables );
        if(result.isSuccess()){
            System.out.println("合同提交成功!");
        }
        return result;
    }

    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_CHANGE_PROCESS")
    public Result PROJECT_CHANGE_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        //新建一个单据
        ApprovalVouchers voucher = approvalVouchersService.generateNewVoucher("1138257208208531457", VoucherTypeEnums.PROJECT_CHANGE_PROCESS);
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        Result result = bpmService.startAuditProcess(voucher,userVo,organizationVo,"PROJECT_CHANGE_PROCESS",processVariables );
        if(result.isSuccess()){
            System.out.println("合同提交成功!");
        }
        return result;
    }

    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_OPERATE_PROCESS")
    public Result PROJECT_OPERATE_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        //新建一个单据
        ApprovalVouchers voucher = approvalVouchersService.generateNewVoucher("1138257208208531457", VoucherTypeEnums.PROJECT_OPERATE_PROCESS);
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        Result result = bpmService.startAuditProcess(voucher,userVo,organizationVo,"PROJECT_OPERATE_PROCESS",processVariables );
        if(result.isSuccess()){
            System.out.println("合同提交成功!");
        }
        return result;
    }

    @ApiOperation(value = "发起流程", notes = "发起流程")
    @PostMapping("/PROJECT_ADJUST_PROCESS")
    public Result PROJECT_ADJUST_PROCESS(@Valid @RequestBody TaskNodeActionVO taskNodeActionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        //新建一个单据
        ApprovalVouchers voucher = approvalVouchersService.generateNewVoucher("1138257208208531457", VoucherTypeEnums.PROJECT_ADJUST_PROCESS);
        Map<String,String> processVariables = new HashMap<>();
        processVariables.put("projectType",taskNodeActionVO.getTaskKey());
        UserVo accptVo = new UserVo();
        accptVo.setId("");
        accptVo.setName("");
        Result result = bpmService.autoStartAuditProcess(voucher,userVo,organizationVo,accptVo,"PROJECT_ADJUST_PROCESS",processVariables );
        if(result.isSuccess()){
            System.out.println("合同提交成功!");
        }
        return result;
    }
    */
}

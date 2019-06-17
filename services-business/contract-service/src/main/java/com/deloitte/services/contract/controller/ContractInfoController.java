package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.param.SysSignSubjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.BasicProjectMapVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherStatusEnums;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.*;
import com.deloitte.services.contract.entity.Process;
import com.deloitte.services.contract.mapper.SysSignSubjectInfoMapper;
import com.deloitte.services.contract.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "2-合同基本信息操作接口")
@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractInfoController {

    @Autowired
    private IBasicInfoService iBasicInfoService;
    @Autowired
    private ISysSignSubjectInfoService iSysSignSubjectInfoService;
    @Autowired
    private IApprovalVoucherService approvalVoucherService;
    @Autowired
    public IBPMService bpmService;
    @Autowired
    public IStandardTemplateService  standardTemplateService;
    @Autowired
    public ICommonService  commonService;
    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;
    @Autowired
    private IProcessService iProcessService;
    /**
     * yangyuanqing
     * @param basicInfoForm
     * @return
     */
    @ApiOperation(value = "2-2-2保存合同信息", notes = "2-2-2保存合同信息")
    @PostMapping("/save")
    public Result<BasicInfoVo> insertOrUpdateContract(@Valid @RequestBody BasicInfoForm basicInfoForm){
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        BasicInfo result = iBasicInfoService.addContractInfo(basicInfoForm, userVo, organizationVo);
        BasicInfoVo basicInfoVo =new BeanUtils<BasicInfoVo>().copyObj(result, BasicInfoVo.class);
        return new Result<BasicInfoVo>().sucess(basicInfoVo);
    }

    @ApiOperation(value = "2-2-1合同基本信息查询", notes = "2-2-1合同基本信息查询")
    @PostMapping("/getContractById")
    public Result<BasicInfoVo> getContractById(@Valid @RequestBody BasicInfoForm basicInfoForm){
        return commonService.getContractByContractId(basicInfoForm.getId()+"");
    }

    @ApiOperation(value = "2-2-3合同签约主体信息查询", notes = "2-2-3合同签约主体信息查询")
    @PostMapping("/getSignSubject")
    public Result<IPage<SysSignSubjectInfoVo>> getSignSubject(@Valid @RequestBody SysSignSubjectInfoQueryForm sysSignSubjectInfoQueryForm){
//        IPage<SysSignSubjectInfo> sysSignSubjectInfoPage = iSysSignSubjectInfoService.selectPage(sysSignSubjectInfoQueryForm);
//        IPage<SysSignSubjectInfoVo> sysSignSubjectInfoVoPage=new BeanUtils<SysSignSubjectInfoVo>().copyPageObjs(sysSignSubjectInfoPage,SysSignSubjectInfoVo.class);
        IPage<SysSignSubjectInfoVo> sysSignSubjectInfoVoPage = iSysSignSubjectInfoService.selectByClient(sysSignSubjectInfoQueryForm);
        return new Result<IPage<SysSignSubjectInfoVo>>().sucess(sysSignSubjectInfoVoPage);
    }

    @ApiOperation(value = "2-1-合同查询", notes = "2-1-合同查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/getContractList")
    public Result<IPage<BasicInfoVo>> getContractList( @Valid @RequestBody BasicInfoQueryForm queryForm) {
        UserVo userVo = commonService.getCurrentUser();
        IPage<BasicInfo> basicInfoMapPage=iBasicInfoService.selectPage(queryForm,userVo);
        IPage<BasicInfoVo> basicInfoVoPage=new BeanUtils<BasicInfoVo>().copyPageObjs(basicInfoMapPage,BasicInfoVo.class);
        return new Result<IPage<BasicInfoVo>>().sucess(basicInfoVoPage);
    }

//    @ApiOperation(value = "2-1-1复制合同页面查询", notes = "2-1-1复制合同页面查询")
//    @PostMapping(value = "/copyContractInfo")
//    @ApiResponses(
//            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
//    )
//    public Result queryCopyInfo(String contractNum) {
//
//        return new Result().sucess(iBasicInfoService.queryCopyInfo());
//    }

    @ApiOperation(value = "2-1-1复制生成新合同", notes = "2-1-1复制生成新合同")
    @PostMapping(value = "/copyContract")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<BasicInfoVo> copyContract(@Valid @RequestBody  BasicInfoVo basicVo) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        BasicInfo basicInfo=iBasicInfoService.InsertCopyInfo(basicVo.getId(),userVo,organizationVo);
        BasicInfoVo basicInfoVo=new BeanUtils<BasicInfoVo>().copyObj(basicInfo,BasicInfoVo.class);
        List<SysSignSubjectInfoForm> subject=iSysSignSubjectInfoService.queryCopyInfo(basicVo.getId());
        basicInfoVo.setSysSignSubjectInfoList(subject);
        return new Result<BasicInfoVo>().sucess(basicInfoVo);
    }

    /*@ApiOperation(value = "2-1-1审批列表页面查询", notes = "2-1-1审批列表页面查询")
    @PostMapping(value = "/selectContract")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<BasicInfoVo> selectContract(BasicInfoForm basicInfoForm) {
        return selectContractById(basicInfoForm);
    }*/

    @ApiOperation(value = "2-1-1草稿箱删除", notes = "2-1-1草稿箱删除")
    @PostMapping(value = "/deleteOutline")
    public Result delete(@Valid @RequestBody BasicInfoVo basicVo ) {
//        iBasicInfoService.removeById(id);
        iBasicInfoService.removeById(basicVo.getId());
//        iBasicInfoService.updateContractStatue(basicVo.getId());
        return Result.success();
    }

    @ApiOperation(value = "2-3-5提交和同信息到BPM {  \"contractId\": \"合同id\",  \"processDefineKey\": \"流程定义key\"}", notes = "2-3-5提交和同信息到BPM{  \"contractId\": \"合同id\",  \"processDefineKey\": \"流程定义key\"}")
    @PostMapping("/submitContract")
    public Result<BasicInfoVo> submitContract(@Valid @RequestBody  BasicInfoVo basicInfoVo){
        AssertUtils.asserts(null == basicInfoVo || null == basicInfoVo.getContractId() || null == basicInfoVo.getProcessDefineKey() ,ContractErrorType.CONTRACT_BOOK_PARAMETER_ERROR);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();

        //流程提交参数
        Map<String,String> processVariables = new HashMap<>();
        if(null != basicInfoVo.getNextNodeVO() && null !=  basicInfoVo.getNextNodeVO().getConditionText()){
            //审批路由参数
            String conditionText =  basicInfoVo.getNextNodeVO().getConditionText();
            if(!"".equals(conditionText)){
                String key = conditionText.replaceAll("\\{","").replaceAll("\\}","").split("==")[0].trim();
                String value = conditionText.replaceAll("\\{","").replaceAll("\\}","").split("==")[1].trim();
                processVariables.put(key,value);
            }
        }
        //用户选择的下一个节点处理人
        UserVo accepVo = new UserVo();
        if(null !=  basicInfoVo.getNextNodeVO() && null !=  basicInfoVo.getNextNodeVO().getBpmApprovalMatrixVoList()){
            List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList =  basicInfoVo.getNextNodeVO().getBpmApprovalMatrixVoList();
            if(null != bpmApprovalMatrixVoList && bpmApprovalMatrixVoList.size() > 0){
                BpmApprovalMatrixVo bpmApprovalMatrixVo = bpmApprovalMatrixVoList.get(0);
                accepVo.setId(bpmApprovalMatrixVo.getAccountNum());
                accepVo.setName(bpmApprovalMatrixVo.getAccountName());
            }
        }
        //判断合同是否已经提交过
        QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,basicInfoVo.getContractId());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,VoucherStatusEnums.AUDITING.getCode());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,VoucherTypeEnums.CONTRACT_BOOK.getCode());
        ApprovalVoucher approvalVoucher = approvalVoucherService.getOne(queryWrapper);
        AssertUtils.asserts(null != approvalVoucher , ContractErrorType.CONTRACT_VOUCHER_IS_ALREADLY_SUBMIT);
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(basicInfoVo.getContractId()), VoucherTypeEnums.CONTRACT_BOOK);
//        processVariables.put("assistant","1");
        AssertUtils.asserts( null == basicInfoVo.getProcessDefineKey() || "".equals(basicInfoVo.getProcessDefineKey()), ContractErrorType.PROCESS_KEY_IS_NULL);
        Result result = bpmService.autoStartAuditProcess(voucher,userVo,organizationVo,accepVo,basicInfoVo.getProcessDefineKey(),processVariables );
        if(result.isSuccess()){//流程发起成功
            //更新合同状态
            BasicInfo contract = iBasicInfoService.getById(basicInfoVo.getContractId());
//            Process process = iProcessService.getEndKeyByStartKey(basicInfoVo.getProcessDefineKey());
//            if (process != null){
//                contract.setCancelProcessKey(process.getProcessDefineKey());
//            }
            contract.setStatue("EXA2000");
            contract.setStatueList("STA8000");
            iBasicInfoService.updateById(contract);
            // 修改变更合同信息状态
            iBasicInfoService.saveBasicRelationStatus(contract.getId().toString(), "EXA9000", "EXA10000", "EXA12000");
            if (!"".equals(contract.getRelationCode()) && !"REL1000".equals(contract.getRelationCode()) && !"REL3000".equals(contract.getRelationCode())){
                iBasicInfoService.selectBasicInfoToFssc(contract.getId());
            }
            //更新单据状态
            voucher.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            approvalVoucherService.updateById(voucher);
        }else{
            approvalVoucherService.removeById(voucher.getId());
        }
        return result;
    }

    @ApiOperation(value = "点击合同名称判断是否需要进入审批页面", notes = "点击合同名称判断是否需要进入审批页面")
    @PostMapping("/getJumpPath")
    public Result<JumpPathVo> getJumpPath(@Valid @RequestBody  JumpPathVo jumpPathVo){
        UserVo userVo = commonService.getCurrentUser();
        //获取对应类型，对应id 是否有正在审批中的单据
        QueryWrapper<ApprovalVoucher> approvalVoucherQueryWrapper = new QueryWrapper<>();
        approvalVoucherQueryWrapper.eq(ApprovalVoucher.BUSINESS_ID,jumpPathVo.getId());
        approvalVoucherQueryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,jumpPathVo.getPath());
        approvalVoucherQueryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,VoucherStatusEnums.AUDITING.getCode());
        ApprovalVoucher approvalVoucher = approvalVoucherService.getOne(approvalVoucherQueryWrapper);
        jumpPathVo.setJumpApproval("0");
        if(null != approvalVoucher){
            //根据单据的id 获取审批的节点信息
            ProcessTaskQueryForm processTaskQueryForm = new ProcessTaskQueryForm();
            processTaskQueryForm.setObjectId(approvalVoucher.getId()+"");
            List<BpmProcessTaskVo> bpmProcessTaskVoList= bpmService.backLog(processTaskQueryForm,userVo).getData().getContent();
            if(null != bpmProcessTaskVoList && bpmProcessTaskVoList.size() > 0 && StringUtils.isNotEmpty(bpmProcessTaskVoList.get(0).getId())){
                jumpPathVo.setJumpApproval("1");
                jumpPathVo.setBpmProcessTaskVo(bpmProcessTaskVoList.get(0));
            }
        }
        return new Result<JumpPathVo>().sucess(jumpPathVo);
    }
}

package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherStatusEnums;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.*;
import com.deloitte.services.contract.entity.Process;
import com.deloitte.services.contract.mapper.BasicInfoMapper;
import com.deloitte.services.contract.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "4-合同履行操作接口")
@Slf4j
@RestController
@RequestMapping("/contractInfoPerfom")
public class ContractPerfomController {
    @Autowired
    private IFinanceInfoService iFinanceInfoService;
    @Autowired
    private IBasicInfoService iBasicInfoService;
    @Autowired
    private ISysProjectInfoService iSysProjectInfoService;
    @Autowired
    private IMonitorInfoService iMonitorInfoService;
    @Autowired
    private ISetupInfoService iSetupInfoService;
    @Autowired
    private ISysSignSubjectInfoService iSysSignSubjectInfoService;
    @Autowired
    private IProcessExecuterTransferService iProcessExecuterTransferService;
    @Autowired
    private IApprovalVoucherService iApprovalVoucherService;
    @Autowired
    private IBPMService ibpmService;
    @Autowired
    private IOrderInfoService iOrderInfoService;
    @Autowired
    private ITicketInfoService iTicketInfoService;
    @Autowired
    public ICommonService  commonService;
    @Autowired
    public IProcessService  processService;

    @ApiOperation(value = "4-3-1合同办结申请单", notes = "3-4-1合同办结申请单")
    @PostMapping("/selectContractSetup")
    public Result<BasicInfoVo> selectContractSetup(@Valid @RequestBody BasicInfoForm basicInfoForm){

        return commonService.getContractByContractId(basicInfoForm.getId().toString());
    }

    @ApiOperation(value = "4-3-1合同办结申请单提交", notes = "3-4-1合同办结申请单提交")
    @PostMapping("/addContractSetup")
    public Result addContractSetup(@Valid @RequestBody SetupInfoForm setupInfoForm ) {
        AssertUtils.asserts(null == setupInfoForm || null == setupInfoForm.getContractId()  ,ContractErrorType.CONTRACT_FINISH_PARAMETER_ERROR);

        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        //判断单据是否提交过审批
        QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,setupInfoForm.getContractId());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,VoucherStatusEnums.AUDITING.getCode());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,VoucherTypeEnums.CONTRACT_FINISH.getCode());
        ApprovalVoucher voucher = iApprovalVoucherService.getOne(queryWrapper);
        AssertUtils.asserts(null != voucher, ContractErrorType.CONTRACT_VOUCHER_FINISH_IS_DOING);

        SetupInfo setupInfo = new BeanUtils<SetupInfo>().copyObj(setupInfoForm, SetupInfo.class);
        setupInfo.setSetupTime(LocalDateTime.now());
        setupInfo.setCreateBy(userVo.getName());
        setupInfo.setUpdateBy(userVo.getName());
        iSetupInfoService.save(setupInfo);
        //新建一个单据
        ApprovalVoucher approvalVoucher = iApprovalVoucherService.generateNewVoucher(setupInfoForm.getContractId(), VoucherTypeEnums.CONTRACT_FINISH);
        Map<String, String> map = new HashMap<>();
        //流程传入参数
        //获取流程信息
        QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.CONTRACT_FINISH.getCode());
        Process process = processService.getOne(processQueryWrapper);
        AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

        Result result = ibpmService.startAuditProcess(approvalVoucher, userVo, organizationVo, process.getProcessDefineKey(), map);
        //流程启动成功
        if (result.isSuccess()) {
            //修改合同状态
            BasicInfo basicInfo = iBasicInfoService.getById(setupInfoForm.getContractId());
            basicInfo.setStatue("EXA7000");
            iBasicInfoService.updateById(basicInfo);
            // 修改变更合同信息状态
            iBasicInfoService.selectBasicInfoToFssc(basicInfo.getId());
        }
        return Result.success(true);
    }

    @ApiOperation(value = "4-1-1履行计划所有信息查询", notes = "4-1-1履行计划所有信息查询")
    @PostMapping("/selectContractFulfill")
    public Result<BasicInfoVo> selectContractFulfill(@Valid @RequestBody BasicInfoForm basicInfoForm){
//        BasicInfoQueryForm basicInfoQueryForm=iBasicInfoService.queryCurrentBasic(basicInfoForm.getId().toString());
        Long contractId = basicInfoForm.getId();
        BasicInfo basicInfo = iBasicInfoService.selectContractInfoById(contractId);
        BasicInfoVo basicInfoVo=new BeanUtils<BasicInfoVo>().copyObj(basicInfo, BasicInfoVo.class);
        //查询合同签约主体信息
        List<BasicSubjectMap> basicSubjectMapList = iBasicInfoService.selectBasicSubjectByContractId(contractId);
        List<BasicSubjectMapVo> basicSubjectMapVoList=new BeanUtils<BasicSubjectMapVo>().copyObjs(basicSubjectMapList,BasicSubjectMapVo.class);
//        for (int i=0; i < basicSubjectMapVoList.size(); i++) {
//            BasicSubjectMapVo basicSubjectMapVo = basicSubjectMapVoList.get(i);
//            SysSignSubjectInfo sysSignSubjectInfo = iSysSignSubjectInfoService.selectObjectById(basicSubjectMapVo.getSubjectId());
//            SysSignSubjectInfoVo sysSignSubjectInfoVo =new BeanUtils<SysSignSubjectInfoVo>().copyObj(sysSignSubjectInfo, SysSignSubjectInfoVo.class);
//            basicSubjectMapVo.setSysSignSubjectInfoVo(sysSignSubjectInfoVo);
//            basicSubjectMapVoList.set(i, basicSubjectMapVo);
//        }
        basicInfoVo.setBasicSubjectList((ArrayList<BasicSubjectMapVo>)basicSubjectMapVoList);
        //旅行人信息
        UserVo userVo = iBasicInfoService.getUserById(basicInfoVo.getExecuterCode());
        //关联合同
        basicInfoVo = iBasicInfoService.queryCorrelationBasic(basicInfoVo);
        //财务信息
        List<FinanceInfoVo> financeInfo = iFinanceInfoService.selectFinanceInfo(contractId.toString());
        //项目信息
        List<SysProjectInfoVo> sysProjectInfoVo = iSysProjectInfoService.querySysProjectInfo(contractId.toString());
        //其他监控计划
        List<MonitorInfoVo> monitorInfoVo = iMonitorInfoService.queryMonitorInfo(contractId.toString());
        //订单信息
        List<OrderInfoVo> orderInfoVo = iOrderInfoService.selectListByContractId(contractId.toString());
        //收/发票信息
        List<TicketInfoVo> ticketInfoVo = iTicketInfoService.selectListByContractId(contractId.toString());
        basicInfoVo.setListFinanceInfoVo(financeInfo);
        basicInfoVo.setListMonitorInfoVo(monitorInfoVo);
        basicInfoVo.setListSysProjectInfoVo(sysProjectInfoVo);
        basicInfoVo.setExecuterUserVo(userVo);
        basicInfoVo.setOrderInfoVo(orderInfoVo);
        basicInfoVo.setTicketInfoVo(ticketInfoVo);
        return new Result<BasicInfoVo>().sucess(basicInfoVo);
    }

    @ApiOperation(value = "4-1-2财务信息保存", notes = "4-1-2财务信息保存")
    @PostMapping("/saveFinanceInfoList")
    public Result<List<FinanceInfoVo>> saveFinanceInfoList(@Valid @RequestBody BasicInfoVo basicInfoVo){
        UserVo userVo = commonService.getCurrentUser();
        List<FinanceInfoVo> financeInfos = iFinanceInfoService.saveFinanceInfoList(basicInfoVo, userVo);
        return new Result<List<FinanceInfoVo>>().sucess(financeInfos);
    }

    @ApiOperation(value = "4-1-3合同与项目关联信息保存", notes = "4-1-3合同与项目关联信息保存")
    @PostMapping("/saveBasicProjectMap")
    public Result saveBasicProjectMap(@Valid @RequestBody BasicProjectMapForm basicProjectMapForm){
        UserVo userVo = commonService.getCurrentUser();
        BasicProjectMap basicProjectMap = new BeanUtils<BasicProjectMap>().copyObj(basicProjectMapForm, BasicProjectMap.class);
        iSysProjectInfoService.saveSysProject(basicProjectMap, userVo);
        return new Result().success();
    }

    @ApiOperation(value = "4-1-4根据合同id和项目id删除合同项目关联信息", notes = "4-1-4根据合同id和项目id删除合同项目关联信息")
    @PostMapping("/deleteBasicProjectMapById")
    public Result deleteBasicProjectMapById(@Valid @RequestBody BasicProjectMap basicProjectMap){
        Long projectId = basicProjectMap.getId();
        iSysProjectInfoService.deleteBasicProjectMap(basicProjectMap);
        return Result.success();
    }

    @ApiOperation(value = "4-1-5其他监控计划信息保存", notes = "4-1-5其他监控计划信息保存")
    @PostMapping("/saveMonitorInfo")
    public Result<MonitorInfo> saveMonitorInfo(@Valid @RequestBody MonitorInfoForm monitorInfoForm){
        UserVo userVo = commonService.getCurrentUser();
        MonitorInfo monitorInfo = new BeanUtils<MonitorInfo>().copyObj(monitorInfoForm, MonitorInfo.class);
        monitorInfo = iMonitorInfoService.saveMonitorInfo(monitorInfo, userVo);
        MonitorInfoVo monitorInfoVo = new BeanUtils<MonitorInfoVo>().copyObj(monitorInfo, MonitorInfoVo.class);
        return new Result<MonitorInfo>().sucess(monitorInfoVo);
    }

    @ApiOperation(value = "4-1-6根据id删除其他监控计划信息", notes = "4-1-6根据id删除其他监控计划信息")
    @PostMapping("/deleteMonitorInfoById")
    public Result deleteMonitorInfoById(@Valid @RequestBody MonitorInfoForm monitorInfoForm){
        iMonitorInfoService.deleteMonitorInfoById(monitorInfoForm.getId());
        return Result.success();
    }

    @ApiOperation(value = "4-1-6.1其他监控计划信息修改", notes = "4-1-6.1其他监控计划信息修改")
    @PostMapping("/updateMonitorInfo")
    public Result<MonitorInfo> updateMonitorInfo(@Valid @RequestBody MonitorInfoForm monitorInfoForm){
        UserVo userVo = commonService.getCurrentUser();
        MonitorInfo monitorInfo = new BeanUtils<MonitorInfo>().copyObj(monitorInfoForm, MonitorInfo.class);
        monitorInfo = iMonitorInfoService.updateMonitorInfo(monitorInfo, userVo);
        MonitorInfoVo monitorInfoVo = new BeanUtils<MonitorInfoVo>().copyObj(monitorInfo, MonitorInfoVo.class);
        return new Result<MonitorInfo>().sucess(monitorInfoVo);
    }

    @ApiOperation(value = "4-1-7根据id修改合同备注保存", notes = "4-1-7根据id修改合同备注保存")
    @PostMapping("/updateBasicInfoRemarkById")
    public Result updateBasicInfoRemarkById(@Valid @RequestBody BasicInfoForm basicInfoForm){
        iBasicInfoService.updateBasicInfoRemarkById(basicInfoForm);
        return Result.success();
    }

    @ApiOperation(value = "4-1-8履行人移交发起流程", notes = "4-1-8履行人移交发起流程")
    @PostMapping("/add")
    public Result add(@Valid @RequestBody ProcessExecuterTransferVo processExecuterTransferVo){
        AssertUtils.asserts(null == processExecuterTransferVo || null == processExecuterTransferVo.getContractId()  ,ContractErrorType.CONTRACT_EXECUTER_TRANSFER_PARAMETER_ERROR);

        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        //判断合同是否已经提交过
        QueryWrapper<ProcessExecuterTransfer> queryExecuter = new QueryWrapper<>();
        queryExecuter.eq(ProcessExecuterTransfer.CONTRACT_ID,processExecuterTransferVo.getContractId());
        ProcessExecuterTransfer executer = iProcessExecuterTransferService.getOne(queryExecuter);
        if(null != executer){
            QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,executer.getId());
            queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,VoucherTypeEnums.CONTRACT_EXECUTER_TRANSFER.getCode());
            queryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,VoucherStatusEnums.AUDITING.getCode());
            ApprovalVoucher voucher = iApprovalVoucherService.getOne(queryWrapper);
            AssertUtils.asserts(null != voucher, ContractErrorType.CONTRACT_VOUCHER_EXECUTE_IS_DOING);

        }
        ProcessExecuterTransfer processExecuterTransfer = new BeanUtils<ProcessExecuterTransfer>().copyObj(processExecuterTransferVo,ProcessExecuterTransfer.class);
        //新建一个单据
        processExecuterTransfer.setStatue("EXA2000");
        processExecuterTransfer.setSendTime(LocalDateTime.now());
        //数据保存到履行人移交关系表
        iProcessExecuterTransferService.save(processExecuterTransfer);
        ApprovalVoucher approvalVoucher=iApprovalVoucherService.generateNewVoucher(processExecuterTransfer.getId(), VoucherTypeEnums.CONTRACT_EXECUTER_TRANSFER);
        //流程提交参数
        Map<String,String> processVariables = new HashMap<>();

        //获取流程信息
        QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.CONTRACT_EXECUTER_TRANSFER.getCode());
        Process process = processService.getOne(processQueryWrapper);
        AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

        Result result=ibpmService.startAuditProcess(approvalVoucher,userVo,organizationVo,process.getProcessDefineKey(),processVariables);
        if(result.isSuccess()){
            processExecuterTransfer.setProcessId(process.getProcessDefineKey());
            processExecuterTransfer.setProcessInstanceId(result.getData().toString());
            //数据保存到履行人移交关系表
            iProcessExecuterTransferService.updateById(processExecuterTransfer);
        }

        return Result.success(true);
    }


    @ApiOperation(value = "4-2-1查询合同履行情况检查", notes = "4-2-1查询合同履行情况检查")
    @PostMapping("/selectContractExecuteCheck")
    public Result<BasicInfoVo> selectContractExecuteCheck(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm){
        UserVo userVo = commonService.getCurrentUser();
        BasicInfoVo list = iBasicInfoService.selectContractExecuteCheck(basicInfoQueryForm, userVo);
        return new Result<BasicInfoVo>().sucess(list);
    }

    @ApiOperation(value = "4-2-2订单信息保存", notes = "4-2-2订单信息保存")
    @PostMapping("/saveOrder")
    public Result<OrderInfoVo> saveOrder(@Valid @RequestBody OrderInfoForm orderInfoForm){
        UserVo userVo = commonService.getCurrentUser();
        OrderInfo orderInfo = iOrderInfoService.saveOrder(orderInfoForm, userVo);
        OrderInfoVo orderInfoVo = new BeanUtils<OrderInfoVo>().copyObj(orderInfo,OrderInfoVo.class);
        return new Result<OrderInfoVo>().sucess(orderInfoVo);
    }

    @ApiOperation(value = "4-2-3订单信息删除", notes = "4-2-3订单信息删除")
    @PostMapping("/deleteOrderById")
    public Result deleteOrderById(@Valid @RequestBody OrderInfoForm orderInfoForm){
        iOrderInfoService.deleteOrderById(orderInfoForm);
        return Result.success();
    }

    @ApiOperation(value = "4-2-4开票信息保存", notes = "4-2-4开票信息保存")
    @PostMapping("/saveTicket")
    public Result<TicketInfoVo> saveTicket(@Valid @RequestBody TicketInfoForm ticketInfoForm){
        UserVo userVo = commonService.getCurrentUser();
        TicketInfo ticketInfo = iTicketInfoService.saveTicket(ticketInfoForm, userVo);
        TicketInfoVo ticketInfoVo = new BeanUtils<TicketInfoVo>().copyObj(ticketInfo,TicketInfoVo.class);
        return new Result<TicketInfoVo>().sucess(ticketInfoVo);
    }

    @ApiOperation(value = "4-2-5开票信息删除", notes = "4-2-5开票信息删除")
    @PostMapping("/deleteTicketById")
    public Result deleteTicketById(@Valid @RequestBody TicketInfoForm ticketInfoForm){
        iTicketInfoService.deleteTicketById(ticketInfoForm);
        return Result.success();
    }

    @ApiOperation(value = "4-1-9查询合同履行计划财务信息推送财务系统", notes = "4-1-9查询合同履行计划财务信息推送财务系统")
    @PostMapping("/selectFinanceToFssc")
    public Result selectFinanceToFssc(@Valid @RequestBody BasicInfoForm basicInfoForm){
        Long contractId = basicInfoForm.getId();
        Result result = iFinanceInfoService.selectFinanceToFssc(contractId.toString());
        return result;
    }

    @ApiOperation(value = "4-4-1生成履行计划预警待阅", notes = "4-4-1生成履行计划预警待阅")
    @PostMapping("/sendExecuteWaring")
    public Result sendExecuteWaring(){
        Result result = iBasicInfoService.sendExecuteWaring();
        return result;
    }

    @ApiOperation(value = "4-4-2生成履行预警待阅", notes = "4-4-2生成履行预警待阅")
    @PostMapping("/sendContractWaring")
    public Result sendContractWaring(){
        Result result = iBasicInfoService.sendContractWaring();
        return result;
    }
}

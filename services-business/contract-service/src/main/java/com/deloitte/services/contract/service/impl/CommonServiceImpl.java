package com.deloitte.services.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.feign.BpmApprovalMatrixFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.*;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.param.SignInfoQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.common.util.HttpUtils;
import com.deloitte.services.contract.common.util.StringUtil;
import com.deloitte.services.contract.entity.*;
import com.deloitte.services.contract.entity.Process;
import com.deloitte.services.contract.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  bpm服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class CommonServiceImpl implements ICommonService {

    @Autowired
    public IBasicInfoService basicInfoService;
    @Autowired
    private ISysSignSubjectInfoService sysSignSubjectInfoService;
    @Autowired
    private IApprovalOpinionService approvalOpinionService;
    @Autowired
    private IProcessTaskService processTaskService;
    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;
    @Autowired
    private IApprovalVoucherService approvalVoucherService;
    @Autowired
    public IStandardTemplateService  standardTemplateService;
    @Autowired
    private IFinanceInfoService iFinanceInfoService;
    @Autowired
    private ISignInfoService signInfoService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private ISetupInfoService setupInfoService;
    @Autowired
    private IEvaluateService evaluateService;
    @Autowired
    private IProcessService processService;
    @Autowired
    private ICarbonCopyService carbonCopyService;

    @Value("${contract.audit.url:http://124.17.100.183:30080/contract/index.html#/loading}")
    private String CONTRACT_AUDIT_URL;

    @Override
    public Result getContractByContractId(String contractId) {
        return  new Result<BasicInfoVo>().sucess(getContractBasicInfo(contractId));
    }

    @Override
    public Result getContractByProcessId(String processId) {
        BpmProcessTaskVo bpmProcessTaskVo = bpmProcessTaskFeignService.get(Long.parseLong(processId)).getData();
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(bpmProcessTaskVo.getObjectId());
        BasicInfoVo contract = getContractBasicInfo(approvalVoucher.getBusinessId());
        getContractApproval(contract,approvalVoucher,processId);
        return new Result().sucess(contract);
    }

    @Override
    public Result getContractByObjectId(String objectId) {
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(objectId);
        BasicInfoVo contract = getContractBasicInfo(approvalVoucher.getBusinessId());
        getContractApproval(contract,approvalVoucher,"");
        return new Result().sucess(contract);
    }

    /**
     * 用户当前用户的单位
     * @return
     */
    @Override
    public UserVo getCurrentUser() {
        return UserUtil.getUserVo();
    }

    /**
     * 获取当前用户的单位
     * @return
     */
    @Override
    public DeptVo getCurrentDept() {
        return UserUtil.getDept();
    }

    /**
     * 获取当前用户的部门
     * @return
     */
    @Override
    public OrganizationVo getOrganization(){
        return UserUtil.getOrganization();
    }

    //获取合同基本信息
    public BasicInfoVo getContractBasicInfo(String contractId){
        //查询合同基本信息
        BasicInfo basicInfoList = basicInfoService.selectContractInfoById(Long.parseLong(contractId));
        BasicInfoVo basicInfoVo =new BeanUtils<BasicInfoVo>().copyObj(basicInfoList, BasicInfoVo.class);
        // 根据审批选择的key获取作废时需要用的key
        Process process = processService.getEndKeyByStartKey(basicInfoVo.getSubmitPocessKey());
        if (process != null){
            basicInfoVo.setCancelProcessKey(process.getProcessDefineKey());
        }
        //查询合同签约主体信息
        List<BasicSubjectMap> basicSubjectMapList = basicInfoService.selectBasicSubjectByContractId(Long.parseLong(contractId));
        List<BasicSubjectMapVo> basicSubjectMapVoList=new BeanUtils<BasicSubjectMapVo>().copyObjs(basicSubjectMapList,BasicSubjectMapVo.class);
        basicInfoVo.setBasicSubjectList((ArrayList<BasicSubjectMapVo>)basicSubjectMapVoList);
        //查询合同关联文件信息
        List<BasicAttamentMap> basicAttamentMapList = basicInfoService.selectbasicAttamentByContractId(Long.parseLong(contractId));
        List<BasicAttamentMapVo> basicAttamentMapVoList=new BeanUtils<BasicAttamentMapVo>().copyObjs(basicAttamentMapList,BasicAttamentMapVo.class);
        basicInfoVo.setBasicAttamentList((ArrayList<BasicAttamentMapVo>)basicAttamentMapVoList);
        //查询打印签署信息
        SignInfoQueryForm signInfoQueryForm = new SignInfoQueryForm();
        signInfoQueryForm.setContractId(contractId);
        List<SignInfo> signInfo = signInfoService.selectList(signInfoQueryForm);
        List<SignInfoVo> signInfoVo = new BeanUtils<SignInfoVo>().copyObjs(signInfo,SignInfoVo.class);
        basicInfoVo.setSignInfoVo(signInfoVo);
        //查询合同评价信息
        QueryWrapper<Evaluate> queryEvaluate = new QueryWrapper<>();
        queryEvaluate.eq(Evaluate.CONTRACT_ID,contractId);
        Evaluate evaluate = evaluateService.getOne(queryEvaluate);
        EvaluateVo evaluateVo = new BeanUtils<EvaluateVo>().copyObj(evaluate,EvaluateVo.class);
        basicInfoVo.setEvaluateVo(evaluateVo);

        //查询合同办结信息
        QueryWrapper<SetupInfo> querySetupInfo = new QueryWrapper<>();
        querySetupInfo.eq(SetupInfo.CONTRACT_ID,contractId);
        SetupInfo setupInfo = setupInfoService.getOne(querySetupInfo);
        SetupInfoVo setupInfoVo = new BeanUtils<SetupInfoVo>().copyObj(setupInfo,SetupInfoVo.class);
        basicInfoVo.setSetupInfoVo(setupInfoVo);

        //查询财务信息
        List<FinanceInfoVo> financeInfo=iFinanceInfoService.selectFinanceInfo(contractId);
//        FinanceInfoVo financeInfoVo=new BeanUtils<FinanceInfoVo>().copyObj(financeInfo, FinanceInfoVo.class);
        basicInfoVo.setListFinanceInfoVo(financeInfo);
        if(financeInfo.size()>0){
            //约定收款比例汇总
            Long appointIncomeRateSum = 0L;
            //约定收款金额汇总
            Double appointIncomeAmountSum = 0.0d;
            //实际收款金额汇总
            Double actIncomeRateSum = 0.0d;
            //约定付款比例汇总
            Long appointPayRateSum = 0L;
            //约定付款金额汇总
            Double appointPayAmountSum = 0.0d;
            //实际付款金额汇总
            Double actPayRateSum = 0.0d;
            for(int i=0;i<financeInfo.size();i++){
                if(null!=financeInfo.get(i).getAppointIncomeRate()){
                    appointIncomeRateSum += financeInfo.get(i).getAppointIncomeRate();}
                if(null!=financeInfo.get(i).getAppointIncomeAmount()){
                    appointIncomeAmountSum += financeInfo.get(i).getAppointIncomeAmount();}
                if(null!=financeInfo.get(i).getActIncomeRate()){
                    actIncomeRateSum += financeInfo.get(i).getActIncomeRate();}
                if(null!=financeInfo.get(i).getAppointPayAmount()){
                    appointPayAmountSum += financeInfo.get(i).getAppointPayAmount();}
                if(null!=financeInfo.get(i).getAppointPayRate()){
                    appointPayRateSum += financeInfo.get(i).getAppointPayRate();}
                if(null!=financeInfo.get(i).getActPayRate()){
                    actPayRateSum += financeInfo.get(i).getActPayRate();}
            }
            basicInfoVo.setAppointIncomeRateSum(appointIncomeRateSum.toString());
            basicInfoVo.setAppointIncomeAmountSum(appointIncomeAmountSum.toString());
            basicInfoVo.setActIncomeRateSum(actIncomeRateSum.toString());
            basicInfoVo.setAppointPayRateSum(appointPayRateSum.toString());
            basicInfoVo.setAppointPayAmountSum(appointPayAmountSum.toString());
            basicInfoVo.setActPayRateSum(actPayRateSum.toString());
        }

        //标准文本名称
        StandardTemplate standardTemplate=standardTemplateService.getById(basicInfoVo.getTemplateCode());
        StandardTemplateVo standardTemplateVo=new BeanUtils<StandardTemplateVo>().copyObj(standardTemplate,StandardTemplateVo.class);
        basicInfoVo.setStandardTemplateVo(standardTemplateVo);

        //判断是够可以有作废按钮
        if("EXA1000".equals(basicInfoVo.getStatue())){
            basicInfoVo.setCanUnable("1");
        }
        return basicInfoVo ;
    }
    //填充合同审批信息，合同是否可以撤回信息
    public void getContractApproval(BasicInfoVo basicInfoVo,ApprovalVoucher approvalVoucher,String processId){
        if(VoucherTypeEnums.CONTRACT_BOOK.getCode().equals(approvalVoucher.getVoucherType())){//单据信息为合同审批时，判断是否可以撤回
            //判断单据是否可以撤回
            BpmProcessTaskQueryWrapper wrapper=new BpmProcessTaskQueryWrapper();
            Map<String,String[]> queryMap=new HashMap<>();
            String[] objectIds={"eq",approvalVoucher.getId()+""};
            queryMap.put(BpmProcessTaskQueryForm.OBJECT_ID,objectIds);
            wrapper.setWrapperMap(queryMap);
            Result<GdcPage<BpmProcessTaskVo>> taskVopage=bpmProcessTaskFeignService.searchByWrapper(wrapper);
            if(taskVopage.getData().getTotal() == 2 ){
                basicInfoVo.setCanRollBack("1");
            }
        }
        basicInfoVo.setIsAfterDuty("0");
        if(null != processId && !"".equals(processId) && VoucherTypeEnums.CONTRACT_BOOK.getCode().equals(approvalVoucher.getVoucherType()) || VoucherTypeEnums.CONTRACT_UNABLE.getCode().equals(approvalVoucher.getVoucherType())){//单据信息为合同审批和合同作废时 修改意见改退回
            //判断节点是够为院校领导节点
            Result<BpmProcessTaskVo> result =  bpmProcessTaskFeignService.get(Long.parseLong(processId));
            if(result.isSuccess()){
                BpmProcessTaskVo bpmProcessTaskVo = result.getData();
                if(bpmProcessTaskVo.getTaskKey().indexOf("duty_leader") != -1 || bpmProcessTaskVo.getTaskKey().indexOf("finance_leader") != -1|| bpmProcessTaskVo.getTaskKey().indexOf("main_leader") != -1    ){
                    basicInfoVo.setIsAfterDuty("1");
                }
            }
        }

        if(VoucherTypeEnums.CONTRACT_SURE.getCode().equals(approvalVoucher.getVoucherType())){//单据信息为定稿时候，返回的是合同审批的审批意见
            QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,approvalVoucher.getBusinessId());
            queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,VoucherTypeEnums.CONTRACT_BOOK.getCode());
            approvalVoucher = approvalVoucherService.getOne(queryWrapper);
        }
        if(null != approvalVoucher ){
            //获取审批节点
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setObjectId(approvalVoucher.getId()+"");
            Result<List<BpmProcessTaskVo>>  resultProcessTaskVo = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
            List<BpmProcessTaskVo> bpmProcessTaskVoList= resultProcessTaskVo.getData();
            List<ContractProcessTaskVo> processTaskVoList=new BeanUtils<ContractProcessTaskVo>().copyObjs(bpmProcessTaskVoList,ContractProcessTaskVo.class);
            List<ContractProcessTaskVo> processTaskVoListCopy= new ArrayList<>();

            if(null != processTaskVoList && processTaskVoList.size() >0){
                for(ContractProcessTaskVo processTaskVo : processTaskVoList){
                    if(null != processTaskVo && !"待提交".equals(processTaskVo.getTaskStauts()) && !"待审批".equals(processTaskVo.getTaskStauts()) && !"待重新提交".equals(processTaskVo.getTaskStauts()) ){//待提交,待审批,待重新提交
                        processTaskVoListCopy.add(processTaskVo);
                    }
                }
            }
            //获取每个审批节点的审批信息
            for(int i = 0 ; i < processTaskVoListCopy.size() ; i++){
                QueryWrapper<ApprovalOpinion> opionWrapper = new QueryWrapper<>();
                opionWrapper.eq(ApprovalOpinion.PROCESS_TASK_ID,processTaskVoListCopy.get(i).getTaskId());
                List<ApprovalOpinion> approvalOpinionList = approvalOpinionService.list(opionWrapper);
                List<ApprovalOpinionVo> approvalOpinionVoList=new BeanUtils<ApprovalOpinionVo>().copyObjs(approvalOpinionList,ApprovalOpinionVo.class);
                processTaskVoListCopy.get(i).setApprovalOpinionVoList(approvalOpinionVoList);
            }
            basicInfoVo.setProcessTaskVoList(processTaskVoListCopy);
        }
    }

    /**
     * 判断是否能跳转审批
     * @param approvalVoucher
     * @return
     */
    public Result getCanjump(ApprovalVoucher approvalVoucher){
        String contractId = approvalVoucher.getBusinessId();
        QueryWrapper<ApprovalVoucher> approvalUnableWrapper = new QueryWrapper <>();
        approvalUnableWrapper.eq(ApprovalVoucher.BUSINESS_ID, contractId);
        approvalUnableWrapper.eq(ApprovalVoucher.VOUCHER_TYPE, "4");
        ApprovalVoucher approvalUnable = approvalVoucherService.getOne(approvalUnableWrapper);
        if (approvalUnable != null){
            String voucherStatus = approvalUnable.getVoucherStatus();
            if (!"4".equals(voucherStatus) || !"5".equals(voucherStatus)){
                AssertUtils.asserts(!approvalVoucher.getVoucherType().equals(approvalUnable.getVoucherType()) , ContractErrorType.CONTRACT_IS_UNABLE);
//                if (approvalVoucher.getVoucherType().equals(approvalUnable.getVoucherType())){
//                    return Result.success();
//                }else{
//                    return Result.fail("合同正在进行作废作废");
//                }
            }
        }

        QueryWrapper<ApprovalVoucher> approvalOperatorWrapper = new QueryWrapper <>();
        approvalOperatorWrapper.eq(ApprovalVoucher.BUSINESS_ID, contractId);
        approvalOperatorWrapper.eq(ApprovalVoucher.VOUCHER_TYPE, "5");
        ApprovalVoucher approvalOperator = approvalVoucherService.getOne(approvalOperatorWrapper);
        if (approvalOperator != null){
            String operatorStatus = approvalOperator.getVoucherStatus();
            if (!"4".equals(operatorStatus) || !"5".equals(operatorStatus)){
                AssertUtils.asserts(!approvalVoucher.getVoucherType().equals(approvalOperator.getVoucherType()) , ContractErrorType.CONTRACT_IS_OPERATOR);
//                if (approvalVoucher.getVoucherType().equals(approvalOperator.getVoucherType())){
//                    return Result.success();
//                }else{
//                    return Result.fail("合同正在进行经办人移交");
//                }
            }
        }

        QueryWrapper<ApprovalVoucher> approvalExecuterWrapper = new QueryWrapper <>();
        approvalExecuterWrapper.eq(ApprovalVoucher.BUSINESS_ID, contractId);
        approvalExecuterWrapper.eq(ApprovalVoucher.VOUCHER_TYPE, "5");
        ApprovalVoucher approvalExecuter = approvalVoucherService.getOne(approvalExecuterWrapper);
        if (approvalExecuter != null){
            String executerStatus = approvalExecuter.getVoucherStatus();
            if (!"4".equals(executerStatus) || !"5".equals(executerStatus)){
                AssertUtils.asserts(!approvalVoucher.getVoucherType().equals(approvalExecuter.getVoucherType()) , ContractErrorType.CONTRACT_IS_EXECUTER);
//                if (approvalVoucher.getVoucherType().equals(approvalExecuter.getVoucherType())){
//                    return Result.success();
//                }else{
//                    return Result.fail("合同正在进行履行人移交");
//                }
            }
        }
        return Result.success();
    }

    @Override
    public Result sendProcessTask(UserVo userVo, String businessId, VoucherTypeEnums typeEnums){
        UserVo currentUserVo = getCurrentUser();
        SendProcessTaskForm sendProcessTaskForm = new SendProcessTaskForm();
        //给某个id 发送一条待阅
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(businessId), typeEnums);
        sendProcessTaskForm.setApproverAcount(userVo.getId());
        sendProcessTaskForm.setApproverName(userVo.getName());
        sendProcessTaskForm.setApproverStation(userVo.getHonor());
        sendProcessTaskForm.setObjectType( typeEnums.getCode());
        sendProcessTaskForm.setObjectId(voucher.getId()+"");
        sendProcessTaskForm.setObjectDescription(voucher.getVoucherName()+"");
        sendProcessTaskForm.setCreateByName(currentUserVo.getName()+"");
        sendProcessTaskForm.setSubmitterCode(currentUserVo.getId()+"");
        sendProcessTaskForm.setSubmitterName(currentUserVo.getName()+"");
        sendProcessTaskForm.setObjectUrl(CONTRACT_AUDIT_URL+"?returnSystem=contract");
        sendProcessTaskForm.setTaskStauts("待阅");
        Result result = carbonCopyService.add(sendProcessTaskForm);
        return result;
    }

    @Override
    public Result sendProcessTask(UserVo userVo, String businessId, VoucherTypeEnums typeEnums, String voucherName){
//        UserVo currentUserVo = getCurrentUser();
        UserVo currentUserVo = new UserVo();
        currentUserVo.setId("99999999");
        currentUserVo.setName("合同发起流程");
        SendProcessTaskForm sendProcessTaskForm = new SendProcessTaskForm();
        //给某个id 发送一条待阅
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(businessId), typeEnums, voucherName);
        sendProcessTaskForm.setApproverAcount(userVo.getId());
        sendProcessTaskForm.setApproverName(userVo.getName());
        sendProcessTaskForm.setApproverStation(userVo.getHonor());
        sendProcessTaskForm.setObjectType( typeEnums.getCode());
        sendProcessTaskForm.setObjectId(voucher.getId()+"");
        sendProcessTaskForm.setObjectDescription(voucher.getVoucherName()+"");
        sendProcessTaskForm.setCreateByName(currentUserVo.getName()+"");
        sendProcessTaskForm.setSubmitterCode(currentUserVo.getId()+"");
        sendProcessTaskForm.setSubmitterName(currentUserVo.getName()+"");
        sendProcessTaskForm.setObjectUrl(CONTRACT_AUDIT_URL+"?returnSystem=contract");
        sendProcessTaskForm.setTaskStauts("待阅");
        Result result = carbonCopyService.add(sendProcessTaskForm);
        return result;
    }
}


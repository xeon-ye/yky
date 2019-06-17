package com.deloitte.services.contract.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.feign.BpmApprovalMatrixFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryParam;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.contract.param.ApprovalVoucherQueryForm;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.param.SysContractNoQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.feign.SendProcessTaskFeignService;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.common.config.DateConfig;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherStatusEnums;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.common.util.DateUtil;
import com.deloitte.services.contract.common.util.StringUtil;
import com.deloitte.services.contract.entity.*;
import com.deloitte.services.contract.entity.Process;
import com.deloitte.services.contract.mapper.SerialNumberMapper;
import com.deloitte.services.contract.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.docx4j.wml.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-08
 * @Description :   我的工作项
 * @Modified :
 */
@Api(tags = "1-我的工作项")
@Slf4j
@RestController
@RequestMapping("/work")
public class MyWorkController {

    @Autowired
    public IBPMService bpmService;

    @Autowired
    public IBasicInfoService basicInfoService;

    @Autowired
    private IApprovalVoucherService approvalVoucherService;

    @Autowired
    private IApprovalOpinionService approvalOpinionService;

    @Autowired
    private ISysSignSubjectInfoService sysSignSubjectInfoService;

    @Autowired
    private ISignInfoService signInfoService;

    @Autowired
    private IProcessTaskService processTaskService;

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Autowired
    private BpmApprovalMatrixFeignService bpmApprovalMatrixFeignService;


    @Autowired
    private ISerialNumberService serialNumberService;

    @Autowired
    public ISysContractNoService  sysContractNoService;

    @Autowired
    public ICommonService  commonService;

    @Autowired
    public IFinanceInfoService  iFinanceInfoService;

    @Autowired
    private IProcessExecuterTransferService processExecuterTransferService;

    @Autowired
    private IProcessOperatorTransferService iProcessOperatorTransferService;

    @Autowired
    private IStandardTemplateService iStandardTemplateService;

    @Autowired
    private IProcessService processService;

    @Autowired
    private ICarbonCopyService carbonCopyService;
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private SendProcessTaskFeignService sendProcessTaskFeignService;
    @Autowired
    private ISysProjectInfoService sysProjectInfoService;
    @Autowired
    private IMonitorInfoService monitorInfoService;
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private ITicketInfoService ticketInfoService;


    @ApiOperation(value = "获取下一步所有的节点信息和审批人-流程已启动", notes = "获取下一步所有的节点信息和审批人-流程已启动")
    @PostMapping("/findNextNodeList")
    public Result findNextNodeList(@Valid @RequestBody TaskNodeActionVO actionVO) {
        AssertUtils.asserts(null == actionVO || null == actionVO.getId() , ContractErrorType.NEXT_NODE_PARAMETER_ERROR);
        supplyTaskNodeActionVO(actionVO);
        Map<String,String> processVariables = new HashMap<>();
        Result result = bpmService.findNextNodeList(actionVO,processVariables);
        List<NextNodeVO> nextNodeVOList = new ArrayList<>();
        //查询单据，获取合同id
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
        if(result.isSuccess()){
            List<Map<String,String>> nextNodeMapList = (List<Map<String,String>>)result.getData();
            for(Map<String,String> nextNodeMap:nextNodeMapList){
                NextNodeVO nextNodeVO = new NextNodeVO();
                nextNodeVO.setTaskKey(nextNodeMap.get("taskKey"));
                nextNodeVO.setConditionText(nextNodeMap.get("conditionText"));
                nextNodeVO.setTaskNodeName(nextNodeMap.get("taskNodeName"));
//                nextNodeVO.setMulti(Boolean.valueOf(nextNodeMap.get("multi")));
//                nextNodeVO.setEndNode(Boolean.valueOf(nextNodeMap.get("isEndNode")));
//                nextNodeVO.setFirstNode(Boolean.valueOf(nextNodeMap.get("isFirstNode")));
//                nextNodeVO.setReject(Boolean.valueOf(nextNodeMap.get("isReject")));
                if(null != nextNodeMap.get("taskKey") && !"".equals(nextNodeMap.get("taskKey"))){
                    //查询每个节点的审批矩阵
                    BpmApprovalMatrixQueryForm  bpmApprovalMatrixQueryForm = new BpmApprovalMatrixQueryForm();
                    bpmApprovalMatrixQueryForm.setActivityId(nextNodeMap.get("taskKey"));
                    bpmApprovalMatrixQueryForm.setProcessDefineKey(actionVO.getProcessDefineKey());
                    //*********************************流程已启动，通过id获取当前流程的提交人机构code，即submitterOrg*********************************
                    bpmApprovalMatrixQueryForm.setChargeOrg(actionVO.getSubmitterOrg());
                    Result<List<BpmApprovalMatrixVo>> matrixReslt = bpmApprovalMatrixFeignService.list(bpmApprovalMatrixQueryForm);
                    if(matrixReslt.isSuccess()){
                        List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList = matrixReslt.getData();
                        nextNodeVO.setBpmApprovalMatrixVoList(bpmApprovalMatrixVoList);
                    }
                }
                if(VoucherTypeEnums.CONTRACT_BOOK.getCode().equals(approvalVoucher.getVoucherType()) || VoucherTypeEnums.CONTRACT_UNABLE.getCode().equals(approvalVoucher.getVoucherType())){
                    //已经复审过的合同不能再次复审
                    if("approval_fir".equals(actionVO.getTaskKey()) && "approval_sec".equals(nextNodeMap.get("taskKey")) && null != contract && "1".equals(contract.getReview())){
                        continue;
                    }
                    // 主板部门分管领导节点 判断主办与财务部门分管领导是否一致
                    if("duty_leader".equals(actionVO.getTaskKey())){

                        //查询每个节点的审批矩阵
                        BpmApprovalMatrixQueryForm  bpmApprovalMatrixQueryForm = new BpmApprovalMatrixQueryForm();
                        bpmApprovalMatrixQueryForm.setActivityId("finance_leader");
                        bpmApprovalMatrixQueryForm.setProcessDefineKey(actionVO.getProcessDefineKey());
                        bpmApprovalMatrixQueryForm.setChargeOrg(actionVO.getSubmitterOrg());
                        Result<List<BpmApprovalMatrixVo>> matrixReslt = bpmApprovalMatrixFeignService.list(bpmApprovalMatrixQueryForm);
                        String financeLeaderId =  "";
                        if(null != matrixReslt.getData() && matrixReslt.getData().size() > 0){
                            financeLeaderId = matrixReslt.getData().get(0).getAccountNum();
                        }else{
                            AssertUtils.asserts(1==1 , ContractErrorType.NEXT_NODE_FINANCE_PARAMETER_ERROR);
                        }
                        UserVo userVo = commonService.getCurrentUser();//当前用户为主办部门分管领导
                        //1、相同  【勾选  1或者1 3 】  【勾选2】
                        //2、不相同  【直接到财务部门分管领导】
                        if(userVo.getId().equals(financeLeaderId)){//分管领导和财务领导相同
                            if("finance_leader".equals(nextNodeMap.get("taskKey"))){
                                continue;
                            }
                            if("end".equals(nextNodeMap.get("taskKey")) && !"2".equals(contract.getApprovalType())){
                                continue;
                            }
                            if("main_leader".equals(nextNodeMap.get("taskKey")) && !"1".equals(contract.getApprovalType()) && !"3".equals(contract.getApprovalType())){
                                continue;
                            }
                        }else{//分管领导和财务领导不相同
                            if(!"finance_leader".equals(nextNodeMap.get("taskKey"))){
                                continue;
                            }
                        }
                    }
                    //财务领导流向
                    // 办公室负责人审批意见
                    // 【1 或者 1 3 】 直接到主要领导
                    //【2】 直接结束
                    if("finance_leader".equals(actionVO.getTaskKey())){
                        if("end".equals(nextNodeMap.get("taskKey")) && !"2".equals(contract.getApprovalType())){
                            continue;
                        }
                        if("main_leader".equals(nextNodeMap.get("taskKey")) && !"1".equals(contract.getApprovalType()) && !"3".equals(contract.getApprovalType())){
                            continue;
                        }
                    }
                }
                nextNodeVOList.add(nextNodeVO);
            }
        }
        return new Result<List<NextNodeVO>>().sucess(nextNodeVOList) ;
    }

    @ApiOperation(value = "获取下一步所有的节点信息和审批人-流程未启动", notes = "获取下一步所有的节点信息和审批人-流程未启动")
    @PostMapping("/findNextNodeListWithoutStart")
    public Result findNextNodeListWithoutStart(@Valid @RequestBody TaskNodeActionVO actionVO) {
        AssertUtils.asserts(null == actionVO || null == actionVO.getProcessDefineKey() , ContractErrorType.NEXT_NODE_PARAMETER_ERROR);
        actionVO.setTaskId("start");
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        Map<String,String> processVariables = new HashMap<>();
        Result result = bpmService.findNextNodeList(actionVO,processVariables);
        List<NextNodeVO> nextNodeVOList = new ArrayList<>();
        if(result.isSuccess()){
            List<Map<String,String>> nextNodeMapList = (List<Map<String,String>>)result.getData();
            for(Map<String,String> nextNodeMap:nextNodeMapList){
                NextNodeVO nextNodeVO = new NextNodeVO();
                nextNodeVO.setTaskKey(nextNodeMap.get("taskKey"));
                nextNodeVO.setConditionText(nextNodeMap.get("conditionText"));
                nextNodeVO.setTaskNodeName(nextNodeMap.get("taskNodeName"));
//                nextNodeVO.setMulti(Boolean.valueOf(nextNodeMap.get("multi")));
//                nextNodeVO.setEndNode(Boolean.valueOf(nextNodeMap.get("isEndNode")));
//                nextNodeVO.setFirstNode(Boolean.valueOf(nextNodeMap.get("isFirstNode")));
//                nextNodeVO.setReject(Boolean.valueOf(nextNodeMap.get("isReject")));
                if(null != nextNodeMap.get("taskKey") && !"".equals(nextNodeMap.get("taskKey"))){
                    //查询每个节点的审批矩阵
                    BpmApprovalMatrixQueryForm bpmApprovalMatrixQueryForm = new BpmApprovalMatrixQueryForm();
                    bpmApprovalMatrixQueryForm.setActivityId(nextNodeMap.get("taskKey"));
                    bpmApprovalMatrixQueryForm.setProcessDefineKey(actionVO.getProcessDefineKey());
                    //*********************************流程未启动，以当前用户的机构code（即userVo.getDept()）做为chargeOrg*********************************
                    bpmApprovalMatrixQueryForm.setChargeOrg(organizationVo.getCode());
                    Result<List<BpmApprovalMatrixVo>> matrixReslt = bpmApprovalMatrixFeignService.list(bpmApprovalMatrixQueryForm);
                    if(matrixReslt.isSuccess()){
                        List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList = matrixReslt.getData();
                        nextNodeVO.setBpmApprovalMatrixVoList(bpmApprovalMatrixVoList);
                    }
                }
                nextNodeVOList.add(nextNodeVO);
            }
        }
        return new Result<List<NextNodeVO>>().sucess(nextNodeVOList) ;
    }

    @ApiOperation(value = "1-1-3审核流程", notes = "1-1-3审核流程")
    @PostMapping("/auditApprove")
    public Result auditApprove(@Valid @RequestBody TaskNodeActionVO actionVO ) {
        supplyTaskNodeActionVO(actionVO);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        Map<String, String> processVariables = new HashMap<>();
        Result result = new Result();

        //查询单据，获取合同id
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());

        result = commonService.getCanjump(approvalVoucher);

        if (VoucherTypeEnums.CONTRACT_BOOK.getCode().equals(approvalVoucher.getVoucherType())) {//合同审批流程的审批结束的业务代码
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

            /**
             * 业务逻辑
             * 1、判断当前审批节点的人需要从审批矩阵获取  还是直接设置下一个节点的处理人
             * 2、合同审批 流程审批
             * 3、保存审批意见
             * 4、判断是否流程是否识审批完成
             * 5、如果合同审批完成，
             *      ① 更新合同状态为 审批通过
             *      ②更新单据状态为审批通过
             *      ③自动发起定稿的审批流程
             *      ④流程发起成功  合同定稿状态修改为未定稿
             *
             * */
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            if("office_leader".equals(actionVO.getTaskKey())){
                AssertUtils.asserts(null ==actionVO.getApprovalType() || "".equals(actionVO.getApprovalType()),ContractErrorType.APPROVAL_TYPE_IS_NULL);
                contract.setApprovalType(actionVO.getApprovalType());
                basicInfoService.updateById(contract);
            }
            //经办人
            UserVo operatorVo = new UserVo();
            operatorVo.setId(contract.getOperatorCode());
            operatorVo.setName(contract.getOperator());
            //履行人
            UserVo executeVo = new UserVo();
            executeVo.setId(contract.getExecuterCode());
            executeVo.setName(contract.getExecuter());
//            processVariables.put("approvalWay",actionVO.getApprovalWay());
//            processVariables.put("financeWay",actionVO.getFinanceWay());
//            processVariables.put("dutyWay",actionVO.getDutyWay());
            //设置所有审批的节点都是当前用户， 后面会根据不同的审批节点 设置下一个审批节点的用户信息
//            result = bpmService.auditProcess(actionVO, userVo, deptVo, processVariables);
            result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,nextNodeParamVoList, processVariables);
            if (result.isSuccess()) {
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                    for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId() + "");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                        if(null == approvalOpinionVo.getId() || !"".equals(approvalOpinionVo)){
                            approvalOpinionService.save(opinion);
                        }else{
                            approvalOpinionService.updateById(opinion);
                        }
                    }
                }
                if("approval_sec".equals(actionVO.getTaskKey())){
                    contract.setReview("1");
                    basicInfoService.updateById(contract);
                }
                if ("EXA4000".equals(contract.getStatue())){
                    contract.setStatue("EXA2000");
                    basicInfoService.updateById(contract);
                }

                //判断是否审批完成
                if (null != result.getData() && "审批结束".equals(result.getData())) {

                    //获取合同编号
                    SysContractNoQueryForm sysContractNoQueryForm = new SysContractNoQueryForm();
                    sysContractNoQueryForm.setDepartmentCode(contract.getOrgCode());
                    sysContractNoQueryForm.setContractYear(DateUtil.getCurrentYear()+"");
                    String contractNo = sysContractNoService.getContractNoForChange(contract, sysContractNoQueryForm);
                    //获取合同流水号
                    String serialNumber = serialNumberService.getSerialNumber().get("serialNumber")+"";

                    //更新合同状态为取消
                    contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                    contract.setStatue("EXA1000");
                    contract.setStatueTime(LocalDateTime.now());
                    contract.setContractNo(contractNo);
                    contract.setContractSerialNumber(serialNumber);
                    contract.setStatueList("STA2000");
                    // 保存提交审批流程key
                    contract.setSubmitPocessKey(actionVO.getProcessDefineKey());
                    basicInfoService.updateById(contract);

                    //将对方，我方签约主体保存到签署信息表
                    //查询合同签约主体信息
                    List<BasicSubjectMap> basicSubjectMapList = basicInfoService.selectBasicSubjectByContractId(Long.parseLong(approvalVoucher.getBusinessId()));
                    if(null != basicSubjectMapList && basicSubjectMapList.size() >0){
                        List<BasicSubjectMapVo> basicSubjectMapVoList=new BeanUtils<BasicSubjectMapVo>().copyObjs(basicSubjectMapList,BasicSubjectMapVo.class);
                        if(basicSubjectMapList.size()>0) {
                            for (int i = 0; i < basicSubjectMapVoList.size(); i++) {
                                SignInfo signInfo = new SignInfo();
                                signInfo.setContractId(approvalVoucher.getBusinessId()+"");
                                signInfo.setContractName(contract.getContractName());
                                BasicSubjectMapVo basicSubjectMapVo = basicSubjectMapVoList.get(i);
                                //保存我方签约主体
                                if (basicSubjectMapVo.getType().equals("1")) {
//                                    SysSignSubjectInfo sysSignSubjectInfo = sysSignSubjectInfoService.selectObjectById(basicSubjectMapVo.getSubjectId());
                                    signInfo.setSubjectCode(basicSubjectMapVo.getSubjectId().toString());
                                    signInfo.setOurSubjectInfo(basicSubjectMapVo.getSubjectName());
                                    signInfo.setType("1");
                                    signInfo.setOurSignPerson(basicSubjectMapVo.getDeptLegalPersonName());
                                    signInfoService.save(signInfo);
                                } else if(basicSubjectMapVo.getType().equals("2")) {
                                    //保存对方签约主体
//                                    SysSignSubjectInfo sysSignSubjectInfo = sysSignSubjectInfoService.selectObjectById(basicSubjectMapVo.getSubjectId());
                                    signInfo.setSubjectCode(basicSubjectMapVo.getSubjectId().toString());
                                    signInfo.setOtherContractName(basicSubjectMapVo.getSubjectName());
                                    signInfo.setType("2");
                                    signInfo.setOtherLegalPerson(basicSubjectMapVo.getDeptLegalPersonName());
                                    signInfoService.save(signInfo);
                                }
                            }
                        }
                    }

                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);

                    // 修改变更合同信息状态
                    basicInfoService.saveBasicRelationStatus(contract.getId().toString(), "", "", "EXA13000");
                    if ("REL5000".equals(contract.getRelationCode())){
                        contract.setStatueList("STA7000");
                        basicInfoService.updateById(contract);
                        basicInfoService.selectBasicInfoToFssc(contract.getId());
                        return Result.success();
                    }
                    //自动发起合同定稿的流程
                    //判断合同是否已经提交过,这个地方先默认为不存在
                      /*  QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq(ApprovalVoucher.CONTRACT_ID,approvalVoucher.getContractId());
                        queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,VoucherTypeEnums.CONTRACT_SURE.getCode());
                        ApprovalVoucher sureVoucher = approvalVoucherService.getOne(queryWrapper);*/
                    //新建一个单据
                    ApprovalVoucher sureVoucher = approvalVoucherService.generateNewVoucher(Long.parseLong(approvalVoucher.getBusinessId()), VoucherTypeEnums.CONTRACT_SURE);
                    //流程提交参数
                    Map<String, String> sureariables = new HashMap<>();

                    //设置自动发送的账号和机构
                    UserVo systemUser = new UserVo();
                    systemUser.setId("99999999");
                    systemUser.setName("合同发起流程");
                    OrganizationVo sysOrg = new OrganizationVo();
                    sysOrg.setCode(contract.getOrgCode());

                    //获取流程信息
                    QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
                    processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.CONTRACT_SURE.getCode());
                    Process process = processService.getOne(processQueryWrapper);
                    AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

                    Result sureResult = bpmService.autoStartAuditProcess( sureVoucher, systemUser, sysOrg, operatorVo,process.getProcessDefineKey(), sureariables);
                    if (sureResult.isSuccess()) {//流程发起成功
                        //更新合同数据
                        contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                        contract.setSureStatue("SUR2000");
                        basicInfoService.updateById(contract);
                    }
                }
            }
        }else if (VoucherTypeEnums.CONTRACT_SURE.getCode().equals(approvalVoucher.getVoucherType())) {//合同定稿
            /**
             * 业务逻辑
             * 1、判断当前审批节点的人需要从审批矩阵获取  还是直接设置下一个节点的处理人
             * 2、合同审批 流程审批
             * 3、判断是否流程是否识审批完成
             * 4、如果合同审批完成，
             *      ① 更新定稿状态为 已定稿
             *      ②更新单据状态为审批通过
             *      ③自动发起打印的审批流程
             *      ④流程发起成功之后，更新合同签署状态为未签署
             * */
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            //经办人
            UserVo operatorVo = new UserVo();
            operatorVo.setId(contract.getOperatorCode());
            operatorVo.setName(contract.getOperator());
            //履行人
            UserVo executeVo = new UserVo();
            executeVo.setId(contract.getExecuterCode());
            executeVo.setName(contract.getExecuter());

            //设置所有审批的节点都是当前用户， 后面会根据不同的审批节点 设置下一个审批节点的用户信息
            result = bpmService.auditProcess(actionVO, userVo, organizationVo, processVariables);
            /*if("sure".equals(actionVO.getTaskKey())){
                result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, deptVo,operatorVo, processVariables);
            }else{
                result = bpmService.auditProcess(actionVO, userVo, deptVo, processVariables);
            }*/
            if (result.isSuccess()) {
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //更新合同数据  合同定稿完成，然后启动合同打印的流程
                    contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                    contract.setSureStatueTime(LocalDateTime.now());
                    contract.setSureStatue("SUR1000");
                    contract.setSurePerson(userVo.getName());
                    contract.setSurePersonCode(userVo.getId());
                    contract.setStatueList("STA3000");
                    basicInfoService.updateById(contract);

                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);

                    //***************************START:合同审批流程完成之后 自动发起合同打印的代办事项***************************
                    //判断合同是否已经提交过,这个地方先默认为不存在
                    //新建一个单据
                    ApprovalVoucher signVoucher = approvalVoucherService.generateNewVoucher(Long.parseLong(approvalVoucher.getBusinessId()), VoucherTypeEnums.CONTRACT_PRINT);
                    //流程提交参数
                    Map<String, String> signariables = new HashMap<>();

                    UserVo systemUser = new UserVo();
                    systemUser.setId("99999999");
                    systemUser.setName("合同发起流程");
                    OrganizationVo sysOrg = new OrganizationVo();
                    sysOrg.setCode(contract.getOrgCode());

                    //获取流程信息
                    QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
                    processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.CONTRACT_PRINT.getCode());
                    Process process = processService.getOne(processQueryWrapper);
                    AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

                    Result signResult = bpmService.autoStartAuditProcess( signVoucher, systemUser, sysOrg, operatorVo,process.getProcessDefineKey(), signariables);
                    if (signResult.isSuccess()) {//流程发起成功
                        //添加业务逻辑处理
                    }
                    //***************************END***************************
                }
            }

        }else if (VoucherTypeEnums.CONTRACT_PRINT.getCode().equals(approvalVoucher.getVoucherType())) {//合同打印
            /**
             * 业务逻辑
             * 1、判断当前审批节点的人需要从审批矩阵获取  还是直接设置下一个节点的处理人
             * 2、合同审批 流程审批
             * 3、判断是否流程是否识审批完成
             * 4、如果合同审批完成，
             *      ①自动发起的审批流程
             * */
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            //经办人
            UserVo operatorVo = new UserVo();
            operatorVo.setId(contract.getOperatorCode());
            operatorVo.setName(contract.getOperator());
            //履行人
            UserVo executeVo = new UserVo();
            executeVo.setId(contract.getExecuterCode());
            executeVo.setName(contract.getExecuter());

            //设置所有审批的节点都是当前用户， 后面会根据不同的审批节点 设置下一个审批节点的用户信息
            result = bpmService.auditProcess(actionVO, userVo, organizationVo, processVariables);
            /*if("sure".equals(actionVO.getTaskKey())){
                result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, deptVo,operatorVo, processVariables);
            }else{
                result = bpmService.auditProcess(actionVO, userVo, deptVo, processVariables);
            }*/
            if (result.isSuccess()) {
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //打印完成后处理 打印的合同状态
                    contract.setStatueList("STA5000");
                    basicInfoService.updateById(contract);

                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);

                    //***************************START:合同打印完成之后，生成合同签署的待办事项***************************
                    //判断合同是否已经提交过,这个地方先默认为不存在
                    //新建一个单据
                    ApprovalVoucher signVoucher = approvalVoucherService.generateNewVoucher(Long.parseLong(approvalVoucher.getBusinessId()), VoucherTypeEnums.CONTRACT_SIGN_BOOK);
                    //流程提交参数
                    Map<String, String> signariables = new HashMap<>();
                    QueryWrapper<SignInfo> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq(SignInfo.CONTRACT_ID,contract.getId());
                    SignInfo signInfo = signInfoService.getOne(queryWrapper);
                    if(null == signInfo || null == signInfo.getStampWay() || "".equals(signInfo.getStampWay())){
                        signariables.put("way","1");
                    }else{
                        signariables.put("way",signInfo.getStampWay());
                    }
                    Result signResult =null;
                    Map<String, String> sureariables = new HashMap<>();
                    UserVo systemUser = new UserVo();
                    systemUser.setId("99999999");
                    systemUser.setName("合同发起流程");
                    OrganizationVo sysOrg = new OrganizationVo();
                    sysOrg.setCode(contract.getOrgCode());

                    //获取流程信息
                    QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
                    processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.CONTRACT_SIGN_BOOK.getCode());
                    Process process = processService.getOne(processQueryWrapper);
                    AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

                    if(signariables.get("way").equals("2")){
                        signResult = bpmService.autoStartAuditProcess( signVoucher,  systemUser, sysOrg, operatorVo,process.getProcessDefineKey(), signariables);
                    }else{
                        signResult = bpmService.startAuditProcess( signVoucher, systemUser, sysOrg,process.getProcessDefineKey(), signariables);
                    }
                    if (signResult.isSuccess()) {//流程发起成功
                        //更新合同数据
                        contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                        contract.setSignStatue("SIG2000");
                        basicInfoService.updateById(contract);
                    }
                    //***************************END***************************
                }
            }

        } else if (VoucherTypeEnums.CONTRACT_SIGN_BOOK.getCode().equals(approvalVoucher.getVoucherType())) {//合同签署
            /**
             * 业务逻辑
             * 1、判断当前审批节点的人需要从审批矩阵获取  还是直接设置下一个节点的处理人
             * 2、合同审批 流程审批
             * 3、判断是否流程是否识审批完成 ，不同的审批节点 审批完成之后 合同状态不一样
             * 4、如果合同审批完成，
             *      ① 更新签署状态为 已签署
             *      ②更新单据状态为审批通过
             *      ③自动发起合同履行的审批流程
             *      ④流程发起成功之后，更新合同履行状态为 非正常履行（有疑问？？？）
             * */
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            //经办人
            UserVo operatorVo = new UserVo();
            operatorVo.setId(contract.getOperatorCode());
            operatorVo.setName(contract.getOperator());
            //履行人
            UserVo executeVo = new UserVo();
            executeVo.setId(contract.getExecuterCode());
            executeVo.setName(contract.getExecuter());

            if(actionVO.getTaskKey().indexOf("other_sign") != -1){
                result = bpmService.auditProcess(actionVO, userVo, organizationVo,processVariables);
            }else{
                result = bpmService.auditProcessWithOutMatrix(actionVO, userVo ,organizationVo,operatorVo,processVariables);
            }

           /* if("sign".equals(actionVO.getTaskKey()) || "other_way_1".equals(actionVO.getTaskKey())|| "other_way_2".equals(actionVO.getTaskKey()) ){
                result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, deptVo, executeVo,processVariables);
            }else{
                result = bpmService.auditProcess(actionVO, userVo, deptVo, processVariables);
            }*/
            if (result.isSuccess()) {
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                    contract.setSignStatueTime(LocalDateTime.now());
                    contract.setSignStatue("EXE1000");
                    contract.setStatue("EXA6000");//签署完成之后，修改合同状态为履行中
                    contract.setStatueList("STA7000");//签署完成之后，修改合同列表查询状态为办结列表中可查询状态
                    contract.setIsToBasicFssc("0");//签署完成后将推送财务系统标记修改为0
                    //保存生效日期
                    QueryWrapper<SignInfo> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq(SignInfo.CONTRACT_ID,approvalVoucher.getBusinessId());
                    List<SignInfo> signInfoList = signInfoService.list(queryWrapper);
                    if(signInfoList != null && signInfoList.size()>0){
                        LocalDateTime maxTime =  LocalDateTime.parse("1980-01-01 00:00:01",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        for(SignInfo signInfo : signInfoList){
                            if("1".equals(signInfo.getStampWay())){
                                if(null != signInfo.getOtherSignTime() && signInfo.getOtherSignTime().isAfter(maxTime)){
                                    maxTime = signInfo.getOtherSignTime();
                                }
                            }
                            if("2".equals(signInfo.getStampWay())){
                                if(null != signInfo.getOurPrintTime() && signInfo.getOurPrintTime().isAfter(maxTime)) {
                                    maxTime = signInfo.getOurPrintTime();
                                }
                            }
                        }
                        if(!maxTime.isEqual(LocalDateTime.parse("1980-01-01 00:00:01",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))){
                            contract.setValidTime(maxTime);
                        }
                    }
                    basicInfoService.updateById(contract);
                    // 设置合同履行期限
                    basicInfoService.setExecuteTime(contract.getId());

                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);
                    // 将原履行单保存到新合同下
                    basicInfoService.saveInfoByOldContractId(contract);
                    // 修改变更合同信息状态
                    basicInfoService.saveBasicRelationStatus(contract.getId().toString(), "EXA6000", "EXA11000", "");
                    //推送财务信息给财务系统
                    basicInfoService.selectBasicInfoToFssc(contract.getId());

                    //***************************START:合同签署流程完成之后 自动发起合同履行人接受代办事项***************************
                    ApprovalVoucher executeAcceptVoucher = approvalVoucherService.generateNewVoucher(Long.parseLong(approvalVoucher.getBusinessId()), VoucherTypeEnums.CONTRACT_EXECUTER_ACCEPT);

                    //流程提交参数
                    Map<String, String> executeAriables = new HashMap<>();
                    OrganizationVo sysOrg = new OrganizationVo();
                    sysOrg.setCode(contract.getOrgCode());
                    //获取流程信息
                    QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
                    processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.CONTRACT_EXECUTER_ACCEPT.getCode());
                    Process process = processService.getOne(processQueryWrapper);
                    AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

                    Result executeAcceptResult = bpmService.autoStartAuditProcess( executeAcceptVoucher,  operatorVo, sysOrg, executeVo,process.getProcessDefineKey(), executeAriables);
                    if(executeAcceptResult.isSuccess()){
                        //履行人接受流程发起成功
                    }
                    //***************************END***************************

                    //***************************START:合同签署流程完成之后 自动发起经办人合同扫描件上传待办事项***************************
                    ApprovalVoucher fileUploadVoucher = approvalVoucherService.generateNewVoucher(Long.parseLong(approvalVoucher.getBusinessId()), VoucherTypeEnums.CONTRACT_FILE_UPLOAD);

                    //流程提交参数
                    Map<String, String> fileUploadAriables = new HashMap<>();
                    UserVo systemUser = new UserVo();
                    systemUser.setId("99999999");
                    systemUser.setName("合同发起流程");
                    sysOrg = new OrganizationVo();
                    sysOrg.setCode(contract.getOrgCode());
                    //获取流程信息
                    processQueryWrapper = new QueryWrapper<>();
                    processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.CONTRACT_FILE_UPLOAD.getCode());
                    process = processService.getOne(processQueryWrapper);
                    AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

                    Result fileUploadResult = bpmService.autoStartAuditProcess( fileUploadVoucher,  systemUser, sysOrg, operatorVo,process.getProcessDefineKey(), fileUploadAriables);
                    if(fileUploadResult.isSuccess()){
                        //扫描件上传流程发起成功
                    }
                    //***************************END***************************
                }
            }
        }else if (VoucherTypeEnums.CONTRACT_FILE_UPLOAD.getCode().equals(approvalVoucher.getVoucherType())){//合同扫描件上传
            result = bpmService.auditProcess(actionVO, userVo, organizationVo,processVariables);
            if (result.isSuccess()){
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //只有一个节点 合同扫描件上传成功
                }
            }
        }else if (VoucherTypeEnums.CONTRACT_EXECUTER_ACCEPT.getCode().equals(approvalVoucher.getVoucherType())){//合同履行人接受
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            if("start".equals(actionVO.getTaskKey())){
                AssertUtils.asserts(StringUtil.isEmpty(actionVO.getExecuter()) || StringUtil.isEmpty(actionVO.getExecuterCode()) || StringUtil.isEmpty(actionVO.getExecuterOrg()) || StringUtil.isEmpty(actionVO.getExecuterOrgId()),ContractErrorType.CONTRACT_EXECUTER_NULL);
                contract.setExecuterCode(actionVO.getExecuterCode());
                contract.setExecuter(actionVO.getExecuter());
                contract.setExecuterOrg(actionVO.getExecuterOrg());
                contract.setExecuterOrgId(actionVO.getExecuterOrgId());
                basicInfoService.updateById(contract);
            }
            //履行人
            UserVo executeVo = new UserVo();
            executeVo.setId(contract.getExecuterCode());
            executeVo.setName(contract.getExecuter());

            result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,executeVo,processVariables);
            if(result.isSuccess()){
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //***************************START:合同审批流程完成之后 自动发起合同履行的代办事项***************************
                    //判断合同是否已经提交过,这个地方先默认为不存在
                    //新建一个单据
                    ApprovalVoucher executeVoucher = approvalVoucherService.generateNewVoucher(Long.parseLong(approvalVoucher.getBusinessId()), VoucherTypeEnums.CONTRACT_EXECUTE);
                    //流程提交参数
                    Map<String, String> executeAriables = new HashMap<>();
                    UserVo systemUser = new UserVo();
                    systemUser.setId("99999999");
                    systemUser.setName("合同发起流程");
                    OrganizationVo sysOrg = new OrganizationVo();
                    sysOrg.setCode(contract.getOrgCode());

                    //获取流程信息
                    QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
                    processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.CONTRACT_EXECUTE.getCode());
                    Process process = processService.getOne(processQueryWrapper);
                    AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

                    Result executeResult = bpmService.autoStartAuditProcess( executeVoucher,  systemUser, sysOrg, executeVo,process.getProcessDefineKey(), executeAriables);
                    if (executeResult.isSuccess()) {//流程发起成功
                        //更新合同数据
                        contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                        contract.setExecuteStatue("EXE2000");
                        contract.setStatue("EXA6000");
                        basicInfoService.updateById(contract);
                    }
                    //***************************END***************************
                }
            }
        }else if (VoucherTypeEnums.CONTRACT_EXECUTE.getCode().equals(approvalVoucher.getVoucherType())) {//合同履行
            /**
             * 业务逻辑
             * 1、判断当前审批节点的人需要从审批矩阵获取  还是直接设置下一个节点的处理人
             * 2、合同审批 流程审批
             * 3、判断是否流程是否识审批完成 ，不同的审批节点 审批完成之后 合同状态不一样
             * 4、如果合同审批完成，
             *      ① 更新合同状态为 履行种
             *      ②更新单据状态为审批通过
             *      ③自动发起合同办结的审批流程
             *      ④流程发起成功之后，更新合同履行状态为 办结中
             * */

            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            //履行人
            UserVo executeVo = new UserVo();
            executeVo.setId(contract.getExecuterCode());
            executeVo.setName(contract.getExecuter());
            result = bpmService.auditProcess(actionVO, userVo, organizationVo,processVariables);
            if (result.isSuccess()) {
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                    for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId() + "");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                        if(null == approvalOpinionVo.getId() || !"".equals(approvalOpinionVo)){
                            approvalOpinionService.save(opinion);
                        }else{
                            approvalOpinionService.updateById(opinion);
                        }
                    }
                }
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //更新合同数据  合同定稿完成，然后启动合同打印的流程
                    contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                    contract.setExecuteStatueTime(LocalDateTime.now());
                    contract.setExecuteStatue("EXE1000");
                    contract.setStatueList("STA7000");
                    contract.setIsToFssc("0");//签署完成后将推送财务系统标记修改为0
                    basicInfoService.updateById(contract);
                    //推送财务信息给财务系统
                    iFinanceInfoService.selectFinanceToFssc(contract.getId().toString());

                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);
                }
            }

        } else if (VoucherTypeEnums.CONTRACT_FINISH.getCode().equals(approvalVoucher.getVoucherType())) {//合同办结

            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            //履行人
            UserVo executeVo = new UserVo();
            executeVo.setId(contract.getExecuterCode());
            executeVo.setName(contract.getExecuter());
            result = bpmService.auditProcess(actionVO, userVo, organizationVo,processVariables);

            if (result.isSuccess()) {
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                    for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId() + "");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                        if(null == approvalOpinionVo.getId() || !"".equals(approvalOpinionVo)){
                            approvalOpinionService.save(opinion);
                        }else{
                            approvalOpinionService.updateById(opinion);
                        }
                    }
                }
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //更新合同数据  合同定稿完成，然后启动合同打印的流程
//                    contract = basicInfoService.getById(approvalVoucher.getContractId());
                    contract.setFinishStatueTime(LocalDateTime.now());
                    contract.setFinishStatue("FIN1000");
                    contract.setStatue("EXA8000");
                    contract.setIsToFssc("0");//签署完成后将推送财务系统标记修改为0
                    basicInfoService.updateById(contract);
                    //推送财务信息给财务系统
                    basicInfoService.selectBasicInfoToFssc(contract.getId());
                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);

                    // 修改变更合同信息状态
                    basicInfoService.saveBasicRelationStatus(contract.getId().toString(), "EXA8000", "EXA8000", "EXA8000");
                }
            }

        } else if (VoucherTypeEnums.CONTRACT_OPERATOR_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())) {//经办人移交
            ProcessOperatorTransfer processOperatorTransfer = iProcessOperatorTransferService.getById(approvalVoucher.getBusinessId());

//            UserVo operatorVo = new UserVo();
//            operatorVo.setId(processOperatorTransfer.getOperatorCode());
//            operatorVo.setName(processOperatorTransfer.getOperator());
            //判断是否为一个机构下的人员
            String orgId = processOperatorTransfer.getOrgId();
            String oldOrgId = processOperatorTransfer.getOldOrgId();
            if (orgId != null && !orgId.equals(oldOrgId)){
                processVariables.put("way","0");
            }else{
                processVariables.put("way","1");
            }

            if("out_leader".equals(actionVO.getTaskKey())){
                if( "0".equals(processVariables.get("way"))){
                    // 根据部门id获取审批矩阵领导
                    BpmApprovalMatrixQueryForm bpmApprovalMatrixQueryForm = new BpmApprovalMatrixQueryForm();
                    bpmApprovalMatrixQueryForm.setChargeOrg(orgId);
                    bpmApprovalMatrixQueryForm.setActivityId("instiution");
                    Result<List<BpmApprovalMatrixVo>> resltBpm = bpmApprovalMatrixFeignService.list(bpmApprovalMatrixQueryForm);
                    if (resltBpm.isSuccess()){
                        List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList =  resltBpm.getData();
                        if (bpmApprovalMatrixVoList != null && bpmApprovalMatrixVoList.size() > 0){
                            BpmApprovalMatrixVo bpmApprovalMatrixVo = bpmApprovalMatrixVoList.get(0);
                            UserVo acceptVo = new UserVo();
                            acceptVo.setId(bpmApprovalMatrixVo.getAccountNum());
                            acceptVo.setName(bpmApprovalMatrixVo.getAccountName());
                            result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,acceptVo, processVariables);
                        }else {
                            AssertUtils.asserts(true ,ContractErrorType.NEXT_NODE_OPERATOR_PARAMETER_ERROR);
                        }
                    }else {
                        return resltBpm;
                    }
                }else{
                    UserVo acceptVo = new UserVo();
                    acceptVo.setId(processOperatorTransfer.getNewOperatorCode());
                    acceptVo.setName(processOperatorTransfer.getNewOperator());
                    result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,acceptVo, processVariables);
                }

            }else if("accept_leader".equals(actionVO.getTaskKey())){
                UserVo acceptVo = new UserVo();
                acceptVo.setId(processOperatorTransfer.getNewOperatorCode());
                acceptVo.setName(processOperatorTransfer.getNewOperator());
                result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,acceptVo, processVariables);
            }else{
                result = bpmService.auditProcess(actionVO, userVo, organizationVo, processVariables);
            }
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                    for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId() + "");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                        if(null == approvalOpinionVo.getId() || !"".equals(approvalOpinionVo)){
                            approvalOpinionService.save(opinion);
                        }else{
                            approvalOpinionService.updateById(opinion);
                        }
                    }
                }
                if(null != result.getData() && "审批结束".equals(result.getData())){
                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);
                    //更新经办人移交关系表
                    processOperatorTransfer.setStatue("EXA1000");
                    iProcessOperatorTransferService.updateById(processOperatorTransfer);
                    //更新合同基本信息表
                    //写入原经办人信息
                     String[] split = processOperatorTransfer.getContractId().split(",");
                     if(split.length>0){
                         for(int i=0;i<split.length;i++){
                             String contractId = split[i];
                             BasicInfo basicInfo = basicInfoService.getById(contractId);
                             basicInfo.setOldOperator(processOperatorTransfer.getOperator());
                             basicInfo.setOldOperatorCode(processOperatorTransfer.getOperatorCode());
                             //更新新经办人
                             basicInfo.setOperator(processOperatorTransfer.getNewOperator());
                             basicInfo.setOperatorCode(processOperatorTransfer.getNewOperatorCode());
                             //部门
                             basicInfo.setOrg(processOperatorTransfer.getOrg());
                                     //部门Id
                             basicInfo.setOrgCode(processOperatorTransfer.getOrgId());
                                     //旅行人联系方式
                             basicInfo.setContactNum(processOperatorTransfer.getOperatorInfomation());
                                     //原旅行人联系方式
                             basicInfo.setOldContactNum(processOperatorTransfer.getOldOperatorInfomation());
                             basicInfoService.updateById(basicInfo);
                         }
                     }

                }
            }

        } else if (VoucherTypeEnums.CONTRACT_EXECUTER_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())) {//履行人移交
            ProcessExecuterTransfer processExecuterTransfer = processExecuterTransferService.getById(approvalVoucher.getBusinessId());

            BasicInfo contract = basicInfoService.getById(processExecuterTransfer.getContractId());
//            UserVo operatorVo = new UserVo();
//            operatorVo.setId(contract.getOperatorCode());
//            operatorVo.setName(contract.getOperator());
            //判断接受人和履行人是否为同一个部门
            String orgId = processExecuterTransfer.getOrgId();
            String oldOrgId = processExecuterTransfer.getOldOrgId();
            if (orgId != null && !orgId.equals(oldOrgId)){
                processVariables.put("way","0");
            }else{
                processVariables.put("way","1");
            }

            if("out_leader".equals(actionVO.getTaskKey())){
                if( "0".equals(processVariables.get("way"))){
                    // 根据部门id获取审批矩阵领导
                    BpmApprovalMatrixQueryForm bpmApprovalMatrixQueryForm = new BpmApprovalMatrixQueryForm();
                    bpmApprovalMatrixQueryForm.setChargeOrg(orgId);
                    bpmApprovalMatrixQueryForm.setActivityId("instiution");
                    Result<List<BpmApprovalMatrixVo>> resltBpm = bpmApprovalMatrixFeignService.list(bpmApprovalMatrixQueryForm);
                    if (resltBpm.isSuccess()){
                        List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList =  resltBpm.getData();
                        if (bpmApprovalMatrixVoList != null && bpmApprovalMatrixVoList.size() > 0){
                            BpmApprovalMatrixVo bpmApprovalMatrixVo = bpmApprovalMatrixVoList.get(0);
                            UserVo acceptVo = new UserVo();
                            acceptVo.setId(bpmApprovalMatrixVo.getAccountNum());
                            acceptVo.setName(bpmApprovalMatrixVo.getAccountName());
                            result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,acceptVo, processVariables);
                        }else {
                            AssertUtils.asserts(true ,ContractErrorType.NEXT_NODE_OPERATOR_PARAMETER_ERROR);
                        }
                    }else {
                        return resltBpm;
                    }
                }else{
                    UserVo acceptVo = new UserVo();
                    acceptVo.setId(processExecuterTransfer.getNewExecuterCode());
                    acceptVo.setName(processExecuterTransfer.getNewExecuter());
                    result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,acceptVo, processVariables);
                }

            }else if("accept_leader".equals(actionVO.getTaskKey())){
                UserVo acceptVo = new UserVo();
                acceptVo.setId(processExecuterTransfer.getNewExecuterCode());
                acceptVo.setName(processExecuterTransfer.getNewExecuter());
                result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,acceptVo, processVariables);
            }else{
                result = bpmService.auditProcess(actionVO, userVo, organizationVo, processVariables);
            }


            if (result.isSuccess()) {

                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                    for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId() + "");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                        if(null == approvalOpinionVo.getId() || !"".equals(approvalOpinionVo)){
                            approvalOpinionService.save(opinion);
                        }else{
                            approvalOpinionService.updateById(opinion);
                        }
                    }
                }

                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //更新流程记录表
                    processExecuterTransfer.setStatue("EXA1000");
                    processExecuterTransferService.updateById(processExecuterTransfer);
//                    contract.setStatue("EXA1000");
//                    basicInfoService.updateById(contract);

                    //更新合同的履行人信息
                    //原履行人
                    contract.setOldExecutePerson(processExecuterTransfer.getExecuter());
                    //原履行人Id
                    contract.setOldExecutePersonCode(processExecuterTransfer.getExecuterCode());
                    //履行人
                    contract.setExecuter(processExecuterTransfer.getNewExecuter());
                    //旅行人Id
                    contract.setExecuterCode(processExecuterTransfer.getNewExecuterCode());
                    //部门
                   contract.setExecuterOrg(processExecuterTransfer.getOrg());
                    //部门Id
                   contract.setExecuterOrgId(processExecuterTransfer.getOrgId());
                    //旅行人联系方式
//                    contract.setContactNum(processExecuterTransfer.getExcuterInfomation());
                    //原旅行人联系方式
//                    contract.setOldContactNum(processExecuterTransfer.getOldExcuterInfomation());
                    basicInfoService.updateById(contract);

                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);
                }
            }

        } else if (VoucherTypeEnums.CONTRACT_UNABLE.getCode().equals(approvalVoucher.getVoucherType())) {//合同作废

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
            if(null != bpmApprovalMatrixVoList && bpmApprovalMatrixVoList.size() > 0){
                BpmApprovalMatrixVo bpmApprovalMatrixVo = bpmApprovalMatrixVoList.get(0);
                accepVo.setId(bpmApprovalMatrixVo.getAccountNum());
                accepVo.setName(bpmApprovalMatrixVo.getAccountName());
            }


            BasicInfo basicInfo = basicInfoService.getById(approvalVoucher.getBusinessId());
            /*if("office_leader".equals(actionVO.getTaskKey())){
                AssertUtils.asserts(null ==actionVO.getApprovalType() || "".equals(actionVO.getApprovalType()),ContractErrorType.APPROVAL_TYPE_IS_NULL);
                basicInfo.setApprovalType(actionVO.getApprovalType());
                basicInfoService.updateById(basicInfo);
            }*/
            UserVo opratorVo = new UserVo();
            opratorVo.setId(basicInfo.getOperatorCode());
            opratorVo.setName(basicInfo.getOperator());
//            processVariables.put("approvalWay",actionVO.getApprovalWay());
//            processVariables.put("financeWay",actionVO.getFinanceWay());
//            processVariables.put("dutyWay",actionVO.getDutyWay());
//            result = bpmService.auditProcess(actionVO, userVo, deptVo, processVariables);
            result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, organizationVo,accepVo, processVariables);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                    for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId() + "");
                        ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                        if(null == approvalOpinionVo.getId() || !"".equals(approvalOpinionVo)){
                            approvalOpinionService.save(opinion);
                        }else{
                            approvalOpinionService.updateById(opinion);
                        }
                    }
                }
                if("approval_sec".equals(actionVO.getTaskKey())){
                    basicInfo.setReview("1");
                    basicInfoService.updateById(basicInfo);
                }
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //更新合同基本信息表
                    basicInfo.setStatue("EXA15000");
                    basicInfoService.updateById(basicInfo);
                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.STOP.getCode());
                    approvalVoucherService.updateById(approvalVoucher);
                    //根据合同Id查找合同关联的流程
                    ApprovalVoucherQueryForm approvalVoucherQueryForm = new ApprovalVoucherQueryForm();
                    approvalVoucherQueryForm.setBusinessId(approvalVoucher.getBusinessId());
                    approvalVoucherQueryForm.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
                    List<ApprovalVoucher> approvalVoucherList = approvalVoucherService.selectList(approvalVoucherQueryForm);
                    if(approvalVoucherList.size()>0){
                        for(ApprovalVoucher approvalVoucherCancel:approvalVoucherList){
                            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
                            bpmProcessTaskQueryForm.setObjectId(approvalVoucherCancel.getId()+"");
                            List<BpmProcessTaskVo>  processTask = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm).getData();
                            for(BpmProcessTaskVo bpmProcessTaskVo : processTask){
                                if("待提交".equals(bpmProcessTaskVo.getTaskStauts()) || "待审批".equals(bpmProcessTaskVo.getTaskStauts())||"待重新提交".equals(bpmProcessTaskVo.getTaskStauts())){
                                    TaskNodeActionVO taskNodeActionVO = new TaskNodeActionVO();
                                    taskNodeActionVO.setId(bpmProcessTaskVo.getId().toString());
                                    //取消合同所关联所有流程
                                    cancel(taskNodeActionVO);
                                }

                            }

                        }
                    }
                }
            }
        } else if (VoucherTypeEnums.STANDARD_BOOK.getCode().equals(approvalVoucher.getVoucherType())) {
            //标准文本审批
            StandardTemplate standardTemplate = iStandardTemplateService.getById(approvalVoucher.getBusinessId());
//            result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, deptVo,null, processVariables);
            result = bpmService.auditProcess(actionVO, userVo, organizationVo, processVariables);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                    for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId() + "");
                        ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                        if(null == approvalOpinionVo.getId() || !"".equals(approvalOpinionVo)){
                            approvalOpinionService.save(opinion);
                        }else{
                            approvalOpinionService.updateById(opinion);
                        }
                    }
                }
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //更新合同基本信息表
                    String standardNumber = serialNumberService.getStandardNumber("2");
                    standardTemplate.setStatue("TEMP3000");
                    standardTemplate.setTemplateCode(standardNumber);
                    iStandardTemplateService.updateById(standardTemplate);
                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);
                }
            }
        } else if (VoucherTypeEnums.STANDARD_UNABLE.getCode().equals(approvalVoucher.getVoucherType())) {
            //标准文本失效
            StandardTemplate standardTemplate = iStandardTemplateService.getById(approvalVoucher.getBusinessId());
//            result = bpmService.auditProcessWithOutMatrix(actionVO, userVo, deptVo,null, processVariables);
            result = bpmService.auditProcess(actionVO, userVo, organizationVo, processVariables);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                    for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId() + "");
                        ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                        if(null == approvalOpinionVo.getId() || !"".equals(approvalOpinionVo)){
                            approvalOpinionService.save(opinion);
                        }else{
                            approvalOpinionService.updateById(opinion);
                        }
                    }
                }
                if (null != result.getData() && "审批结束".equals(result.getData())) {
                    //更新合同基本信息表
                    standardTemplate.setStatue("TEMP5000");
                    iStandardTemplateService.updateById(standardTemplate);
                    //更新单据状态
                    approvalVoucher.setVoucherStatus(VoucherStatusEnums.FINISH.getCode());
                    approvalVoucherService.updateById(approvalVoucher);

                    if(null != standardTemplate && null != standardTemplate.getOrgCode() && !"".equals(standardTemplate.getOrgCode())){
                        String[] orgList = standardTemplate.getOrgCode().split(",");
                        for(String org : orgList ){
                            //获取当前机构下的所有人员信息，然后再给对应的人员添加待阅事项
                            List<UserVo> userVoList = userFeignService.getByOrgCodeList(org).getData();
                            for(UserVo acceptVo : userVoList){
                                commonService.sendProcessTask(acceptVo,standardTemplate.getId()+"",VoucherTypeEnums.STANDARD_BOOK_SUCCESS);
                            }
                        }
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
        //查询单据，获取合同id
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        if(VoucherTypeEnums.CONTRACT_BOOK.getCode().equals(approvalVoucher.getVoucherType())){//合同审批流程
            actionVO.setTaskKey("start");//设置驳回的节点，驳回到start节点
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            UserVo acceptVo = new UserVo();
            acceptVo.setId(contract.getOperatorCode());
            acceptVo.setName(contract.getOperator());
            result = bpmService.rejectProcess(actionVO,userVo,acceptVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                //更新合同状态为退回
                contract.setStatue("EXA4000");
                contract.setReview("2");//设置可以再次复审
                basicInfoService.updateById(contract);
                if (!"".equals(contract.getRelationCode()) && !"REL1000".equals(contract.getRelationCode()) && !"REL3000".equals(contract.getRelationCode())){
                    basicInfoService.saveBasicRelationStatusLast(contract.getId());
                    basicInfoService.selectBasicInfoToFssc(contract.getId());
                }
                //更新单据的状态
                /*approvalVoucher.setVoucherStatus(VoucherStatusEnums.BACK.getCode());
                approvalVoucherService.updateById(approvalVoucher);*/

            }
        }else if(VoucherTypeEnums.CONTRACT_SURE.getCode().equals(approvalVoucher.getVoucherType())){//合同定稿

        }else if(VoucherTypeEnums.CONTRACT_PRINT.getCode().equals(approvalVoucher.getVoucherType())){//合同打印

        }else if(VoucherTypeEnums.CONTRACT_SIGN_BOOK.getCode().equals(approvalVoucher.getVoucherType())){//合同签署
            if("we_sign_2".equals(actionVO.getTaskKey())){//设置驳回节点 taskkey
                actionVO.setTaskKey("other_sign_2");
            }else{
                actionVO.setTaskKey("we_sign_1");
            }
            //选择历史驳回的人员信息
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setObjectId(actionVO.getObjectId());
            bpmProcessTaskQueryForm.setTaskKey(actionVO.getTaskKey());
            List<BpmProcessTaskVo> bpmProcessTaskVoList = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm).getData();

            UserVo acceptVo = new UserVo();
            acceptVo.setId(bpmProcessTaskVoList.get(0).getApproverAcount());
            acceptVo.setName(bpmProcessTaskVoList.get(0).getApproverName());

            result = bpmService.rejectProcess(actionVO,userVo,acceptVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                //更新合同状态为退回
                BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                contract.setStatue("EXA4000");
                basicInfoService.updateById(contract);

                //更新单据的状态
                /*approvalVoucher.setVoucherStatus(VoucherStatusEnums.BACK.getCode());
                approvalVoucherService.updateById(approvalVoucher);*/

            }

        }else if(VoucherTypeEnums.CONTRACT_EXECUTER_ACCEPT.getCode().equals(approvalVoucher.getVoucherType())){//履行人接受流程
            actionVO.setTaskKey("start");//设置驳回的节点，驳回到start节点
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            UserVo acceptVo = new UserVo();
            acceptVo.setId(contract.getOperatorCode());
            acceptVo.setName(contract.getOperator());
            result = bpmService.rejectProcess(actionVO,userVo,acceptVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
            }
        }else if(VoucherTypeEnums.CONTRACT_EXECUTE.getCode().equals(approvalVoucher.getVoucherType())){//合同履行
            actionVO.setTaskKey("execute");//设置驳回的节点，驳回到execute节点
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            UserVo acceptVo = new UserVo();
            acceptVo.setId(contract.getExecuterCode());
            acceptVo.setName(contract.getExecuter());
            result = bpmService.rejectProcess(actionVO,userVo,acceptVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                //更新单据的状态
               /* approvalVoucher.setVoucherStatus(VoucherStatusEnums.BACK.getCode());
                approvalVoucherService.updateById(approvalVoucher);*/

            }


        }else if(VoucherTypeEnums.CONTRACT_FINISH.getCode().equals(approvalVoucher.getVoucherType())){//合同办结
            actionVO.setTaskKey("start");//设置驳回的节点，驳回到start节点
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            UserVo acceptVo = new UserVo();
            acceptVo.setId(contract.getExecuterCode());
            acceptVo.setName(contract.getExecuter());
            result = bpmService.rejectProcess(actionVO,userVo,acceptVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                basicInfoService.saveBasicRelationStatus(contract.getId().toString(), "EXA6000", "EXA6000", "EXA6000");
                basicInfoService.selectBasicInfoToFssc(contract.getId());
                //更新单据的状态
              /*  approvalVoucher.setVoucherStatus(VoucherStatusEnums.BACK.getCode());
                approvalVoucherService.updateById(approvalVoucher);*/

            }

        }else if(VoucherTypeEnums.CONTRACT_UNABLE.getCode().equals(approvalVoucher.getVoucherType())){//合同作废
            actionVO.setTaskKey("start");//设置驳回的节点，驳回到start节点
            BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
            UserVo acceptVo = new UserVo();
            acceptVo.setId(contract.getOperatorCode());
            acceptVo.setName(contract.getOperator());
            result = bpmService.rejectProcess(actionVO,userVo,acceptVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                contract.setReview("2");//设置可以再次复审
                basicInfoService.updateById(contract);
                //更新单据的状态
                /*approvalVoucher.setVoucherStatus(VoucherStatusEnums.BACK.getCode());
                approvalVoucherService.updateById(approvalVoucher);*/
            }
        }else if(VoucherTypeEnums.CONTRACT_OPERATOR_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())){//经办人移交

        }else if(VoucherTypeEnums.CONTRACT_EXECUTER_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())){//履行人移交

        }else if(VoucherTypeEnums.STANDARD_BOOK.getCode().equals(approvalVoucher.getVoucherType())){
            // 标准文本审批驳回
            actionVO.setTaskKey("start");//设置驳回的节点，驳回到start节点
            StandardTemplate standardTemplate = iStandardTemplateService.getById(approvalVoucher.getBusinessId());
            UserVo acceptVo = new UserVo();
            acceptVo.setId(standardTemplate.getCreateBy());
            acceptVo.setName(standardTemplate.getCreateByName());
            result = bpmService.rejectProcess(actionVO,userVo,acceptVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
//                standardTemplate.setStatue("TEMP1000");
                iStandardTemplateService.updateById(standardTemplate);
            }
        }else if(VoucherTypeEnums.STANDARD_UNABLE.getCode().equals(approvalVoucher.getVoucherType())){
            // 标准文本审批驳回
            actionVO.setTaskKey("start");//设置驳回的节点，驳回到start节点
            StandardTemplate standardTemplate = iStandardTemplateService.getById(approvalVoucher.getBusinessId());
            UserVo acceptVo = new UserVo();
            acceptVo.setId(standardTemplate.getCreateBy());
            acceptVo.setName(standardTemplate.getCreateByName());
            result = bpmService.rejectProcess(actionVO,userVo,acceptVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                //更新合同状态为退回
//                standardTemplate.setStatue("TEMP1000");
                iStandardTemplateService.updateById(standardTemplate);
            }
        }
        return result;
    }

    @ApiOperation(value = "查询待办", notes = "查询待办")
    @PostMapping("/upcoming")
    public Result searchBackLog(@Valid @RequestBody BaseQueryForm pageForm) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        return bpmService.searchBackLog(pageForm,userVo) ;
    }

    @ApiOperation(value = "查询已办", notes = "查询已办")
    @PostMapping("/hasDone")
    public Result hasDone(@Valid @RequestBody BaseQueryForm pageForm) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        return bpmService.searchDone(pageForm,userVo) ;
    }

    @ApiOperation(value = "流程转办", notes = "流程转办")
    @PostMapping("/turnToProcess")
    public Result turnToProcess(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        return bpmService.turnToProcess(actionVO,userVo,organizationVo,actionVO.getProcessDefineKey()) ;
    }

    @ApiOperation(value = "1-1-2查询合同的待办/已办 详情", notes = "1-1-2查询合同的待办/已办 详情")
    @PostMapping("/detail")
    public Result detail(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        supplyTaskNodeActionVO(actionVO);
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
//        Result result = commonService.getCanjump(approvalVoucher);
        //根据合同类型判断审批页面
        if(VoucherTypeEnums.CONTRACT_BOOK.getCode().equals(approvalVoucher.getVoucherType())){//合同审批流程
            return commonService.getContractByProcessId(actionVO.getId());
        }else if(VoucherTypeEnums.CONTRACT_SURE.getCode().equals(approvalVoucher.getVoucherType())){//合同定稿
            return commonService.getContractByProcessId(actionVO.getId());
        }else if(VoucherTypeEnums.CONTRACT_PRINT.getCode().equals(approvalVoucher.getVoucherType())){//合同打印
            return commonService.getContractByProcessId(actionVO.getId());
        }else if(VoucherTypeEnums.CONTRACT_SIGN_BOOK.getCode().equals(approvalVoucher.getVoucherType())){//合同签署
            return commonService.getContractByProcessId(actionVO.getId());
        }else if(VoucherTypeEnums.CONTRACT_FILE_UPLOAD.getCode().equals(approvalVoucher.getVoucherType())){//合同扫描件上传
            return commonService.getContractByProcessId(actionVO.getId());
        }else if(VoucherTypeEnums.CONTRACT_EXECUTER_ACCEPT.getCode().equals(approvalVoucher.getVoucherType())){//履行人接受流程
            return commonService.getContractByProcessId(actionVO.getId());
        }else if(VoucherTypeEnums.CONTRACT_EXECUTE.getCode().equals(approvalVoucher.getVoucherType())){//合同履行
            return commonService.getContractByProcessId(actionVO.getId());
        }else if(VoucherTypeEnums.CONTRACT_FINISH.getCode().equals(approvalVoucher.getVoucherType())){//合同办结
            return commonService.getContractByProcessId(actionVO.getId());
        }else if(VoucherTypeEnums.CONTRACT_UNABLE.getCode().equals(approvalVoucher.getVoucherType())){//合同作废
            return commonService.getContractByProcessId(actionVO.getId());
        }else if(VoucherTypeEnums.CONTRACT_OPERATOR_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())){//经办人移交
//            BpmProcessTaskVo bpmProcessTaskVo = bpmProcessTaskFeignService.get(Long.parseLong(actionVO.getId())).getData();
            ProcessOperatorTransfer processOperatorTransfer = iProcessOperatorTransferService.getById(approvalVoucher.getBusinessId());
            ProcessOperatorTransferVo processOperatorTransferVo = new BeanUtils<ProcessOperatorTransferVo>().copyObj(processOperatorTransfer,ProcessOperatorTransferVo.class);
            String[] str = processOperatorTransfer.getContractId().split(",");
            List<BasicInfoVo> listBasicInfoVo = new ArrayList<>();
            if(str.length>0){
                for(int i=0;i<str.length;i++) {
                    BasicInfo basicInfo = basicInfoService.getById(str[i]);
                    List<BasicSubjectMap> basicSubjectMapList = basicInfoService.selectBasicSubjectByContractId(Long.parseLong(str[i]));
                    List<BasicSubjectMapVo> basicSubjectMapVoList = new BeanUtils<BasicSubjectMapVo>().copyObjs(basicSubjectMapList, BasicSubjectMapVo.class);
                    BasicInfoVo basicInfoVo1 = new BeanUtils<BasicInfoVo>().copyObj(basicInfo, BasicInfoVo.class);
                    basicInfoVo1.setBasicSubjectList((ArrayList<BasicSubjectMapVo>) basicSubjectMapVoList);
                    listBasicInfoVo.add(basicInfoVo1);
                }
                processOperatorTransferVo.setListBasicInfoVo(listBasicInfoVo);
            }
            //获取审批单据 ，页面只显示 合同审批 流程的审批节点和审批意见
            QueryWrapper<ApprovalVoucher> voucherWrapper = new QueryWrapper<>();
            voucherWrapper.eq(ApprovalVoucher.BUSINESS_ID,approvalVoucher.getBusinessId());
            voucherWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,"5");
            ApprovalVoucher approval = approvalVoucherService.getOne(voucherWrapper);
            if(null != approvalVoucher ){
                //获取审批节点
                BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
                bpmProcessTaskQueryForm.setObjectId(approvalVoucher.getId()+"");
                Result<List<BpmProcessTaskVo>>  resultProcessTaskVo = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
                List<BpmProcessTaskVo> bpmProcessTaskVoList= resultProcessTaskVo.getData();
                List<ContractProcessTaskVo> processTaskVoList=new BeanUtils<ContractProcessTaskVo>().copyObjs(bpmProcessTaskVoList,ContractProcessTaskVo.class);

                //获取每个审批节点的审批信息
                for(int i = 0 ; i < processTaskVoList.size() ; i++){
                    QueryWrapper<ApprovalOpinion> opionWrapper = new QueryWrapper<>();
                    opionWrapper.eq(ApprovalOpinion.PROCESS_TASK_ID,processTaskVoList.get(i).getTaskId());
                    List<ApprovalOpinion> approvalOpinionList = approvalOpinionService.list(opionWrapper);
                    List<ApprovalOpinionVo> approvalOpinionVoList=new BeanUtils<ApprovalOpinionVo>().copyObjs(approvalOpinionList,ApprovalOpinionVo.class);
                    processTaskVoList.get(i).setApprovalOpinionVoList(approvalOpinionVoList);
                }
                processOperatorTransferVo.setProcessTaskVoList(processTaskVoList);

                //判断单据是否可以撤回
                BpmProcessTaskQueryWrapper wrapper=new BpmProcessTaskQueryWrapper();
                Map<String,String[]> queryMap=new HashMap<>();
                String[] taskStauts={"notIn","待提交,待审批,待重新提交"};
                String[] objectId={"eq",approvalVoucher.getId()+""};
                queryMap.put(BpmProcessTaskQueryForm.TASK_STAUTS,taskStauts);
                queryMap.put(BpmProcessTaskQueryForm.OBJECT_ID,objectId);
                wrapper.setWrapperMap(queryMap);
                Result<GdcPage<BpmProcessTaskVo>> taskVopage=bpmProcessTaskFeignService.searchByWrapper(wrapper);
                if(taskVopage.getData().getTotal() > 1){
                    processOperatorTransferVo.setCanRollBack("0");
                }else{
                    processOperatorTransferVo.setCanRollBack("1");
                }
            }
            return new Result<ProcessOperatorTransferVo>().sucess(processOperatorTransferVo);

        }else if(VoucherTypeEnums.CONTRACT_EXECUTER_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())){//履行人移交
            ProcessExecuterTransfer processExecuterTransfer = processExecuterTransferService.getById(approvalVoucher.getBusinessId());
            ProcessExecuterTransferVo processExecuterTransferVo = new BeanUtils<ProcessExecuterTransferVo>().copyObj(processExecuterTransfer,ProcessExecuterTransferVo.class);
//            BasicInfo basicInfo = basicInfoService.selectContractInfoById(processExecuterTransfer.getContractId());
            BasicInfo basicInfo = basicInfoService.getById(processExecuterTransfer.getContractId());
            BasicInfoVo basicInfoVo=new BeanUtils<BasicInfoVo>().copyObj(basicInfo, BasicInfoVo.class);
            //查询合同签约主体信息
            List<BasicSubjectMap> basicSubjectMapList = basicInfoService.selectBasicSubjectByContractId(processExecuterTransfer.getContractId());
            List<BasicSubjectMapVo> basicSubjectMapVoList=new BeanUtils<BasicSubjectMapVo>().copyObjs(basicSubjectMapList,BasicSubjectMapVo.class);
//            for (int i=0; i < basicSubjectMapVoList.size(); i++) {
//                BasicSubjectMapVo basicSubjectMapVo = basicSubjectMapVoList.get(i);
//                SysSignSubjectInfo sysSignSubjectInfo = sysSignSubjectInfoService.selectObjectById(basicSubjectMapVo.getSubjectId());
//                SysSignSubjectInfoVo sysSignSubjectInfoVo =new BeanUtils<SysSignSubjectInfoVo>().copyObj(sysSignSubjectInfo, SysSignSubjectInfoVo.class);
//                basicSubjectMapVo.setSysSignSubjectInfoVo(sysSignSubjectInfoVo);
//                basicSubjectMapVoList.set(i, basicSubjectMapVo);
//            }
            basicInfoVo.setBasicSubjectList((ArrayList<BasicSubjectMapVo>)basicSubjectMapVoList);
            //关联合同
            basicInfoVo = basicInfoService.queryCorrelationBasic(basicInfoVo);
            processExecuterTransferVo.setBasicInfo(basicInfoVo);
            //获取审批单据 ，页面只显示 合同审批 流程的审批节点和审批意见
            QueryWrapper<ApprovalVoucher> voucherWrapper = new QueryWrapper<>();
            voucherWrapper.eq(ApprovalVoucher.BUSINESS_ID,approvalVoucher.getBusinessId());
            voucherWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,"6");
            ApprovalVoucher approval = approvalVoucherService.getOne(voucherWrapper);
            if(null != approvalVoucher ){
                //获取审批节点
                BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
                bpmProcessTaskQueryForm.setObjectId(approvalVoucher.getId()+"");
                Result<List<BpmProcessTaskVo>>  resultProcessTaskVo = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
                List<BpmProcessTaskVo> bpmProcessTaskVoList= resultProcessTaskVo.getData();
                List<ContractProcessTaskVo> processTaskVoList=new BeanUtils<ContractProcessTaskVo>().copyObjs(bpmProcessTaskVoList,ContractProcessTaskVo.class);

                //获取每个审批节点的审批信息
                for(int i = 0 ; i < processTaskVoList.size() ; i++){
                    QueryWrapper<ApprovalOpinion> opionWrapper = new QueryWrapper<>();
                    opionWrapper.eq(ApprovalOpinion.PROCESS_TASK_ID,processTaskVoList.get(i).getTaskId());
                    List<ApprovalOpinion> approvalOpinionList = approvalOpinionService.list(opionWrapper);
                    List<ApprovalOpinionVo> approvalOpinionVoList=new BeanUtils<ApprovalOpinionVo>().copyObjs(approvalOpinionList,ApprovalOpinionVo.class);
                    processTaskVoList.get(i).setApprovalOpinionVoList(approvalOpinionVoList);
                }
                processExecuterTransferVo.setProcessTaskVoList(processTaskVoList);

                //判断单据是否可以撤回
                BpmProcessTaskQueryWrapper wrapper=new BpmProcessTaskQueryWrapper();
                Map<String,String[]> queryMap=new HashMap<>();
                String[] taskStauts={"notIn","待提交,待审批,待重新提交"};
                String[] objectId={"eq",approvalVoucher.getId()+""};
                queryMap.put(BpmProcessTaskQueryForm.TASK_STAUTS,taskStauts);
                queryMap.put(BpmProcessTaskQueryForm.OBJECT_ID,objectId);
                wrapper.setWrapperMap(queryMap);
                Result<GdcPage<BpmProcessTaskVo>> taskVopage=bpmProcessTaskFeignService.searchByWrapper(wrapper);
                if(taskVopage.getData().getTotal() > 1){
                    processExecuterTransferVo.setCanRollBack("0");
                }else{
                    processExecuterTransferVo.setCanRollBack("1");
                }
            }
            return new Result<ProcessExecuterTransferVo>().sucess(processExecuterTransferVo);

        }else if(VoucherTypeEnums.STANDARD_BOOK.getCode().equals(approvalVoucher.getVoucherType())) {
            //标准文本审批
            StandardTemplate standardTemplate = iStandardTemplateService.getById(approvalVoucher.getBusinessId());
            StandardTemplateVo standardTemplateVo = new BeanUtils<StandardTemplateVo>().copyObj(standardTemplate,StandardTemplateVo.class);

            //获取审批节点
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setObjectId(approvalVoucher.getId()+"");
            Result<List<BpmProcessTaskVo>> resultProcessTaskVo = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
            List<BpmProcessTaskVo> bpmProcessTaskVoList= resultProcessTaskVo.getData();
            List<ContractProcessTaskVo> processTaskVoList=new BeanUtils<ContractProcessTaskVo>().copyObjs(bpmProcessTaskVoList,ContractProcessTaskVo.class);

            //获取每个审批节点的审批信息
            for(int i = 0 ; i < processTaskVoList.size() ; i++){
                QueryWrapper<ApprovalOpinion> opionWrapper = new QueryWrapper<>();
                opionWrapper.eq(ApprovalOpinion.PROCESS_TASK_ID,processTaskVoList.get(i).getTaskId());
                List<ApprovalOpinion> approvalOpinionList = approvalOpinionService.list(opionWrapper);
                List<ApprovalOpinionVo> approvalOpinionVoList=new BeanUtils<ApprovalOpinionVo>().copyObjs(approvalOpinionList,ApprovalOpinionVo.class);
                processTaskVoList.get(i).setApprovalOpinionVoList(approvalOpinionVoList);
            }
            standardTemplateVo.setProcessTaskVoList(processTaskVoList);
            return new Result<StandardTemplateVo>().sucess(standardTemplateVo);
        }else if(VoucherTypeEnums.STANDARD_UNABLE.getCode().equals(approvalVoucher.getVoucherType())) {
            //标准文本失效
            StandardTemplate standardTemplate = iStandardTemplateService.getById(approvalVoucher.getBusinessId());
            StandardTemplateVo standardTemplateVo = new BeanUtils<StandardTemplateVo>().copyObj(standardTemplate,StandardTemplateVo.class);

            //获取审批节点
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setObjectId(approvalVoucher.getId()+"");
            Result<List<BpmProcessTaskVo>> resultProcessTaskVo = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
            List<BpmProcessTaskVo> bpmProcessTaskVoList= resultProcessTaskVo.getData();
            List<ContractProcessTaskVo> processTaskVoList=new BeanUtils<ContractProcessTaskVo>().copyObjs(bpmProcessTaskVoList,ContractProcessTaskVo.class);

            //获取每个审批节点的审批信息
            for(int i = 0 ; i < processTaskVoList.size() ; i++){
                QueryWrapper<ApprovalOpinion> opionWrapper = new QueryWrapper<>();
                opionWrapper.eq(ApprovalOpinion.PROCESS_TASK_ID,processTaskVoList.get(i).getTaskId());
                List<ApprovalOpinion> approvalOpinionList = approvalOpinionService.list(opionWrapper);
                List<ApprovalOpinionVo> approvalOpinionVoList=new BeanUtils<ApprovalOpinionVo>().copyObjs(approvalOpinionList,ApprovalOpinionVo.class);
                processTaskVoList.get(i).setApprovalOpinionVoList(approvalOpinionVoList);
            }
            standardTemplateVo.setProcessTaskVoList(processTaskVoList);
            return new Result<StandardTemplateVo>().sucess(standardTemplateVo);
        }
        return Result.fail(ContractErrorType.DATA_IS_NULL);
    }


    @ApiOperation(value = "1-1-10待阅 已阅的详情", notes = "1-1-10待阅 已阅的详情")
    @PostMapping("/detailRead")
    public Result detailRead(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        supplySendProcessTaskNodeActionVO(actionVO);
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        if(VoucherTypeEnums.STANDARD_BOOK_SUCCESS.getCode().equals(approvalVoucher.getVoucherType())) {
            //标准文本详情
            StandardTemplate standardTemplate = iStandardTemplateService.getById(approvalVoucher.getBusinessId());
            StandardTemplateVo standardTemplateVo = new BeanUtils<StandardTemplateVo>().copyObj(standardTemplate,StandardTemplateVo.class);
            return new Result<StandardTemplateVo>().sucess(standardTemplateVo);
        }else if(VoucherTypeEnums.CONTRACT_CONTRACT_EXECUTE.getCode().equals(approvalVoucher.getVoucherType())){
            Long contractId = Long.parseLong(approvalVoucher.getBusinessId());
            BasicInfo basicInfo = basicInfoService.selectContractInfoById(contractId);
            BasicInfoVo basicInfoVo=new BeanUtils<BasicInfoVo>().copyObj(basicInfo, BasicInfoVo.class);
            //查询合同签约主体信息
            List<BasicSubjectMap> basicSubjectMapList = basicInfoService.selectBasicSubjectByContractId(contractId);
            List<BasicSubjectMapVo> basicSubjectMapVoList=new BeanUtils<BasicSubjectMapVo>().copyObjs(basicSubjectMapList,BasicSubjectMapVo.class);
            basicInfoVo.setBasicSubjectList((ArrayList<BasicSubjectMapVo>)basicSubjectMapVoList);
            //旅行人信息
            UserVo userVoExe = basicInfoService.getUserById(basicInfoVo.getExecuterCode());
            //关联合同
            basicInfoVo = basicInfoService.queryCorrelationBasic(basicInfoVo);
            //财务信息
            List<FinanceInfoVo> financeInfo = iFinanceInfoService.selectFinanceInfo(contractId.toString());
            //项目信息
            List<SysProjectInfoVo> sysProjectInfoVo = sysProjectInfoService.querySysProjectInfo(contractId.toString());
            //其他监控计划
            List<MonitorInfoVo> monitorInfoVo = monitorInfoService.queryMonitorInfo(contractId.toString());
            //订单信息
            List<OrderInfoVo> orderInfoVo = orderInfoService.selectListByContractId(contractId.toString());
            //收/发票信息
            List<TicketInfoVo> ticketInfoVo = ticketInfoService.selectListByContractId(contractId.toString());
            basicInfoVo.setListFinanceInfoVo(financeInfo);
            basicInfoVo.setListMonitorInfoVo(monitorInfoVo);
            basicInfoVo.setListSysProjectInfoVo(sysProjectInfoVo);
            basicInfoVo.setExecuterUserVo(userVoExe);
            basicInfoVo.setOrderInfoVo(orderInfoVo);
            basicInfoVo.setTicketInfoVo(ticketInfoVo);
            return new Result<BasicInfoVo>().sucess(basicInfoVo);
        }
        return Result.fail(ContractErrorType.DATA_IS_NULL);
    }

    @ApiOperation(value = "1-1-1查询待办（新）", notes = "1-1-1查询待办（新）")
    @PostMapping("/backLog")
    public Result backLog(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        return bpmService.backLog(processTaskQueryForm,userVo) ;
    }

    @ApiOperation(value = "1-2-1查询已办（新）", notes = "1-2-1查询已办（新）")
    @PostMapping("/done")
    public Result done(@Valid @RequestBody ProcessTaskQueryForm processTaskQueryForm) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        return bpmService.done(processTaskQueryForm,userVo) ;
    }

    @ApiOperation(value = "1-2-2撤回到经办人", notes = "1-2-2撤回到经办人")
    @PostMapping("/rollBackProcess")
    public Result rollBackProcess(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        supplyTaskNodeActionVO(actionVO);
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        Result result = new Result();
        //根据合同类型判断审批页面
        if(VoucherTypeEnums.CONTRACT_BOOK.getCode().equals(approvalVoucher.getVoucherType())){//合同审批流程
            result =  bpmService.rollBackProcess(actionVO,userVo) ;
            if(result.isSuccess()){
                //更新合同状态为撤回
                BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                contract.setStatue("EXA4000");
                basicInfoService.updateById(contract);

                //更新单据的状态 合同撤回也当做是审批中的状态
               /* approvalVoucher.setVoucherStatus(VoucherStatusEnums.ROLLBACK.getCode());
                approvalVoucherService.updateById(approvalVoucher);*/
            }

        }else if(VoucherTypeEnums.CONTRACT_SURE.getCode().equals(approvalVoucher.getVoucherType())){//合同定稿

        }else if(VoucherTypeEnums.CONTRACT_PRINT.getCode().equals(approvalVoucher.getVoucherType())){//合同打印

        }else if(VoucherTypeEnums.CONTRACT_SIGN_BOOK.getCode().equals(approvalVoucher.getVoucherType())){//合同签署

        }else if(VoucherTypeEnums.CONTRACT_EXECUTE.getCode().equals(approvalVoucher.getVoucherType())){//合同履行

        }else if(VoucherTypeEnums.CONTRACT_FINISH.getCode().equals(approvalVoucher.getVoucherType())){//合同办结

        }else if(VoucherTypeEnums.CONTRACT_UNABLE.getCode().equals(approvalVoucher.getVoucherType())){//合同作废

        }else if(VoucherTypeEnums.CONTRACT_OPERATOR_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())){//经办人移交

        }else if(VoucherTypeEnums.CONTRACT_EXECUTER_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())){//履行人移交

        }
        return result;
    }

    @ApiOperation(value = "1-1-5经办人取消合同/流程作废", notes = "1-1-5经办人取消合同/流程作废")
    @PostMapping("/cancel")
    public Result cancel(@Valid @RequestBody TaskNodeActionVO actionVO) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        supplyTaskNodeActionVO(actionVO);
        Result result = new Result();
        //查询单据，获取合同id
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        if(VoucherTypeEnums.CONTRACT_BOOK.getCode().equals(approvalVoucher.getVoucherType())){//合同审批流程
            result = bpmService.cancel(actionVO,userVo) ;
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                //更新合同状态为取消
                BasicInfo contract = basicInfoService.getById(approvalVoucher.getBusinessId());
                contract.setStatue("EXA5000");
                basicInfoService.updateById(contract);

                //更新单据的状态
                approvalVoucher.setVoucherStatus(VoucherStatusEnums.STOP.getCode());
                approvalVoucherService.updateById(approvalVoucher);

                // 将对应老合同状态变回 EXA6000
                basicInfoService.saveBasicOldContractStatus(contract.getId().toString());
                basicInfoService.selectBasicInfoToFssc(contract.getId());
            }
        }else if(VoucherTypeEnums.CONTRACT_SURE.getCode().equals(approvalVoucher.getVoucherType())){//合同定稿

        }else if(VoucherTypeEnums.CONTRACT_PRINT.getCode().equals(approvalVoucher.getVoucherType())){//合同打印

        }else if(VoucherTypeEnums.CONTRACT_SIGN_BOOK.getCode().equals(approvalVoucher.getVoucherType())){//合同签署

        }else if(VoucherTypeEnums.CONTRACT_EXECUTE.getCode().equals(approvalVoucher.getVoucherType())){//合同履行

        }else if(VoucherTypeEnums.CONTRACT_FINISH.getCode().equals(approvalVoucher.getVoucherType())){//合同办结
            BasicInfo basicInfo = basicInfoService.getById(approvalVoucher.getBusinessId());
            result = bpmService.cancel(actionVO,userVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                basicInfo.setStatue("EXA6000");
                basicInfoService.updateById(basicInfo);
                //更新单据的状态
                approvalVoucher.setVoucherStatus(VoucherStatusEnums.STOP.getCode());
                approvalVoucherService.updateById(approvalVoucher);

            }
        }else if(VoucherTypeEnums.CONTRACT_UNABLE.getCode().equals(approvalVoucher.getVoucherType())){//合同作废
            BasicInfo basicInfo = basicInfoService.getById(approvalVoucher.getBusinessId());
            result = bpmService.cancel(actionVO,userVo);
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                basicInfo.setStatue(basicInfo.getSpareField2());
                basicInfoService.updateById(basicInfo);
                //更新单据的状态
                approvalVoucher.setVoucherStatus(VoucherStatusEnums.STOP.getCode());
                approvalVoucherService.updateById(approvalVoucher);

            }
        }else if(VoucherTypeEnums.CONTRACT_OPERATOR_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())){//经办人移交
            ProcessOperatorTransfer processOperatorTransfer = iProcessOperatorTransferService.getById(approvalVoucher.getBusinessId());
            result = bpmService.cancel(actionVO,userVo) ;
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                processOperatorTransfer.setStatue("EXA15000");
                iProcessOperatorTransferService.updateById(processOperatorTransfer);
                //更新单据的状态
                approvalVoucher.setVoucherStatus(VoucherStatusEnums.STOP.getCode());
                approvalVoucherService.updateById(approvalVoucher);

            }
        }else if(VoucherTypeEnums.CONTRACT_EXECUTER_TRANSFER.getCode().equals(approvalVoucher.getVoucherType())){//履行人移交
            ProcessExecuterTransfer processExecuterTransfer = processExecuterTransferService.getById(approvalVoucher.getBusinessId());
            result = bpmService.cancel(actionVO,userVo) ;
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                processExecuterTransfer.setStatue("EXA15000");
                processExecuterTransferService.updateById(processExecuterTransfer);
                //更新单据的状态
                approvalVoucher.setVoucherStatus(VoucherStatusEnums.STOP.getCode());
                approvalVoucherService.updateById(approvalVoucher);

            }
        }else if(VoucherTypeEnums.STANDARD_BOOK.getCode().equals(approvalVoucher.getVoucherType())){
            //标准文本审批
            result = bpmService.cancel(actionVO,userVo) ;
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                StandardTemplate standardTemplate = iStandardTemplateService.getById(approvalVoucher.getBusinessId());
                standardTemplate.setStatue("TEMP1000");
                iStandardTemplateService.updateById(standardTemplate);

                //更新单据的状态
                approvalVoucher.setVoucherStatus(VoucherStatusEnums.STOP.getCode());
                approvalVoucherService.updateById(approvalVoucher);
            }
        }else if(VoucherTypeEnums.STANDARD_UNABLE.getCode().equals(approvalVoucher.getVoucherType())){
            //标准文本失效
            result = bpmService.cancel(actionVO,userVo) ;
            if(result.isSuccess()){
                //保存审批意见
                List<ApprovalOpinionVo> ApprovalOpinionList = actionVO.getApprovalOpinionList();
                if(null != ApprovalOpinionList && ApprovalOpinionList.size() > 0){
                    for(ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList){
                        approvalOpinionVo.setContractId(approvalVoucher.getBusinessId()+"");
                        approvalOpinionVo.setProcessTaskId(actionVO.getTaskId()+"");
                        approvalOpinionVo.setIsUsed("1");
                        approvalOpinionVo.setCreateTime(LocalDateTime.now());
                        approvalOpinionVo.setCreateBy(userVo.getId());
                        ApprovalOpinion opinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo,ApprovalOpinion.class);
                        approvalOpinionService.save(opinion);
                    }
                }
                StandardTemplate standardTemplate = iStandardTemplateService.getById(approvalVoucher.getBusinessId());
                standardTemplate.setStatue("TEMP3000");
                iStandardTemplateService.updateById(standardTemplate);

                //更新单据的状态
                approvalVoucher.setVoucherStatus(VoucherStatusEnums.STOP.getCode());
                approvalVoucherService.updateById(approvalVoucher);
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
    public  void supplySendProcessTaskNodeActionVO(TaskNodeActionVO actionVO){
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


    @ApiOperation(value = "1-1-6待阅列表", notes = "1-1-6待阅列表")
    @PostMapping("/noRead")
    public Result<GdcPage<SendProcessTaskVo>> noRead(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        sendProcessTaskQueryForm.setApproverAcount(userVo.getId());
        sendProcessTaskQueryForm.setTaskStauts("待阅");
        sendProcessTaskQueryForm.setSourceSystem("CONTRACT");
        return carbonCopyService.search(sendProcessTaskQueryForm);
    }

    @ApiOperation(value = "1-1-7已阅列表", notes = "1-1-7已阅列表")
    @PostMapping("/hasRead")
    public Result<GdcPage<SendProcessTaskVo>> hasRead(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm) {
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo =commonService.getOrganization();
        sendProcessTaskQueryForm.setApproverAcount(userVo.getId());
        sendProcessTaskQueryForm.setTaskStauts("已阅");
        sendProcessTaskQueryForm.setSourceSystem("CONTRACT");
        return carbonCopyService.search(sendProcessTaskQueryForm);
    }

    @ApiOperation(value = "1-1-8发送一条待阅", notes = "1-1-8发送一条待阅")
    @PostMapping("/beNoRead")
    public Result beNoRead(@Valid @RequestBody SendProcessTaskForm sendProcessTaskForm) {
        Result<UserVo>  result = userFeignService.get(Long.parseLong(sendProcessTaskForm.getApproverAcount()));
        if(result.isSuccess()){
            //给某个id 发送一条待阅
            UserVo userVo = result.getData();
            Result result1 = commonService.sendProcessTask(userVo,"1130778117188239362",VoucherTypeEnums.STANDARD_BOOK_SUCCESS);
            return result1;
        }
        return result;
    }

}

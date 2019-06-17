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
import com.deloitte.platform.api.contract.vo.ProcessTaskForm;
import com.deloitte.platform.api.contract.vo.TaskNodeActionVO;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.common.util.StringUtil;
import com.deloitte.services.contract.entity.ApprovalVoucher;
import com.deloitte.services.contract.entity.BasicInfo;
import com.deloitte.services.contract.entity.ProcessTask;
import com.deloitte.services.contract.service.IApprovalVoucherService;
import com.deloitte.services.contract.service.IBPMService;
import com.deloitte.services.contract.service.ICommonService;
import com.deloitte.services.contract.service.IProcessTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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
public class BPMServiceImpl implements IBPMService {

    @Autowired
    private IApprovalVoucherService approvalVoucherService;

    @Autowired
    private IProcessTaskService processTaskService;

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;

    @Autowired
    private BpmApprovalMatrixFeignService bpmApprovalMatrixFeignService;

    @Autowired
    private ICommonService commonService;

    @Value("${contract.audit.url:http://124.17.100.183:30080/contract/index.html#/loading}")
    private String CONTRACT_AUDIT_URL;

    //发起流程
    @Override
    @Transactional
    public Result startAuditProcess(ApprovalVoucher voucher, UserVo userVo, OrganizationVo organizationVo, String processDefineKey, Map<String,String> processVariables) {
        if (voucher == null){
            throw new ServiceException(ContractErrorType.VOUCHER_IS_NULL);
        }
        BpmProcessOperatorFormStart startForm = new BpmProcessOperatorFormStart();
        BpmProcessTaskFormStart processConfig = new BpmProcessTaskFormStart();
        processConfig.setProcessDefineKey(processDefineKey);
        processConfig.setSubmitterCode(userVo.getId());//提交人id
        processConfig.setSubmitterName(userVo.getName());//提交人姓名
        processConfig.setSubmitterStation(userVo.getHonor());//提交人岗位
        processConfig.setSubmitterOrg(organizationVo.getCode());//提交人岗位
        processConfig.setObjectId(String.valueOf(voucher.getId()));//单据id
        processConfig.setObjectOrderNum(voucher.getBizNumber());//单据编号
        processConfig.setObjectType(voucher.getVoucherType());//单据类型
        processConfig.setObjectDescription(voucher.getVoucherName());//单据描述
        processConfig.setSourceSystem("CONTRACT");//单据类型
        ArrayList<NextNodeParamVo> nextNodeParamVo = this.getNextNodeParamVo(voucher, organizationVo.getCode(), processDefineKey, "start",processVariables);
        startForm.setBpmProcessTaskForm(processConfig);
        startForm.setNextNodeParamVo(nextNodeParamVo);
        startForm.setProcessVariables(processVariables);
        log.info("[BPM]bpmOperatorFeignService.startProcess req:{}", JSONObject.toJSONString(startForm));
        Result result = bpmOperatorFeignService.startProcess(startForm);
        log.info("[BPM]bpmOperatorFeignService.startProcess rsp:{}", JSONObject.toJSONString(result));
        getAllProcessTask(String.valueOf(voucher.getId()));
        return result;
    }


    //自动发起流程，参数设置流程发起人 和流程接受人
    @Override
    @Transactional
    public Result autoStartAuditProcess(ApprovalVoucher voucher, UserVo userVo, OrganizationVo organizationVo, UserVo acceptVo,String processDefineKey,Map<String,String> processVariables) {
        if (voucher == null){
            throw new ServiceException(ContractErrorType.VOUCHER_IS_NULL);
        }
        BpmProcessOperatorFormStart startForm = new BpmProcessOperatorFormStart();
        BpmProcessTaskFormStart processConfig = new BpmProcessTaskFormStart();
        processConfig.setProcessDefineKey(processDefineKey);
        processConfig.setSubmitterCode(userVo.getId());//提交人id
        processConfig.setSubmitterName(userVo.getName());//提交人姓名
        processConfig.setSubmitterStation(userVo.getHonor());//提交人岗位
        processConfig.setSubmitterOrg(organizationVo.getCode());//提交人岗位
        processConfig.setObjectId(String.valueOf(voucher.getId()));//单据id
        processConfig.setObjectOrderNum(voucher.getBizNumber());//单据编号
        processConfig.setObjectType(voucher.getVoucherType());//单据类型
        processConfig.setObjectDescription(voucher.getVoucherName());//单据描述
        processConfig.setSourceSystem("CONTRACT");//单据类型
//        ArrayList<NextNodeParamVo> nextNodeParamVo = this.getNextNodeParamVo(voucher, deptVo.getDeptCode(), processDefineKey, "start");
        ArrayList<NextNodeParamVo> nextNodeParamVo = new ArrayList<>();
        NextNodeParamVo nodeParamVo = new NextNodeParamVo();
        nodeParamVo.setAcountId(acceptVo.getId());
        nodeParamVo.setAcountName(acceptVo.getName());
        nodeParamVo.setObjectUrl(CONTRACT_AUDIT_URL+"?returnSystem=contract");
        nextNodeParamVo.add(nodeParamVo);

        startForm.setBpmProcessTaskForm(processConfig);
        startForm.setNextNodeParamVo(nextNodeParamVo);
        startForm.setProcessVariables(processVariables);
        log.info("[BPM]bpmOperatorFeignService.startProcess req:{}", JSONObject.toJSONString(startForm));
        Result result = bpmOperatorFeignService.startProcess(startForm);
        log.info("[BPM]bpmOperatorFeignService.startProcess rsp:{}", JSONObject.toJSONString(result));
        getAllProcessTask(String.valueOf(voucher.getId()));
        return result;
    }

    //审批通过
    @Override
    public Result auditProcess(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo,Map<String,String> processVariables) {
        ApprovalVoucher voucher = approvalVoucherService.getById(actionVO.getObjectId());
        //查询下一个节点审核者
        ArrayList<NextNodeParamVo> nextNodeParamVoList = this.getNextNodeParamVo(voucher,organizationVo.getCode(), actionVO.getProcessDefineKey(), actionVO.getTaskId(),processVariables);
        BpmProcessOperatorApprove approveReq = new BpmProcessOperatorApprove();
        BpmProcessTaskFormApprove taskPrarm = new BpmProcessTaskFormApprove();
        taskPrarm.setId(Long.parseLong(actionVO.getId()));
        //设置objectDescription
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        taskPrarm.setObjectDescription(approvalVoucher.getVoucherName());

        approveReq.setBpmProcessTaskForm(taskPrarm);
        approveReq.setNextNodeParamVo(nextNodeParamVoList);
        approveReq.setProcessVariables(processVariables);
        log.info("[BPM]bpmOperatorFeignService.approveProcess req:{}", JSONObject.toJSONString(approveReq));
        Result result = bpmOperatorFeignService.approveProcess(approveReq);
        log.info("[BPM]bpmOperatorFeignService.approveProcess rsp:{}", JSONObject.toJSONString(result));
        getAllProcessTask(actionVO.getObjectId());
        return result;
    }
    //审批通过
    @Override
    public Result auditProcessWithOutMatrix(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo,UserVo acceptVo,Map<String,String> processVariables) {
        ApprovalVoucher voucher = approvalVoucherService.getById(actionVO.getObjectId());

        ArrayList<NextNodeParamVo> nextNodeParamVo = new ArrayList<>();
        NextNodeParamVo nodeParamVo = new NextNodeParamVo();
        nodeParamVo.setAcountId(acceptVo.getId());
        nodeParamVo.setAcountName(acceptVo.getName());
        nodeParamVo.setObjectUrl(CONTRACT_AUDIT_URL+"?returnSystem=contract");
        nextNodeParamVo.add(nodeParamVo);

        BpmProcessOperatorApprove approveReq = new BpmProcessOperatorApprove();
        BpmProcessTaskFormApprove taskPrarm = new BpmProcessTaskFormApprove();
        taskPrarm.setId(Long.parseLong(actionVO.getId()));
        //设置objectDescription
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        taskPrarm.setObjectDescription(approvalVoucher.getVoucherName());

        approveReq.setBpmProcessTaskForm(taskPrarm);
        approveReq.setNextNodeParamVo(nextNodeParamVo);
        approveReq.setProcessVariables(processVariables);
        log.info("[BPM]bpmOperatorFeignService.approveProcess req:{}", JSONObject.toJSONString(approveReq));
        Result result = bpmOperatorFeignService.approveProcess(approveReq);
        log.info("[BPM]bpmOperatorFeignService.approveProcess rsp:{}", JSONObject.toJSONString(result));
        getAllProcessTask(actionVO.getObjectId());
        return result;
    }

    //审批通过
    @Override
    public Result auditProcessWithOutMatrix(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo,ArrayList<NextNodeParamVo> nextNodeParamVoList,Map<String,String> processVariables) {
        ApprovalVoucher voucher = approvalVoucherService.getById(actionVO.getObjectId());
        if(null != nextNodeParamVoList && nextNodeParamVoList.size() > 0 ){
            for(NextNodeParamVo nextNodeParamVo:nextNodeParamVoList){
                nextNodeParamVo.setObjectUrl(CONTRACT_AUDIT_URL+"?returnSystem=contract");
            }
        }
        if (null == nextNodeParamVoList || nextNodeParamVoList.size() ==0 ){
            nextNodeParamVoList = new ArrayList<>();
            nextNodeParamVoList.add(new NextNodeParamVo());
        }
        BpmProcessOperatorApprove approveReq = new BpmProcessOperatorApprove();
        BpmProcessTaskFormApprove taskPrarm = new BpmProcessTaskFormApprove();
        taskPrarm.setId(Long.parseLong(actionVO.getId()));
        //设置objectDescription
        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        taskPrarm.setObjectDescription(approvalVoucher.getVoucherName());

        approveReq.setBpmProcessTaskForm(taskPrarm);
        approveReq.setNextNodeParamVo(nextNodeParamVoList);
        approveReq.setProcessVariables(processVariables);
        log.info("[BPM]bpmOperatorFeignService.approveProcess req:{}", JSONObject.toJSONString(approveReq));
        Result result = bpmOperatorFeignService.approveProcess(approveReq);
        log.info("[BPM]bpmOperatorFeignService.approveProcess rsp:{}", JSONObject.toJSONString(result));
        getAllProcessTask(actionVO.getObjectId());
        return result;
    }

    @Override
    public Result rejectProcess(TaskNodeActionVO actionVO, UserVo userVo,UserVo acceptVo) {
        BpmProcessOperatorApprove reject = new BpmProcessOperatorApprove();
        BpmProcessTaskVo taskVo = getTaskVoById(Long.parseLong(actionVO.getId()));

        BpmProcessTaskFormApprove formApprove = new BpmProcessTaskFormApprove();
        formApprove.setId(Long.parseLong(taskVo.getId()));
        formApprove.setOpinion(actionVO.getOpinion());
        formApprove.setObjectDescription(taskVo.getObjectDescription());

        reject.setBpmProcessTaskForm(formApprove);
        ArrayList<NextNodeParamVo> list = new ArrayList<>();
        NextNodeParamVo paramVo = new NextNodeParamVo();

        paramVo.setAcountId(acceptVo.getId()+"");
        paramVo.setAcountName(acceptVo.getName());
        paramVo.setObjectUrl(CONTRACT_AUDIT_URL+"?returnSystem=contract");
//        paramVo.setStationId(acceptVo.getPositionTitle());
        list.add(paramVo);
        reject.setNextNodeParamVo(list);
        log.info("[BPM]bpmOperatorFeignService.rejectToFirstProcess req:{}", JSON.toJSONString(reject));
        Result result = bpmOperatorFeignService.rejectProcess(actionVO.getTaskKey(),reject);
        log.info("[BPM]bpmOperatorFeignService.rejectToFirstProcess rsp:{}", JSON.toJSONString(result));
        AssertUtils.asserts(result.isFail(), ContractErrorType.APPROVE_PASS_FIALD);

        getAllProcessTask(actionVO.getObjectId());
        return result;
    }

    @Override
    public Result<GdcPage<BpmProcessTaskVo>> searchBackLog(BaseQueryForm pageForm,UserVo userVo) {
        BpmConductListQueryForm bpmConductListQueryForm = new BpmConductListQueryForm();
        bpmConductListQueryForm.setSourceSystem("CONTRACT");
        bpmConductListQueryForm.setApproverAcount(userVo.getId());
        bpmConductListQueryForm.setCurrentPage(pageForm.getCurrentPage());
        bpmConductListQueryForm.setPageSize(pageForm.getPageSize());
        return bpmProcessTaskFeignService.searchBackLog(bpmConductListQueryForm);
    }

    @Override
    public Result<GdcPage<BpmProcessTaskVo>> searchDone(BaseQueryForm pageForm,UserVo userVo) {
        BpmConductListQueryForm bpmConductListQueryForm = new BpmConductListQueryForm();
        bpmConductListQueryForm.setSourceSystem("CONTRACT");
        bpmConductListQueryForm.setApproverAcount(userVo.getId());
        bpmConductListQueryForm.setCurrentPage(pageForm.getCurrentPage());
        bpmConductListQueryForm.setPageSize(pageForm.getPageSize());
        return bpmProcessTaskFeignService.searchDone(bpmConductListQueryForm);
    }

    @Override
    public Result turnToProcess(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo,String processDefineKey) {
        BpmProcessOperatorApprove bpmProcessOperatorApprove = new BpmProcessOperatorApprove();

        BpmProcessTaskVo taskVo = getTaskVoById(Long.parseLong(actionVO.getId()));

        BpmProcessTaskFormApprove formApprove = new BpmProcessTaskFormApprove();
        formApprove.setId(Long.parseLong(taskVo.getId()));
        formApprove.setOpinion(actionVO.getOpinion());
        formApprove.setObjectDescription(taskVo.getObjectDescription());

        bpmProcessOperatorApprove.setBpmProcessTaskForm(formApprove);
        ArrayList<NextNodeParamVo> list = new ArrayList<>();
        NextNodeParamVo paramVo = new NextNodeParamVo();
        // 转办人为
        paramVo.setAcountId(userVo.getId()+"");
        paramVo.setAcountName(userVo.getName());
        paramVo.setStationId(userVo.getPositionTitle());
        paramVo.setObjectUrl(CONTRACT_AUDIT_URL+"?returnSystem=contract");
        list.add(paramVo);
        bpmProcessOperatorApprove.setNextNodeParamVo(list);
        log.info("[BPM]bpmOperatorFeignService.turnToProcess req:{}", JSON.toJSONString(bpmProcessOperatorApprove));
         Result result = bpmOperatorFeignService.turnToProcess(bpmProcessOperatorApprove);
        log.info("[BPM]bpmOperatorFeignService.turnToProcess req:{}", JSON.toJSONString(result));
        return result;

    }

    /**
     * 获取下一个节点审核者
     * @param voucher
     * @param deptCode
     * @param processDefineKey
     * @param taskId
     * @return ArrayList<NextNodeParamVo>
     */
    private ArrayList<NextNodeParamVo> getNextNodeParamVo(ApprovalVoucher voucher, String deptCode, String processDefineKey, String taskId,Map<String,String>  processVariables){
        ArrayList<NextNodeParamVo> nextList = new ArrayList<>();
        BpmApprovalMatrixQueryFormForApproval param = new BpmApprovalMatrixQueryFormForApproval();
        String bizType = null;
        bizType = voucher.getVoucherType();
        param.setChargeBusiness(bizType);
        param.setChargeOrg(voucher.getOrgCode());
        param.setProcessDefineKey(processDefineKey);
        param.setTaskID(taskId);
        param.setProcessVariables(processVariables);

        log.info("[BPM]bpmApprovalMatrixFeignService.findNextApprover req:{}", JSONObject.toJSONString(param));
        Result<List<BpmApprovalMatrixVo>> result = bpmApprovalMatrixFeignService.findNextApprover(param);
        log.info("[BPM]bpmApprovalMatrixFeignService.findNextApprover rsp:{}", JSONObject.toJSONString(result));
        if (result != null && result.isSuccess() && result.getData() != null){
            List<BpmApprovalMatrixVo> matrixVoList = result.getData();
            for (BpmApprovalMatrixVo matrixVo: matrixVoList){
                NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                nodeParamVo.setAcountId(matrixVo.getAccountNum());
                nodeParamVo.setAcountName(matrixVo.getAccountName());
                nodeParamVo.setObjectUrl(CONTRACT_AUDIT_URL+"?returnSystem=contract");
                nextList.add(nodeParamVo);
            }
        }
        //如果没有审批人 加一个空对象
        if (nextList.size() == 0){
            nextList.add(new NextNodeParamVo());
        }
        return nextList;
    }
    /**
     * 获取任务vo
     * @param id
     * @return BpmProcessTaskVo
     */
    private BpmProcessTaskVo getTaskVoById(Long id) {
        Result<BpmProcessTaskVo> taskVoResult = bpmProcessTaskFeignService.get(id);
        AssertUtils.asserts(taskVoResult.isFail(), ContractErrorType.GET_TASK_FAILD);
        return taskVoResult.getData();
    }
    /***
     * 根据单据id 获取审批节点的信息
     * @param objectId
     */
    private void getAllProcessTask(String objectId){
        //清除actionVO.getObjectId()所有审批节点
        QueryWrapper<ProcessTask> queryWrapper = new QueryWrapper <ProcessTask>();
        queryWrapper.eq(ProcessTask.OBJECT_ID,objectId);
        processTaskService.remove(queryWrapper);

        ApprovalVoucher approvalVoucher = approvalVoucherService.getById(objectId);

        //新增actionVO.getObjectId()所有审批节点
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setObjectId(objectId);
        List<BpmProcessTaskVo>  taskList = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm).getData();
        for(BpmProcessTaskVo taskVo : taskList){
            ProcessTask task =new BeanUtils<ProcessTask>().copyObj(taskVo,ProcessTask.class);
            task.setId(Long.parseLong(taskVo.getId()));
            task.setContractId(approvalVoucher.getBusinessId()+"");
            processTaskService.save(task);
        }
    }

    @Override
    public Result<GdcPage<BpmProcessTaskVo>> backLog(ProcessTaskQueryForm processTaskQueryForm, UserVo userVo) {

        BpmProcessTaskQueryWrapper wrapper=new BpmProcessTaskQueryWrapper();

        Map<String,String[]> queryMap=new HashMap<>();
        if(null != processTaskQueryForm.getSpareField4() && !"".equals(processTaskQueryForm.getSpareField4())){
            String[] createTime={"between",processTaskQueryForm.getSpareField4()+","+processTaskQueryForm.getUpdateTime()};
            queryMap.put(BpmProcessTaskQueryForm.CREATE_TIME,createTime);
        }
        if(null != processTaskQueryForm.getObjectDescription() && !"".equals(processTaskQueryForm.getObjectDescription())){
            String[] objectDescription={"like",processTaskQueryForm.getObjectDescription()};
            queryMap.put(BpmProcessTaskQueryForm.OBJECT_DESCRIPTION,objectDescription);
        }
        if(null != processTaskQueryForm.getObjectType() && !"".equals(processTaskQueryForm.getObjectType())){
            String[] objectType={"eq",processTaskQueryForm.getObjectType()};
            queryMap.put(BpmProcessTaskQueryForm.OBJECT_TYPE,objectType);
        }
        if(null != processTaskQueryForm.getObjectId() && !"".equals(processTaskQueryForm.getObjectId())){
            String[] objectId={"eq",processTaskQueryForm.getObjectId()};
            queryMap.put(BpmProcessTaskQueryForm.OBJECT_ID,objectId);
        }
        if(null != processTaskQueryForm.getProcessInstanceId() && !"".equals(processTaskQueryForm.getProcessInstanceId())){
            String[] processInstanceId={"eq",processTaskQueryForm.getProcessInstanceId()};
            queryMap.put(BpmProcessTaskQueryForm.PROCESS_INSTANCE_ID,processInstanceId);
        }
        if(null != userVo){
            String[] approverAcount={"eq", userVo.getId()};
            queryMap.put(BpmProcessTaskQueryForm.APPROVER_ACOUNT,approverAcount);
        }
        String[] taskStauts={"in","待提交,待审批,待重新提交"};
        String[] sourceSystem={"eq","CONTRACT"};
        queryMap.put(BpmProcessTaskQueryForm.TASK_STAUTS,taskStauts);
        queryMap.put(BpmProcessTaskQueryForm.SOURCE_SYSTEM,sourceSystem);
        wrapper.setWrapperMap(queryMap);
        wrapper.setCurrentPage(processTaskQueryForm.getCurrentPage());
        wrapper.setPageSize(processTaskQueryForm.getPageSize());

        Result<GdcPage<BpmProcessTaskVo>> taskVopage=bpmProcessTaskFeignService.searchByWrapper(wrapper);

        return taskVopage;
    }
    @Override
    public Result<GdcPage<BpmProcessTaskVo>> done(ProcessTaskQueryForm processTaskQueryForm, UserVo userVo) {
        BpmProcessTaskQueryWrapper wrapper=new BpmProcessTaskQueryWrapper();

        Map<String,String[]> queryMap=new HashMap<>();
        if(null != processTaskQueryForm.getSpareField4() && !"".equals(processTaskQueryForm.getSpareField4())){
            String[] createTime={"between",processTaskQueryForm.getSpareField4()+","+processTaskQueryForm.getUpdateTime()};
            queryMap.put(BpmProcessTaskQueryForm.CREATE_TIME,createTime);
        }
        if(null != processTaskQueryForm.getObjectDescription() && !"".equals(processTaskQueryForm.getObjectDescription())){
            String[] objectDescription={"like",processTaskQueryForm.getObjectDescription()};
            queryMap.put(BpmProcessTaskQueryForm.OBJECT_DESCRIPTION,objectDescription);
        }
        if(null != processTaskQueryForm.getObjectType() && !"".equals(processTaskQueryForm.getObjectType())){
            String[] objectType={"eq",processTaskQueryForm.getObjectType()};
            queryMap.put(BpmProcessTaskQueryForm.OBJECT_TYPE,objectType);
        }
        String[] approverAcount={"eq", userVo.getId()};
        String[] taskStauts={"notIn","待提交,待审批,待重新提交"};
        String[] sourceSystem={"eq","CONTRACT"};
        queryMap.put(BpmProcessTaskQueryForm.APPROVER_ACOUNT,approverAcount);
        queryMap.put(BpmProcessTaskQueryForm.TASK_STAUTS,taskStauts);
        queryMap.put(BpmProcessTaskQueryForm.SOURCE_SYSTEM,sourceSystem);
        wrapper.setWrapperMap(queryMap);
        wrapper.setCurrentPage(processTaskQueryForm.getCurrentPage());
        wrapper.setPageSize(processTaskQueryForm.getPageSize());

        Result<GdcPage<BpmProcessTaskVo>> taskVopage=bpmProcessTaskFeignService.searchByWrapper(wrapper);

        for(BpmProcessTaskVo bpmProcessTaskVo:taskVopage.getData()){
            if("start".equals(bpmProcessTaskVo.getTaskKey()) && "已提交".equals(bpmProcessTaskVo.getTaskStauts()) && VoucherTypeEnums.CONTRACT_BOOK.getCode().equals(bpmProcessTaskVo.getObjectType())){
                bpmProcessTaskVo.setObjectDescription(bpmProcessTaskVo.getObjectDescription().replace("【"+VoucherTypeEnums.CONTRACT_BOOK.getValue()+"】","【合同提交】"));
            }
            if(!"".equals(bpmProcessTaskVo.getProcessInstanceId())){
                ProcessTaskQueryForm backLogQueryForm = new ProcessTaskQueryForm();
                backLogQueryForm.setProcessInstanceId(bpmProcessTaskVo.getProcessInstanceId());
                backLogQueryForm.setObjectId(bpmProcessTaskVo.getObjectId());
                Result<GdcPage<BpmProcessTaskVo>> backLog = backLog(backLogQueryForm,null);
                if(backLog.isSuccess() && backLog.getData().getContent().size() > 0 ){
                    bpmProcessTaskVo.setTaskName(backLog.getData().getContent().get(0).getTaskName());
                }else{
                    bpmProcessTaskVo.setTaskName("审批结束");
                }
            }
        }
        return taskVopage;
    }

    @Override
    public Result rollBackProcess(TaskNodeActionVO actionVO, UserVo userVo){
        BpmProcessOperatorApprove approve = new BpmProcessOperatorApprove();

        BpmProcessTaskQueryForm bpmProcessTaskQueryForm =
                new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setTaskKey("start");
        bpmProcessTaskQueryForm.setProcessInstanceId(actionVO.getProcessInstanceId());
        Result<List<BpmProcessTaskVo>> taskResult = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
        AssertUtils.asserts(!taskResult.isSuccess(),ContractErrorType.PROCESS_TASK_IS_NULL);
        BpmProcessTaskVo taskVo = taskResult.getData().get(0);
        //撤回审批到经办人
        ArrayList<NextNodeParamVo> paramVoArrayList = new ArrayList<>();
        NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
        nextNodeParamVo.setAcountId(userVo.getId());
        nextNodeParamVo.setAcountName(userVo.getName());
        nextNodeParamVo.setApproverDescription(userVo.getPositionTitle());
        nextNodeParamVo.setStationId(userVo.getHonor());
        nextNodeParamVo.setObjectUrl(CONTRACT_AUDIT_URL+"?returnSystem=contract");
        paramVoArrayList.add(nextNodeParamVo);
        //构造主要参数
        BpmProcessTaskFormApprove formApprove = new BpmProcessTaskFormApprove();
        formApprove.setId(Long.parseLong(taskVo.getId()));
        formApprove.setOpinion("撤回流程");
        formApprove.setObjectDescription(taskVo.getObjectDescription());

        approve.setBpmProcessTaskForm(formApprove);
        approve.setNextNodeParamVo(paramVoArrayList);

        log.info("[BPM]bpmOperatorFeignService.rollBackProcess req:{}", JSONObject.toJSONString(approve));
        Result result = bpmOperatorFeignService.rollBackProcess(approve);
        log.info("[BPM]bpmOperatorFeignService.rollBackProcess req:{}", JSON.toJSONString(result));
        return result ;
    }


    @Override
    public Result cancel(TaskNodeActionVO actionVO, UserVo userVo){
        BpmProcessTaskFormApprove taskReq = new BpmProcessTaskFormApprove();
        taskReq.setId(Long.parseLong(actionVO.getId()));
        taskReq.setOpinion(actionVO.getOpinion());
        log.info("[BPM]bpmOperatorFeignService.stopProcess req:{}", JSONObject.toJSONString(taskReq));
        Result result = bpmOperatorFeignService.stopProcess(taskReq);
        log.info("[BPM]bpmOperatorFeignService.stopProcess rsp:{}", JSONObject.toJSONString(result));
        return result ;
    }

    @Override
    public Result findNextNodeList(TaskNodeActionVO actionVO,Map<String,String> processVariables){
        BpmTaskNextNodeForm bpmTaskNextNodeForm = new BpmTaskNextNodeForm();
        bpmTaskNextNodeForm.setProcessInstanceId(actionVO.getProcessInstanceId());
        bpmTaskNextNodeForm.setTaskId(actionVO.getTaskId());
        bpmTaskNextNodeForm.setProcessVariables(processVariables);
        bpmTaskNextNodeForm.setProcessDefId(actionVO.getProcessDefineKey());
        log.info("[BPM]bpmOperatorFeignService.findNextNodeList req:{}", JSONObject.toJSONString(bpmTaskNextNodeForm));
        Result result =bpmOperatorFeignService.findNextNodeList(bpmTaskNextNodeForm);
        log.info("[BPM]bpmOperatorFeignService.findNextNodeList rsp:{}", JSONObject.toJSONString(result));
       return  result;
    }
}


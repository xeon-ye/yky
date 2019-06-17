package com.deloitte.services.project.service.impl;

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
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.project.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.project.vo.ProcessTaskApprovalVo;
import com.deloitte.platform.api.project.vo.TaskNodeActionVO;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.entity.ApprovalVouchers;
import com.deloitte.services.project.service.IApprovalVouchersService;
import com.deloitte.services.project.service.IBPMService;
import com.deloitte.services.project.service.ICommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class BPMServiceImpl implements IBPMService {

    @Autowired
    private IApprovalVouchersService approvalVoucherService;


    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;

    @Autowired
    private BpmApprovalMatrixFeignService bpmApprovalMatrixFeignService;

    @Autowired
    private ICommonService commonService;

    @Value("${project.audit.url:www.baidu.com}")
    private String PROJECT_AUDIT_URL;

    //发起流程
    @Override
    @Transactional
    public Result startAuditProcess(ApprovalVouchers voucher, UserVo userVo, OrganizationVo organizationVo, String processDefineKey, Map<String,String> processVariables) {
        if (voucher == null){
            throw new ServiceException(ProjectErrorType.VOUCHER_IS_NULL);
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
        processConfig.setSourceSystem("PROJECT");//单据类型
        ArrayList<NextNodeParamVo> nextNodeParamVo = this.getNextNodeParamVo(voucher,  processDefineKey, "start",processVariables);
        startForm.setBpmProcessTaskForm(processConfig);
        startForm.setNextNodeParamVo(nextNodeParamVo);
        startForm.setProcessVariables(processVariables);
        log.info("[BPM]bpmOperatorFeignService.startProcess req:{}", JSONObject.toJSONString(startForm));
        Result result = bpmOperatorFeignService.startProcess(startForm);
        log.info("[BPM]bpmOperatorFeignService.startProcess rsp:{}", JSONObject.toJSONString(result));
        return result;
    }


    //自动发起流程，参数设置流程发起人 和流程接受人
    @Override
    @Transactional
    public Result autoStartAuditProcess(ApprovalVouchers voucher, UserVo userVo, OrganizationVo organizationVo, UserVo acceptVo, String processDefineKey, Map<String,String> processVariables) {
        if (voucher == null){
            throw new ServiceException(ProjectErrorType.VOUCHER_IS_NULL);
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
        processConfig.setSourceSystem("PROJECT");//单据类型
//        ArrayList<NextNodeParamVo> nextNodeParamVo = this.getNextNodeParamVo(voucher, organizationVo.getDeptCode(), processDefineKey, "start");
        ArrayList<NextNodeParamVo> nextNodeParamVo = new ArrayList<>();
        NextNodeParamVo nodeParamVo = new NextNodeParamVo();
        nodeParamVo.setAcountId(acceptVo.getId());
        nodeParamVo.setAcountName(acceptVo.getName());
        nodeParamVo.setObjectUrl(PROJECT_AUDIT_URL+"?returnSystem=project");
        nextNodeParamVo.add(nodeParamVo);

        startForm.setBpmProcessTaskForm(processConfig);
        startForm.setNextNodeParamVo(nextNodeParamVo);
        startForm.setProcessVariables(processVariables);
        log.info("[BPM]bpmOperatorFeignService.startProcess req:{}", JSONObject.toJSONString(startForm));
        Result result = bpmOperatorFeignService.startProcess(startForm);
        log.info("[BPM]bpmOperatorFeignService.startProcess rsp:{}", JSONObject.toJSONString(result));
        return result;
    }

    //审批通过
    @Override
    public Result auditProcess(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo,Map<String,String> processVariables) {
        ApprovalVouchers voucher = approvalVoucherService.getById(actionVO.getObjectId());
        //查询下一个节点审核者
        ArrayList<NextNodeParamVo> nextNodeParamVoList = this.getNextNodeParamVo(voucher, actionVO.getProcessDefineKey(), actionVO.getTaskId(),processVariables);
        BpmProcessOperatorApprove approveReq = new BpmProcessOperatorApprove();
        BpmProcessTaskFormApprove taskPrarm = new BpmProcessTaskFormApprove();
        taskPrarm.setId(Long.parseLong(actionVO.getId()));
        //设置objectDescription
        ApprovalVouchers approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        taskPrarm.setObjectDescription(approvalVoucher.getVoucherName());
        taskPrarm.setOpinion(actionVO.getOpinion());//审批意见
        approveReq.setBpmProcessTaskForm(taskPrarm);
        approveReq.setNextNodeParamVo(nextNodeParamVoList);
        approveReq.setProcessVariables(processVariables);
        log.info("[BPM]bpmOperatorFeignService.approveProcess req:{}", JSONObject.toJSONString(approveReq));
        Result result = bpmOperatorFeignService.approveProcess(approveReq);
        log.info("[BPM]bpmOperatorFeignService.approveProcess rsp:{}", JSONObject.toJSONString(result));
        return result;
    }
    //审批通过
    @Override
    public Result auditProcessWithOutMatrix(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo,UserVo acceptVo,Map<String,String> processVariables) {
        ApprovalVouchers voucher = approvalVoucherService.getById(actionVO.getObjectId());

        ArrayList<NextNodeParamVo> nextNodeParamVo = new ArrayList<>();
        NextNodeParamVo nodeParamVo = new NextNodeParamVo();
        nodeParamVo.setAcountId(acceptVo.getId());
        nodeParamVo.setAcountName(acceptVo.getName());
        nodeParamVo.setObjectUrl(PROJECT_AUDIT_URL+"?returnSystem=project");
        nextNodeParamVo.add(nodeParamVo);

        BpmProcessOperatorApprove approveReq = new BpmProcessOperatorApprove();
        BpmProcessTaskFormApprove taskPrarm = new BpmProcessTaskFormApprove();
        taskPrarm.setId(Long.parseLong(actionVO.getId()));
        //设置objectDescription
        ApprovalVouchers approvalVoucher = approvalVoucherService.getById(actionVO.getObjectId());
        taskPrarm.setObjectDescription(approvalVoucher.getVoucherName());

        approveReq.setBpmProcessTaskForm(taskPrarm);
        approveReq.setNextNodeParamVo(nextNodeParamVo);
        approveReq.setProcessVariables(processVariables);
        log.info("[BPM]bpmOperatorFeignService.approveProcess req:{}", JSONObject.toJSONString(approveReq));
        Result result = bpmOperatorFeignService.approveProcess(approveReq);
        log.info("[BPM]bpmOperatorFeignService.approveProcess rsp:{}", JSONObject.toJSONString(result));
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
        paramVo.setObjectUrl(PROJECT_AUDIT_URL+"?returnSystem=project");
//        paramVo.setStationId(acceptVo.getPositionTitle());
        list.add(paramVo);
        reject.setNextNodeParamVo(list);
        log.info("[BPM]bpmOperatorFeignService.rejectToFirstProcess req:{}", JSON.toJSONString(reject));
        Result result = bpmOperatorFeignService.rejectProcess(actionVO.getTaskKey(),reject);
        log.info("[BPM]bpmOperatorFeignService.rejectToFirstProcess rsp:{}", JSON.toJSONString(result));
        AssertUtils.asserts(result.isFail(), ProjectErrorType.APPROVE_PASS_FIALD);
        return result;
    }

    @Override
    public Result<GdcPage<BpmProcessTaskVo>> searchBackLog(BaseQueryForm pageForm,UserVo userVo) {
        BpmConductListQueryForm bpmConductListQueryForm = new BpmConductListQueryForm();
        bpmConductListQueryForm.setSourceSystem("PROJECT");
        bpmConductListQueryForm.setApproverAcount(userVo.getId());
        bpmConductListQueryForm.setCurrentPage(pageForm.getCurrentPage());
        bpmConductListQueryForm.setPageSize(pageForm.getPageSize());
        return bpmProcessTaskFeignService.searchBackLog(bpmConductListQueryForm);
    }

    @Override
    public Result<GdcPage<BpmProcessTaskVo>> searchDone(BaseQueryForm pageForm,UserVo userVo) {
        BpmConductListQueryForm bpmConductListQueryForm = new BpmConductListQueryForm();
        bpmConductListQueryForm.setSourceSystem("PROJECT");
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
        paramVo.setObjectUrl(PROJECT_AUDIT_URL+"?returnSystem=project");
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
     * @param processDefineKey
     * @param taskId
     * @return ArrayList<NextNodeParamVo>
     */
    private ArrayList<NextNodeParamVo> getNextNodeParamVo(ApprovalVouchers voucher, String processDefineKey, String taskId,Map<String,String>  processVariables){
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
                nodeParamVo.setObjectUrl(PROJECT_AUDIT_URL+"?returnSystem=project");
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
        AssertUtils.asserts(taskVoResult.isFail(), ProjectErrorType.GET_TASK_FAILD);
        return taskVoResult.getData();
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
        String[] sourceSystem={"eq","PROJECT"};
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
        if(null != processTaskQueryForm.getObjectId() && !"".equals(processTaskQueryForm.getObjectId())){
            String[] objectId={"eq",processTaskQueryForm.getObjectId()};
            queryMap.put(BpmProcessTaskQueryForm.OBJECT_ID,objectId);
        }
        if(null != userVo){
            String[] approverAcount={"eq", userVo.getId()};
            queryMap.put(BpmProcessTaskQueryForm.APPROVER_ACOUNT,approverAcount);
        }
        String[] taskStauts={"notIn","待提交,待审批,待重新提交"};
        String[] sourceSystem={"eq","PROJECT"};
        queryMap.put(BpmProcessTaskQueryForm.TASK_STAUTS,taskStauts);
        queryMap.put(BpmProcessTaskQueryForm.SOURCE_SYSTEM,sourceSystem);
        wrapper.setWrapperMap(queryMap);
        wrapper.setCurrentPage(processTaskQueryForm.getCurrentPage());
        wrapper.setPageSize(processTaskQueryForm.getPageSize());

        Result<GdcPage<BpmProcessTaskVo>> taskVopage=bpmProcessTaskFeignService.searchByWrapper(wrapper);
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
        AssertUtils.asserts(!taskResult.isSuccess(),ProjectErrorType.PROCESS_TASK_IS_NULL);
        BpmProcessTaskVo taskVo = taskResult.getData().get(0);
        //撤回审批到流程创建人
        ArrayList<NextNodeParamVo> paramVoArrayList = new ArrayList<>();
        NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
        nextNodeParamVo.setAcountId(userVo.getId());
        nextNodeParamVo.setAcountName(userVo.getName());
        nextNodeParamVo.setApproverDescription(userVo.getPositionTitle());
        nextNodeParamVo.setStationId(userVo.getHonor());
        nextNodeParamVo.setObjectUrl(PROJECT_AUDIT_URL+"?returnSystem=project");
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

    @Override
    public Result<GdcPage<BpmProcessTaskVo>> myApply(ProcessTaskQueryForm processTaskQueryForm, UserVo userVo) {
        //分页查询我的申请流程列表
        BpmProcessTaskQueryForm  bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setSourceSystem("PROJECT");
        bpmProcessTaskQueryForm.setSubmitterCode(userVo.getId());
        bpmProcessTaskQueryForm.setTaskKey("start");
        bpmProcessTaskQueryForm.setTaskStauts("已提交");
        bpmProcessTaskQueryForm.setPageSize(processTaskQueryForm.getPageSize());
        bpmProcessTaskQueryForm.setCurrentPage(processTaskQueryForm.getCurrentPage());
        Result<GdcPage<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.search(bpmProcessTaskQueryForm);
        return result;
    }

    @Override
    public Result<GdcPage<BpmProcessTaskVo>> myApproval(ProcessTaskQueryForm processTaskQueryForm, UserVo userVo) {
        //分页查询我的审批流程列表
        BpmProcessTaskQueryForm  bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setSourceSystem("PROJECT");
        bpmProcessTaskQueryForm.setApproverAcount(userVo.getId());
        bpmProcessTaskQueryForm.setTaskStauts("已批准");
        bpmProcessTaskQueryForm.setPageSize(processTaskQueryForm.getPageSize());
        bpmProcessTaskQueryForm.setCurrentPage(processTaskQueryForm.getCurrentPage());
        Result<GdcPage<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.search(bpmProcessTaskQueryForm);
        return result;
    }

}


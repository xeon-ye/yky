package com.deloitte.services.oaservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.bpmservice.feign.BpmApprovalMatrixFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.*;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaWorkflowDriverForm;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.oa.exception.OaErrorType;
import com.deloitte.services.oa.util.OABPMConstantUtil;
import com.deloitte.services.oaservice.service.IOaBpmFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class OaBpmFlowServiceImpl implements IOaBpmFlowService {

    private String BPM_CLIENT_NAME = "oaservice";

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;

    @Autowired
    private BpmApprovalMatrixFeignService bpmApprovalMatrixFeignService;

    public Result driveProcess(OaWorkflowDriverForm taskForm, ArrayList<NextNodeParamVo> nextNodeParamVo,UserVo userVo, DeptVo deptVo){
        Result result;
        BpmProcessOperatorApprove bpmProcessParamForm = new BpmProcessOperatorApprove();
        BpmProcessTaskFormApprove bpmProcessTaskFormApprove = new BpmProcessTaskFormApprove();
        String workflowId = taskForm.getWorkflowId();
        if(workflowId!=null&&!"".equals(workflowId)&&workflowId.matches("^[-\\+]?[\\d]*$")){
            bpmProcessTaskFormApprove.setId(Long.valueOf(workflowId));
        }
        bpmProcessTaskFormApprove.setOpinion(taskForm.getOpinion());
        bpmProcessTaskFormApprove.setObjectDescription(taskForm.getObjectDescription());
        bpmProcessParamForm.setBpmProcessTaskForm(bpmProcessTaskFormApprove);
        if(nextNodeParamVo==null||nextNodeParamVo.size()==0){
            if(OABPMConstantUtil.OPTION_TYPE_SUMBIT.equals(taskForm.getOptionType())) {
                bpmProcessParamForm.setNextNodeParamVo(this.getNextNodeParamVo(taskForm.getObjectType(), deptVo==null?"-1":deptVo.getDeptId(), taskForm.getProcessDefineKey(), taskForm.getTaskId(), taskForm.getObjectUrl(),taskForm.getProcessVariables()));//下一环节审批参数 ArrayList<NextNodeParamVo>
            }
        }else{
            bpmProcessParamForm.setNextNodeParamVo(nextNodeParamVo);
        }
        bpmProcessParamForm.setProcessVariables(taskForm.getProcessVariables());
        if(OABPMConstantUtil.OPTION_TYPE_SUMBIT.equals(taskForm.getOptionType())){
            if(taskForm.getTaskId()==null||"".equals(taskForm.getTaskId())||"start".equals(taskForm.getTaskId())) {
                BpmProcessOperatorFormStart bpmProcessOperatorFormStart = new BpmProcessOperatorFormStart();
                BpmProcessTaskFormStart bpmProcessTaskFormStart = new BeanUtils<BpmProcessTaskFormStart>().copyObj(taskForm,BpmProcessTaskFormStart.class);
                bpmProcessTaskFormStart.setSubmitterCode(String.valueOf(userVo.getId()));//提交人账号
                bpmProcessTaskFormStart.setSubmitterName(userVo.getName());//提交人姓名
                //userVo.getCurrentDeputyAccount().getOrgCode();
                List<DeputyAccountVo> list = userVo.getDeputyAccount();
                if(list!=null&&list.size()>0){
                    bpmProcessTaskFormStart.setSubmitterOrg(list.get(0).getOrgCode());//提交人组织
                }
                //bBpmProcessTaskFormStart.setSubmitterStation(userVo.);//提交人岗位
                bpmProcessOperatorFormStart.setNextNodeParamVo(bpmProcessParamForm.getNextNodeParamVo());//下一环节审批参数 ArrayList<NextNodeParamVo>
                bpmProcessOperatorFormStart.setBpmProcessTaskForm(bpmProcessTaskFormStart);
                bpmProcessOperatorFormStart.setProcessVariables(taskForm.getProcessVariables());
                result = bpmOperatorFeignService.startProcess(bpmProcessOperatorFormStart);//流程启动，只有第一次启动的时候调用
            }else{
                result = bpmOperatorFeignService.approveProcess(bpmProcessParamForm);//流程驱动，流程启动之后调用
            }
        }else if(OABPMConstantUtil.OPTION_TYPE_REJECT_TO_START.equals(taskForm.getOptionType())){
            Result<BpmProcessTaskVo> historyResult = bpmProcessTaskFeignService.get(bpmProcessTaskFormApprove.getId());
            if(historyResult.isSuccess()) {
                BpmProcessTaskVo history = historyResult.getData();
                ArrayList<NextNodeParamVo> nextList = new ArrayList<>();
                NextNodeParamVo firstNodeParamVo = new NextNodeParamVo();
                firstNodeParamVo.setAcountId(history.getSubmitterCode());
                firstNodeParamVo.setAcountName(history.getSubmitterName());
                firstNodeParamVo.setOrgId(history.getSubmitterOrg());
                firstNodeParamVo.setObjectUrl(taskForm.getObjectUrl());
                nextList.add(firstNodeParamVo);
                bpmProcessParamForm.setNextNodeParamVo(nextList);
                result = bpmOperatorFeignService.rejectToFirstProcess(bpmProcessParamForm);
            }else{
                result = historyResult;
            }

        }else if(OABPMConstantUtil.OPTION_TYPE_REJECT.equals(taskForm.getOptionType())){
            Result<BpmProcessTaskVo> historyResult = bpmProcessTaskFeignService.get(bpmProcessTaskFormApprove.getId());
            if(historyResult.isSuccess()) {
                BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
                BpmProcessTaskVo history2 = historyResult.getData();
                bpmProcessTaskQueryForm.setProcessInstanceId(history2.getProcessInstanceId());
                bpmProcessTaskQueryForm.setTaskId(history2.getPreviousTaskId());
                Result<List<BpmProcessTaskVo>> resultList = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
                if(resultList.isSuccess()){
                    List<BpmProcessTaskVo> historyList = resultList.getData();
                    if(historyList==null||historyList.size()==0){
                        return Result.fail("无法查询到上一节点信息");
                    }
                    BpmProcessTaskVo history = historyList.get(0);
                    ArrayList<NextNodeParamVo> nextList = new ArrayList<>();
                    NextNodeParamVo perviousNodeParamVo = new NextNodeParamVo();
                    perviousNodeParamVo.setAcountId(history.getSubmitterCode());
                    perviousNodeParamVo.setAcountName(history.getSubmitterName());
                    perviousNodeParamVo.setOrgId(history.getSubmitterOrg());
                    perviousNodeParamVo.setObjectUrl(taskForm.getObjectUrl());
                    nextList.add(perviousNodeParamVo);
                    bpmProcessParamForm.setNextNodeParamVo(nextList);
                    result = bpmOperatorFeignService.rejectProcess(history.getTaskKey(), bpmProcessParamForm);
                }else{
                    result = resultList;
                }
            }else{
                result = historyResult;
            }
        }else if(OABPMConstantUtil.OPTION_TYPE_REFER_TO.equals(taskForm.getOptionType())){
            result = bpmOperatorFeignService.turnToProcess(bpmProcessParamForm);
        }else{
            result = Result.fail("无此操作类型");
        }
        return result;
    }

    @Override
    public List<BpmProcessTaskVo> queryAuditHistory(String processId){
        List<BpmProcessTaskVo> list = new ArrayList<>();
        BpmProcessTaskQueryForm queryForm = new BpmProcessTaskQueryForm();
        queryForm.setProcessInstanceId(processId);
        log.info("[BPM]bpmProcessTaskFeignService.list req: processId={}", processId);
        Result<List<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.list(queryForm);
        log.info("[BPM]bpmProcessTaskFeignService.list rsp: {}", JSONObject.toJSONString(result));
        if (result != null && result.isSuccess() && result.getData()!=null){
            list = result.getData();
        }
        return list;
    }

    @Override
    public ArrayList<NextNodeParamVo> getNextNodeParamVo(String bizType, String chargeOrg, String processDefineKey, String taskId, String url, Map<String,String> processVariables){
        ArrayList<NextNodeParamVo> nextList = new ArrayList<>();
        BpmApprovalMatrixQueryFormForApproval param = new BpmApprovalMatrixQueryFormForApproval();
        param.setChargeBusiness(bizType);
        param.setChargeOrg(chargeOrg);
        param.setProcessDefineKey(processDefineKey);
        if(taskId==null||"".equals(taskId)){
            param.setTaskID("start");
        }else {
            param.setTaskID(taskId);
        }
        param.setProcessVariables(processVariables);
        log.info("[BPM]bpmApprovalMatrixFeignService.findNextApprover req:{}", JSONObject.toJSONString(param));
        Result<List<BpmApprovalMatrixVo>> result = bpmApprovalMatrixFeignService.findNextApprover(param);
        log.info("[BPM]bpmApprovalMatrixFeignService.findNextApprover rsp:{}", JSONObject.toJSONString(result));
        if(result.isSuccess()){
            if (result != null && result.getData() != null){
                List<BpmApprovalMatrixVo> matrixVoList = result.getData();
                for (BpmApprovalMatrixVo matrixVo: matrixVoList){
                    NextNodeParamVo nodeParamVo = new NextNodeParamVo();
                    nodeParamVo.setAcountId(matrixVo.getAccountNum());
                    nodeParamVo.setAcountName(matrixVo.getAccountName());
                    nodeParamVo.setObjectUrl(url);
                    nodeParamVo.setOrgId(matrixVo.getOrgCode());
                    nodeParamVo.setStationId(matrixVo.getPosition());
                    nextList.add(nodeParamVo);
                }
            }
        }else{
            //抛异常
            throw new ServiceException(OaErrorType.BPM_NEXT_APPROVER_ERROR,result.getMesg());
        }
        //如果没有审批人 加一个空对象
        if (nextList.size() == 0){
            nextList.add(new NextNodeParamVo());
        }
        return nextList;
    }

    /**
     * 查询待办列表
     */
    public IPage<BpmProcessTaskVo> queryBackLogPage(BaseQueryForm pageForm, UserVo userVo){
        IPage<BpmProcessTaskVo> taskNodePage = new Page<>();
        log.info("[BPM]bpmProcessTaskFeignService.searchBackLog req: userId={}", userVo.getId());
        BpmConductListQueryForm queryForm = (BpmConductListQueryForm)pageForm;
        queryForm.setApproverAcount(userVo.getId());
        queryForm.setPageSize(pageForm.getPageSize());
        queryForm.setCurrentPage(pageForm.getCurrentPage());
        queryForm.setSourceSystem(BPM_CLIENT_NAME);
        Result<GdcPage<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.searchBackLog(queryForm);
        log.info("[BPM]bpmProcessTaskFeignService.searchBackLog rsp: {}", JSONObject.toJSONString(result));
        if (result != null && result.isSuccess()){
            GdcPage<BpmProcessTaskVo> taskPage = result.getData();
            if (taskPage != null){
                taskNodePage = new BeanUtils<BpmProcessTaskVo>().copyPageObjs(taskPage,BpmProcessTaskVo.class);
            }
        }
        //fillVoucherDateToTaskNode(taskNodePage);
        return taskNodePage;
    }


    /**
     * 查询已办列表
     */
    public IPage<BpmProcessTaskVo> queryDonePage(BaseQueryForm pageForm, UserVo userVo){
        IPage<BpmProcessTaskVo> taskNodePage = new Page<>();
        log.info("[BPM]bpmProcessTaskFeignService.searchDone req: userId={}", userVo.getId());
        BpmConductListQueryForm queryForm = (BpmConductListQueryForm)pageForm;
        queryForm.setApproverAcount(userVo.getId());
        queryForm.setPageSize(pageForm.getPageSize());
        queryForm.setCurrentPage(pageForm.getCurrentPage());
        queryForm.setSourceSystem(BPM_CLIENT_NAME);
        Result<GdcPage<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.searchDone(queryForm);
        log.info("[BPM]bpmProcessTaskFeignService.searchDone rsp: {}", JSONObject.toJSONString(result));
        if (result != null && result.isSuccess()){
            GdcPage<BpmProcessTaskVo> taskPage = result.getData();
            if (taskPage != null){
                taskNodePage = new BeanUtils<BpmProcessTaskVo>().copyPageObjs(taskPage,BpmProcessTaskVo.class);
            }
        }
        //fillVoucherDateToTaskNode(taskNodePage);
        return taskNodePage;
    }
}

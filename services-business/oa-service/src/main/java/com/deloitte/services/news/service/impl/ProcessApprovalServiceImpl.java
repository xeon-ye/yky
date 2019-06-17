package com.deloitte.services.news.service.impl;

import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.oaservice.news.vo.ProcessParam;
import com.deloitte.platform.api.oaservice.news.vo.ProcessStatusVo;
import com.deloitte.platform.api.oaservice.news.vo.RespProcessParam;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.news.service.ProcessApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ProcessApprovalServiceImpl implements ProcessApprovalService {

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Override
    public Result<ProcessStatusVo> getProcessStatus(String processInstanceId, String sourceSystem) {
        ProcessStatusVo processStatusVo = new ProcessStatusVo();
        List<ProcessParam> respList = new ArrayList<>();
        List<RespProcessParam> processParamList = new ArrayList<>();
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setProcessInstanceId(processInstanceId);
        bpmProcessTaskQueryForm.setSourceSystem("oaservice");
        //查询审批历史
        Result<List<BpmProcessTaskVo>> list = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
        List<BpmProcessTaskVo> dataList = list.getData();
        if (CollectionUtils.isEmpty(dataList)) {
            return null;
        }
//        processStatusVo.setTotalNum(dataList.size());

        //总的已提交
        List<BpmProcessTaskVo> submitList = dataList.stream()
                .filter(BpmProcessTaskVo -> ((!"待审批".equals(BpmProcessTaskVo.getTaskStauts()) && !"待提交".equals(BpmProcessTaskVo.getTaskStauts())) && null != BpmProcessTaskVo.getSignTime()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(submitList)) {
            processStatusVo.setSubmitted(0);
//            processStatusVo.setNotSubmitted(dataList.size());
        } else {
            processStatusVo.setSubmitted(submitList.size());
//            processStatusVo.setNotSubmitted(dataList.size() - submitList.size());
        }
        //总的已查看
        List<BpmProcessTaskVo> checkList = dataList.stream()
                .filter(BpmProcessTaskVo -> (("待审批".equals(BpmProcessTaskVo.getTaskStauts()) || "待提交".equals(BpmProcessTaskVo.getTaskStauts())) && null != BpmProcessTaskVo.getSignTime()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(checkList)) {
            processStatusVo.setChecked(0);
//            processStatusVo.setNotChecked(dataList.size());
        } else {
            processStatusVo.setChecked(checkList.size());
//            processStatusVo.setNotChecked(dataList.size() - checkList.size());
        }
        //未查看
        List<BpmProcessTaskVo> notCheckList = dataList.stream()
                .filter(BpmProcessTaskVo -> (null == BpmProcessTaskVo.getSignTime()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(notCheckList)) {
            processStatusVo.setNotChecked(0);
        } else {
            processStatusVo.setNotChecked(notCheckList.size());
        }
        processStatusVo.setNotSubmitted(processStatusVo.getChecked() + processStatusVo.getNotChecked());
        processStatusVo.setTotalNum(processStatusVo.getSubmitted() + processStatusVo.getNotSubmitted());

        List<String> taskKeyList = new ArrayList<>();
        for (BpmProcessTaskVo taskVo : dataList) {
            RespProcessParam respProcessParam = new RespProcessParam();
            respProcessParam.setOperatorName(taskVo.getApproverName());
            respProcessParam.setOperatorStatus(taskVo.getTaskStauts());
            respProcessParam.setReceiveTime(taskVo.getCreateTime());
            respProcessParam.setOperatorTime(taskVo.getUpdateTime());
            respProcessParam.setDurationTime(taskVo.getDurationTime());
            respProcessParam.setTastKey(taskVo.getTaskKey());
            respProcessParam.setSignTime(taskVo.getSignTime());
            processParamList.add(respProcessParam);

            taskKeyList.add(taskVo.getTaskKey());
        }
        //对taskKey去重
        taskKeyList = new ArrayList<>(new HashSet<>(taskKeyList));
        for (String taskKey : taskKeyList) {
            ProcessParam submitPerson = this.getInfo(processParamList, taskKey);
            respList.add(submitPerson);
        }
        processStatusVo.setRespList(respList);
        return Result.success(processStatusVo);
    }


    private ProcessParam getInfo(List<RespProcessParam> dataList, String taskKey) {
        ProcessParam processParam = new ProcessParam();
        processParam.setTaskKey(taskKey);
        List<RespProcessParam> processParamList = new ArrayList<>();
        List<RespProcessParam> respInfo = this.getRespInfo(dataList, taskKey);
        if (CollectionUtils.isEmpty(respInfo)) {
            return null;
        }
        //已提交
        List<RespProcessParam> submit = respInfo.stream()
                .filter(RespProcessParam -> ((!"待审批".equals(RespProcessParam.getOperatorStatus()) && !"待提交".equals(RespProcessParam.getOperatorStatus())) && null != RespProcessParam.getSignTime()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(submit)) {
            processParam.setSubmitted(0);
        } else {
            processParam.setSubmitted(submit.size());
            submit = submit.stream().filter(respProcessParam -> {
                if (("待审批".equals(respProcessParam.getOperatorStatus()) || "待提交".equals(respProcessParam.getOperatorStatus())) && null != respProcessParam.getSignTime()) {
                    respProcessParam.setOperatorStatus("已提交");
                }
                return true;
            }).collect(Collectors.toList());
            processParamList.addAll(submit);
        }
        //已查看
        List<RespProcessParam> check = respInfo.stream()
                .filter(RespProcessParam -> (("待审批".equals(RespProcessParam.getOperatorStatus()) || "待提交".equals(RespProcessParam.getOperatorStatus())) && null != RespProcessParam.getSignTime()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(check)) {
            processParam.setChecked(0);
//            processParam.setNotChecked(respInfo.size());
        } else {
            processParam.setChecked(check.size());
//            processParam.setNotChecked(respInfo.size() - check.size());
            check = check.stream().filter(respProcessParam -> {
                if (("待审批".equals(respProcessParam.getOperatorStatus()) || "待提交".equals(respProcessParam.getOperatorStatus())) && null != respProcessParam.getSignTime()) {
                    respProcessParam.setOperatorStatus("已查看");
                }
                return true;
            }).collect(Collectors.toList());
            processParamList.addAll(check);
        }
        //未查看
        List<RespProcessParam> notCheck = respInfo.stream()
                .filter(RespProcessParam -> (null == RespProcessParam.getSignTime()))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(notCheck)) {
            notCheck = check.stream().filter(respProcessParam -> {
                if (null == respProcessParam.getSignTime()) {
                    respProcessParam.setOperatorStatus("未查看");
                }
                return true;
            }).collect(Collectors.toList());
            processParamList.addAll(notCheck);
        }
        processParam.setTotalNum(processParam.getSubmitted() + processParam.getChecked() + processParam.getNotChecked());
        processParam.setProcessParamList(processParamList);
        return processParam;
    }

    /**
     * 根据taskKey筛选操作信息
     *
     * @param dataList
     * @param taskKey
     * @return
     */
    private List<RespProcessParam> getRespInfo(List<RespProcessParam> dataList, String taskKey) {
        return dataList.stream()
                .filter(RespProcessParam -> (taskKey.equals(RespProcessParam.getTastKey())))
                .collect(Collectors.toList());
    }

}

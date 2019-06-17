package com.deloitte.services.fssc.business.bpm.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.bpm.param.TaskQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.entity.Process;
import com.deloitte.services.fssc.business.bpm.entity.ProcessDef;
import com.deloitte.services.fssc.business.bpm.service.IProcessDefService;
import com.deloitte.services.fssc.business.bpm.service.IProcessService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BpmTaskBizImpl implements BpmTaskBiz {

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;
    @Autowired
    private IProcessService processService;
    @Autowired
    private IProcessDefService processDefService;

    /**
     * 查询审批历史
     *
     * @param documentId   单据ID
     * @param documentType 单据头表 表名
     * @return
     */
    @Override
    public List<BpmProcessTaskVo> findHistory(Long documentId, String documentType) {
        AssertUtils.asserts(documentId != null && StringUtil.isNotEmpty(documentType), FsscErrorType.DOCUMENT_INFO_IS_NULL);
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        QueryWrapper<Process> wrapper = new QueryWrapper<>();
        wrapper.eq("DOCUMENT_ID", documentId).eq("DOCUMENT_TYPE", documentType);
        Process process = processService.getOne(wrapper);
        AssertUtils.asserts(process != null, FsscErrorType.THIS_PROCESS_IS_NOT_FIND);

        ProcessDef processDef = processDefService.getById(process.getProcessDefId());
        AssertUtils.asserts(processDef != null, FsscErrorType.PROCESS_DEF_IS_NULL);

        bpmProcessTaskQueryForm.setProcessInstanceId(process.getProcessId());
        bpmProcessTaskQueryForm.setSourceSystem(FsscConstants.SOURCE_SYSTEM_NAME);
        Result<List<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
        AssertUtils.asserts(result.isSuccess(), FsscErrorType.FIND_HISTORY_FAILD);

        return result.getData();
    }


    /**
     * 查询审批历史
     *
     * @param documentId   单据ID
     * @param documentType 单据头表 表名
     * @return
     */
    @Override
    public List<BpmProcessTaskVo> findHistoryNoException(Long documentId, String documentType) {
        AssertUtils.asserts(documentId != null && StringUtil.isNotEmpty(documentType), FsscErrorType.DOCUMENT_INFO_IS_NULL);
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        QueryWrapper<Process> wrapper = new QueryWrapper<>();
        wrapper.eq("DOCUMENT_ID", documentId).eq("DOCUMENT_TYPE", documentType);
        Process process = processService.getOne(wrapper);
        if (process == null) {
            return Collections.emptyList();
        }
        ProcessDef processDef = processDefService.getById(process.getProcessDefId());
        if (processDef == null) {
            return Collections.emptyList();
        }
        bpmProcessTaskQueryForm.setProcessInstanceId(process.getProcessId());
        bpmProcessTaskQueryForm.setSourceSystem(FsscConstants.SOURCE_SYSTEM_NAME);
        Result<List<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
        if (!result.isSuccess()) {
            return Collections.emptyList();
        }
        return result.getData();
    }

    /**
     * 查询我的待办
     *
     * @param queryForm@return
     */
    @Override
    public GdcPage<BpmProcessTaskVo> findMyToDo(TaskQueryForm queryForm) {
        Map<String, String[]> stringMap = buildWrapper(queryForm, false);
        BpmProcessTaskQueryWrapper queryWrapper = new BpmProcessTaskQueryWrapper();
        queryWrapper.setCurrentPage(queryForm.getCurrentPage());
        queryWrapper.setPageSize(queryForm.getPageSize());
        queryWrapper.setWrapperMap(stringMap);
        Result<GdcPage<BpmProcessTaskVo>> search = bpmProcessTaskFeignService.searchByWrapper(queryWrapper);
        AssertUtils.asserts(search.isSuccess(), FsscErrorType.GET_TASK_FAILD);
        return search.getData();
    }

    /**
     * 查询已办
     *
     * @param queryForm@return
     */
    @Override
    public GdcPage<BpmProcessTaskVo> findMyDone(TaskQueryForm queryForm) {
        Map<String, String[]> stringMap = buildWrapper(queryForm, true);
        BpmProcessTaskQueryWrapper queryWrapper = new BpmProcessTaskQueryWrapper();
        queryWrapper.setCurrentPage(queryForm.getCurrentPage());
        queryWrapper.setPageSize(queryForm.getPageSize());
        queryWrapper.setWrapperMap(stringMap);
        Result<GdcPage<BpmProcessTaskVo>> pageResult = bpmProcessTaskFeignService.searchByWrapper(queryWrapper);
        AssertUtils.asserts(pageResult.isSuccess(), FsscErrorType.GET_TASK_FAILD);
        return pageResult.getData();
    }


    private Map<String, String[]> buildWrapper(TaskQueryForm queryForm, boolean isDone) {
        Map<String, String[]> wrapperMap = new HashMap<>();
        String documentNum = queryForm.getDocumentNum();
        String documentType = queryForm.getDocumentType();
        String userName = queryForm.getSubmitUserName();
        LocalDateTime endTime = queryForm.getEndTime();
        LocalDateTime startTime = queryForm.getStartTime();
        String userId = queryForm.getUserId();

        if (StringUtil.isNotEmpty(documentNum)) {
            wrapperMap.put(BpmProcessTaskQueryForm.OBJECT_ORDER_NUM, new String[]{"like", documentNum});
        }
        if (StringUtil.isNotEmpty(documentType)) {
            wrapperMap.put(BpmProcessTaskQueryForm.OBJECT_TYPE, new String[]{"eq", documentType});
        }
        if (StringUtil.isNotEmpty(userId)) {
            wrapperMap.put(BpmProcessTaskQueryForm.APPROVER_ACOUNT, new String[]{"eq", userId});
        }
        if (StringUtil.isNotEmpty(userName)) {
            wrapperMap.put(BpmProcessTaskQueryForm.SUBMITTER_NAME, new String[]{"like", userName});
        }
        if (startTime != null && endTime != null) {
            String s = LocalDateTimeUtils.formatTime(startTime, "yyyy-MM-dd HH:mm:ss");
            String e = LocalDateTimeUtils.formatTime(endTime, "yyyy-MM-dd HH:mm:ss");
            String time = s + "," + e;
            wrapperMap.put(BpmProcessTaskQueryForm.CREATE_TIME, new String[]{"between", time});
        }
        if (isDone) {
            wrapperMap.put(BpmProcessTaskQueryForm.TASK_STAUTS, new String[]{"notIn", "待审批,待重新提交,撤回,已重新提交,已提交,已驳回"});
        } else {
            wrapperMap.put(BpmProcessTaskQueryForm.TASK_STAUTS, new String[]{"eq", "待审批"});
        }
        wrapperMap.put(BpmProcessTaskQueryForm.SOURCE_SYSTEM, new String[]{"eq", "FSSC"});
        return wrapperMap;
    }
}

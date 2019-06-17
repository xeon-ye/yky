package com.deloitte.services.oaservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaWorkflowDriverForm;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IOaBpmFlowService {

    /**
     * 启动流程
     * @return
     */
    Result driveProcess(OaWorkflowDriverForm taskForm,ArrayList<NextNodeParamVo> nextNodeParamVo, UserVo userVo, DeptVo deptVo);


    /**
     * 查询审核历史
     */
    List<BpmProcessTaskVo> queryAuditHistory(String processId);

    /**
     * 获取下一个节点审核者
     */
    ArrayList<NextNodeParamVo> getNextNodeParamVo(String bizType, String chargeOrg, String processDefineKey, String taskId, String url, Map<String,String> processVariables);

    IPage<BpmProcessTaskVo> queryBackLogPage(BaseQueryForm pageForm, UserVo userVo);

    IPage<BpmProcessTaskVo> queryDonePage(BaseQueryForm pageForm, UserVo userVo);
}

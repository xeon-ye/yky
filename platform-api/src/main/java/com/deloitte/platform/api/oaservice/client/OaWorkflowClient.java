package com.deloitte.platform.api.oaservice.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OaWorkflowClient {
    public static final String path="/oaservice";
    /**
     * 查询待办列表
     * @return
     */
    @PostMapping(value = path+"/page/process/backlog")
    Result<IPage<BpmProcessTaskVo>> queryUserBackLog(@RequestBody BpmConductListQueryForm queryForm);

    /**
     * 查询已办列表
     * @return
     */
    @PostMapping(value = path+"/page/process/doneList")
    Result<IPage<BpmProcessTaskVo>> queryUserDoneList(@RequestBody BpmConductListQueryForm queryForm);

    /**
     * 查询审核历史
     * @return
     */
    @PostMapping(value = path+"/page/process/history/{processId}")
    Result<List<BpmProcessTaskVo>> queryAuditHistory(@PathVariable(value = "processId") String processId);

    /**
     *  检查单据是否在流程中
     * @param businessId
     * @param bizType
     * @return
     */
    @GetMapping(value=path+"/workflow/check")
    Result workflowDriving(@RequestParam(name="businessId")String businessId,@RequestParam(name="bizType")String bizType);

    /**
     * 检查任务是否完成
     * @return
     */
    @PostMapping(value = path+"/workflow/validataTask/{id}")
    Result validationTaskEnd(@PathVariable(value = "id") long id);

    /**
     * 获取下一节点处理人
     * @return
     */
    @GetMapping(value = path+"/workflow/nextParam")
    Result getNextNodeParamVo(@RequestParam(name="bizType")String bizType,@RequestParam(name="chargeOrg")String chargeOrg,@RequestParam(name="processDefineKey")String processDefineKey,@RequestParam(name="taskId")String taskId,@RequestParam(name="url")String url,@RequestParam(name="processVariables")String processVariables);

    /**
     *  流程签收
     */
    @PostMapping(value = path+"/signTask/{id}")
    Result signTask(@PathVariable(value="id") long id);

    @PostMapping(value = path+"/history/imageInfo/{processInstanceId}")
    Result getFlowImgInfoByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId);
}

package com.deloitte.services.oaservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.client.OaWorkflowClient;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.oaservice.service.IOaBpmFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "Oa服务流程操作接口")
@Slf4j
@RestController

public class OaWorkflowController implements OaWorkflowClient {

    @Autowired
    IOaBpmFlowService bpmService;

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    /**
     * 查询待办列表
     * @return
     */
    @ApiOperation(value = "查询待办列表", notes = "查询待办列表")
    public Result<IPage<BpmProcessTaskVo>> queryUserBackLog(@RequestBody BpmConductListQueryForm queryForm){
        IPage<BpmProcessTaskVo> page = bpmService.queryBackLogPage(queryForm, UserUtil.getUserVo());
        return Result.success(page);
    }


    /**
     * 查询已办列表
     * @return
     */
    @ApiOperation(value = "查询已办列表", notes = "查询已办列表")
    public Result<IPage<BpmProcessTaskVo>> queryUserDoneList(@RequestBody BpmConductListQueryForm queryForm){
        IPage<BpmProcessTaskVo> page = bpmService.queryDonePage(queryForm, UserUtil.getUserVo());
        return Result.success(page);
    }

    /**
     * 查询审核历史
     * @return
     */
    @ApiOperation(value = "查询审核历史", notes = "查询审核历史")
    public Result<List<BpmProcessTaskVo>> queryAuditHistory(@PathVariable(value = "processId") String processId) {
        List<BpmProcessTaskVo> list = bpmService.queryAuditHistory(processId);
        return Result.success(list);
    }

    @Override
    @ApiOperation(value = "通过业务编号查询是否存在此单据是否在流程中", notes = "通过业务i编号查询是否存在此单据是否在流程中")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "businessId", value = "OaScheduleTable的businessId", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query",name="bizType", value = "根据条件查询业务类型",  dataType = "string")
    })
    public Result workflowDriving(@RequestParam(name="businessId")String businessId, @RequestParam(name="bizType")String bizType){
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setObjectOrderNum(businessId);
        bpmProcessTaskQueryForm.setObjectStauts("审批中");
        bpmProcessTaskQueryForm.setTaskStauts("待审批");
        bpmProcessTaskQueryForm.setObjectType(bizType);
        Result<List<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
        if(result.isSuccess()){
            List<BpmProcessTaskVo> list = result.getData();
            if(list!=null&&list.size()>0){
                return Result.success(true);
            }else{
                bpmProcessTaskQueryForm.setTaskStauts("待重新提交");
                result = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
                if(result.isSuccess()){
                    List<BpmProcessTaskVo> list2 = result.getData();
                    if(list2!=null&&list2.size()>0) {
                        return Result.success(true);
                    }else{
                        return  Result.success(false);
                    }
                }else{
                    return result;
                }
            }
        }else{
            return result;
        }
    }

    @Override
    @ApiOperation(value = "通过流程历史id查询此任务是否完成", notes = "通过流程历史id查询此任务是否完成")
    @ApiImplicitParam(paramType = "path", name = "id", value = "流程历史主键", required = true, dataType = "long")
    public Result validationTaskEnd(@PathVariable(value = "id") long id){
        Result<BpmProcessTaskVo> result = bpmProcessTaskFeignService.get(id);
        Map<String,Object> map = new HashMap<String,Object>();
        if(result.isSuccess()){
            BpmProcessTaskVo history = result.getData();
            map.put("history",history);
            if(!"待审批".equals(history.getTaskStauts()) && !"待重新提交".equals(history.getTaskStauts())){
                //return Result.success("END");
                map.put("flag","END");
            }else{
                if("start".equals(history.getTaskKey())){
                    //return Result.success("START");
                    map.put("flag","START");
                }else{
                   //return Result.success("PROCESS");
                    map.put("flag","PROCESS");
                }
            }
            return Result.success(map);
        }else{
            return result;
        }
    }

    @Override
    @ApiOperation(value = "获取提交下一节点的处理人", notes = "获取提交下一节点的处理人")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bizType", value = "单据类型", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query",name="deptCode", value = "筛选矩阵匹配部门", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query",name="processDefineKey", value = "流程定义id", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query",name="taskId", value = "第一环节时传start，其他传taskId", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query",name="url", value = "下一节点打开单据的url", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query",name="processVariables", value = "json格式数据{}", required = true, dataType = "string")
    })
    public Result getNextNodeParamVo(@RequestParam(name="bizType")String bizType,@RequestParam(name="chargeOrg")String chargeOrg,@RequestParam(name="processDefineKey")String processDefineKey,@RequestParam(name="taskId")String taskId,@RequestParam(name="url")String url,@RequestParam(name="processVariables")String processVariables){
        Map<String,String> map = JSONObject.parseObject(processVariables, new TypeReference<Map<String, String>>(){});
        //json.
        ArrayList<NextNodeParamVo> nextNodeParamVos = bpmService.getNextNodeParamVo(bizType,chargeOrg,processDefineKey,taskId,url,map);
        return Result.success(nextNodeParamVos);
    }

    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;

    @Override
    @ApiOperation(value = "流程签收", notes = "依据流程历史id签收流程")
    public Result signTask(@PathVariable(value="id") long id){
        return bpmOperatorFeignService.signTask(id);
    }

    @Override
    @ApiOperation(value = "获取流程图的各节点信息", notes = "获取流程图的各节点信息")
    public Result getFlowImgInfoByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId){
        return bpmProcessTaskFeignService.getFlowImgInfoByInstantId(processInstanceId);
    }
}

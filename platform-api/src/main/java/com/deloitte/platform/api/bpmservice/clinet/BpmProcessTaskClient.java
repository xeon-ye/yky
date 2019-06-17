package com.deloitte.platform.api.bpmservice.clinet;

import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessImgInfoVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-28
 * @Description :  BpmProcessTask控制器接口
 * @Modified :
 */
public interface BpmProcessTaskClient {

    public static final String path="/bpmservice/bpmProcessTask";


    /**
     *  POST---新增
     * @param bpmProcessTaskForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BpmProcessTaskForm bpmProcessTaskForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, bpmProcessTaskForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BpmProcessTaskForm bpmProcessTaskForm);

    /**
     *  Patch----部分更新
     * @param  bpmProcessTaskForm
     * @return
     */
    @PatchMapping(value = path+"/updateHistory")
    Result updateByProcessInsIdAndTaskId(@Valid @RequestBody BpmProcessTaskForm bpmProcessTaskForm);

    /**
     *  post----部分更新
     * @param  bpmProcessTaskForm
     * @return
     */
    @PostMapping(value = path+"/updateHistory")
    Result updateHistoryByProcessInsIdAndTaskId(@Valid @RequestBody BpmProcessTaskForm bpmProcessTaskForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BpmProcessTaskVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   bpmProcessTaskQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BpmProcessTaskVo>> list(@Valid @RequestBody BpmProcessTaskQueryForm bpmProcessTaskQueryForm);

    /**
     *  POST----复杂查询
     * @param  bpmProcessTaskQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<GdcPage<BpmProcessTaskVo>> search(@Valid @RequestBody BpmProcessTaskQueryForm bpmProcessTaskQueryForm);

    @PostMapping(value = path+"/page/wrapper")
    Result<GdcPage<BpmProcessTaskVo>> searchByWrapper(@Valid @RequestBody BpmProcessTaskQueryWrapper queryWrapper);


    @PostMapping(value = path+"/page/BackLog")
    Result<GdcPage<BpmProcessTaskVo>> searchBackLog(@Valid @RequestBody BpmConductListQueryForm bpmConductListQueryForm);


    @PostMapping(value = path+"/page/Done")
    Result<GdcPage<BpmProcessTaskVo>> searchDone(@Valid @RequestBody BpmConductListQueryForm bpmConductListQueryForm);

    @GetMapping(value = path+"/processVariables/{processInstanceId}")
    public Result<HashMap<String, Object>>  getProcessVariables(@PathVariable(value = "processInstanceId") String processInstanceId);

    @GetMapping(value = path+"/history/image/{processInstanceId}")
    void  getFlowImgByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId, HttpServletResponse response);

    @PostMapping(value = path+"/history/imageInfo/{processInstanceId}")
    Result getFlowImgInfoByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId);

    @GetMapping(value = path+"/process/end")
    Result workflowEnd(@RequestParam(name = "processInstanceId") String processInstanceId);

    @GetMapping(value = path+"/process/task/end")
    Result taskIsEnd(@RequestParam(name = "processInstanceId") String processInstanceId,@RequestParam(name = "taskId") String taskId);


}

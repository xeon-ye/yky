package com.deloitte.platform.basic.bpmservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.clinet.BpmProcessTaskClient;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessImgInfoVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.basic.bpmservice.core.IActivityService;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTask;
import com.deloitte.platform.basic.bpmservice.exception.BpmErrorType;
import com.deloitte.platform.basic.bpmservice.service.IBpmProcessTaskService;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.platform.common.core.util.WarpperUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : jackliu
 * @Date : Create in 2019-02-28
 * @Description :   BpmProcessTask控制器实现类
 * @Modified :
 */
@Api(tags = "BpmProcessTask操作接口")
@Slf4j
@RestController
public class BpmProcessTaskController implements BpmProcessTaskClient {

    @Autowired
    public IBpmProcessTaskService  bpmProcessTaskService;

    @Autowired
    public IActivityService activityService;


    @Override
    @ApiOperation(value = "新增BpmProcessTask", notes = "新增一个BpmProcessTask")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="bpmProcessTaskForm",value="新增BpmProcessTask的form表单",required=true)  BpmProcessTaskForm bpmProcessTaskForm) {
        log.info("form:",  bpmProcessTaskForm.toString());
        BpmProcessTask history = bpmProcessTaskService.findBpmProcessTask(bpmProcessTaskForm.getProcessInstanceId(),bpmProcessTaskForm.getTaskId());
        if(history!=null&&history.getTaskId()!=null&&!"".equals(history.getTaskId())){
            return Result.success("当前任务已存在");
        }
        BpmProcessTask bpmProcessTask =new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm,BpmProcessTask.class);
        return Result.success( bpmProcessTaskService.save(bpmProcessTask));
    }


    @Override
    @ApiOperation(value = "删除BpmProcessTask", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BpmProcessTaskID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        bpmProcessTaskService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改BpmProcessTask", notes = "修改指定BpmProcessTask信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "BpmProcessTask的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="bpmProcessTaskForm",value="修改BpmProcessTask的form表单",required=true)  BpmProcessTaskForm bpmProcessTaskForm) {
        BpmProcessTask bpmProcessTask =new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm,BpmProcessTask.class);
        bpmProcessTask.setId(id);
        bpmProcessTaskService.saveOrUpdate(bpmProcessTask);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "通过流程实例ID和流程任务ID修改BpmProcessTask", notes = "通过流程实例ID和流程任务ID修改指定BpmProcessTask信息")
    public Result updateByProcessInsIdAndTaskId(@Valid @RequestBody @ApiParam(name="bpmProcessTaskForm",value="修改BpmProcessTask的form表单",required=true)  BpmProcessTaskForm bpmProcessTaskForm) {
        BpmProcessTask bpmProcessTask =new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm,BpmProcessTask.class);
        String processInstanceId = bpmProcessTask.getProcessInstanceId();
        String taskId = bpmProcessTask.getTaskId();
        if(processInstanceId!=null&&taskId!=null&&!"".equals(processInstanceId)&&!"".equals(taskId)){
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setProcessInstanceId(processInstanceId);
            bpmProcessTaskQueryForm.setTaskId(taskId);
            List<BpmProcessTask> list = bpmProcessTaskService.selectList(bpmProcessTaskQueryForm);
            if(list!=null&&list.size()==1){
                BpmProcessTask task = list.get(0);
                bpmProcessTask.setId(task.getId());
                bpmProcessTaskService.saveOrUpdate(bpmProcessTask);
            }else{
                return Result.fail(BpmErrorType.BPM_PROCESS_NO_UNIQUE);
            }
            return Result.success();
        }else{
            return Result.fail(BpmErrorType.BPM_PROCESS_OR_TASK_IS_EMPTY);
        }
    }

    @Override
    @ApiOperation(value = "通过流程实例ID和流程任务ID修改BpmProcessTask", notes = "通过流程实例ID和流程任务ID修改指定BpmProcessTask信息")
    public Result updateHistoryByProcessInsIdAndTaskId(@Valid @RequestBody @ApiParam(name="bpmProcessTaskForm",value="修改BpmProcessTask的form表单",required=true)  BpmProcessTaskForm bpmProcessTaskForm) {
        BpmProcessTask bpmProcessTask =new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm,BpmProcessTask.class);
        String processInstanceId = bpmProcessTask.getProcessInstanceId();
        String taskId = bpmProcessTask.getTaskId();
        if(processInstanceId!=null&&taskId!=null&&!"".equals(processInstanceId)&&!"".equals(taskId)){
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setProcessInstanceId(processInstanceId);
            bpmProcessTaskQueryForm.setTaskId(taskId);
            BpmProcessTask history = bpmProcessTaskService.findBpmProcessTask(processInstanceId,taskId);
            if(history!=null){
                bpmProcessTask.setId(history.getId());
                bpmProcessTaskService.saveOrUpdate(bpmProcessTask);
                //如果状态为已通过，需要把所有历史记录均更新为已通过
                if("已通过".equals(bpmProcessTask.getObjectStauts())){
                    bpmProcessTaskService.updateObjectStatusByProcessId("已通过",history.getProcessInstanceId());
                }
            }else{
                return Result.fail(BpmErrorType.BPM_PROCESS_NO_UNIQUE);
            }
            return Result.success();
        }else{
            return Result.fail(BpmErrorType.BPM_PROCESS_OR_TASK_IS_EMPTY);
        }
    }


    @Override
    @ApiOperation(value = "获取BpmProcessTask", notes = "获取指定ID的BpmProcessTask信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BpmProcessTask的ID", required = true, dataType = "long")
    public Result<BpmProcessTaskVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BpmProcessTask bpmProcessTask=bpmProcessTaskService.getById(id);
        BpmProcessTaskVo bpmProcessTaskVo=new BeanUtils<BpmProcessTaskVo>().copyObj(bpmProcessTask,BpmProcessTaskVo.class);
        return new Result<BpmProcessTaskVo>().sucess(bpmProcessTaskVo);
    }

    @Override
    @ApiOperation(value = "列表查询BpmProcessTask", notes = "根据条件查询BpmProcessTask列表数据")
    public Result<List<BpmProcessTaskVo>> list(@Valid @RequestBody @ApiParam(name="bpmProcessTaskQueryForm",value="BpmProcessTask查询参数",required=true) BpmProcessTaskQueryForm bpmProcessTaskQueryForm) {
        log.info("search with bpmProcessTaskQueryForm:", bpmProcessTaskQueryForm.toString());
        List<BpmProcessTask> bpmProcessTaskList=bpmProcessTaskService.selectList(bpmProcessTaskQueryForm);
        List<BpmProcessTaskVo> bpmProcessTaskVoList=new BeanUtils<BpmProcessTaskVo>().copyObjs(bpmProcessTaskList,BpmProcessTaskVo.class);
        return new Result<List<BpmProcessTaskVo>>().sucess(bpmProcessTaskVoList);
    }


    @Override
    @ApiOperation(value = "分页查询BpmProcessTask", notes = "根据条件查询BpmProcessTask分页数据")
    public Result<GdcPage<BpmProcessTaskVo>> search(@Valid @RequestBody @ApiParam(name="bpmProcessTaskQueryForm",value="BpmProcessTask查询参数",required=true) BpmProcessTaskQueryForm bpmProcessTaskQueryForm) {
        log.info("search with bpmProcessTaskQueryForm:", bpmProcessTaskQueryForm.toString());
        IPage<BpmProcessTask> bpmProcessTaskPage=bpmProcessTaskService.selectPage(bpmProcessTaskQueryForm);
        IPage<BpmProcessTaskVo> bpmProcessTaskVoPage=new BeanUtils<BpmProcessTaskVo>().copyPageObjs(bpmProcessTaskPage,BpmProcessTaskVo.class);
        GdcPage<BpmProcessTaskVo> pages=new GdcPage(bpmProcessTaskVoPage.getRecords(),bpmProcessTaskVoPage.getTotal(),(int)bpmProcessTaskVoPage.getCurrent(),(int)bpmProcessTaskVoPage.getSize());
        return new Result<GdcPage<BpmProcessTaskVo>>().sucess(pages);
    }

    @Override
    @ApiOperation(value = "分页查询BpmProcessTask", notes = "自己定义Wrapper查询BpmProcessTask分页数据")
    public Result<GdcPage<BpmProcessTaskVo>> searchByWrapper(@Valid @RequestBody @ApiParam(name="BpmProcessTask",value="BpmProcessTask warpper",required=true) BpmProcessTaskQueryWrapper queryWrapper) {
        log.info("search with bpmProcessTaskQueryForm:", queryWrapper.toString());
        int currentPage=queryWrapper.getCurrentPage();
        int pageSize=queryWrapper.getPageSize();
        QueryWrapper queryWrapperForm= WarpperUtil.constructWrapper(queryWrapper.getWrapperMap());
        IPage<BpmProcessTask> bpmProcessTaskPage=bpmProcessTaskService.selectPageByWrapper(currentPage,pageSize,queryWrapperForm);
        IPage<BpmProcessTaskVo> bpmProcessTaskVoPage=new BeanUtils<BpmProcessTaskVo>().copyPageObjs(bpmProcessTaskPage,BpmProcessTaskVo.class);
        GdcPage<BpmProcessTaskVo> pages=new GdcPage(bpmProcessTaskVoPage.getRecords(),bpmProcessTaskVoPage.getTotal(),(int)bpmProcessTaskVoPage.getCurrent(),(int)bpmProcessTaskVoPage.getSize());
        return new Result<GdcPage<BpmProcessTaskVo>>().sucess(pages);
    }


    @Override
    @ApiOperation(value = "分页查询待办", notes = "根据人员ID查询待办分页数据")
    public Result<GdcPage<BpmProcessTaskVo>> searchBackLog(@Valid @RequestBody @ApiParam(name="BpmConductListQueryForm",value="审批记录查询参数",required=true) BpmConductListQueryForm bpmConductListQueryForm) {
        BpmProcessTaskQueryForm queryForm=new BeanUtils<BpmProcessTaskQueryForm>().copyObj(bpmConductListQueryForm,BpmProcessTaskQueryForm.class);
        IPage<BpmProcessTask> bpmProcessTaskPage=bpmProcessTaskService.selectBackLog(queryForm);
        IPage<BpmProcessTaskVo> bpmProcessTaskVoPage=new BeanUtils<BpmProcessTaskVo>().copyPageObjs(bpmProcessTaskPage,BpmProcessTaskVo.class);
        GdcPage<BpmProcessTaskVo> pages=new GdcPage(bpmProcessTaskVoPage.getRecords(),bpmProcessTaskVoPage.getTotal(),(int)bpmProcessTaskVoPage.getCurrent(),(int)bpmProcessTaskVoPage.getSize());
        return new Result<GdcPage<BpmProcessTaskVo>>().sucess(pages);
    }


    @Override
    @ApiOperation(value = "分页查询已办", notes = "根据人员ID查询已办分页数据")
    public Result<GdcPage<BpmProcessTaskVo>> searchDone(@Valid @RequestBody @ApiParam(name="BpmConductListQueryForm",value="审批记录查询参数",required=true) BpmConductListQueryForm bpmConductListQueryForm) {
        BpmProcessTaskQueryForm queryForm=new BeanUtils<BpmProcessTaskQueryForm>().copyObj(bpmConductListQueryForm,BpmProcessTaskQueryForm.class);
        IPage<BpmProcessTask> bpmProcessTaskPage=bpmProcessTaskService.selectDnoe(queryForm);
        IPage<BpmProcessTaskVo> bpmProcessTaskVoPage=new BeanUtils<BpmProcessTaskVo>().copyPageObjs(bpmProcessTaskPage,BpmProcessTaskVo.class);
        GdcPage<BpmProcessTaskVo> pages=new GdcPage(bpmProcessTaskVoPage.getRecords(),bpmProcessTaskVoPage.getTotal(),(int)bpmProcessTaskVoPage.getCurrent(),(int)bpmProcessTaskVoPage.getSize());
        return new Result<GdcPage<BpmProcessTaskVo>>().sucess(pages);
    }

    @Override
    @ApiOperation(value = "查询流程参数", notes = "根据流程实例查询流程参数")
    @ApiImplicitParam(paramType = "path", name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    public Result<HashMap<String, Object>>  getProcessVariables(@PathVariable(value = "processInstanceId") String processInstanceId){
        Map<String, Object> variables=activityService.getProcessVariables(processInstanceId);
        return new Result<HashMap<String, Object>>().sucess(variables);

    }

    @Override
    @ApiOperation(value = "查询流程实例是否结束", notes = "根据流程实例查询流程是否结束")
    @ApiImplicitParam(paramType = "query", name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    public Result workflowEnd(@RequestParam(name = "processInstanceId") String processInstanceId){
        return Result.success(bpmProcessTaskService.workflowEnd(processInstanceId));
    }

    @Override
    @ApiOperation(value = "查询流程任务是否完成", notes = "根据流程实例和任务查询任务是否完成")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "processInstanceId", value = "流程实例ID", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "taskId", value = "流程实例ID", required = true, dataType = "String")})
    public Result taskIsEnd(@RequestParam(name = "processInstanceId") String processInstanceId,@RequestParam(name = "taskId") String taskId){
        return Result.success(bpmProcessTaskService.taskIsEnd(processInstanceId,taskId));
    }

    @Override
    @ApiOperation(value = "查询流程跟踪图", notes = "根据流程实例id查询流程跟踪图")
    @ApiImplicitParam(paramType = "path", name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    public  void  getFlowImgByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId, HttpServletResponse response){
        InputStream imageStream = bpmProcessTaskService.getFlowImgByInstantId(processInstanceId);
        response.setContentType("image/png");
        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        try {
            while ((len = imageStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(imageStream!=null){
                try {
                    imageStream.close();
                }catch (IOException ioe){

                }
            }
        }
    }

    @Override
    @ApiOperation(value = "查询流程跟踪图的基本信息", notes = "根据流程实例id查询流程跟踪图的基本信息")
    @ApiImplicitParam(paramType = "path", name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    public  Result  getFlowImgInfoByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId){
        return Result.success(bpmProcessTaskService.getFlowImgInfoByInstantId(processInstanceId));

    }

}




package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessTaskForm;
import com.deloitte.platform.api.contract.vo.ProcessTaskVo;
import com.deloitte.platform.api.contract.client.ProcessTaskClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IProcessTaskService;
import com.deloitte.services.contract.entity.ProcessTask;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-02
 * @Description :   ProcessTask控制器实现类
 * @Modified :
 */
@Api(tags = "流程任务节点操作接口")
@Slf4j
@RestController
public class ProcessTaskController implements ProcessTaskClient {

    @Autowired
    public IProcessTaskService  processTaskService;


    @Override
    @ApiOperation(value = "新增ProcessTask", notes = "新增一个ProcessTask")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="processTaskForm",value="新增ProcessTask的form表单",required=true)  ProcessTaskForm processTaskForm) {
        log.info("form:",  processTaskForm.toString());
        ProcessTask processTask =new BeanUtils<ProcessTask>().copyObj(processTaskForm,ProcessTask.class);
        return Result.success( processTaskService.save(processTask));
    }


    @Override
    @ApiOperation(value = "删除ProcessTask", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProcessTaskID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        processTaskService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ProcessTask", notes = "修改指定ProcessTask信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ProcessTask的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="processTaskForm",value="修改ProcessTask的form表单",required=true)  ProcessTaskForm processTaskForm) {
        ProcessTask processTask =new BeanUtils<ProcessTask>().copyObj(processTaskForm,ProcessTask.class);
        processTask.setId(id);
        processTaskService.saveOrUpdate(processTask);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ProcessTask", notes = "获取指定ID的ProcessTask信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProcessTask的ID", required = true, dataType = "long")
    public Result<ProcessTaskVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ProcessTask processTask=processTaskService.getById(id);
        ProcessTaskVo processTaskVo=new BeanUtils<ProcessTaskVo>().copyObj(processTask,ProcessTaskVo.class);
        return new Result<ProcessTaskVo>().sucess(processTaskVo);
    }

    @Override
    @ApiOperation(value = "列表查询ProcessTask", notes = "根据条件查询ProcessTask列表数据")
    public Result<List<ProcessTaskVo>> list(@Valid @RequestBody @ApiParam(name="processTaskQueryForm",value="ProcessTask查询参数",required=true) ProcessTaskQueryForm processTaskQueryForm) {
        log.info("search with processTaskQueryForm:", processTaskQueryForm.toString());
        List<ProcessTask> processTaskList=processTaskService.selectList(processTaskQueryForm);
        List<ProcessTaskVo> processTaskVoList=new BeanUtils<ProcessTaskVo>().copyObjs(processTaskList,ProcessTaskVo.class);
        return new Result<List<ProcessTaskVo>>().sucess(processTaskVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ProcessTask", notes = "根据条件查询ProcessTask分页数据")
    public Result<IPage<ProcessTaskVo>> search(@Valid @RequestBody @ApiParam(name="processTaskQueryForm",value="ProcessTask查询参数",required=true) ProcessTaskQueryForm processTaskQueryForm) {
        log.info("search with processTaskQueryForm:", processTaskQueryForm.toString());
        IPage<ProcessTask> processTaskPage=processTaskService.selectPage(processTaskQueryForm);
        IPage<ProcessTaskVo> processTaskVoPage=new BeanUtils<ProcessTaskVo>().copyPageObjs(processTaskPage,ProcessTaskVo.class);
        return new Result<IPage<ProcessTaskVo>>().sucess(processTaskVoPage);
    }

}




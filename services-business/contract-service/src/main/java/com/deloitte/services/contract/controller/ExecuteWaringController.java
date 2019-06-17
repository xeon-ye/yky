package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.ExecuteWaringQueryForm;
import com.deloitte.platform.api.contract.vo.ExecuteWaringForm;
import com.deloitte.platform.api.contract.vo.ExecuteWaringVo;
import com.deloitte.platform.api.contract.client.ExecuteWaringClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IExecuteWaringService;
import com.deloitte.services.contract.entity.ExecuteWaring;


/**
 * @Author : yangyq
 * @Date : Create in 2019-04-28
 * @Description :   ExecuteWaring控制器实现类
 * @Modified :
 */
@Api(tags = "ExecuteWaring操作接口")
@Slf4j
@RestController
public class ExecuteWaringController implements ExecuteWaringClient {

    @Autowired
    public IExecuteWaringService  executeWaringService;


    @Override
    @ApiOperation(value = "新增ExecuteWaring", notes = "新增一个ExecuteWaring")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="executeWaringForm",value="新增ExecuteWaring的form表单",required=true)  ExecuteWaringForm executeWaringForm) {
        log.info("form:",  executeWaringForm.toString());
        ExecuteWaring executeWaring =new BeanUtils<ExecuteWaring>().copyObj(executeWaringForm,ExecuteWaring.class);
        return Result.success( executeWaringService.save(executeWaring));
    }


    @Override
    @ApiOperation(value = "删除ExecuteWaring", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExecuteWaringID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        executeWaringService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ExecuteWaring", notes = "修改指定ExecuteWaring信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ExecuteWaring的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="executeWaringForm",value="修改ExecuteWaring的form表单",required=true)  ExecuteWaringForm executeWaringForm) {
        ExecuteWaring executeWaring =new BeanUtils<ExecuteWaring>().copyObj(executeWaringForm,ExecuteWaring.class);
        executeWaring.setId(id);
        executeWaringService.saveOrUpdate(executeWaring);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ExecuteWaring", notes = "获取指定ID的ExecuteWaring信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExecuteWaring的ID", required = true, dataType = "long")
    public Result<ExecuteWaringVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ExecuteWaring executeWaring=executeWaringService.getById(id);
        ExecuteWaringVo executeWaringVo=new BeanUtils<ExecuteWaringVo>().copyObj(executeWaring,ExecuteWaringVo.class);
        return new Result<ExecuteWaringVo>().sucess(executeWaringVo);
    }

    @Override
    @ApiOperation(value = "列表查询ExecuteWaring", notes = "根据条件查询ExecuteWaring列表数据")
    public Result<List<ExecuteWaringVo>> list(@Valid @RequestBody @ApiParam(name="executeWaringQueryForm",value="ExecuteWaring查询参数",required=true) ExecuteWaringQueryForm executeWaringQueryForm) {
        log.info("search with executeWaringQueryForm:", executeWaringQueryForm.toString());
        List<ExecuteWaring> executeWaringList=executeWaringService.selectList(executeWaringQueryForm);
        List<ExecuteWaringVo> executeWaringVoList=new BeanUtils<ExecuteWaringVo>().copyObjs(executeWaringList,ExecuteWaringVo.class);
        return new Result<List<ExecuteWaringVo>>().sucess(executeWaringVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ExecuteWaring", notes = "根据条件查询ExecuteWaring分页数据")
    public Result<IPage<ExecuteWaringVo>> search(@Valid @RequestBody @ApiParam(name="executeWaringQueryForm",value="ExecuteWaring查询参数",required=true) ExecuteWaringQueryForm executeWaringQueryForm) {
        log.info("search with executeWaringQueryForm:", executeWaringQueryForm.toString());
        IPage<ExecuteWaring> executeWaringPage=executeWaringService.selectPage(executeWaringQueryForm);
        IPage<ExecuteWaringVo> executeWaringVoPage=new BeanUtils<ExecuteWaringVo>().copyPageObjs(executeWaringPage,ExecuteWaringVo.class);
        return new Result<IPage<ExecuteWaringVo>>().sucess(executeWaringVoPage);
    }

}




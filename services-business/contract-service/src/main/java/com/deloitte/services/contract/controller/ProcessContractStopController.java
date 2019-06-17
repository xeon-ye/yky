package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.ProcessContractStopQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessContractStopForm;
import com.deloitte.platform.api.contract.vo.ProcessContractStopVo;
import com.deloitte.platform.api.contract.client.ProcessContractStopClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IProcessContractStopService;
import com.deloitte.services.contract.entity.ProcessContractStop;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   ProcessContractStop控制器实现类
 * @Modified :
 */
@Api(tags = "合同作废操作接口")
@Slf4j
@RestController
public class ProcessContractStopController implements ProcessContractStopClient {

    @Autowired
    public IProcessContractStopService  processContractStopService;


    @Override
    @ApiOperation(value = "新增ProcessContractStop", notes = "新增一个ProcessContractStop")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="processContractStopForm",value="新增ProcessContractStop的form表单",required=true)  ProcessContractStopForm processContractStopForm) {
        log.info("form:",  processContractStopForm.toString());
        ProcessContractStop processContractStop =new BeanUtils<ProcessContractStop>().copyObj(processContractStopForm,ProcessContractStop.class);
        return Result.success( processContractStopService.save(processContractStop));
    }


    @Override
    @ApiOperation(value = "删除ProcessContractStop", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProcessContractStopID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        processContractStopService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ProcessContractStop", notes = "修改指定ProcessContractStop信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ProcessContractStop的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="processContractStopForm",value="修改ProcessContractStop的form表单",required=true)  ProcessContractStopForm processContractStopForm) {
        ProcessContractStop processContractStop =new BeanUtils<ProcessContractStop>().copyObj(processContractStopForm,ProcessContractStop.class);
        processContractStop.setId(id);
        processContractStopService.saveOrUpdate(processContractStop);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ProcessContractStop", notes = "获取指定ID的ProcessContractStop信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProcessContractStop的ID", required = true, dataType = "long")
    public Result<ProcessContractStopVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ProcessContractStop processContractStop=processContractStopService.getById(id);
        ProcessContractStopVo processContractStopVo=new BeanUtils<ProcessContractStopVo>().copyObj(processContractStop,ProcessContractStopVo.class);
        return new Result<ProcessContractStopVo>().sucess(processContractStopVo);
    }

    @Override
    @ApiOperation(value = "列表查询ProcessContractStop", notes = "根据条件查询ProcessContractStop列表数据")
    public Result<List<ProcessContractStopVo>> list(@Valid @RequestBody @ApiParam(name="processContractStopQueryForm",value="ProcessContractStop查询参数",required=true) ProcessContractStopQueryForm processContractStopQueryForm) {
        log.info("search with processContractStopQueryForm:", processContractStopQueryForm.toString());
        List<ProcessContractStop> processContractStopList=processContractStopService.selectList(processContractStopQueryForm);
        List<ProcessContractStopVo> processContractStopVoList=new BeanUtils<ProcessContractStopVo>().copyObjs(processContractStopList,ProcessContractStopVo.class);
        return new Result<List<ProcessContractStopVo>>().sucess(processContractStopVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ProcessContractStop", notes = "根据条件查询ProcessContractStop分页数据")
    public Result<IPage<ProcessContractStopVo>> search(@Valid @RequestBody @ApiParam(name="processContractStopQueryForm",value="ProcessContractStop查询参数",required=true) ProcessContractStopQueryForm processContractStopQueryForm) {
        log.info("search with processContractStopQueryForm:", processContractStopQueryForm.toString());
        IPage<ProcessContractStop> processContractStopPage=processContractStopService.selectPage(processContractStopQueryForm);
        IPage<ProcessContractStopVo> processContractStopVoPage=new BeanUtils<ProcessContractStopVo>().copyPageObjs(processContractStopPage,ProcessContractStopVo.class);
        return new Result<IPage<ProcessContractStopVo>>().sucess(processContractStopVoPage);
    }

}




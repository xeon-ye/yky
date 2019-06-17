package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.ProcessExecuterTransferQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessExecuterTransferForm;
import com.deloitte.platform.api.contract.vo.ProcessExecuterTransferVo;
import com.deloitte.platform.api.contract.client.ProcessExecuterTransferClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IProcessExecuterTransferService;
import com.deloitte.services.contract.entity.ProcessExecuterTransfer;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   ProcessExecuterTransfer控制器实现类
 * @Modified :
 */
@Api(tags = "流程履行人移交操作接口")
@Slf4j
@RestController
public class ProcessExecuterTransferController implements ProcessExecuterTransferClient {

    @Autowired
    public IProcessExecuterTransferService  processExecuterTransferService;


    @Override
    @ApiOperation(value = "新增ProcessExecuterTransfer", notes = "新增一个ProcessExecuterTransfer")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="processExecuterTransferForm",value="新增ProcessExecuterTransfer的form表单",required=true)  ProcessExecuterTransferForm processExecuterTransferForm) {
        log.info("form:",  processExecuterTransferForm.toString());
        ProcessExecuterTransfer processExecuterTransfer =new BeanUtils<ProcessExecuterTransfer>().copyObj(processExecuterTransferForm,ProcessExecuterTransfer.class);
        return Result.success( processExecuterTransferService.save(processExecuterTransfer));
    }


    @Override
    @ApiOperation(value = "删除ProcessExecuterTransfer", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProcessExecuterTransferID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        processExecuterTransferService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ProcessExecuterTransfer", notes = "修改指定ProcessExecuterTransfer信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ProcessExecuterTransfer的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="processExecuterTransferForm",value="修改ProcessExecuterTransfer的form表单",required=true)  ProcessExecuterTransferForm processExecuterTransferForm) {
        ProcessExecuterTransfer processExecuterTransfer =new BeanUtils<ProcessExecuterTransfer>().copyObj(processExecuterTransferForm,ProcessExecuterTransfer.class);
        processExecuterTransfer.setId(id);
        processExecuterTransferService.saveOrUpdate(processExecuterTransfer);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ProcessExecuterTransfer", notes = "获取指定ID的ProcessExecuterTransfer信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProcessExecuterTransfer的ID", required = true, dataType = "long")
    public Result<ProcessExecuterTransferVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ProcessExecuterTransfer processExecuterTransfer=processExecuterTransferService.getById(id);
        ProcessExecuterTransferVo processExecuterTransferVo=new BeanUtils<ProcessExecuterTransferVo>().copyObj(processExecuterTransfer,ProcessExecuterTransferVo.class);
        return new Result<ProcessExecuterTransferVo>().sucess(processExecuterTransferVo);
    }

    @Override
    @ApiOperation(value = "列表查询ProcessExecuterTransfer", notes = "根据条件查询ProcessExecuterTransfer列表数据")
    public Result<List<ProcessExecuterTransferVo>> list(@Valid @RequestBody @ApiParam(name="processExecuterTransferQueryForm",value="ProcessExecuterTransfer查询参数",required=true) ProcessExecuterTransferQueryForm processExecuterTransferQueryForm) {
        log.info("search with processExecuterTransferQueryForm:", processExecuterTransferQueryForm.toString());
        List<ProcessExecuterTransfer> processExecuterTransferList=processExecuterTransferService.selectList(processExecuterTransferQueryForm);
        List<ProcessExecuterTransferVo> processExecuterTransferVoList=new BeanUtils<ProcessExecuterTransferVo>().copyObjs(processExecuterTransferList,ProcessExecuterTransferVo.class);
        return new Result<List<ProcessExecuterTransferVo>>().sucess(processExecuterTransferVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ProcessExecuterTransfer", notes = "根据条件查询ProcessExecuterTransfer分页数据")
    public Result<IPage<ProcessExecuterTransferVo>> search(@Valid @RequestBody @ApiParam(name="processExecuterTransferQueryForm",value="ProcessExecuterTransfer查询参数",required=true) ProcessExecuterTransferQueryForm processExecuterTransferQueryForm) {
        log.info("search with processExecuterTransferQueryForm:", processExecuterTransferQueryForm.toString());
        IPage<ProcessExecuterTransfer> processExecuterTransferPage=processExecuterTransferService.selectPage(processExecuterTransferQueryForm);
        IPage<ProcessExecuterTransferVo> processExecuterTransferVoPage=new BeanUtils<ProcessExecuterTransferVo>().copyPageObjs(processExecuterTransferPage,ProcessExecuterTransferVo.class);
        return new Result<IPage<ProcessExecuterTransferVo>>().sucess(processExecuterTransferVoPage);
    }

}




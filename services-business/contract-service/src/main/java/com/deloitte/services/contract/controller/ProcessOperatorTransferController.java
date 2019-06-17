package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.ProcessOperatorTransferQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessOperatorTransferForm;
import com.deloitte.platform.api.contract.vo.ProcessOperatorTransferVo;
import com.deloitte.platform.api.contract.client.ProcessOperatorTransferClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IProcessOperatorTransferService;
import com.deloitte.services.contract.entity.ProcessOperatorTransfer;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   ProcessOperatorTransfer控制器实现类
 * @Modified :
 */
@Api(tags = "流程经办人操作接口")
@Slf4j
@RestController
public class ProcessOperatorTransferController implements ProcessOperatorTransferClient {

    @Autowired
    public IProcessOperatorTransferService  processOperatorTransferService;


    @Override
    @ApiOperation(value = "新增ProcessOperatorTransfer", notes = "新增一个ProcessOperatorTransfer")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="processOperatorTransferForm",value="新增ProcessOperatorTransfer的form表单",required=true)  ProcessOperatorTransferForm processOperatorTransferForm) {
        log.info("form:",  processOperatorTransferForm.toString());
        ProcessOperatorTransfer processOperatorTransfer =new BeanUtils<ProcessOperatorTransfer>().copyObj(processOperatorTransferForm,ProcessOperatorTransfer.class);
        return Result.success( processOperatorTransferService.save(processOperatorTransfer));
    }


    @Override
    @ApiOperation(value = "删除ProcessOperatorTransfer", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProcessOperatorTransferID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        processOperatorTransferService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ProcessOperatorTransfer", notes = "修改指定ProcessOperatorTransfer信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ProcessOperatorTransfer的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="processOperatorTransferForm",value="修改ProcessOperatorTransfer的form表单",required=true)  ProcessOperatorTransferForm processOperatorTransferForm) {
        ProcessOperatorTransfer processOperatorTransfer =new BeanUtils<ProcessOperatorTransfer>().copyObj(processOperatorTransferForm,ProcessOperatorTransfer.class);
        processOperatorTransfer.setId(id);
        processOperatorTransferService.saveOrUpdate(processOperatorTransfer);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ProcessOperatorTransfer", notes = "获取指定ID的ProcessOperatorTransfer信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProcessOperatorTransfer的ID", required = true, dataType = "long")
    public Result<ProcessOperatorTransferVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ProcessOperatorTransfer processOperatorTransfer=processOperatorTransferService.getById(id);
        ProcessOperatorTransferVo processOperatorTransferVo=new BeanUtils<ProcessOperatorTransferVo>().copyObj(processOperatorTransfer,ProcessOperatorTransferVo.class);
        return new Result<ProcessOperatorTransferVo>().sucess(processOperatorTransferVo);
    }

    @Override
    @ApiOperation(value = "列表查询ProcessOperatorTransfer", notes = "根据条件查询ProcessOperatorTransfer列表数据")
    public Result<List<ProcessOperatorTransferVo>> list(@Valid @RequestBody @ApiParam(name="processOperatorTransferQueryForm",value="ProcessOperatorTransfer查询参数",required=true) ProcessOperatorTransferQueryForm processOperatorTransferQueryForm) {
        log.info("search with processOperatorTransferQueryForm:", processOperatorTransferQueryForm.toString());
        List<ProcessOperatorTransfer> processOperatorTransferList=processOperatorTransferService.selectList(processOperatorTransferQueryForm);
        List<ProcessOperatorTransferVo> processOperatorTransferVoList=new BeanUtils<ProcessOperatorTransferVo>().copyObjs(processOperatorTransferList,ProcessOperatorTransferVo.class);
        return new Result<List<ProcessOperatorTransferVo>>().sucess(processOperatorTransferVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ProcessOperatorTransfer", notes = "根据条件查询ProcessOperatorTransfer分页数据")
    public Result<IPage<ProcessOperatorTransferVo>> search(@Valid @RequestBody @ApiParam(name="processOperatorTransferQueryForm",value="ProcessOperatorTransfer查询参数",required=true) ProcessOperatorTransferQueryForm processOperatorTransferQueryForm) {
        log.info("search with processOperatorTransferQueryForm:", processOperatorTransferQueryForm.toString());
        IPage<ProcessOperatorTransfer> processOperatorTransferPage=processOperatorTransferService.selectPage(processOperatorTransferQueryForm);
        IPage<ProcessOperatorTransferVo> processOperatorTransferVoPage=new BeanUtils<ProcessOperatorTransferVo>().copyPageObjs(processOperatorTransferPage,ProcessOperatorTransferVo.class);
        return new Result<IPage<ProcessOperatorTransferVo>>().sucess(processOperatorTransferVoPage);
    }

}




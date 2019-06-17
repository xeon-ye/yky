package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.param.ProcessQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.ProcessForm;
import com.deloitte.platform.api.contract.vo.ProcessVo;
import com.deloitte.platform.api.contract.client.ProcessClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.AssertUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import com.deloitte.services.contract.service.IProcessService;
import com.deloitte.services.contract.entity.Process;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-30
 * @Description :   流程列表控制器实现类
 * @Modified :
 */
@Api(tags = "9-系统流程列表操作接口")
@Slf4j
@RestController
@RequestMapping("/process")
public class ProcessController implements ProcessClient {

    @Autowired
    public IProcessService  processService;
    @Autowired
    public BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Override
    @ApiOperation(value = "新增Process", notes = "新增一个Process")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="processForm",value="新增Process的form表单",required=true)  ProcessForm processForm) {
        log.info("form:",  processForm.toString());
        Process process =new BeanUtils<Process>().copyObj(processForm,Process.class);
        return Result.success( processService.save(process));
    }


    @Override
    @ApiOperation(value = "删除Process", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProcessID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        processService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Process", notes = "修改指定Process信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Process的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="processForm",value="修改Process的form表单",required=true)  ProcessForm processForm) {
        Process process =new BeanUtils<Process>().copyObj(processForm,Process.class);
        process.setId(id);
        processService.saveOrUpdate(process);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Process", notes = "获取指定ID的Process信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Process的ID", required = true, dataType = "long")
    public Result<ProcessVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Process process=processService.getById(id);
        ProcessVo processVo=new BeanUtils<ProcessVo>().copyObj(process,ProcessVo.class);
        return new Result<ProcessVo>().sucess(processVo);
    }

    @Override
    @ApiOperation(value = "2-3-4获取流程列表查询", notes = "2-3-4获取流程列表查询")
    @PostMapping("/list")
    public Result<List<ProcessVo>> list(@Valid @RequestBody ProcessQueryForm processQueryForm) {
        /*log.info("search with processQueryForm:", processQueryForm.toString());
        List<Process> processList=processService.selectList(processQueryForm);
        List<ProcessVo> processVoList=new BeanUtils<ProcessVo>().copyObjs(processList,ProcessVo.class);*/
        List<ProcessVo> processVoList= new ArrayList<>();
        ProcessVo processVo = new ProcessVo();
        processVo.setId(2l);
        processVo.setProcessDefineKey("C1000000000200");
        processVo.setProcessDefineName("院校合同审批流程-已超限额");
        processVoList.add(processVo);
        return new Result<List<ProcessVo>>().sucess(processVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Process", notes = "根据条件查询Process分页数据")
    public Result<IPage<ProcessVo>> search(@Valid @RequestBody @ApiParam(name="processQueryForm",value="Process查询参数",required=true) ProcessQueryForm processQueryForm) {
        log.info("search with processQueryForm:", processQueryForm.toString());
        IPage<Process> processPage=processService.selectPage(processQueryForm);
        IPage<ProcessVo> processVoPage=new BeanUtils<ProcessVo>().copyPageObjs(processPage,ProcessVo.class);
        return new Result<IPage<ProcessVo>>().sucess(processVoPage);
    }

    @ApiOperation(value = "2-3-4获取流程列表查询-合同起草", notes = "2-3-4获取流程列表查询-合同起草")
    @PostMapping("/listProcessSubmit")
    public Result<List<ProcessVo>> listProcessSubmit(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm) {
        AssertUtils.asserts(null == basicInfoQueryForm || null == basicInfoQueryForm.getAmountIncludTax() , ContractErrorType.AMOUNT_IS_NULL);
        ProcessQueryForm processQueryForm = new ProcessQueryForm();
        processQueryForm.setContractAmountMin(basicInfoQueryForm.getAmountIncludTax()+"");
        processQueryForm.setContractAmountMax(basicInfoQueryForm.getAmountIncludTax()+"");
        processQueryForm.setProcessType(VoucherTypeEnums.CONTRACT_BOOK.getCode());
        List<Process> processList = processService.selectList(processQueryForm);
        List<ProcessVo> processVoList=new BeanUtils<ProcessVo>().copyObjs(processList,ProcessVo.class);
        return new Result<List<ProcessVo>>().sucess(processVoList);
    }

    @ApiOperation(value = "获取流程列表查询-合同作废", notes = "获取流程列表查询-合同作废")
    @PostMapping("/listProcessUnable")
    public Result<List<ProcessVo>> listProcessUnable(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm) {
        AssertUtils.asserts(null == basicInfoQueryForm || null == basicInfoQueryForm.getAmountIncludTax() , ContractErrorType.AMOUNT_IS_NULL);
        ProcessQueryForm processQueryForm = new ProcessQueryForm();
        processQueryForm.setContractAmountMin(basicInfoQueryForm.getAmountIncludTax()+"");
        processQueryForm.setContractAmountMax(basicInfoQueryForm.getAmountIncludTax()+"");
        processQueryForm.setProcessType(VoucherTypeEnums.CONTRACT_UNABLE.getCode());
        List<Process> processList = processService.selectList(processQueryForm);
        List<ProcessVo> processVoList=new BeanUtils<ProcessVo>().copyObjs(processList,ProcessVo.class);
        return new Result<List<ProcessVo>>().sucess(processVoList);
    }
    @ApiOperation(value = "获取流程图的各节点信息", notes = "获取流程图的各节点信息")
    @PostMapping(value = "/history/imageInfo/{processInstanceId}")
    public Result getFlowImgInfoByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId){
        return bpmProcessTaskFeignService.getFlowImgInfoByInstantId(processInstanceId);
    }
}




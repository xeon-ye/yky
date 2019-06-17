package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.project.param.ApprovalProcessQueryForm;
import com.deloitte.platform.api.project.vo.ApprovalProcessForm;
import com.deloitte.platform.api.project.vo.ApprovalProcessVo;
import com.deloitte.platform.api.project.client.ApprovalProcessClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IApprovalProcessService;
import com.deloitte.services.project.entity.ApprovalProcess;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-22
 * @Description :   ApprovalProcess控制器实现类
 * @Modified :
 */
@Api(tags = "ApprovalProcess操作接口")
@Slf4j
@RestController
public class ApprovalProcessController implements ApprovalProcessClient {

    @Autowired
    public IApprovalProcessService  approvalProcessService;
    @Autowired
    public BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Override
    @ApiOperation(value = "新增ApprovalProcess", notes = "新增一个ApprovalProcess")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="approvalProcessForm",value="新增ApprovalProcess的form表单",required=true)  ApprovalProcessForm approvalProcessForm) {
        log.info("form:",  approvalProcessForm.toString());
        ApprovalProcess approvalProcess =new BeanUtils<ApprovalProcess>().copyObj(approvalProcessForm,ApprovalProcess.class);
        return Result.success( approvalProcessService.save(approvalProcess));
    }


    @Override
    @ApiOperation(value = "删除ApprovalProcess", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApprovalProcessID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        approvalProcessService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ApprovalProcess", notes = "修改指定ApprovalProcess信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ApprovalProcess的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="approvalProcessForm",value="修改ApprovalProcess的form表单",required=true)  ApprovalProcessForm approvalProcessForm) {
        ApprovalProcess approvalProcess =new BeanUtils<ApprovalProcess>().copyObj(approvalProcessForm,ApprovalProcess.class);
        approvalProcess.setId(id);
        approvalProcessService.saveOrUpdate(approvalProcess);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ApprovalProcess", notes = "获取指定ID的ApprovalProcess信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApprovalProcess的ID", required = true, dataType = "long")
    public Result<ApprovalProcessVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ApprovalProcess approvalProcess=approvalProcessService.getById(id);
        ApprovalProcessVo approvalProcessVo=new BeanUtils<ApprovalProcessVo>().copyObj(approvalProcess,ApprovalProcessVo.class);
        return new Result<ApprovalProcessVo>().sucess(approvalProcessVo);
    }

    @Override
    @ApiOperation(value = "列表查询ApprovalProcess", notes = "根据条件查询ApprovalProcess列表数据")
    public Result<List<ApprovalProcessVo>> list(@Valid @RequestBody @ApiParam(name="approvalProcessQueryForm",value="ApprovalProcess查询参数",required=true) ApprovalProcessQueryForm approvalProcessQueryForm) {
        log.info("search with approvalProcessQueryForm:", approvalProcessQueryForm.toString());
        List<ApprovalProcess> approvalProcessList=approvalProcessService.selectList(approvalProcessQueryForm);
        List<ApprovalProcessVo> approvalProcessVoList=new BeanUtils<ApprovalProcessVo>().copyObjs(approvalProcessList,ApprovalProcessVo.class);
        return new Result<List<ApprovalProcessVo>>().sucess(approvalProcessVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ApprovalProcess", notes = "根据条件查询ApprovalProcess分页数据")
    public Result<IPage<ApprovalProcessVo>> search(@Valid @RequestBody @ApiParam(name="approvalProcessQueryForm",value="ApprovalProcess查询参数",required=true) ApprovalProcessQueryForm approvalProcessQueryForm) {
        log.info("search with approvalProcessQueryForm:", approvalProcessQueryForm.toString());
        IPage<ApprovalProcess> approvalProcessPage=approvalProcessService.selectPage(approvalProcessQueryForm);
        IPage<ApprovalProcessVo> approvalProcessVoPage=new BeanUtils<ApprovalProcessVo>().copyPageObjs(approvalProcessPage,ApprovalProcessVo.class);
        return new Result<IPage<ApprovalProcessVo>>().sucess(approvalProcessVoPage);
    }
    @ApiOperation(value = "获取流程图的各节点信息", notes = "获取流程图的各节点信息")
    @PostMapping(value = "/history/imageInfo/{processInstanceId}")
    public Result getFlowImgInfoByInstantId(@PathVariable(value = "processInstanceId") String processInstanceId){
        return bpmProcessTaskFeignService.getFlowImgInfoByInstantId(processInstanceId);
    }
}




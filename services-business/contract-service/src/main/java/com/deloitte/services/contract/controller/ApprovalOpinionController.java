package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.ApprovalOpinionQueryForm;
import com.deloitte.platform.api.contract.vo.ApprovalOpinionForm;
import com.deloitte.platform.api.contract.vo.ApprovalOpinionVo;
import com.deloitte.platform.api.contract.client.ApprovalOpinionClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IApprovalOpinionService;
import com.deloitte.services.contract.entity.ApprovalOpinion;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-01
 * @Description :   ApprovalOpinion控制器实现类
 * @Modified :
 */
@Api(tags = "审批意见操作接口")
@Slf4j
@RestController
public class ApprovalOpinionController implements ApprovalOpinionClient {

    @Autowired
    public IApprovalOpinionService  approvalOpinionService;


    @Override
    @ApiOperation(value = "新增ApprovalOpinion", notes = "新增一个ApprovalOpinion")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="approvalOpinionForm",value="新增ApprovalOpinion的form表单",required=true)  ApprovalOpinionForm approvalOpinionForm) {
        log.info("form:",  approvalOpinionForm.toString());
        ApprovalOpinion approvalOpinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionForm,ApprovalOpinion.class);
        return Result.success( approvalOpinionService.save(approvalOpinion));
    }


    @Override
    @ApiOperation(value = "删除ApprovalOpinion", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApprovalOpinionID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        approvalOpinionService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ApprovalOpinion", notes = "修改指定ApprovalOpinion信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ApprovalOpinion的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="approvalOpinionForm",value="修改ApprovalOpinion的form表单",required=true)  ApprovalOpinionForm approvalOpinionForm) {
        ApprovalOpinion approvalOpinion =new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionForm,ApprovalOpinion.class);
        approvalOpinion.setId(id);
        approvalOpinionService.saveOrUpdate(approvalOpinion);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ApprovalOpinion", notes = "获取指定ID的ApprovalOpinion信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApprovalOpinion的ID", required = true, dataType = "long")
    public Result<ApprovalOpinionVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ApprovalOpinion approvalOpinion=approvalOpinionService.getById(id);
        ApprovalOpinionVo approvalOpinionVo=new BeanUtils<ApprovalOpinionVo>().copyObj(approvalOpinion,ApprovalOpinionVo.class);
        return new Result<ApprovalOpinionVo>().sucess(approvalOpinionVo);
    }

    @Override
    @ApiOperation(value = "列表查询ApprovalOpinion", notes = "根据条件查询ApprovalOpinion列表数据")
    public Result<List<ApprovalOpinionVo>> list(@Valid @RequestBody @ApiParam(name="approvalOpinionQueryForm",value="ApprovalOpinion查询参数",required=true) ApprovalOpinionQueryForm approvalOpinionQueryForm) {
        log.info("search with approvalOpinionQueryForm:", approvalOpinionQueryForm.toString());
        List<ApprovalOpinion> approvalOpinionList=approvalOpinionService.selectList(approvalOpinionQueryForm);
        List<ApprovalOpinionVo> approvalOpinionVoList=new BeanUtils<ApprovalOpinionVo>().copyObjs(approvalOpinionList,ApprovalOpinionVo.class);
        return new Result<List<ApprovalOpinionVo>>().sucess(approvalOpinionVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ApprovalOpinion", notes = "根据条件查询ApprovalOpinion分页数据")
    public Result<IPage<ApprovalOpinionVo>> search(@Valid @RequestBody @ApiParam(name="approvalOpinionQueryForm",value="ApprovalOpinion查询参数",required=true) ApprovalOpinionQueryForm approvalOpinionQueryForm) {
        log.info("search with approvalOpinionQueryForm:", approvalOpinionQueryForm.toString());
        IPage<ApprovalOpinion> approvalOpinionPage=approvalOpinionService.selectPage(approvalOpinionQueryForm);
        IPage<ApprovalOpinionVo> approvalOpinionVoPage=new BeanUtils<ApprovalOpinionVo>().copyPageObjs(approvalOpinionPage,ApprovalOpinionVo.class);
        return new Result<IPage<ApprovalOpinionVo>>().sucess(approvalOpinionVoPage);
    }

}




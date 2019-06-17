package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.ApprovalVoucherQueryForm;
import com.deloitte.platform.api.contract.vo.ApprovalVoucherForm;
import com.deloitte.platform.api.contract.vo.ApprovalVoucherVo;
import com.deloitte.platform.api.contract.client.ApprovalVoucherClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IApprovalVoucherService;
import com.deloitte.services.contract.entity.ApprovalVoucher;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-27
 * @Description :   ApprovalVoucher控制器实现类
 * @Modified :
 */
@Api(tags = "流程单据操作接口")
@Slf4j
@RestController
public class ApprovalVoucherController implements ApprovalVoucherClient {

    @Autowired
    public IApprovalVoucherService  approvalVoucherService;


    @Override
    @ApiOperation(value = "新增ApprovalVoucher", notes = "新增一个ApprovalVoucher")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="approvalVoucherForm",value="新增ApprovalVoucher的form表单",required=true)  ApprovalVoucherForm approvalVoucherForm) {
        log.info("form:",  approvalVoucherForm.toString());
        ApprovalVoucher approvalVoucher =new BeanUtils<ApprovalVoucher>().copyObj(approvalVoucherForm,ApprovalVoucher.class);
        return Result.success( approvalVoucherService.save(approvalVoucher));
    }


    @Override
    @ApiOperation(value = "删除ApprovalVoucher", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApprovalVoucherID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        approvalVoucherService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ApprovalVoucher", notes = "修改指定ApprovalVoucher信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ApprovalVoucher的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="approvalVoucherForm",value="修改ApprovalVoucher的form表单",required=true)  ApprovalVoucherForm approvalVoucherForm) {
        ApprovalVoucher approvalVoucher =new BeanUtils<ApprovalVoucher>().copyObj(approvalVoucherForm,ApprovalVoucher.class);
        approvalVoucher.setId(id);
        approvalVoucherService.saveOrUpdate(approvalVoucher);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ApprovalVoucher", notes = "获取指定ID的ApprovalVoucher信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApprovalVoucher的ID", required = true, dataType = "long")
    public Result<ApprovalVoucherVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ApprovalVoucher approvalVoucher=approvalVoucherService.getById(id);
        ApprovalVoucherVo approvalVoucherVo=new BeanUtils<ApprovalVoucherVo>().copyObj(approvalVoucher,ApprovalVoucherVo.class);
        return new Result<ApprovalVoucherVo>().sucess(approvalVoucherVo);
    }

    @Override
    @ApiOperation(value = "列表查询ApprovalVoucher", notes = "根据条件查询ApprovalVoucher列表数据")
    public Result<List<ApprovalVoucherVo>> list(@Valid @RequestBody @ApiParam(name="approvalVoucherQueryForm",value="ApprovalVoucher查询参数",required=true) ApprovalVoucherQueryForm approvalVoucherQueryForm) {
        log.info("search with approvalVoucherQueryForm:", approvalVoucherQueryForm.toString());
        List<ApprovalVoucher> approvalVoucherList=approvalVoucherService.selectList(approvalVoucherQueryForm);
        List<ApprovalVoucherVo> approvalVoucherVoList=new BeanUtils<ApprovalVoucherVo>().copyObjs(approvalVoucherList,ApprovalVoucherVo.class);
        return new Result<List<ApprovalVoucherVo>>().sucess(approvalVoucherVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ApprovalVoucher", notes = "根据条件查询ApprovalVoucher分页数据")
    public Result<IPage<ApprovalVoucherVo>> search(@Valid @RequestBody @ApiParam(name="approvalVoucherQueryForm",value="ApprovalVoucher查询参数",required=true) ApprovalVoucherQueryForm approvalVoucherQueryForm) {
        log.info("search with approvalVoucherQueryForm:", approvalVoucherQueryForm.toString());
        IPage<ApprovalVoucher> approvalVoucherPage=approvalVoucherService.selectPage(approvalVoucherQueryForm);
        IPage<ApprovalVoucherVo> approvalVoucherVoPage=new BeanUtils<ApprovalVoucherVo>().copyPageObjs(approvalVoucherPage,ApprovalVoucherVo.class);
        return new Result<IPage<ApprovalVoucherVo>>().sucess(approvalVoucherVoPage);
    }

}




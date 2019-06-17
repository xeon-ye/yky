package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ApprovalVouchersQueryForm;
import com.deloitte.platform.api.project.vo.ApprovalVouchersForm;
import com.deloitte.platform.api.project.vo.ApprovalVouchersVo;
import com.deloitte.platform.api.project.client.ApprovalVouchersClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IApprovalVouchersService;
import com.deloitte.services.project.entity.ApprovalVouchers;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-22
 * @Description :   ApprovalVouchers控制器实现类
 * @Modified :
 */
@Api(tags = "ApprovalVouchers操作接口")
@Slf4j
@RestController
public class ApprovalVouchersController implements ApprovalVouchersClient {

    @Autowired
    public IApprovalVouchersService  approvalVouchersService;


    @Override
    @ApiOperation(value = "新增ApprovalVouchers", notes = "新增一个ApprovalVouchers")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="approvalVouchersForm",value="新增ApprovalVouchers的form表单",required=true)  ApprovalVouchersForm approvalVouchersForm) {
        log.info("form:",  approvalVouchersForm.toString());
        ApprovalVouchers approvalVouchers =new BeanUtils<ApprovalVouchers>().copyObj(approvalVouchersForm,ApprovalVouchers.class);
        return Result.success( approvalVouchersService.save(approvalVouchers));
    }


    @Override
    @ApiOperation(value = "删除ApprovalVouchers", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApprovalVouchersID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        approvalVouchersService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ApprovalVouchers", notes = "修改指定ApprovalVouchers信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ApprovalVouchers的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="approvalVouchersForm",value="修改ApprovalVouchers的form表单",required=true)  ApprovalVouchersForm approvalVouchersForm) {
        ApprovalVouchers approvalVouchers =new BeanUtils<ApprovalVouchers>().copyObj(approvalVouchersForm,ApprovalVouchers.class);
        approvalVouchers.setId(id);
        approvalVouchersService.saveOrUpdate(approvalVouchers);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ApprovalVouchers", notes = "获取指定ID的ApprovalVouchers信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApprovalVouchers的ID", required = true, dataType = "long")
    public Result<ApprovalVouchersVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ApprovalVouchers approvalVouchers=approvalVouchersService.getById(id);
        ApprovalVouchersVo approvalVouchersVo=new BeanUtils<ApprovalVouchersVo>().copyObj(approvalVouchers,ApprovalVouchersVo.class);
        return new Result<ApprovalVouchersVo>().sucess(approvalVouchersVo);
    }

    @Override
    @ApiOperation(value = "列表查询ApprovalVouchers", notes = "根据条件查询ApprovalVouchers列表数据")
    public Result<List<ApprovalVouchersVo>> list(@Valid @RequestBody @ApiParam(name="approvalVouchersQueryForm",value="ApprovalVouchers查询参数",required=true) ApprovalVouchersQueryForm approvalVouchersQueryForm) {
        log.info("search with approvalVouchersQueryForm:", approvalVouchersQueryForm.toString());
        List<ApprovalVouchers> approvalVouchersList=approvalVouchersService.selectList(approvalVouchersQueryForm);
        List<ApprovalVouchersVo> approvalVouchersVoList=new BeanUtils<ApprovalVouchersVo>().copyObjs(approvalVouchersList,ApprovalVouchersVo.class);
        return new Result<List<ApprovalVouchersVo>>().sucess(approvalVouchersVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ApprovalVouchers", notes = "根据条件查询ApprovalVouchers分页数据")
    public Result<IPage<ApprovalVouchersVo>> search(@Valid @RequestBody @ApiParam(name="approvalVouchersQueryForm",value="ApprovalVouchers查询参数",required=true) ApprovalVouchersQueryForm approvalVouchersQueryForm) {
        log.info("search with approvalVouchersQueryForm:", approvalVouchersQueryForm.toString());
        IPage<ApprovalVouchers> approvalVouchersPage=approvalVouchersService.selectPage(approvalVouchersQueryForm);
        IPage<ApprovalVouchersVo> approvalVouchersVoPage=new BeanUtils<ApprovalVouchersVo>().copyPageObjs(approvalVouchersPage,ApprovalVouchersVo.class);
        return new Result<IPage<ApprovalVouchersVo>>().sucess(approvalVouchersVoPage);
    }

}




package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.CommonReasonQueryForm;
import com.deloitte.platform.api.contract.vo.CommonReasonForm;
import com.deloitte.platform.api.contract.vo.CommonReasonVo;
import com.deloitte.platform.api.contract.client.CommonReasonClient;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.service.ICommonService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.ICommonReasonService;
import com.deloitte.services.contract.entity.CommonReason;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-09
 * @Description :   CommonReason控制器实现类
 * @Modified :
 */
@Api(tags = "常见原因操作接口")
@Slf4j
@RestController
public class CommonReasonController implements CommonReasonClient {

    @Autowired
    public ICommonReasonService  commonReasonService;
    @Autowired
    private ICommonService commonService;


    @Override
    @ApiOperation(value = "新增常见原因", notes = "新增常见原因")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="commonReasonForm",value="新增CommonReason的form表单",required=true)  CommonReasonForm commonReasonForm) {
        log.info("form:",  commonReasonForm.toString());
        CommonReason commonReason =new BeanUtils<CommonReason>().copyObj(commonReasonForm,CommonReason.class);
        return Result.success( commonReasonService.save(commonReason));
    }


    @Override
    @ApiOperation(value = "删除CommonReason", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "CommonReasonID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        commonReasonService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改CommonReason", notes = "修改指定CommonReason信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "CommonReason的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="commonReasonForm",value="修改CommonReason的form表单",required=true)  CommonReasonForm commonReasonForm) {
        CommonReason commonReason =new BeanUtils<CommonReason>().copyObj(commonReasonForm,CommonReason.class);
        commonReason.setId(id);
        commonReasonService.saveOrUpdate(commonReason);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取CommonReason", notes = "获取指定ID的CommonReason信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "CommonReason的ID", required = true, dataType = "long")
    public Result<CommonReasonVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        CommonReason commonReason=commonReasonService.getById(id);
        CommonReasonVo commonReasonVo=new BeanUtils<CommonReasonVo>().copyObj(commonReason,CommonReasonVo.class);
        return new Result<CommonReasonVo>().sucess(commonReasonVo);
    }

    @Override
    @ApiOperation(value = "列表查询CommonReason", notes = "根据条件查询CommonReason列表数据")
    public Result<List<CommonReasonVo>> list(@Valid @RequestBody @ApiParam(name="commonReasonQueryForm",value="CommonReason查询参数",required=true) CommonReasonQueryForm commonReasonQueryForm) {
        log.info("search with commonReasonQueryForm:", commonReasonQueryForm.toString());
        List<CommonReason> commonReasonList=commonReasonService.selectList(commonReasonQueryForm);
        List<CommonReasonVo> commonReasonVoList=new BeanUtils<CommonReasonVo>().copyObjs(commonReasonList,CommonReasonVo.class);
        return new Result<List<CommonReasonVo>>().sucess(commonReasonVoList);
    }


    @Override
    @ApiOperation(value = "分页查询CommonReason", notes = "根据条件查询CommonReason分页数据")
    public Result<IPage<CommonReasonVo>> search(@Valid @RequestBody @ApiParam(name="commonReasonQueryForm",value="CommonReason查询参数",required=true) CommonReasonQueryForm commonReasonQueryForm) {
        log.info("search with commonReasonQueryForm:", commonReasonQueryForm.toString());
        IPage<CommonReason> commonReasonPage=commonReasonService.selectPage(commonReasonQueryForm);
        IPage<CommonReasonVo> commonReasonVoPage=new BeanUtils<CommonReasonVo>().copyPageObjs(commonReasonPage,CommonReasonVo.class);
        return new Result<IPage<CommonReasonVo>>().sucess(commonReasonVoPage);
    }

    @ApiOperation(value = "新增前用户常常见原因", notes = "新增前用户常常见原因")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping("/saveCommonReason")
    public Result saveCommonReason(@Valid @RequestBody @ApiParam(name="commonReasonForm",value="新增CommonReason的form表单",required=true)  CommonReasonForm commonReasonForm) {
        log.info("form:",  commonReasonForm.toString());
        UserVo userVo = commonService.getCurrentUser();
        commonReasonForm.setCreateBy(userVo.getId());
        CommonReason commonReason =new BeanUtils<CommonReason>().copyObj(commonReasonForm,CommonReason.class);
        return Result.success( commonReasonService.save(commonReason));
    }

    @ApiOperation(value = "查询当前用户常用原因", notes = "查询当前用户常用原因")
    @PostMapping("/getList")
    public Result<IPage<CommonReasonVo>> getList(@Valid @RequestBody @ApiParam(name="commonReasonQueryForm",value="CommonReason查询参数",required=true) CommonReasonQueryForm commonReasonQueryForm) {
        log.info("search with commonReasonQueryForm:", commonReasonQueryForm.toString());
        UserVo userVo = commonService.getCurrentUser();
        commonReasonQueryForm.setCreateBy(userVo.getId());
        IPage<CommonReason> commonReasonPage=commonReasonService.selectPage(commonReasonQueryForm);
        IPage<CommonReasonVo> commonReasonVoPage=new BeanUtils<CommonReasonVo>().copyPageObjs(commonReasonPage,CommonReasonVo.class);
        return new Result<IPage<CommonReasonVo>>().sucess(commonReasonVoPage);
    }
}




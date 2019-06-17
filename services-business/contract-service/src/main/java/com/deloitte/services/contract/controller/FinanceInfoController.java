package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.FinanceInfoQueryForm;
import com.deloitte.platform.api.contract.vo.FinanceInfoForm;
import com.deloitte.platform.api.contract.vo.FinanceInfoVo;
import com.deloitte.platform.api.contract.client.FinanceInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.contract.service.IFinanceInfoService;
import com.deloitte.services.contract.entity.FinanceInfo;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   FinanceInfo控制器实现类
 * @Modified :
 */
@Api(tags = "合同履行-财务信息操作接口")
@Slf4j
@RestController
public class FinanceInfoController implements FinanceInfoClient {

    @Autowired
    public IFinanceInfoService  financeInfoService;


    @Override
    @ApiOperation(value = "新增FinanceInfo", notes = "新增一个FinanceInfo")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="financeInfoForm",value="新增FinanceInfo的form表单",required=true)  FinanceInfoForm financeInfoForm) {
        log.info("form:",  financeInfoForm.toString());
        FinanceInfo financeInfo =new BeanUtils<FinanceInfo>().copyObj(financeInfoForm,FinanceInfo.class);
        return Result.success( financeInfoService.save(financeInfo));
    }


    @Override
    @ApiOperation(value = "删除FinanceInfo", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "FinanceInfoID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        financeInfoService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改FinanceInfo", notes = "修改指定FinanceInfo信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "FinanceInfo的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="financeInfoForm",value="修改FinanceInfo的form表单",required=true)  FinanceInfoForm financeInfoForm) {
        FinanceInfo financeInfo =new BeanUtils<FinanceInfo>().copyObj(financeInfoForm,FinanceInfo.class);
        financeInfo.setId(id);
        financeInfoService.saveOrUpdate(financeInfo);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取FinanceInfo", notes = "获取指定ID的FinanceInfo信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "FinanceInfo的ID", required = true, dataType = "long")
    public Result<FinanceInfoVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        FinanceInfo financeInfo=financeInfoService.getById(id);
        FinanceInfoVo financeInfoVo=new BeanUtils<FinanceInfoVo>().copyObj(financeInfo,FinanceInfoVo.class);
        return new Result<FinanceInfoVo>().sucess(financeInfoVo);
    }

    @Override
    @ApiOperation(value = "列表查询FinanceInfo", notes = "根据条件查询FinanceInfo列表数据")
    public Result<List<FinanceInfoVo>> list(@Valid @RequestBody @ApiParam(name="financeInfoQueryForm",value="FinanceInfo查询参数",required=true) FinanceInfoQueryForm financeInfoQueryForm) {
        log.info("search with financeInfoQueryForm:", financeInfoQueryForm.toString());
        List<FinanceInfo> financeInfoList=financeInfoService.selectList(financeInfoQueryForm);
        List<FinanceInfoVo> financeInfoVoList=new BeanUtils<FinanceInfoVo>().copyObjs(financeInfoList,FinanceInfoVo.class);
        return new Result<List<FinanceInfoVo>>().sucess(financeInfoVoList);
    }


    @Override
    @ApiOperation(value = "分页查询FinanceInfo", notes = "根据条件查询FinanceInfo分页数据")
    public Result<IPage<FinanceInfoVo>> search(@Valid @RequestBody @ApiParam(name="financeInfoQueryForm",value="FinanceInfo查询参数",required=true) FinanceInfoQueryForm financeInfoQueryForm) {
        log.info("search with financeInfoQueryForm:", financeInfoQueryForm.toString());
        IPage<FinanceInfo> financeInfoPage=financeInfoService.selectPage(financeInfoQueryForm);
        IPage<FinanceInfoVo> financeInfoVoPage=new BeanUtils<FinanceInfoVo>().copyPageObjs(financeInfoPage,FinanceInfoVo.class);
        return new Result<IPage<FinanceInfoVo>>().sucess(financeInfoVoPage);
    }

}




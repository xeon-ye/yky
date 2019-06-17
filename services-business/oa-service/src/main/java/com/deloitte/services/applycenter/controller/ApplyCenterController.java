package com.deloitte.services.applycenter.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.applycenter.client.ApplyCenterClient;
import com.deloitte.platform.api.oaservice.applycenter.param.ApplyCenterQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.ApplyCenterForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.ApplyCenterVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.applycenter.entity.ApplyCenter;
import com.deloitte.services.applycenter.service.IApplyCenterService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :   ApplyCenter控制器实现类
 * @Modified :
 */
@Api(tags = "ApplyCenter操作接口")
@Slf4j
@RestController
public class ApplyCenterController implements ApplyCenterClient {

    @Autowired
    public IApplyCenterService applyCenterService;


    @Override
    @ApiOperation(value = "新增ApplyCenter", notes = "新增一个ApplyCenter")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name = "applyCenterForm", value = "新增ApplyCenter的form表单", required = true) ApplyCenterForm applyCenterForm) {
        log.info("form:{}", applyCenterForm.toString());
        return applyCenterService.save(applyCenterForm);
    }


    @Override
    @ApiOperation(value = "删除ApplyCenter", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApplyCenterID", required = true, dataType = "string")
    public Result delete(@PathVariable String id) {
        applyCenterService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ApplyCenter", notes = "修改指定ApplyCenter信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApplyCenter的ID", required = true, dataType = "string")
    public Result update(@PathVariable String id,
                         @Valid @RequestBody @ApiParam(name = "applyCenterForm", value = "修改ApplyCenter的form表单", required = true) ApplyCenterForm applyCenterForm) {
        ApplyCenter applyCenter = new BeanUtils<ApplyCenter>().copyObj(applyCenterForm, ApplyCenter.class);
        applyCenter.setApplyId(Long.valueOf(id));
        applyCenterService.saveOrUpdate(applyCenter);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ApplyCenter", notes = "获取指定ID的ApplyCenter信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApplyCenter的ID", required = true, dataType = "string")
    public Result<ApplyCenterVo> get(@PathVariable String id) {
        log.info("get with id:{}", id);
        ApplyCenter applyCenter = applyCenterService.getById(id);
        ApplyCenterVo applyCenterVo = new BeanUtils<ApplyCenterVo>().copyObj(applyCenter, ApplyCenterVo.class);
        return new Result<ApplyCenterVo>().sucess(applyCenterVo);
    }

    @Override
    @ApiOperation(value = "列表查询ApplyCenter", notes = "根据条件查询ApplyCenter列表数据")
    public Result<List<ApplyCenterVo>> list(@Valid @RequestBody @ApiParam(name = "applyCenterQueryForm", value = "ApplyCenter查询参数", required = true) ApplyCenterQueryForm applyCenterQueryForm) {
        log.info("search with applyCenterQueryForm:{}", applyCenterQueryForm.toString());
        List<ApplyCenter> applyCenterList = applyCenterService.selectList(applyCenterQueryForm);
        List<ApplyCenterVo> applyCenterVoList = new BeanUtils<ApplyCenterVo>().copyObjs(applyCenterList, ApplyCenterVo.class);
        return new Result<List<ApplyCenterVo>>().sucess(applyCenterVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ApplyCenter", notes = "根据条件查询ApplyCenter分页数据")
    public Result<IPage<ApplyCenterVo>> search(@Valid @RequestBody @ApiParam(name = "applyCenterQueryForm", value = "ApplyCenter查询参数", required = true) ApplyCenterQueryForm applyCenterQueryForm) {
        log.info("search with applyCenterQueryForm:{}", applyCenterQueryForm.toString());
        GdcPage<ApplyCenter> applyCenterPage = applyCenterService.selectPage(applyCenterQueryForm);
        IPage<ApplyCenterVo> applyCenterVoPage = new BeanUtils<ApplyCenterVo>().copyPageObjs(applyCenterPage, ApplyCenterVo.class);
        return new Result<IPage<ApplyCenterVo>>().sucess(applyCenterVoPage);
    }

}




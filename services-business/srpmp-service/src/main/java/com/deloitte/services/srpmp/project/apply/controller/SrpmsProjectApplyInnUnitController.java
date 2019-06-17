package com.deloitte.services.srpmp.project.apply.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.SrpmsProjectApplyInnUnitClient;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnUnitService;


/**
 * @Author : lixin
 * @Date : Create in 2019-05-22
 * @Description :   SrpmsProjectApplyInnUnit控制器实现类
 * @Modified :
 */
@Api(tags = "创新单元申请书操作接口")
@Slf4j
@RestController
public class SrpmsProjectApplyInnUnitController implements SrpmsProjectApplyInnUnitClient {

    @Autowired
    public ISrpmsProjectApplyInnUnitService  srpmsProjectApplyInnUnitService;


    @Override
    @ApiParam(name="srpmsProjectApplyInnUnitForm",value="新增SrpmsProjectApplyInnUnit的form表单",required=true)
    @ApiOperation(value = "保存或更新创新单元申请书", notes = "保存或更新创新单元申请书")
    public Result save(@Valid @RequestBody SrpmsProjectApplyInnUnitForm srpmsProjectApplyInnUnitForm, DeptVo deptVo) {
        log.info("ISrpmsProjectApplyInnUnitService save form:{}", srpmsProjectApplyInnUnitForm.toString());
        Long id = srpmsProjectApplyInnUnitService.save(srpmsProjectApplyInnUnitForm, deptVo);
        return Result.success(String.valueOf(id));
    }

    @Override
    @ApiParam(name="提交srpmsProjectApplyInnUnitForm",value="提交SrpmsProjectApplyInnUnit的form表单",required=true)
    @ApiOperation(value = "提交创新单元申请书", notes = "提交创新单元申请书")
    public Result submit(@Valid @RequestBody SrpmsProjectApplyInnUnitForm srpmsProjectApplyInnUnitForm, UserVo userVo, DeptVo deptVo) {
        log.info("ISrpmsProjectApplyInnUnitService submit form:{}", srpmsProjectApplyInnUnitForm.toString());
        srpmsProjectApplyInnUnitService.submit(srpmsProjectApplyInnUnitForm, userVo, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取SrpmsProjectApplyInnUnit", notes = "获取指定ID的SrpmsProjectApplyInnUnit信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "项目ID", required = true, dataType = "long")
    public Result<SrpmsProjectApplyInnUnitVo> get(@PathVariable long id) {
        log.info("ISrpmsProjectApplyInnUnitService get with id:{}", id);
        SrpmsProjectApplyInnUnitVo vo = srpmsProjectApplyInnUnitService.get(id);
        return new Result<SrpmsProjectApplyInnUnitVo>().sucess(vo);
    }

}




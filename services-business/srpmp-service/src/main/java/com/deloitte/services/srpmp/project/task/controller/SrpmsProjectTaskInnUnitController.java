package com.deloitte.services.srpmp.project.task.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.task.SrpmsProjectTaskInnUnitClient;
import com.deloitte.platform.api.srpmp.project.task.param.SrpmsProjectTaskInnUnitQueryForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnUnitService;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInnUnit;


/**
 * @Author : lixin
 * @Date : Create in 2019-05-22
 * @Description :   SrpmsProjectTaskInnUnit控制器实现类
 * @Modified :
 */
@Api(tags = "创新单元任务书操作接口")
@Slf4j
@RestController
public class SrpmsProjectTaskInnUnitController implements SrpmsProjectTaskInnUnitClient {

    @Autowired
    public ISrpmsProjectTaskInnUnitService  srpmsProjectTaskInnUnitService;


    @Override
    @ApiParam(name="srpmsProjectTaskInnUnitForm",value="新增SrpmsProjectTaskInnUnit的form表单",required=true)
    @ApiOperation(value = "新增SrpmsProjectTaskInnUnit", notes = "新增一个SrpmsProjectTaskInnUnit")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result save(@Valid @RequestBody SrpmsProjectTaskInnUnitForm srpmsProjectTaskInnUnitForm) {
        log.info("ISrpmsProjectTaskInnUnitService save form:{}",  srpmsProjectTaskInnUnitForm.toString());
        Long id = srpmsProjectTaskInnUnitService.saveTask(srpmsProjectTaskInnUnitForm);
        return Result.success(id);
    }

    @Override
    @ApiOperation(value = "获取SrpmsProjectTaskInnUnit", notes = "获取指定ID的SrpmsProjectTaskInnUnit信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectTaskInnUnit的ID", required = true, dataType = "long")
    public Result<SrpmsProjectTaskInnUnitVo> get(@PathVariable long id) {
        log.info("ISrpmsProjectTaskInnUnitService get with id:{}", id);
        SrpmsProjectTaskInnUnit srpmsProjectTaskInnUnit=srpmsProjectTaskInnUnitService.getById(id);
        SrpmsProjectTaskInnUnitVo srpmsProjectTaskInnUnitVo=new BeanUtils<SrpmsProjectTaskInnUnitVo>().copyObj(srpmsProjectTaskInnUnit,SrpmsProjectTaskInnUnitVo.class);
        return new Result<SrpmsProjectTaskInnUnitVo>().sucess(srpmsProjectTaskInnUnitVo);
    }

    @Override
    public Result submit(@Valid @RequestBody SrpmsProjectTaskInnUnitForm innUnitForm, UserVo userVo, DeptVo deptVo) {
        log.info("ISrpmsProjectApplyInnUnitService submit form:{}", innUnitForm.toString());
        srpmsProjectTaskInnUnitService.submitTaskInnUnit(innUnitForm, userVo, deptVo);
        return Result.success();
    }
}




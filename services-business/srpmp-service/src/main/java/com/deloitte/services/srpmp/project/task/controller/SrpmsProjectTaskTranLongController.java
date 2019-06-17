package com.deloitte.services.srpmp.project.task.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.task.SrpmsProjectTaskTranLongClient;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskTranLongForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskTranLongService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author : Apeng
 * @Date : Create in 2019-04-17
 * @Description :   SrpmsProjectTaskTranLong控制器实现类
 * @Modified :
 */
@Api(tags = "任务书-横纵向项目操作接口", value = "任务书-横纵向项目操作接口")
@Slf4j
@RestController
public class SrpmsProjectTaskTranLongController implements SrpmsProjectTaskTranLongClient {

    @Autowired
    public ISrpmsProjectTaskTranLongService taskTranLongService;

    /**
     * 横纵项目点击新增按钮跳转接口
     *
     * @param user
     * @param dept
     * @return
     */
    @Override
    @ApiOperation(value = "横纵向项目点击新增按钮跳转接口", notes = "横纵项向目点击新增按钮跳转接口")
    public Result jump(UserVo user, DeptVo dept) {
        return Result.success(taskTranLongService.saveJumpPage(user, dept));
    }

    /**
     * 根据ID获取横纵向项目接口
     *
     * @param  id
     * @return
     */
    @Override
    @ApiOperation(value = "根据ID获取横纵向项目接口", notes = "根据ID获取横纵向项目接口")
    public Result getById(@PathVariable long id) {
        return Result.success(taskTranLongService.queryById(id));
    }

    /**
     * 保存横纵向项目接口
     *
     * @param form
     * @param user
     * @param dept
     * @return
     */
    @Override
    @ApiOperation(value = "保存横纵向项目接口", notes = "保存横纵向项目接口")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@RequestBody @ApiParam(name="SrpmsProjectTaskTranLongForm",value="新增SrpmsProjectTaskTranLong的form表单",required=true) SrpmsProjectTaskTranLongForm form, UserVo user, DeptVo dept) {
        return Result.success(taskTranLongService.saveOrUpdate(form, user, dept));
    }

    /**
     * 提交横纵向项目接口
     *
     * @param form
     * @param user
     * @param dept
     * @return
     */
    @Override
    @ApiOperation(value = "提交横纵向项目接口", notes = "提交横纵向项目接口")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result submit(@RequestBody @ApiParam(name="SrpmsProjectTaskTranLongForm",value="新增SrpmsProjectTaskTranLong的form表单",required=true) SrpmsProjectTaskTranLongForm form, UserVo user, DeptVo dept) {
        return Result.success(taskTranLongService.submit(form, user, dept));
    }


}




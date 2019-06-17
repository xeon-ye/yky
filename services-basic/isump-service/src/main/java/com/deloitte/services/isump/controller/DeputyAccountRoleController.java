package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.DeputyAccountRoleQueryForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountRoleForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountRoleVo;
import com.deloitte.platform.api.isump.DeputyAccountRoleClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.IDeputyAccountRoleService;
import com.deloitte.services.isump.entity.DeputyAccountRole;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   DeputyAccountRole控制器实现类
 * @Modified :
 */
@Api(tags = "DeputyAccountRole操作接口")
@Slf4j
@RestController
public class DeputyAccountRoleController implements DeputyAccountRoleClient {

    @Autowired
    public IDeputyAccountRoleService  deputyAccountRoleService;


    @Override
    @ApiOperation(value = "新增DeputyAccountRole", notes = "新增一个DeputyAccountRole")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="deputyAccountRoleForm",value="新增DeputyAccountRole的form表单",required=true) DeputyAccountRoleForm deputyAccountRoleForm) {
        log.info("form:",  deputyAccountRoleForm.toString());
        DeputyAccountRole deputyAccountRole =new BeanUtils<DeputyAccountRole>().copyObj(deputyAccountRoleForm,DeputyAccountRole.class);
        return Result.success( deputyAccountRoleService.save(deputyAccountRole));
    }


    @Override
    @ApiOperation(value = "删除DeputyAccountRole", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DeputyAccountRoleID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        deputyAccountRoleService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改DeputyAccountRole", notes = "修改指定DeputyAccountRole信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "DeputyAccountRole的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="deputyAccountRoleForm",value="修改DeputyAccountRole的form表单",required=true)  DeputyAccountRoleForm deputyAccountRoleForm) {
        DeputyAccountRole deputyAccountRole =new BeanUtils<DeputyAccountRole>().copyObj(deputyAccountRoleForm,DeputyAccountRole.class);
        deputyAccountRole.setId(id);
        deputyAccountRoleService.saveOrUpdate(deputyAccountRole);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取DeputyAccountRole", notes = "获取指定ID的DeputyAccountRole信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DeputyAccountRole的ID", required = true, dataType = "long")
    public Result<DeputyAccountRoleVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        DeputyAccountRole deputyAccountRole=deputyAccountRoleService.getById(id);
        DeputyAccountRoleVo deputyAccountRoleVo=new BeanUtils<DeputyAccountRoleVo>().copyObj(deputyAccountRole,DeputyAccountRoleVo.class);
        return new Result<DeputyAccountRoleVo>().sucess(deputyAccountRoleVo);
    }

    @Override
    @ApiOperation(value = "列表查询DeputyAccountRole", notes = "根据条件查询DeputyAccountRole列表数据")
    public Result<List<DeputyAccountRoleVo>> list(@Valid @RequestBody @ApiParam(name="deputyAccountRoleQueryForm",value="DeputyAccountRole查询参数",required=true) DeputyAccountRoleQueryForm deputyAccountRoleQueryForm) {
        log.info("search with deputyAccountRoleQueryForm:", deputyAccountRoleQueryForm.toString());
        List<DeputyAccountRole> deputyAccountRoleList=deputyAccountRoleService.selectList(deputyAccountRoleQueryForm);
        List<DeputyAccountRoleVo> deputyAccountRoleVoList=new BeanUtils<DeputyAccountRoleVo>().copyObjs(deputyAccountRoleList,DeputyAccountRoleVo.class);
        return new Result<List<DeputyAccountRoleVo>>().sucess(deputyAccountRoleVoList);
    }


    @Override
    @ApiOperation(value = "分页查询DeputyAccountRole", notes = "根据条件查询DeputyAccountRole分页数据")
    public Result<IPage<DeputyAccountRoleVo>> search(@Valid @RequestBody @ApiParam(name="deputyAccountRoleQueryForm",value="DeputyAccountRole查询参数",required=true) DeputyAccountRoleQueryForm deputyAccountRoleQueryForm) {
        log.info("search with deputyAccountRoleQueryForm:", deputyAccountRoleQueryForm.toString());
        IPage<DeputyAccountRole> deputyAccountRolePage=deputyAccountRoleService.selectPage(deputyAccountRoleQueryForm);
        IPage<DeputyAccountRoleVo> deputyAccountRoleVoPage=new BeanUtils<DeputyAccountRoleVo>().copyPageObjs(deputyAccountRolePage,DeputyAccountRoleVo.class);
        return new Result<IPage<DeputyAccountRoleVo>>().sucess(deputyAccountRoleVoPage);
    }

}




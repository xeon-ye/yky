package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.RoleQueryForm;
import com.deloitte.platform.api.isump.vo.RoleForm;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.api.isump.RoleClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.IRoleService;
import com.deloitte.services.isump.entity.Role;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   Role控制器实现类
 * @Modified :
 */
@Api(tags = "Role操作接口")
@Slf4j
@RestController
public class RoleController implements RoleClient {

    @Autowired
    public IRoleService  roleService;


    @Override
    @ApiOperation(value = "新增Role", notes = "新增一个Role")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="roleForm",value="新增Role的form表单",required=true)  RoleForm roleForm) {
        log.info("form:",  roleForm.toString());
        Role role =new BeanUtils<Role>().copyObj(roleForm,Role.class);
        return Result.success( roleService.save(role));
    }


    @Override
    @ApiOperation(value = "删除Role", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "RoleID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        roleService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Role", notes = "修改指定Role信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Role的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="roleForm",value="修改Role的form表单",required=true)  RoleForm roleForm) {
        Role role =new BeanUtils<Role>().copyObj(roleForm,Role.class);
        role.setId(id);
        roleService.saveOrUpdate(role);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Role", notes = "获取指定ID的Role信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Role的ID", required = true, dataType = "long")
    public Result<RoleVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Role role=roleService.getById(id);
        RoleVo roleVo=new BeanUtils<RoleVo>().copyObj(role,RoleVo.class);
        return new Result<RoleVo>().sucess(roleVo);
    }

    @Override
    @ApiOperation(value = "列表查询Role", notes = "根据条件查询Role列表数据")
    public Result<List<RoleVo>> list(@Valid @RequestBody @ApiParam(name="roleQueryForm",value="Role查询参数",required=true) RoleQueryForm roleQueryForm) {
        log.info("search with roleQueryForm:", roleQueryForm.toString());
        List<Role> roleList=roleService.selectList(roleQueryForm);
        List<RoleVo> roleVoList=new BeanUtils<RoleVo>().copyObjs(roleList,RoleVo.class);
        return new Result<List<RoleVo>>().sucess(roleVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Role", notes = "根据条件查询Role分页数据")
    public Result<IPage<RoleVo>> search(@Valid @RequestBody @ApiParam(name="roleQueryForm",value="Role查询参数",required=true) RoleQueryForm roleQueryForm) {
        log.info("search with roleQueryForm:", roleQueryForm.toString());
        IPage<Role> rolePage=roleService.selectPage(roleQueryForm);
        IPage<RoleVo> roleVoPage=new BeanUtils<RoleVo>().copyPageObjs(rolePage,RoleVo.class);
        return new Result<IPage<RoleVo>>().sucess(roleVoPage);
    }

    @Override
    @ApiOperation(value = "根据副账号ID,角色类型，服务类型查询角色列表", notes = "根据副账号ID,角色类型，服务类型查询角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType ="query", name = "id", value = "副账号ID", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType ="query",name = "type", value = "角色类型type", required = true, dataType = "String"),
            @ApiImplicitParam(paramType ="query",name = "service", value = "系统类型service", required = true, dataType = "String")
    })
    public Result<List<RoleVo>> getByDeputyAccountId(@RequestParam("id") long id,@RequestParam("type") String type,@RequestParam("service") String service) {
        List<RoleVo> list = roleService.getByDeputyAccountId(id,type,service);
        return Result.success(list);
    }
}

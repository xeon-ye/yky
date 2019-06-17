package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.OrgRoleQueryForm;
import com.deloitte.platform.api.isump.vo.OrgRoleForm;
import com.deloitte.platform.api.isump.vo.OrgRoleVo;
import com.deloitte.platform.api.isump.OrgRoleClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.IOrgRoleService;
import com.deloitte.services.isump.entity.OrgRole;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   OrgRole控制器实现类
 * @Modified :
 */
@Api(tags = "OrgRole操作接口")
@Slf4j
@RestController
public class OrgRoleController implements OrgRoleClient {

    @Autowired
    public IOrgRoleService  orgRoleService;


    @Override
    @ApiOperation(value = "新增OrgRole", notes = "新增一个OrgRole")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="orgRoleForm",value="新增OrgRole的form表单",required=true)  OrgRoleForm orgRoleForm) {
        log.info("form:",  orgRoleForm.toString());
        OrgRole orgRole =new BeanUtils<OrgRole>().copyObj(orgRoleForm,OrgRole.class);
        return Result.success( orgRoleService.save(orgRole));
    }


    @Override
    @ApiOperation(value = "删除OrgRole", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OrgRoleID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        orgRoleService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OrgRole", notes = "修改指定OrgRole信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OrgRole的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="orgRoleForm",value="修改OrgRole的form表单",required=true)  OrgRoleForm orgRoleForm) {
        OrgRole orgRole =new BeanUtils<OrgRole>().copyObj(orgRoleForm,OrgRole.class);
        orgRole.setId(id);
        orgRoleService.saveOrUpdate(orgRole);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OrgRole", notes = "获取指定ID的OrgRole信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OrgRole的ID", required = true, dataType = "long")
    public Result<OrgRoleVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OrgRole orgRole=orgRoleService.getById(id);
        OrgRoleVo orgRoleVo=new BeanUtils<OrgRoleVo>().copyObj(orgRole,OrgRoleVo.class);
        return new Result<OrgRoleVo>().sucess(orgRoleVo);
    }

    @Override
    @ApiOperation(value = "列表查询OrgRole", notes = "根据条件查询OrgRole列表数据")
    public Result<List<OrgRoleVo>> list(@Valid @RequestBody @ApiParam(name="orgRoleQueryForm",value="OrgRole查询参数",required=true) OrgRoleQueryForm orgRoleQueryForm) {
        log.info("search with orgRoleQueryForm:", orgRoleQueryForm.toString());
        List<OrgRole> orgRoleList=orgRoleService.selectList(orgRoleQueryForm);
        List<OrgRoleVo> orgRoleVoList=new BeanUtils<OrgRoleVo>().copyObjs(orgRoleList,OrgRoleVo.class);
        return new Result<List<OrgRoleVo>>().sucess(orgRoleVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OrgRole", notes = "根据条件查询OrgRole分页数据")
    public Result<IPage<OrgRoleVo>> search(@Valid @RequestBody @ApiParam(name="orgRoleQueryForm",value="OrgRole查询参数",required=true) OrgRoleQueryForm orgRoleQueryForm) {
        log.info("search with orgRoleQueryForm:", orgRoleQueryForm.toString());
        IPage<OrgRole> orgRolePage=orgRoleService.selectPage(orgRoleQueryForm);
        IPage<OrgRoleVo> orgRoleVoPage=new BeanUtils<OrgRoleVo>().copyPageObjs(orgRolePage,OrgRoleVo.class);
        return new Result<IPage<OrgRoleVo>>().sucess(orgRoleVoPage);
    }

}




package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.RoleResourceQueryForm;
import com.deloitte.platform.api.isump.vo.RoleResourceForm;
import com.deloitte.platform.api.isump.vo.RoleResourceVo;
import com.deloitte.platform.api.isump.RoleResourceClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.IRoleResourceService;
import com.deloitte.services.isump.entity.RoleResource;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   RoleResource控制器实现类
 * @Modified :
 */
@Api(tags = "RoleResource操作接口")
@Slf4j
@RestController
public class RoleResourceController implements RoleResourceClient {

    @Autowired
    public IRoleResourceService  roleResourceService;


    @Override
    @ApiOperation(value = "新增RoleResource", notes = "新增一个RoleResource")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="roleResourceForm",value="新增RoleResource的form表单",required=true)  RoleResourceForm roleResourceForm) {
        log.info("form:",  roleResourceForm.toString());
        RoleResource roleResource =new BeanUtils<RoleResource>().copyObj(roleResourceForm,RoleResource.class);
        return Result.success( roleResourceService.save(roleResource));
    }


    @Override
    @ApiOperation(value = "删除RoleResource", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "RoleResourceID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        roleResourceService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改RoleResource", notes = "修改指定RoleResource信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "RoleResource的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="roleResourceForm",value="修改RoleResource的form表单",required=true)  RoleResourceForm roleResourceForm) {
        RoleResource roleResource =new BeanUtils<RoleResource>().copyObj(roleResourceForm,RoleResource.class);
        roleResource.setId(id);
        roleResourceService.saveOrUpdate(roleResource);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取RoleResource", notes = "获取指定ID的RoleResource信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "RoleResource的ID", required = true, dataType = "long")
    public Result<RoleResourceVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        RoleResource roleResource=roleResourceService.getById(id);
        RoleResourceVo roleResourceVo=new BeanUtils<RoleResourceVo>().copyObj(roleResource,RoleResourceVo.class);
        return new Result<RoleResourceVo>().sucess(roleResourceVo);
    }

    @Override
    @ApiOperation(value = "列表查询RoleResource", notes = "根据条件查询RoleResource列表数据")
    public Result<List<RoleResourceVo>> list(@Valid @RequestBody @ApiParam(name="roleResourceQueryForm",value="RoleResource查询参数",required=true) RoleResourceQueryForm roleResourceQueryForm) {
        log.info("search with roleResourceQueryForm:", roleResourceQueryForm.toString());
        List<RoleResource> roleResourceList=roleResourceService.selectList(roleResourceQueryForm);
        List<RoleResourceVo> roleResourceVoList=new BeanUtils<RoleResourceVo>().copyObjs(roleResourceList,RoleResourceVo.class);
        return new Result<List<RoleResourceVo>>().sucess(roleResourceVoList);
    }


    @Override
    @ApiOperation(value = "分页查询RoleResource", notes = "根据条件查询RoleResource分页数据")
    public Result<IPage<RoleResourceVo>> search(@Valid @RequestBody @ApiParam(name="roleResourceQueryForm",value="RoleResource查询参数",required=true) RoleResourceQueryForm roleResourceQueryForm) {
        log.info("search with roleResourceQueryForm:", roleResourceQueryForm.toString());
        IPage<RoleResource> roleResourcePage=roleResourceService.selectPage(roleResourceQueryForm);
        IPage<RoleResourceVo> roleResourceVoPage=new BeanUtils<RoleResourceVo>().copyPageObjs(roleResourcePage,RoleResourceVo.class);
        return new Result<IPage<RoleResourceVo>>().sucess(roleResourceVoPage);
    }

}




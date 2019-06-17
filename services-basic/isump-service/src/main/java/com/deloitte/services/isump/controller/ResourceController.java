package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.ResourceQueryForm;
import com.deloitte.platform.api.isump.vo.ResourceForm;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.api.isump.ResourceClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.apache.commons.configuration.tree.TreeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.validation.Valid;
import java.util.List;
import java.util.TreeMap;

import com.deloitte.services.isump.service.IResourceService;
import com.deloitte.services.isump.entity.Resource;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   Resource控制器实现类
 * @Modified :
 */
@Api(tags = "Resource操作接口")
@Slf4j
@RestController
public class ResourceController implements ResourceClient {

    @Autowired
    public IResourceService  resourceService;


    @Override
    @ApiOperation(value = "新增Resource", notes = "新增一个Resource")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="resourceForm",value="新增Resource的form表单",required=true)  ResourceForm resourceForm) {
        log.info("form:",  resourceForm.toString());
        Resource resource =new BeanUtils<Resource>().copyObj(resourceForm,Resource.class);
        return Result.success( resourceService.save(resource));
    }


    @Override
    @ApiOperation(value = "删除Resource", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ResourceID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        resourceService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Resource", notes = "修改指定Resource信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Resource的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="resourceForm",value="修改Resource的form表单",required=true)  ResourceForm resourceForm) {
        Resource resource =new BeanUtils<Resource>().copyObj(resourceForm,Resource.class);
        resource.setId(id);
        resourceService.saveOrUpdate(resource);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Resource", notes = "获取指定ID的Resource信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Resource的ID", required = true, dataType = "long")
    public Result<ResourceVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Resource resource=resourceService.getById(id);
        ResourceVo resourceVo=new BeanUtils<ResourceVo>().copyObj(resource,ResourceVo.class);
        return new Result<ResourceVo>().sucess(resourceVo);
    }

    @Override
    @ApiOperation(value = "列表查询Resource", notes = "根据条件查询Resource列表数据")
    public Result<List<ResourceVo>> list(@Valid @RequestBody @ApiParam(name="resourceQueryForm",value="Resource查询参数",required=true) ResourceQueryForm resourceQueryForm) {
        log.info("search with resourceQueryForm:", resourceQueryForm.toString());
        List<Resource> resourceList=resourceService.selectList(resourceQueryForm);
        List<ResourceVo> resourceVoList=new BeanUtils<ResourceVo>().copyObjs(resourceList,ResourceVo.class);
        return new Result<List<ResourceVo>>().sucess(resourceVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Resource", notes = "根据条件查询Resource分页数据")
    public Result<IPage<ResourceVo>> search(@Valid @RequestBody @ApiParam(name="resourceQueryForm",value="Resource查询参数",required=true) ResourceQueryForm resourceQueryForm) {
        log.info("search with resourceQueryForm:", resourceQueryForm.toString());
        IPage<Resource> resourcePage=resourceService.selectPage(resourceQueryForm);
        IPage<ResourceVo> resourceVoPage=new BeanUtils<ResourceVo>().copyPageObjs(resourcePage,ResourceVo.class);
        return new Result<IPage<ResourceVo>>().sucess(resourceVoPage);
    }

    @Override
    @ApiOperation(value = "根据用户副账号ID和系统code查询菜单权限列表", notes = "根据用户副账号ID和系统code查询菜单权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType ="query", name = "deputyAccountId", value = "副账号ID", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType ="query",name = "sysCode", value = "系统code", required = true, dataType = "String")
    })
    public Result tree( @RequestParam("deputyAccountId") Long deputyAccountId,@RequestParam("sysCode") String sysCode){
        ResourceVo resource = resourceService.findTree(deputyAccountId,sysCode);
        return Result.success(resource);
    }

}




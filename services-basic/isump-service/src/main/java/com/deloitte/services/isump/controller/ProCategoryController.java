package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.ProCategoryQueryForm;
import com.deloitte.platform.api.isump.vo.ProCategoryForm;
import com.deloitte.platform.api.isump.vo.ProCategoryVo;
import com.deloitte.platform.api.isump.ProCategoryClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.isump.service.IProCategoryService;
import com.deloitte.services.isump.entity.ProCategory;


/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :   ProCategory控制器实现类
 * @Modified :
 */
@Api(tags = "ProCategory操作接口")
@Slf4j
@RestController
public class ProCategoryController implements ProCategoryClient {

    @Autowired
    public IProCategoryService  proCategoryService;


    @Override
    @ApiOperation(value = "新增ProCategory", notes = "新增一个ProCategory")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="proCategoryForm",value="新增ProCategory的form表单",required=true)  ProCategoryForm proCategoryForm) {
        log.info("form:",  proCategoryForm.toString());
        ProCategory proCategory =new BeanUtils<ProCategory>().copyObj(proCategoryForm,ProCategory.class);
        return Result.success( proCategoryService.save(proCategory));
    }


    @Override
    @ApiOperation(value = "删除ProCategory", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProCategoryID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        proCategoryService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ProCategory", notes = "修改指定ProCategory信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ProCategory的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="proCategoryForm",value="修改ProCategory的form表单",required=true)  ProCategoryForm proCategoryForm) {
        ProCategory proCategory =new BeanUtils<ProCategory>().copyObj(proCategoryForm,ProCategory.class);
        proCategory.setId(id);
        proCategoryService.saveOrUpdate(proCategory);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ProCategory", notes = "获取指定ID的ProCategory信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProCategory的ID", required = true, dataType = "long")
    public Result<ProCategoryVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ProCategory proCategory=proCategoryService.getById(id);
        ProCategoryVo proCategoryVo=new BeanUtils<ProCategoryVo>().copyObj(proCategory,ProCategoryVo.class);
        return new Result<ProCategoryVo>().sucess(proCategoryVo);
    }

    @Override
    @ApiOperation(value = "根据副账号ID获取ProCategory列表", notes = "根据副账号ID获取ProCategory列表")
    @ApiImplicitParam(paramType = "path", name = "id", value = "副账号的ID", required = true, dataType = "long")
    public Result<List<ProCategoryVo>> getByDeputyAccountId(@PathVariable long id) {
        log.info("get with id:{}", id);
        List<ProCategoryVo> list=proCategoryService.getByDeputyAccountId(id);
        return new Result<List<ProCategoryVo>>().sucess(list);
    }

    @Override
    @ApiOperation(value = "列表查询ProCategory", notes = "根据条件查询ProCategory列表数据")
    public Result<List<ProCategoryVo>> list(@Valid @RequestBody @ApiParam(name="proCategoryQueryForm",value="ProCategory查询参数",required=true) ProCategoryQueryForm proCategoryQueryForm) {
        log.info("search with proCategoryQueryForm:", proCategoryQueryForm.toString());
        List<ProCategory> proCategoryList=proCategoryService.selectList(proCategoryQueryForm);
        List<ProCategoryVo> proCategoryVoList=new BeanUtils<ProCategoryVo>().copyObjs(proCategoryList,ProCategoryVo.class);
        return new Result<List<ProCategoryVo>>().sucess(proCategoryVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ProCategory", notes = "根据条件查询ProCategory分页数据")
    public Result<IPage<ProCategoryVo>> search(@Valid @RequestBody @ApiParam(name="proCategoryQueryForm",value="ProCategory查询参数",required=true) ProCategoryQueryForm proCategoryQueryForm) {
        log.info("search with proCategoryQueryForm:", proCategoryQueryForm.toString());
        IPage<ProCategory> proCategoryPage=proCategoryService.selectPage(proCategoryQueryForm);
        IPage<ProCategoryVo> proCategoryVoPage=new BeanUtils<ProCategoryVo>().copyPageObjs(proCategoryPage,ProCategoryVo.class);
        return new Result<IPage<ProCategoryVo>>().sucess(proCategoryVoPage);
    }

}




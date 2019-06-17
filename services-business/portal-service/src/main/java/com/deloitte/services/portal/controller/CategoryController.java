package com.deloitte.services.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.portal.param.CategoryQueryForm;
import com.deloitte.platform.api.portal.vo.CategoryForm;
import com.deloitte.platform.api.portal.vo.CategoryVo;
import com.deloitte.platform.api.portal.client.CategoryClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.portal.service.ICategoryService;
import com.deloitte.services.portal.entity.Category;


/**
 * @Author : pengchao
 * @Date : Create in 2019-04-03
 * @Description :   Category控制器实现类
 * @Modified :
 */
@Api(tags = "Category操作接口")
@Slf4j
@RestController
public class CategoryController implements CategoryClient {

    @Autowired
    public ICategoryService  categoryService;


    @Override
    @ApiOperation(value = "新增Category", notes = "新增一个Category")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="categoryForm",value="新增Category的form表单",required=true)  CategoryForm categoryForm) {
        log.info("form:",  categoryForm.toString());
        Category category =new BeanUtils<Category>().copyObj(categoryForm,Category.class);
        return Result.success( categoryService.save(category));
    }


    @Override
    @ApiOperation(value = "删除Category", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "CategoryID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        categoryService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Category", notes = "修改指定Category信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Category的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="categoryForm",value="修改Category的form表单",required=true)  CategoryForm categoryForm) {
        Category category =new BeanUtils<Category>().copyObj(categoryForm,Category.class);
        category.setCategoryId(id);
        categoryService.saveOrUpdate(category);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Category", notes = "获取指定ID的Category信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Category的ID", required = true, dataType = "long")
    public Result<CategoryVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Category category=categoryService.getById(id);
        CategoryVo categoryVo=new BeanUtils<CategoryVo>().copyObj(category,CategoryVo.class);
        return new Result<CategoryVo>().sucess(categoryVo);
    }

    @Override
    @ApiOperation(value = "列表查询Category", notes = "根据条件查询Category列表数据")
    public Result<List<CategoryVo>> list(@Valid @RequestBody @ApiParam(name="categoryQueryForm",value="Category查询参数",required=true) CategoryQueryForm categoryQueryForm) {
        log.info("search with categoryQueryForm:", categoryQueryForm.toString());
        List<Category> categoryList=categoryService.selectList(categoryQueryForm);
        List<CategoryVo> categoryVoList=new BeanUtils<CategoryVo>().copyObjs(categoryList,CategoryVo.class);
        return new Result<List<CategoryVo>>().sucess(categoryVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Category", notes = "根据条件查询Category分页数据")
    public Result<IPage<CategoryVo>> search(@Valid @RequestBody @ApiParam(name="categoryQueryForm",value="Category查询参数",required=true) CategoryQueryForm categoryQueryForm) {
        log.info("search with categoryQueryForm:", categoryQueryForm.toString());
        IPage<Category> categoryPage=categoryService.selectPage(categoryQueryForm);
        IPage<CategoryVo> categoryVoPage=new BeanUtils<CategoryVo>().copyPageObjs(categoryPage,CategoryVo.class);
        return new Result<IPage<CategoryVo>>().sucess(categoryVoPage);
    }

    @Override
    public Result<List<CategoryVo>> selectHomeCategories() {
        log.info("search home categories");
        List<Category> categoryList = categoryService.selectHomeCategories();
        List<CategoryVo> categoryVoList=new BeanUtils<CategoryVo>().copyObjs(categoryList,CategoryVo.class);
        return new Result<List<CategoryVo>>().sucess(categoryVoList);
    }


}




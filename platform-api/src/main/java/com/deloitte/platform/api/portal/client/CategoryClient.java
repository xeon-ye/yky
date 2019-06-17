package com.deloitte.platform.api.portal.client;

import com.deloitte.platform.api.portal.param.CategoryQueryForm;
import com.deloitte.platform.api.portal.vo.CategoryForm;
import com.deloitte.platform.api.portal.vo.CategoryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-03
 * @Description :  Category控制器接口
 * @Modified :
 */
public interface CategoryClient {

    public static final String path="/portal/category";

    /**
     *  POST---新增
     * @param categoryForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CategoryForm categoryForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, categoryForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CategoryForm categoryForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CategoryVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   categoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CategoryVo>> list(@Valid @RequestBody CategoryQueryForm categoryQueryForm);


    /**
     *  POST----复杂查询
     * @param  categoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CategoryVo>> search(@Valid @RequestBody CategoryQueryForm categoryQueryForm);

    @GetMapping(value = path+"/list/homecategories")
    Result<List<CategoryVo>> selectHomeCategories();

}

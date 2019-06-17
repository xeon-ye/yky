package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.ProCategoryQueryForm;
import com.deloitte.platform.api.isump.vo.ProCategoryForm;
import com.deloitte.platform.api.isump.vo.ProCategoryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :  ProCategory控制器接口
 * @Modified :
 */
public interface ProCategoryClient {

    public static final String path="/isump/pro-category";


    /**
     *  POST---新增
     * @param proCategoryForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProCategoryForm proCategoryForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, proCategoryForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ProCategoryForm proCategoryForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProCategoryVo> get(@PathVariable(value = "id") long id);

    /**
     * 根据副账号ID查询项目类型列表
     * @param id
     * @return
     */
    @GetMapping(value = path+"/getByDeputyAccountId/{id}")
    Result<List<ProCategoryVo>> getByDeputyAccountId(@PathVariable(value = "id") long id);
    /**
     *  POST----列表查询
     * @param   proCategoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProCategoryVo>> list(@Valid @RequestBody ProCategoryQueryForm proCategoryQueryForm);


    /**
     *  POST----复杂查询
     * @param  proCategoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProCategoryVo>> search(@Valid @RequestBody ProCategoryQueryForm proCategoryQueryForm);
}

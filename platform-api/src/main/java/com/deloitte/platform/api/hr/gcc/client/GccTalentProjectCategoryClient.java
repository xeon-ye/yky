package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTalentProjectCategoryQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectCategoryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectCategoryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccTalentProjectCategory控制器接口
 * @Modified :
 */
public interface GccTalentProjectCategoryClient {

    public static final String path="/hr/gcc-talent-project-category";


    /**
     *  POST---新增
     * @param gccTalentProjectCategoryForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccTalentProjectCategoryForm gccTalentProjectCategoryForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccTalentProjectCategoryForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccTalentProjectCategoryForm gccTalentProjectCategoryForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccTalentProjectCategoryVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccTalentProjectCategoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccTalentProjectCategoryVo>> list(@Valid @RequestBody GccTalentProjectCategoryQueryForm gccTalentProjectCategoryQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccTalentProjectCategoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccTalentProjectCategoryVo>> search(@Valid @RequestBody GccTalentProjectCategoryQueryForm gccTalentProjectCategoryQueryForm);

    /**
     * post---导出
     * @param  gccTalentProjectCategoryQueryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccTalentProjectCategoryQueryForm gccTalentProjectCategoryQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     *  Delete---批量删除
     * @param  form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);


    /**
     *  Delete---获取列表
     * @param  projectCode
     * @return
     */
    @GetMapping(value = path+"/project/{projectCode}")
    Result<List<GccTalentProjectCategoryVo>> getListByProjectCode(@PathVariable(value = "projectCode") Long  projectCode);
}

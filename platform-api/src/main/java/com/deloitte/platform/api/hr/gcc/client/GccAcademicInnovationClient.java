package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccAcademicInnovationQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchUpdateForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAcademicInnovationForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAcademicInnovationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccAcademicInnovation控制器接口
 * @Modified :
 */
public interface GccAcademicInnovationClient {

    public static final String path="/hr/gcc-academic-innovation";


    /**
     *  POST---新增
     * @param gccAcademicInnovationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccAcademicInnovationForm gccAcademicInnovationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccAcademicInnovationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccAcademicInnovationForm gccAcademicInnovationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccAcademicInnovationVo> get(@PathVariable(value = "id") long id);



    /**
     *  GET----根据ID获取
     * @param  userId
     * @return
     */
    @GetMapping(value = path+"/getByUserId/{userId}")
    Result<GccAcademicInnovationVo> getByUserId(@PathVariable(value = "userId") long userId);

    /**
     *  POST----列表查询
     * @param   gccAcademicInnovationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccAcademicInnovationVo>> list(@Valid @RequestBody GccAcademicInnovationQueryForm gccAcademicInnovationQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccAcademicInnovationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccAcademicInnovationVo>> search(@Valid @RequestBody GccAcademicInnovationQueryForm gccAcademicInnovationQueryForm);

    /**
     *  Delete---批量修改审批状态
     * @param  form
     * @return
     */
    @PostMapping(value = path+"/updateList")
    Result updateList(@Valid @RequestBody GccBaseBatchUpdateForm form);


    /**
     *  POST---新增
     * @param gccAcademicInnovationForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdateByUserId")
    Result saveOrUpdateByUserId(@Valid @ModelAttribute GccAcademicInnovationForm gccAcademicInnovationForm);
}

package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccAcademicSocietiesQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAcademicSocietiesForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAcademicSocietiesVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccAcademicSocieties控制器接口
 * @Modified :
 */
public interface GccAcademicSocietiesClient {

    public static final String path="/hr/gcc-academic-societies";


    /**
     *  POST---新增
     * @param gccAcademicSocietiesForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccAcademicSocietiesForm gccAcademicSocietiesForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccAcademicSocietiesForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccAcademicSocietiesForm gccAcademicSocietiesForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccAcademicSocietiesVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccAcademicSocietiesQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccAcademicSocietiesVo>> list(@Valid @RequestBody GccAcademicSocietiesQueryForm gccAcademicSocietiesQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccAcademicSocietiesQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccAcademicSocietiesVo>> search(@Valid @RequestBody GccAcademicSocietiesQueryForm gccAcademicSocietiesQueryForm);


    /**
     *  POST---批量新增或更新
     * @param gccAcademicSocietiesForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccAcademicSocietiesForm> gccAcademicSocietiesForms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);

}

package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccStudyExperienceQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccStudyExperienceForm;
import com.deloitte.platform.api.hr.gcc.vo.GccStudyExperienceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccStudyExperience控制器接口
 * @Modified :
 */
public interface GccStudyExperienceClient {

    public static final String path="/hr/gcc-study-experience";


    /**
     *  POST---新增
     * @param gccStudyExperienceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccStudyExperienceForm gccStudyExperienceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccStudyExperienceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccStudyExperienceForm gccStudyExperienceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccStudyExperienceVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccStudyExperienceQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccStudyExperienceVo>> list(@Valid @RequestBody GccStudyExperienceQueryForm gccStudyExperienceQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccStudyExperienceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccStudyExperienceVo>> search(@Valid @RequestBody GccStudyExperienceQueryForm gccStudyExperienceQueryForm);


    /**
     *  POST---批量新增或更新
     * @param forms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccStudyExperienceForm> forms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);


}

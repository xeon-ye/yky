package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccWorkExperienceQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccWorkExperienceForm;
import com.deloitte.platform.api.hr.gcc.vo.GccWorkExperienceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccWorkExperience控制器接口
 * @Modified :
 */
public interface GccWorkExperienceClient {

    public static final String path="/hr/gcc-work-experience";


    /**
     *  POST---新增
     * @param gccWorkExperienceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccWorkExperienceForm gccWorkExperienceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccWorkExperienceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccWorkExperienceForm gccWorkExperienceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccWorkExperienceVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccWorkExperienceQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccWorkExperienceVo>> list(@Valid @RequestBody GccWorkExperienceQueryForm gccWorkExperienceQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccWorkExperienceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccWorkExperienceVo>> search(@Valid @RequestBody GccWorkExperienceQueryForm gccWorkExperienceQueryForm);


    /**
     *  POST---批量新增或更新
     * @param gccWorkExperienceForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccWorkExperienceForm> gccWorkExperienceForms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}

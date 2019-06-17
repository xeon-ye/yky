package com.deloitte.platform.api.fssc.pe.client;

import com.deloitte.platform.api.fssc.pe.param.PerSelfAssessmentQueryForm;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfAssessmentForm;
import com.deloitte.platform.api.fssc.pe.vo.PerSelfAssessmentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :  PerSelfAssessment控制器接口
 * @Modified :
 */
public interface PerSelfAssessmentClient {

    public static final String path="/pe/per-self-assessment";


    /**
     *  POST---新增
     * @param perSelfAssessmentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PerSelfAssessmentForm perSelfAssessmentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, perSelfAssessmentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PerSelfAssessmentForm perSelfAssessmentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PerSelfAssessmentVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   perSelfAssessmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PerSelfAssessmentVo>> list(@Valid @RequestBody PerSelfAssessmentQueryForm perSelfAssessmentQueryForm);


    /**
     *  POST----复杂查询
     * @param  perSelfAssessmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PerSelfAssessmentVo>> search(@Valid @RequestBody PerSelfAssessmentQueryForm perSelfAssessmentQueryForm);
}

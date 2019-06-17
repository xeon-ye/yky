package com.deloitte.platform.api.hr.registrationResultEnquiry.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrEducationExperienceQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrEducationExperienceForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrEducationExperienceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :  HrEducationExperience控制器接口
 * @Modified :
 */
public interface HrEducationExperienceClient {

    public static final String path="/hr/registrationResultEnquiry/hr-education-experience";


    /**
     *  POST---新增
     * @param hrEducationExperienceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrEducationExperienceForm hrEducationExperienceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrEducationExperienceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrEducationExperienceForm hrEducationExperienceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrEducationExperienceVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrEducationExperienceQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrEducationExperienceVo>> list(@Valid @RequestBody HrEducationExperienceQueryForm hrEducationExperienceQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrEducationExperienceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrEducationExperienceVo>> search(@Valid @RequestBody HrEducationExperienceQueryForm hrEducationExperienceQueryForm);
}

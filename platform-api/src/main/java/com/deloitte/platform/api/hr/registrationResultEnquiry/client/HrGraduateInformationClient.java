package com.deloitte.platform.api.hr.registrationResultEnquiry.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrGraduateInformationQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrGraduateInformationForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrGraduateInformationVo;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrSocialPersonalInformationForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :  HrGraduateInformation控制器接口
 * @Modified :
 */
public interface HrGraduateInformationClient {

    public static final String path="/hr/registrationResultEnquiry/hr-graduate-information";


    /**
     *  POST---新增
     * @param hrGraduateInformationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrGraduateInformationForm hrGraduateInformationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrGraduateInformationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrGraduateInformationForm hrGraduateInformationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrGraduateInformationVo> get(@PathVariable(value = "id") long id);

    /**
     * 根据UserId获取信息
     * @param id
     * @return
     */
    @GetMapping(value = path+"/getByUserId/{id}")
    Result<HrGraduateInformationVo> getByUserId(@PathVariable long id);

    /**
     *  POST----列表查询
     * @param   hrGraduateInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrGraduateInformationVo>> list(@Valid @RequestBody HrGraduateInformationQueryForm hrGraduateInformationQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrGraduateInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrGraduateInformationVo>> search(@Valid @RequestBody HrGraduateInformationQueryForm hrGraduateInformationQueryForm);

    /**
     *  POST---保存
     * @param hrGraduateInformationForm
     * @return
     */
    @PostMapping(value = path+"/save")
    Result save(@Valid @ModelAttribute HrGraduateInformationForm hrGraduateInformationForm);

    /**
     *  POST---提交
     * @param hrGraduateInformationForm
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute HrGraduateInformationForm hrGraduateInformationForm);
}

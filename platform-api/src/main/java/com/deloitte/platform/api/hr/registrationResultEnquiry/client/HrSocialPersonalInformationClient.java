package com.deloitte.platform.api.hr.registrationResultEnquiry.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrSocialPersonalInformationQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrGraduateInformationVo;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrSocialPersonalInformationForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrSocialPersonalInformationVo;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrExternalTransferForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :  HrSocialPersonalInformation控制器接口
 * @Modified :
 */
public interface HrSocialPersonalInformationClient {

    public static final String path="/hr/registrationResultEnquiry/hr-social-personal-information";


    /**
     *  POST---新增
     * @param hrSocialPersonalInformationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrSocialPersonalInformationForm hrSocialPersonalInformationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrSocialPersonalInformationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrSocialPersonalInformationForm hrSocialPersonalInformationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrSocialPersonalInformationVo> get(@PathVariable(value = "id") long id);

    /**
     * 根据UserId获取信息
     * @param id
     * @return
     */
    @GetMapping(value = path+"/getByUserId/{id}")
    Result<HrSocialPersonalInformationVo> getByUserId(@PathVariable long id);

    /**
     *  POST----列表查询
     * @param   hrSocialPersonalInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrSocialPersonalInformationVo>> list(@Valid @RequestBody HrSocialPersonalInformationQueryForm hrSocialPersonalInformationQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrSocialPersonalInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrSocialPersonalInformationVo>> search(@Valid @RequestBody HrSocialPersonalInformationQueryForm hrSocialPersonalInformationQueryForm);

    /**
     *  POST---保存
     * @param hrSocialPersonalInformationForm
     * @return
     */
    @PostMapping(value = path+"/save")
    Result save(@Valid @ModelAttribute HrSocialPersonalInformationForm hrSocialPersonalInformationForm);

    /**
     *  POST---提交
     * @param hrSocialPersonalInformationForm
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute HrSocialPersonalInformationForm hrSocialPersonalInformationForm);
}

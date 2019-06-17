package com.deloitte.platform.api.hr.registrationResultEnquiry.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrFamilyMemberQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrFamilyMemberForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrFamilyMemberVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :  HrFamilyMember控制器接口
 * @Modified :
 */
public interface HrFamilyMemberClient {

    public static final String path="/hr/registrationResultEnquiry/hr-family-member";


    /**
     *  POST---新增
     * @param hrFamilyMemberForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrFamilyMemberForm hrFamilyMemberForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrFamilyMemberForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrFamilyMemberForm hrFamilyMemberForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrFamilyMemberVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrFamilyMemberQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrFamilyMemberVo>> list(@Valid @RequestBody HrFamilyMemberQueryForm hrFamilyMemberQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrFamilyMemberQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrFamilyMemberVo>> search(@Valid @RequestBody HrFamilyMemberQueryForm hrFamilyMemberQueryForm);
}

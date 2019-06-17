package com.deloitte.platform.api.hr.staffAllotment.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrAccountEnquiryQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.param.HrAccountQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrAccountForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description :  HrAccount控制器接口
 * @Modified :
 */
public interface HrAccountClient {

    public static final String path="/hr/staffAllotment/hr-account";


    /**
     *  POST---新增
     * @param hrAccountForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrAccountForm hrAccountForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrAccountForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrAccountForm hrAccountForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrAccountVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrAccountVo>> list(@Valid @RequestBody HrAccountQueryForm hrAccountQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrAccountVo>> search(@Valid @RequestBody HrAccountQueryForm hrAccountQueryForm);
}

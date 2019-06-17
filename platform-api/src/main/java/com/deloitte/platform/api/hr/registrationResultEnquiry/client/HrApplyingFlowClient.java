package com.deloitte.platform.api.hr.registrationResultEnquiry.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrApplyingFlowQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrApplyingFlowForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrApplyingFlowVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :  HrApplyingFlow控制器接口
 * @Modified :
 */
public interface HrApplyingFlowClient {

    public static final String path="/hr/registrationResultEnquiry/hr-applying-flow";


    /**
     *  POST---新增
     * @param hrApplyingFlowForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrApplyingFlowForm hrApplyingFlowForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrApplyingFlowForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrApplyingFlowForm hrApplyingFlowForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrApplyingFlowVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrApplyingFlowQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrApplyingFlowVo>> list(@Valid @RequestBody HrApplyingFlowQueryForm hrApplyingFlowQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrApplyingFlowQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrApplyingFlowVo>> search(@Valid @RequestBody HrApplyingFlowQueryForm hrApplyingFlowQueryForm);
}

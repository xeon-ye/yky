package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpAssessmentQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpAssessmentForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpAssessmentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpAssessment控制器接口
 * @Modified :
 */
public interface HrZpcpAssessmentClient {

    public static final String path="/hr/recruitment/hr-zpcp-assessment";


    /**
     *  POST---新增
     * @param hrZpcpAssessmentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrZpcpAssessmentForm hrZpcpAssessmentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrZpcpAssessmentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrZpcpAssessmentForm hrZpcpAssessmentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrZpcpAssessmentVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrZpcpAssessmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrZpcpAssessmentVo>> list(@Valid @RequestBody HrZpcpAssessmentQueryForm hrZpcpAssessmentQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrZpcpAssessmentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrZpcpAssessmentVo>> search(@Valid @RequestBody HrZpcpAssessmentQueryForm hrZpcpAssessmentQueryForm);

    /**
     * GET----根据用户编号条件导出列表
     *
     * @param queryForm
     * @return
     */
    @GetMapping(value = path + "/exportAssessmentList")
    Result exportAssessmentList(@Valid @ModelAttribute HrZpcpAssessmentQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);


}

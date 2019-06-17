package com.deloitte.platform.api.hr.employ.client;

import com.deloitte.platform.api.hr.employ.param.EmployCountQueryForm;
import com.deloitte.platform.api.hr.employ.vo.EmployCountForm;
import com.deloitte.platform.api.hr.employ.vo.EmployCountVo;
import com.deloitte.platform.api.hr.employ.vo.ListEmployCountForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.param.HrApplyingFlowQueryForm;
import com.deloitte.platform.api.hr.registrationResultEnquiry.vo.HrApplyingFlowVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :  EmployCount控制器接口
 * @Modified :
 */
public interface EmployCountClient {

    public static final String path="/hr/employ-count";


    /**
     * 应聘人员查询（人力资源处）点击执行
     */
    @PostMapping(value = path+"/execute")
    Result clickExecute(@Valid @RequestBody ListEmployCountForm listEmployCountForm);

    /**
     * 获取单个应聘人员的流程详情
     * @param id
     * @return
     */
    @GetMapping(value = "/getFlow/{id}")
    Result<List<HrApplyingFlowVo>> getApplyingFlow(@PathVariable long id);

    /**
     * 导出应聘人员汇总表
     * @param
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/list/exportEmployList")
    void exportEmployList(@Valid @ModelAttribute HrApplyingFlowQueryForm hrApplyingFlowQueryForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  POST---新增
     * @param employCountForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployCountForm employCountForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employCountForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployCountForm employCountForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployCountVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employCountQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployCountVo>> list(@Valid @RequestBody EmployCountQueryForm employCountQueryForm);


    /**
     *  POST----复杂查询
     * @param  employCountQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployCountVo>> search(@Valid @RequestBody EmployCountQueryForm employCountQueryForm);
}

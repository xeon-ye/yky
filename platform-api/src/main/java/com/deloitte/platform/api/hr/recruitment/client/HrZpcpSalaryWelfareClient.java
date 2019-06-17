package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpSalaryWelfareQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpSalaryWelfareForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpSalaryWelfareVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpSalaryWelfare控制器接口
 * @Modified :
 */
public interface HrZpcpSalaryWelfareClient {

    public static final String path="/hr/recruitment/hr-zpcp-salary-welfare";


    /**
     *  POST---新增
     * @param hrZpcpSalaryWelfareForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrZpcpSalaryWelfareForm hrZpcpSalaryWelfareForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrZpcpSalaryWelfareForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrZpcpSalaryWelfareForm hrZpcpSalaryWelfareForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrZpcpSalaryWelfareVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrZpcpSalaryWelfareQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrZpcpSalaryWelfareVo>> list(@Valid @RequestBody HrZpcpSalaryWelfareQueryForm hrZpcpSalaryWelfareQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrZpcpSalaryWelfareQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrZpcpSalaryWelfareVo>> search(@Valid @RequestBody HrZpcpSalaryWelfareQueryForm hrZpcpSalaryWelfareQueryForm);



    /**
     * GET----聘任审核时根据条件导出列表
     *
     * @param queryForm
     * @return
     */
    @GetMapping(value = path+"/exportEmploywWelfareList")
    Result exportEmploywWelfareList(@Valid @ModelAttribute HrZpcpSalaryWelfareQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);

}

package com.deloitte.platform.api.fssc.report.client;

import com.deloitte.platform.api.fssc.report.param.ReportDeptBudgetDoStatisQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportDeptBudgetDoStatisForm;
import com.deloitte.platform.api.fssc.report.vo.ReportDeptBudgetDoStatisVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :  ReportDeptBudgetDoStatis控制器接口
 * @Modified :
 */
public interface ReportDeptBudgetDoStatisClient {

    public static final String path="/fssc/report-dept-budget-do-statis";


    /**
     *  POST---新增
     * @param reportDeptBudgetDoStatisForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ReportDeptBudgetDoStatisForm reportDeptBudgetDoStatisForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, reportDeptBudgetDoStatisForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ReportDeptBudgetDoStatisForm reportDeptBudgetDoStatisForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ReportDeptBudgetDoStatisVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   reportDeptBudgetDoStatisQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ReportDeptBudgetDoStatisVo>> list(@Valid @RequestBody ReportDeptBudgetDoStatisQueryForm reportDeptBudgetDoStatisQueryForm);


    /**
     *  POST----复杂查询
     * @param  reportDeptBudgetDoStatisQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ReportDeptBudgetDoStatisVo>> search(@Valid @RequestBody ReportDeptBudgetDoStatisQueryForm reportDeptBudgetDoStatisQueryForm);
}

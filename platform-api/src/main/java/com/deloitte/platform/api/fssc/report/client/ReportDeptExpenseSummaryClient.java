package com.deloitte.platform.api.fssc.report.client;

import com.deloitte.platform.api.fssc.report.param.ReportDeptExpenseSummaryQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportDeptExpenseSummaryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportDeptExpenseSummaryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportDeptExpenseSummary控制器接口
 * @Modified :
 */
public interface ReportDeptExpenseSummaryClient {

    public static final String path="/fssc/report-dept-expense-summary";


    /**
     *  POST---新增
     * @param reportDeptExpenseSummaryForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ReportDeptExpenseSummaryForm reportDeptExpenseSummaryForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, reportDeptExpenseSummaryForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ReportDeptExpenseSummaryForm reportDeptExpenseSummaryForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ReportDeptExpenseSummaryVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   reportDeptExpenseSummaryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ReportDeptExpenseSummaryVo>> list(@Valid @RequestBody ReportDeptExpenseSummaryQueryForm reportDeptExpenseSummaryQueryForm);


    /**
     *  POST----复杂查询
     * @param  reportDeptExpenseSummaryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ReportDeptExpenseSummaryVo>> search(@Valid @RequestBody ReportDeptExpenseSummaryQueryForm reportDeptExpenseSummaryQueryForm);
}

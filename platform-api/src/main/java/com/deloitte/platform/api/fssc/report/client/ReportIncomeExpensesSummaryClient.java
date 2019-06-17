package com.deloitte.platform.api.fssc.report.client;

import com.deloitte.platform.api.fssc.report.param.ReportIncomeExpensesSummaryQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportIncomeExpensesSummaryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportIncomeExpensesSummaryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportIncomeExpensesSummary控制器接口
 * @Modified :
 */
public interface ReportIncomeExpensesSummaryClient {

    public static final String path="/fssc/report-income-expenses-summary";


    /**
     *  POST---新增
     * @param reportIncomeExpensesSummaryForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ReportIncomeExpensesSummaryForm reportIncomeExpensesSummaryForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, reportIncomeExpensesSummaryForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ReportIncomeExpensesSummaryForm reportIncomeExpensesSummaryForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ReportIncomeExpensesSummaryVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   reportIncomeExpensesSummaryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ReportIncomeExpensesSummaryVo>> list(@Valid @RequestBody ReportIncomeExpensesSummaryQueryForm reportIncomeExpensesSummaryQueryForm);


    /**
     *  POST----复杂查询
     * @param  reportIncomeExpensesSummaryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ReportIncomeExpensesSummaryVo>> search(@Valid @RequestBody ReportIncomeExpensesSummaryQueryForm reportIncomeExpensesSummaryQueryForm);
}

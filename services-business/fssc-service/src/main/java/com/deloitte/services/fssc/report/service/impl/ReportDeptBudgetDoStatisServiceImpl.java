package com.deloitte.services.fssc.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.report.param.ReportDeptBudgetDoStatisQueryForm;
import com.deloitte.services.fssc.report.entity.ReportDeptBudgetDoStatis;
import com.deloitte.services.fssc.report.mapper.ReportDeptBudgetDoStatisMapper;
import com.deloitte.services.fssc.report.service.IReportDeptBudgetDoStatisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :  ReportDeptBudgetDoStatis服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReportDeptBudgetDoStatisServiceImpl extends ServiceImpl<ReportDeptBudgetDoStatisMapper, ReportDeptBudgetDoStatis> implements IReportDeptBudgetDoStatisService {


    @Autowired
    private ReportDeptBudgetDoStatisMapper reportDeptBudgetDoStatisMapper;

    @Override
    public IPage<ReportDeptBudgetDoStatis> selectPage(ReportDeptBudgetDoStatisQueryForm queryForm ) {
        QueryWrapper<ReportDeptBudgetDoStatis> queryWrapper = new QueryWrapper <>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reportDeptBudgetDoStatisMapper.selectPage(new Page<>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ReportDeptBudgetDoStatis> selectList(ReportDeptBudgetDoStatisQueryForm queryForm) {
        QueryWrapper<ReportDeptBudgetDoStatis> queryWrapper = new QueryWrapper <>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reportDeptBudgetDoStatisMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ReportDeptBudgetDoStatis> getQueryWrapper(QueryWrapper<ReportDeptBudgetDoStatis> queryWrapper,ReportDeptBudgetDoStatisQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getUnitId())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitCode())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getYear())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.YEAR,queryForm.getYear());
        }
        if(StringUtils.isNotBlank(queryForm.getMonth())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.MONTH,queryForm.getMonth());
        }
        if(StringUtils.isNotBlank(queryForm.getStatus())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.STATUS,queryForm.getStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getPeriodType())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.PERIOD_TYPE,queryForm.getPeriodType());
        }
        if(StringUtils.isNotBlank(queryForm.getMergeFlag())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.MERGE_FLAG,queryForm.getMergeFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem1())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_1,queryForm.getAnnualBudgetItem1());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem2())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_2,queryForm.getAnnualBudgetItem2());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem3())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_3,queryForm.getAnnualBudgetItem3());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem4())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_4,queryForm.getAnnualBudgetItem4());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem5())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_5,queryForm.getAnnualBudgetItem5());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem6())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_6,queryForm.getAnnualBudgetItem6());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem7())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_7,queryForm.getAnnualBudgetItem7());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem8())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_8,queryForm.getAnnualBudgetItem8());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem9())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_9,queryForm.getAnnualBudgetItem9());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem10())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_10,queryForm.getAnnualBudgetItem10());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem11())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_11,queryForm.getAnnualBudgetItem11());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem12())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_12,queryForm.getAnnualBudgetItem12());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem13())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_13,queryForm.getAnnualBudgetItem13());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem14())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_14,queryForm.getAnnualBudgetItem14());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem15())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_15,queryForm.getAnnualBudgetItem15());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem16())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_16,queryForm.getAnnualBudgetItem16());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem17())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_17,queryForm.getAnnualBudgetItem17());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem18())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_18,queryForm.getAnnualBudgetItem18());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem19())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_19,queryForm.getAnnualBudgetItem19());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualBudgetItem20())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.ANNUAL_BUDGET_ITEM_20,queryForm.getAnnualBudgetItem20());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem1())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_1,queryForm.getExpenseAmountItem1());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem2())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_2,queryForm.getExpenseAmountItem2());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem3())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_3,queryForm.getExpenseAmountItem3());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem4())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_4,queryForm.getExpenseAmountItem4());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem5())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_5,queryForm.getExpenseAmountItem5());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem6())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_6,queryForm.getExpenseAmountItem6());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem7())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_7,queryForm.getExpenseAmountItem7());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem8())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_8,queryForm.getExpenseAmountItem8());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem9())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_9,queryForm.getExpenseAmountItem9());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem10())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_10,queryForm.getExpenseAmountItem10());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem11())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_11,queryForm.getExpenseAmountItem11());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem12())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_12,queryForm.getExpenseAmountItem12());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem13())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_13,queryForm.getExpenseAmountItem13());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem14())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_14,queryForm.getExpenseAmountItem14());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem15())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_15,queryForm.getExpenseAmountItem15());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem16())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_16,queryForm.getExpenseAmountItem16());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem17())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_17,queryForm.getExpenseAmountItem17());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem18())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_18,queryForm.getExpenseAmountItem18());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem19())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_19,queryForm.getExpenseAmountItem19());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmountItem20())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXPENSE_AMOUNT_ITEM_20,queryForm.getExpenseAmountItem20());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem1())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_1,queryForm.getRpocessRateItem1());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem2())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_2,queryForm.getRpocessRateItem2());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem3())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_3,queryForm.getRpocessRateItem3());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem4())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_4,queryForm.getRpocessRateItem4());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem5())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_5,queryForm.getRpocessRateItem5());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem6())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_6,queryForm.getRpocessRateItem6());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem7())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_7,queryForm.getRpocessRateItem7());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem8())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_8,queryForm.getRpocessRateItem8());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem9())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_9,queryForm.getRpocessRateItem9());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem10())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_10,queryForm.getRpocessRateItem10());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem11())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_11,queryForm.getRpocessRateItem11());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem12())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_12,queryForm.getRpocessRateItem12());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem13())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_13,queryForm.getRpocessRateItem13());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem14())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_14,queryForm.getRpocessRateItem14());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem15())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_15,queryForm.getRpocessRateItem15());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem16())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_16,queryForm.getRpocessRateItem16());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem17())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_17,queryForm.getRpocessRateItem17());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem18())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_18,queryForm.getRpocessRateItem18());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem19())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_19,queryForm.getRpocessRateItem19());
        }
        if(StringUtils.isNotBlank(queryForm.getRpocessRateItem20())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.RPOCESS_RATE_ITEM_20,queryForm.getRpocessRateItem20());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(ReportDeptBudgetDoStatis.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }
     */
}


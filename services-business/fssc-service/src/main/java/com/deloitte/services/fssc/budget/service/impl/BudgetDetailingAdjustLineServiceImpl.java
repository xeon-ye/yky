package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseBudgetAccountQueryForm;
import com.deloitte.platform.api.fssc.budget.param.BudgetDetailingAdjustLineQueryForm;
import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustLine;
import com.deloitte.services.fssc.budget.mapper.BudgetDetailingAdjustLineMapper;
import com.deloitte.services.fssc.budget.service.IBudgetDetailingAdjustLineService;
import com.deloitte.services.fssc.eums.FsscEums;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-06
 * @Description :  BudgetDetailingAdjustLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetDetailingAdjustLineServiceImpl extends ServiceImpl<BudgetDetailingAdjustLineMapper, BudgetDetailingAdjustLine>
        implements IBudgetDetailingAdjustLineService {


    @Autowired
    private BudgetDetailingAdjustLineMapper lineMapper;

    @Autowired
    private IBaseBudgetAccountService budgetAccountService;

    @Override
    public IPage<BudgetDetailingAdjustLine> selectPage(BudgetDetailingAdjustLineQueryForm queryForm) {
        QueryWrapper<BudgetDetailingAdjustLine> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return lineMapper.selectPage(new Page<>(queryForm.getCurrentPage(),
                queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<BudgetDetailingAdjustLine> selectList(BudgetDetailingAdjustLineQueryForm queryForm) {
        QueryWrapper<BudgetDetailingAdjustLine> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return lineMapper.selectList(queryWrapper);
    }

    @Override
    public List<BudgetDetailingAdjustLine> selectList(Long documentId) {
        QueryWrapper<BudgetDetailingAdjustLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetDetailingAdjustLine.DOCUMENT_ID,documentId);
        List<BudgetDetailingAdjustLine> adjustLineList =  lineMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(adjustLineList)){
            return Collections.EMPTY_LIST;
        }
        BaseBudgetAccountQueryForm queryAccountForm = new BaseBudgetAccountQueryForm();
        queryAccountForm.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
        Map<String,String> codeNameMap = budgetAccountService.getCodeNameMap(queryAccountForm);
        for (BudgetDetailingAdjustLine adjustLine : adjustLineList){
            adjustLine.setBudgetAccountName(codeNameMap.get(adjustLine.getBudgetAccountCode()));
        }
        return adjustLineList;
    }

    @Override
    public Integer countByBudgetAccountIds(List<Long> ids) {
        QueryWrapper<BudgetDetailingAdjustLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BudgetDetailingAdjustLine.BUDGET_ACCOUNT_ID,ids);
        return count(queryWrapper);
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<BudgetDetailingAdjustLine> getQueryWrapper(QueryWrapper<BudgetDetailingAdjustLine> queryWrapper,
                                                                   BudgetDetailingAdjustLineQueryForm queryForm) {
        //条件拼接
        if (queryForm.getDocumentId() != null) {
            queryWrapper.eq(BudgetDetailingAdjustLine.DOCUMENT_ID, queryForm.getDocumentId());
        }
        if (queryForm.getBudgetAccountId() != null) {
            queryWrapper.eq(BudgetDetailingAdjustLine.BUDGET_ACCOUNT_ID, queryForm.getBudgetAccountId());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetAccountCode())) {
            queryWrapper.eq(BudgetDetailingAdjustLine.BUDGET_ACCOUNT_CODE, queryForm.getBudgetAccountCode());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetDetailingAdjustLine.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(BudgetDetailingAdjustLine.ORG_PATH, queryForm.getOrgPath());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetDetailingAdjustLine.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetDetailingAdjustLine.UPDATE_BY, queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
}


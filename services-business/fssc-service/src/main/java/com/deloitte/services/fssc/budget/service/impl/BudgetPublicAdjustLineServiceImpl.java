package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.services.fssc.budget.entity.BudgetPublicAdjustLine;
import com.deloitte.services.fssc.budget.mapper.BudgetPublicAdjustLineMapper;
import com.deloitte.services.fssc.budget.service.IBudgetPublicAdjustLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-30
 * @Description :  BudgetPublicAdjustLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetPublicAdjustLineServiceImpl extends ServiceImpl<BudgetPublicAdjustLineMapper, BudgetPublicAdjustLine>
        implements IBudgetPublicAdjustLineService {


    @Autowired
    private BudgetPublicAdjustLineMapper adjustLineMapper;

    @Override
    public IPage<BudgetPublicAdjustLine> selectPage(Long documentId, Integer currentPage, Integer pageSize) {
        QueryWrapper<BudgetPublicAdjustLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetPublicAdjustLine.DOCUMENT_ID,documentId);
        return this.page(new Page<>(currentPage,pageSize),queryWrapper);
    }

    @Override
    public List<BudgetPublicAdjustLine> selectList(Long documentId) {
        QueryWrapper<BudgetPublicAdjustLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetPublicAdjustLine.DOCUMENT_ID,documentId);
        return this.list(queryWrapper);
    }

    @Override
    public Integer countByBudgetAccountIds(List<Long> ids) {
        QueryWrapper<BudgetPublicAdjustLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BudgetPublicAdjustLine.BUDGET_ACCOUNT_ID,ids);
        return this.count(queryWrapper);
    }
}


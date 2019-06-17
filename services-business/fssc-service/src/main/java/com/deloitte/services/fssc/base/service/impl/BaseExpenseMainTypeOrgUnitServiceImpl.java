package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseMainTypeUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainTypeUnit;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainTypeOrgUnit;
import com.deloitte.services.fssc.base.mapper.BaseExpenseMainTypeUnitMapper;
import com.deloitte.services.fssc.base.service.IBaseExpenseMainTypeOrgUnitService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Deprecated
public class BaseExpenseMainTypeOrgUnitServiceImpl extends
        ServiceImpl<BaseExpenseMainTypeUnitMapper, BaseExpenseMainTypeUnit> implements
        IBaseExpenseMainTypeOrgUnitService {

    @Autowired
    private BaseExpenseMainTypeUnitMapper mainTypeOrgUnitMapper;

    @Override
    public boolean updateMainTypeOrgUnit(BaseExpenseMainTypeUnit mainTypeOrgUnit) {
        return false;
    }

    @Override
    public IPage<BaseExpenseMainTypeUnit> selectPage(
            BaseExpenseMainTypeUnitQueryForm queryForm) {
        QueryWrapper<BaseExpenseMainTypeUnit> queryWrapper = new QueryWrapper<>();
        this.getQueryWrapper(queryWrapper, queryForm);
        return mainTypeOrgUnitMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public List<BaseExpenseMainTypeUnit> selectList(
            BaseExpenseMainTypeUnitQueryForm queryForm) {
        QueryWrapper<BaseExpenseMainTypeUnit> queryWrapper = new QueryWrapper<>();
        this.getQueryWrapper(queryWrapper, queryForm);
        return mainTypeOrgUnitMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseExpenseMainTypeUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseExpenseMainTypeUnit.ID, idList);
        List<BaseExpenseMainTypeUnit> mainTypeOrgUnitList = this.list(queryWrapper);
        for (BaseExpenseMainTypeUnit mainTypeOrgUnit : mainTypeOrgUnitList) {
            mainTypeOrgUnit.setValidFlag(validFlag);
        }
        return this.updateBatchById(mainTypeOrgUnitList);
    }

    /**
     * 通用查询
     */
    public QueryWrapper<BaseExpenseMainTypeUnit> getQueryWrapper(
            QueryWrapper<BaseExpenseMainTypeUnit> queryWrapper,
            BaseExpenseMainTypeUnitQueryForm queryForm) {
        //条件拼接
        if (queryForm.getExpenseMainTypeId() != null) {
            queryWrapper.eq(BaseExpenseMainTypeUnit.EXPENSE_MAIN_TYPE_ID,
                    queryForm.getExpenseMainTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitCode())) {
            queryWrapper.like(BaseIncomeMainTypeOrgUnit.ORG_UNIT_CODE, queryForm.getOrgUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitName())) {
            queryWrapper.like(BaseIncomeMainTypeOrgUnit.ORG_UNIT_NAME, queryForm.getOrgUnitName());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseIncomeMainType.VALID_FLAG, queryForm.getValidFlag());
        }
        return queryWrapper;
    }
}

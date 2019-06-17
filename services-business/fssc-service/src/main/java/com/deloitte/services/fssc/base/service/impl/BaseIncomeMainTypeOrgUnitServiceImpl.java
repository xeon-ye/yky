package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeMainTypeOrgUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainTypeOrgUnit;
import com.deloitte.services.fssc.base.mapper.BaseIncomeMainTypeOrgUnitMapper;
import com.deloitte.services.fssc.base.service.IBaseIncomeMainTypeOrgUnitService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Deprecated
public class BaseIncomeMainTypeOrgUnitServiceImpl extends
        ServiceImpl<BaseIncomeMainTypeOrgUnitMapper, BaseIncomeMainTypeOrgUnit> implements
        IBaseIncomeMainTypeOrgUnitService {

    @Autowired
    private BaseIncomeMainTypeOrgUnitMapper mainTypeOrgUnitMapper;

    @Override
    public boolean updateMainTypeOrgUnit(BaseIncomeMainTypeOrgUnit mainTypeOrgUnit) {
        return false;
    }

    @Override
    public IPage<BaseIncomeMainTypeOrgUnit> selectPage(
            BaseIncomeMainTypeOrgUnitQueryForm queryForm) {
        QueryWrapper<BaseIncomeMainTypeOrgUnit> queryWrapper = new QueryWrapper<>();
        this.getQueryWrapper(queryWrapper, queryForm);
        return mainTypeOrgUnitMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public List<BaseIncomeMainTypeOrgUnit> selectList(
            BaseIncomeMainTypeOrgUnitQueryForm queryForm) {
        QueryWrapper<BaseIncomeMainTypeOrgUnit> queryWrapper = new QueryWrapper<>();
        this.getQueryWrapper(queryWrapper, queryForm);
        return mainTypeOrgUnitMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseIncomeMainTypeOrgUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseIncomeMainTypeOrgUnit.ID, idList);
        List<BaseIncomeMainTypeOrgUnit> mainTypeOrgUnitList = this.list(queryWrapper);
        for (BaseIncomeMainTypeOrgUnit mainTypeOrgUnit : mainTypeOrgUnitList) {
            mainTypeOrgUnit.setValidFlag(validFlag);
        }
        return this.updateBatchById(mainTypeOrgUnitList);
    }


    /**
     * 通用查询
     */
    private QueryWrapper<BaseIncomeMainTypeOrgUnit> getQueryWrapper(
            QueryWrapper<BaseIncomeMainTypeOrgUnit> queryWrapper,
            BaseIncomeMainTypeOrgUnitQueryForm queryForm) {
        //条件拼接
        if (queryForm.getIncomeMainTypeId() != null) {
            queryWrapper.eq(BaseIncomeMainTypeOrgUnit.INCOME_MAIN_TYPE_ID,
                    queryForm.getIncomeMainTypeId());
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

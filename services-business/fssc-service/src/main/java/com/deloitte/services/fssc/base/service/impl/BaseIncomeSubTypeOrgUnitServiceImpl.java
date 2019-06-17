package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeSubTypeOrgUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainTypeOrgUnit;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubTypeOrgUnit;
import com.deloitte.services.fssc.base.mapper.BaseIncomeSubTypeOrgUnitMapper;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeOrgUnitService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Deprecated
public class BaseIncomeSubTypeOrgUnitServiceImpl extends
        ServiceImpl<BaseIncomeSubTypeOrgUnitMapper, BaseIncomeSubTypeOrgUnit> implements
        IBaseIncomeSubTypeOrgUnitService {

    @Autowired
    BaseIncomeSubTypeOrgUnitMapper subTypeOrgUnitMapper;

    @Override
    public boolean updateSubTypeOrgUnit(BaseIncomeSubTypeOrgUnit subTypeOrgUnit) {
        return false;
    }

    @Override
    public IPage<BaseIncomeSubTypeOrgUnit> selectPage(BaseIncomeSubTypeOrgUnitQueryForm queryForm) {
        QueryWrapper<BaseIncomeSubTypeOrgUnit> queryWrapper = new QueryWrapper<>();
        this.getQueryWrapper(queryWrapper, queryForm);
        return subTypeOrgUnitMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public List<BaseIncomeSubTypeOrgUnit> selectList(BaseIncomeSubTypeOrgUnitQueryForm queryForm) {
        QueryWrapper<BaseIncomeSubTypeOrgUnit> queryWrapper = new QueryWrapper<>();
        this.getQueryWrapper(queryWrapper, queryForm);
        return subTypeOrgUnitMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseIncomeSubTypeOrgUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseIncomeMainTypeOrgUnit.ID, idList);
        List<BaseIncomeSubTypeOrgUnit> subTypeOrgUnitList = this.list(queryWrapper);
        for (BaseIncomeSubTypeOrgUnit mainTypeOrgUnit : subTypeOrgUnitList) {
            mainTypeOrgUnit.setValidFlag(validFlag);
        }
        return this.updateBatchById(subTypeOrgUnitList);
    }

    /**
     * 通用查询
     */
    private QueryWrapper<BaseIncomeSubTypeOrgUnit> getQueryWrapper(
            QueryWrapper<BaseIncomeSubTypeOrgUnit> queryWrapper,
            BaseIncomeSubTypeOrgUnitQueryForm queryForm) {
        if (queryForm.getIncomeSubTypeId() != null) {
            queryWrapper.eq(BaseIncomeSubTypeOrgUnit.INCOME_SUB_TYPE_ID,
                    queryForm.getIncomeSubTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitCode())) {
            queryWrapper.like(BaseIncomeSubTypeOrgUnit.ORG_UNIT_CODE, queryForm.getOrgUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitName())) {
            queryWrapper.like(BaseIncomeSubTypeOrgUnit.ORG_UNIT_NAME, queryForm.getOrgUnitName());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseIncomeMainType.VALID_FLAG, queryForm.getValidFlag());
        }
        return queryWrapper;
    }

}

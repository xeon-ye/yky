package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseSubTypeUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseExpenseSubTypeUnit;
import com.deloitte.services.fssc.base.mapper.BaseExpenseSubTypeUnitMapper;
import com.deloitte.services.fssc.base.service.IBaseExpenseSubTypeUnitService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-02
 * @Description :  BaseExpenseSubTypeUnit服务实现类
 * @Modified :
 */
@Service
@Transactional
@Deprecated
public class BaseExpenseSubTypeUnitServiceImpl extends
        ServiceImpl<BaseExpenseSubTypeUnitMapper, BaseExpenseSubTypeUnit> implements
        IBaseExpenseSubTypeUnitService {


    @Autowired
    private BaseExpenseSubTypeUnitMapper baseExpenseSubTypeUnitMapper;

    @Override
    public boolean updateSubTypeOrgUnit(BaseExpenseSubTypeUnit subTypeOrgUnit) {
        return false;
    }

    @Override
    public IPage<BaseExpenseSubTypeUnit> selectPage(BaseExpenseSubTypeUnitQueryForm queryForm) {
        QueryWrapper<BaseExpenseSubTypeUnit> queryWrapper = new QueryWrapper<>();
        this.getQueryWrapper(queryWrapper, queryForm);
        return baseExpenseSubTypeUnitMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }


    @Override
    public List<BaseExpenseSubTypeUnit> selectList(BaseExpenseSubTypeUnitQueryForm queryForm) {
        QueryWrapper<BaseExpenseSubTypeUnit> queryWrapper = new QueryWrapper<>();
        this.getQueryWrapper(queryWrapper, queryForm);
        return baseExpenseSubTypeUnitMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseExpenseSubTypeUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseExpenseSubTypeUnit.ID, idList);
        List<BaseExpenseSubTypeUnit> subTypeOrgUnitList = this.list(queryWrapper);
        for (BaseExpenseSubTypeUnit mainTypeOrgUnit : subTypeOrgUnitList) {
            mainTypeOrgUnit.setValidFlag(validFlag);
        }
        return this.updateBatchById(subTypeOrgUnitList);
    }


    /**
     * 通用查询
     */
    private QueryWrapper<BaseExpenseSubTypeUnit> getQueryWrapper(
            QueryWrapper<BaseExpenseSubTypeUnit> queryWrapper,
            BaseExpenseSubTypeUnitQueryForm queryForm) {
        if (queryForm.getExpenseSubTypeId() != null) {
            queryWrapper.eq(BaseExpenseSubTypeUnit.EXPENSE_SUB_TYPE_ID,
                    queryForm.getExpenseSubTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitCode())) {
            queryWrapper.like(BaseExpenseSubTypeUnit.ORG_UNIT_CODE, queryForm.getOrgUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitName())) {
            queryWrapper.like(BaseExpenseSubTypeUnit.ORG_UNIT_NAME, queryForm.getOrgUnitName());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseExpenseSubTypeUnit.VALID_FLAG, queryForm.getValidFlag());
        }
        return queryWrapper;
    }

}


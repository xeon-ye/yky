package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseBudgetAccountQueryForm;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.mapper.BaseBudgetAccountMapper;
import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-06
 * @Description :  BaseBudgetAccount服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseBudgetAccountServiceImpl extends
        ServiceImpl<BaseBudgetAccountMapper, BaseBudgetAccount> implements
        IBaseBudgetAccountService {


    @Autowired
    private BaseBudgetAccountMapper budgetAccountMapper;

    @Override
    public BaseBudgetAccount getBudgetAccountByCode(String code) {
        QueryWrapper<BaseBudgetAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseBudgetAccount.CODE, code);
        return this.getOne(queryWrapper);
    }

    @Override
    public BaseBudgetAccount getBudgetAccountByCode(String code, String budgetType) {
        QueryWrapper<BaseBudgetAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseBudgetAccount.CODE, code);
        if (StringUtils.isNotBlank(budgetType)){
            queryWrapper.eq(BaseBudgetAccount.BUDGET_TYPE,budgetType);
        }
        return this.getOne(queryWrapper);
    }

    @Override
    public Integer countByCode(String code) {
        QueryWrapper<BaseBudgetAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseBudgetAccount.CODE, code);
        return this.count(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseBudgetAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseBudgetAccount.ID, idList);
        List<BaseBudgetAccount> mainTypeList = this.list(queryWrapper);
        for (BaseBudgetAccount mainType : mainTypeList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(mainType.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
            } else {
                if (validFlag.equals(mainType.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_INVALID);
                }
            }
            mainType.setValidFlag(validFlag);
        }
        return this.updateBatchById(mainTypeList);
    }

    @Override
    public IPage<BaseBudgetAccount> selectPage(BaseBudgetAccountQueryForm queryForm) {
        QueryWrapper<BaseBudgetAccount> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetAccountMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);

    }

    @Override
    public List<BaseBudgetAccount> selectList(BaseBudgetAccountQueryForm queryForm) {
        QueryWrapper<BaseBudgetAccount> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetAccountMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, String> getCodeNameMap(BaseBudgetAccountQueryForm queryForm) {
        QueryWrapper<BaseBudgetAccount> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        List<BaseBudgetAccount> budgetAccountList =  budgetAccountMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(budgetAccountList)){
            return Collections.EMPTY_MAP;
        }
        Map<String,String> codeNameMap = new HashMap<>(budgetAccountList.size());
        for (BaseBudgetAccount budgetAccount : budgetAccountList){
            codeNameMap.put(budgetAccount.getCode(),budgetAccount.getName());
        }
        return codeNameMap;
    }

    /**
     * 通用查询
     */
    public QueryWrapper<BaseBudgetAccount> getQueryWrapper(
            QueryWrapper<BaseBudgetAccount> queryWrapper,
            BaseBudgetAccountQueryForm queryForm) {
        //条件拼接
        if (queryForm.getId() != null) {
            queryWrapper.eq(BaseBudgetAccount.ID, queryForm.getId());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.like(BaseBudgetAccount.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(BaseBudgetAccount.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getDescription())) {
            queryWrapper.like(BaseBudgetAccount.DESCRIPTION, queryForm.getDescription());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseBudgetAccount.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetType())) {
            queryWrapper.eq(BaseBudgetAccount.BUDGET_TYPE, queryForm.getBudgetType());
        }
        if (StringUtils.isNotBlank(queryForm.getSort()) && StringUtils
                .isNotBlank(queryForm.getSortOrder())) {
            if (FsscEums.ASC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByAsc(queryForm.getSort());
            } else if (FsscEums.DESC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByDesc(queryForm.getSort());
            }
        } else {
            queryWrapper.orderByDesc(BaseBudgetAccount.UPDATE_TIME);
        }
        return queryWrapper;
    }

}


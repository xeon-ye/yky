package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseMainTypeQueryForm;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.base.mapper.BaseExpenseMainTypeMapper;
import com.deloitte.services.fssc.base.service.IBaseExpenseMainTypeService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :  BaseeExpenseMainType服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseExpenseMainTypeServiceImpl extends
        ServiceImpl<BaseExpenseMainTypeMapper, BaseExpenseMainType> implements
        IBaseExpenseMainTypeService {

    @Autowired
    private BaseExpenseMainTypeMapper baseExpenseMainTypeMapper;

    @Override
    public void deleteByMainTypeIds(List<Long> ids) {
        if(CollectionUtils.isNotEmpty(ids)){
            deleteById(ids);
        }
    }
    //递归删除s
    private void deleteById(List<Long> ids){
        List<BaseExpenseMainType> mainType = baseExpenseMainTypeMapper.selectBatchIds(ids);
        if(mainType!=null){
            for (BaseExpenseMainType type:mainType){
                String code = type.getCode();
                QueryWrapper<BaseExpenseMainType> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("PARENT_CODE",code);
                List<BaseExpenseMainType> list = baseExpenseMainTypeMapper.selectList(queryWrapper);
                if(CollectionUtils.isNotEmpty(list)){
                    baseExpenseMainTypeMapper.deleteBatchIds(ids);
                    List<Long> collect = list.stream().map(m -> m.getId()).collect(Collectors.toList());
                    deleteById(collect);
                }
            }
        }
    }


    @Override
    public BaseExpenseMainType getMainTypeByCode(String code,String unitCode) {
        QueryWrapper<BaseExpenseMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseExpenseMainType.CODE, code);
        queryWrapper.eq(BaseExpenseMainType.UNIT_CODE, unitCode);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<BaseExpenseMainType> getChildMainType(String parentCode,String unitCode) {
        QueryWrapper<BaseExpenseMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseExpenseMainType.PARENT_CODE, parentCode);
        queryWrapper.eq(BaseExpenseMainType.UNIT_CODE, unitCode);
        return baseExpenseMainTypeMapper.selectList(queryWrapper);
    }

    @Override
    public boolean updateMainType(BaseExpenseMainType mainType) {
        List<BaseExpenseMainType> childMainTypeList = this.getChildMainType(mainType.getCode(),
                mainType.getUnitCode());
        if (baseExpenseMainTypeMapper.updateById(mainType) < 1) {
            throw new FSSCException(FsscErrorType.SAVE_FAIL);
        }
        if (CollectionUtils.isNotEmpty(childMainTypeList)) {
            for (BaseExpenseMainType child : childMainTypeList) {
                child.setParentName(mainType.getName());
            }
            if (!this.saveOrUpdateBatch(childMainTypeList)) {
                throw new FSSCException(FsscErrorType.SAVE_FAIL);
            }
        }
        return true;
    }

    @Override
    public IPage<BaseExpenseMainType> selectPage(BaseExpenseMainTypeQueryForm queryForm) {
        QueryWrapper<BaseExpenseMainType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseExpenseMainTypeMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public List<BaseExpenseMainType> selectList(BaseExpenseMainTypeQueryForm queryForm) {
        QueryWrapper<BaseExpenseMainType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseExpenseMainTypeMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseExpenseMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseExpenseMainType.ID, idList);
        List<BaseExpenseMainType> mainTypeList = this.list(queryWrapper);
        for (BaseExpenseMainType mainType : mainTypeList) {
            mainType.setInvalidFlag(validFlag);
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                mainType.setValidDate(LocalDateTime.now());
            } else {
                mainType.setInvalidDate(LocalDateTime.now());
            }
        }
        return this.updateBatchById(mainTypeList);
    }

    @Override
    public boolean updateBudgetAccountId(List<Long> ids, Long budgetAccountId,String unitCode) {
        Collection<BaseExpenseMainType> mainTypeList = this.listByIds(ids);
        for (BaseExpenseMainType mainType : mainTypeList) {
            if (mainType.getBudgetAccountId() != null && budgetAccountId
                    .equals(mainType.getBudgetAccountId())) {
                throw new FSSCException(FsscErrorType.EXPENSE_MAIN_TYPE_RELATED);
            }
            mainType.setBudgetAccountId(budgetAccountId);
        }
        QueryWrapper<BaseExpenseMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseExpenseMainType.BUDGET_ACCOUNT_ID,budgetAccountId);
        queryWrapper.eq(BaseExpenseMainType.UNIT_CODE,unitCode);
        List<BaseExpenseMainType> relatedMainTypeList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(relatedMainTypeList)){
            for (BaseExpenseMainType relatedMainType : relatedMainTypeList){
                //解除绑定
                relatedMainType.setBudgetAccountId(null);
            }
            this.updateBatchById(relatedMainTypeList);
        }
        return this.updateBatchById(mainTypeList);
    }

    @Override
    public boolean unRelateBudgetAccount(List<Long> ids) {
        return baseExpenseMainTypeMapper.clearBudgetAccountId(ids) > 0;
    }

    @Override
    public Integer countByBudgetAccountIds(List<Long> budgetAccountIds) {
        QueryWrapper<BaseExpenseMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseExpenseMainType.PARENT_FLAG,FsscEums.NO.getValue());
        queryWrapper.in(BaseExpenseMainType.BUDGET_ACCOUNT_ID,budgetAccountIds);
        return this.count(queryWrapper);
    }

    @Override
    public Integer countByBudgetAccountIds(List<Long> budgetAccountIds, String unitCode) {
        QueryWrapper<BaseExpenseMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseExpenseMainType.PARENT_FLAG,FsscEums.NO.getValue());
        queryWrapper.in(BaseExpenseMainType.BUDGET_ACCOUNT_ID,budgetAccountIds);
        queryWrapper.eq(BaseExpenseMainType.UNIT_CODE,unitCode);
        return this.count(queryWrapper);
    }

    @Override
    public boolean isRelateBudgetAccount(List<Long> ids) {
        QueryWrapper<BaseExpenseMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseExpenseMainType.PARENT_FLAG,FsscEums.NO.getValue());
        queryWrapper.in(BaseExpenseMainType.ID,ids);
        queryWrapper.isNotNull(BaseExpenseMainType.BUDGET_ACCOUNT_ID);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public List<BaseExpenseMainType> selectListData(List<Long> ids) {
        List<BaseExpenseMainType> mainType = baseExpenseMainTypeMapper.selectBatchIds(ids);
        return mainType;
    }

    /**
     * 通用查询
     */
    private QueryWrapper<BaseExpenseMainType> getQueryWrapper(
            QueryWrapper<BaseExpenseMainType> queryWrapper,
            BaseExpenseMainTypeQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.like(BaseExpenseMainType.UNIT_CODE, queryForm.getUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.like(BaseExpenseMainType.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(BaseExpenseMainType.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getParentFlag())) {
            queryWrapper.eq(BaseExpenseMainType.PARENT_FLAG, queryForm.getParentFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getInvalidFlag())) {
            queryWrapper.eq(BaseExpenseMainType.INVALID_FLAG, queryForm.getInvalidFlag());
        }
        if (queryForm.isSelectByBudgetAccount()){
            queryWrapper.isNull(BaseExpenseMainType.BUDGET_ACCOUNT_ID);
        }
        if (queryForm.getBudgetAccountId() != null) {
            queryWrapper.eq(BaseExpenseMainType.BUDGET_ACCOUNT_ID, queryForm.getBudgetAccountId());
        }
        if (StringUtils.isNotBlank(queryForm.getSort()) && StringUtils.isNotBlank(queryForm.getSortOrder())) {
            if (FsscEums.ASC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByAsc(queryForm.getSort());
            } else if (FsscEums.DESC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByDesc(queryForm.getSort());
            }
        }else{
            queryWrapper.orderByAsc(BaseExpenseMainType.CODE);
        }
        return queryWrapper;
    }
}


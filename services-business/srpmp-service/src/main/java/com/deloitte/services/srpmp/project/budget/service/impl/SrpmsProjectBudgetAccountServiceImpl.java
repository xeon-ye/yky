package com.deloitte.services.srpmp.project.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetAccountQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetAccountForm;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetAccount;
import com.deloitte.services.srpmp.project.budget.mapper.SrpmsProjectBudgetAccountMapper;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description :  SrpmsProjectBudgetAccount服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectBudgetAccountServiceImpl extends ServiceImpl<SrpmsProjectBudgetAccountMapper, SrpmsProjectBudgetAccount> implements ISrpmsProjectBudgetAccountService {


    @Autowired
    private SrpmsProjectBudgetAccountMapper srpmsProjectBudgetAccountMapper;

    @Override
    public IPage<SrpmsProjectBudgetAccount> selectPage(SrpmsProjectBudgetAccountQueryForm queryForm ) {
        QueryWrapper<SrpmsProjectBudgetAccount> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectBudgetAccountMapper.selectPage(new Page<>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsProjectBudgetAccount> selectList(SrpmsProjectBudgetAccountQueryForm queryForm) {
        QueryWrapper<SrpmsProjectBudgetAccount> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectBudgetAccountMapper.selectList(queryWrapper);
    }

    @Override
    public Long saveOrUpdateBudgetAccount(SrpmsProjectBudgetAccountForm form) {
        if (null == form) {
            throw new ServiceException(SrpmsErrorType.PARAM_NULL, "保存参数为空");
        }
        SrpmsProjectBudgetAccount budgetAccount =new BeanUtils<SrpmsProjectBudgetAccount>().copyObj(form,SrpmsProjectBudgetAccount.class);
        if (null == budgetAccount.getId()) {
            if (budgetAccount.getParentId() == null) {
                throw new ServiceException(SrpmsErrorType.PARAM_NULL, "父级ID不能为空");
            }
            if (budgetAccount.getBudgetAccountName() == null) {
                throw new ServiceException(SrpmsErrorType.PARAM_NULL, "预算科目名称不能为空");
            }
            if (budgetAccount.getBudgetAccountStatus() == null) {
                throw new ServiceException(SrpmsErrorType.PARAM_NULL, "预算科目状态不能为空");
            }
            if (budgetAccount.getProjectCategory() == null) {
                throw new ServiceException(SrpmsErrorType.PARAM_NULL, "预算科目所属项目类型不能为空");
            }
            budgetAccount.setBudgetAccountCode(getBudgetAccountCode(form.getProjectCategory()));
        }
        this.saveOrUpdate(budgetAccount);
        return budgetAccount.getId();
    }

    private String getBudgetAccountCode(String projectCategory) {
        String begin = "100001";
        String budgetAccountCode = "";
        QueryWrapper<SrpmsProjectBudgetAccount> queryWrapper = new QueryWrapper<>();
        if (StringUtils.equals(projectCategory, ProjectCategoryEnums.INNOVATE_BCOO.getHeader())) {
            queryWrapper.eq(SrpmsProjectBudgetAccount.PROJECT_CATEGORY, projectCategory);
            queryWrapper.orderByDesc(SrpmsProjectBudgetAccount.CREATE_TIME);
            SrpmsProjectBudgetAccount account = this.getOne(queryWrapper);
            if (null == account) {
                budgetAccountCode = "BCOO" + begin;
            } else {
                int suffix = Integer.parseInt(StringUtils.substring(account.getBudgetAccountCode(), 4));
                suffix += 1;
                budgetAccountCode = "BCOO" + suffix;
            }
        }
        if (StringUtils.equals(projectCategory, ProjectCategoryEnums.INNOVATE_COO.getHeader())) {
            queryWrapper.eq(SrpmsProjectBudgetAccount.PROJECT_CATEGORY, projectCategory);
            queryWrapper.orderByDesc(SrpmsProjectBudgetAccount.CREATE_TIME);
            SrpmsProjectBudgetAccount account = this.getOne(queryWrapper);
            if (null == account) {
                budgetAccountCode = "COO" + begin;
            } else {
                int suffix = Integer.parseInt(StringUtils.substring(account.getBudgetAccountCode(), 3));
                suffix += 1;
                budgetAccountCode = "COO" + suffix;
            }
        }
        if (StringUtils.equals(projectCategory, ProjectCategoryEnums.INNOVATE_PRE.getHeader())) {
            queryWrapper.eq(SrpmsProjectBudgetAccount.PROJECT_CATEGORY, projectCategory);
            queryWrapper.orderByDesc(SrpmsProjectBudgetAccount.CREATE_TIME);
            SrpmsProjectBudgetAccount account = this.getOne(queryWrapper);
            if (null == account) {
                budgetAccountCode = "PRE" + begin;
            } else {
                int suffix = Integer.parseInt(StringUtils.substring(account.getBudgetAccountCode(), 3));
                suffix += 1;
                budgetAccountCode = "PRE" + suffix;
            }
        }
        if (StringUtils.equals(projectCategory, ProjectCategoryEnums.ACADEMY.getHeader())) {
            queryWrapper.eq(SrpmsProjectBudgetAccount.PROJECT_CATEGORY, projectCategory);
            queryWrapper.orderByDesc(SrpmsProjectBudgetAccount.CREATE_TIME);
            SrpmsProjectBudgetAccount account = this.getOne(queryWrapper);
            if (null == account) {
                budgetAccountCode = "ACADEMY" + begin;
            } else {
                int suffix = Integer.parseInt(StringUtils.substring(account.getBudgetAccountCode(), 7));
                suffix += 1;
                budgetAccountCode = "ACADEMY" + suffix;
            }
        }
        if (StringUtils.equals(projectCategory, ProjectCategoryEnums.SCHOOL_TEACH.getHeader())) {
            queryWrapper.eq(SrpmsProjectBudgetAccount.PROJECT_CATEGORY, projectCategory);
            queryWrapper.orderByDesc(SrpmsProjectBudgetAccount.CREATE_TIME);
            SrpmsProjectBudgetAccount account = this.getOne(queryWrapper);
            if (null == account) {
                budgetAccountCode = "TEACH" + begin;
            } else {
                int suffix = Integer.parseInt(StringUtils.substring(account.getBudgetAccountCode(), 5));
                suffix += 1;
                budgetAccountCode = "TEACH" + suffix;
            }
        }
        if (StringUtils.equals(projectCategory, ProjectCategoryEnums.SCHOOL_STU.getHeader())) {
            queryWrapper.eq(SrpmsProjectBudgetAccount.PROJECT_CATEGORY, projectCategory);
            queryWrapper.orderByDesc(SrpmsProjectBudgetAccount.CREATE_TIME);
            SrpmsProjectBudgetAccount account = this.getOne(queryWrapper);
            if (null == account) {
                budgetAccountCode = "STU" + begin;
            } else {
                int suffix = Integer.parseInt(StringUtils.substring(account.getBudgetAccountCode(), 3));
                suffix += 1;
                budgetAccountCode = "STU" + suffix;
            }
        }
        return budgetAccountCode;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<SrpmsProjectBudgetAccount> getQueryWrapper(QueryWrapper<SrpmsProjectBudgetAccount> queryWrapper,SrpmsProjectBudgetAccountQueryForm queryForm){
        if (null == queryForm) {
            return queryWrapper;
        }
        //条件拼接
        if(null != queryForm.getParentId()){
            queryWrapper.eq(SrpmsProjectBudgetAccount.PARENT_ID,queryForm.getParentId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCategory())){
            queryWrapper.eq(SrpmsProjectBudgetAccount.PROJECT_CATEGORY,queryForm.getProjectCategory());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetAccountYear())){
            queryWrapper.eq(SrpmsProjectBudgetAccount.BUDGET_ACCOUNT_YEAR,queryForm.getBudgetAccountYear());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetAccountCode())){
            queryWrapper.eq(SrpmsProjectBudgetAccount.BUDGET_ACCOUNT_CODE,queryForm.getBudgetAccountCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetAccountName())){
            queryWrapper.eq(SrpmsProjectBudgetAccount.BUDGET_ACCOUNT_NAME,queryForm.getBudgetAccountName());
        }
        if(null != queryForm.getBudgetAccountStatus()){
            queryWrapper.eq(SrpmsProjectBudgetAccount.BUDGET_ACCOUNT_STATUS,queryForm.getBudgetAccountStatus());
        }
        if(null != queryForm.getCreateTime()){
            queryWrapper.eq(SrpmsProjectBudgetAccount.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SrpmsProjectBudgetAccount.CREATE_BY,queryForm.getCreateBy());
        }
        if(null != queryForm.getUpdateTime()){
            queryWrapper.eq(SrpmsProjectBudgetAccount.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SrpmsProjectBudgetAccount.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }

}


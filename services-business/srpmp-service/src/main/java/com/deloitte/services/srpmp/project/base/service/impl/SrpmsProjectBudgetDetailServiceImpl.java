package com.deloitte.services.srpmp.project.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetDetailQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectBudgetDetail;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectBudgetDetailMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectBudgetDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :  SrpmsProjectBudgetDetail服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectBudgetDetailServiceImpl extends ServiceImpl<SrpmsProjectBudgetDetailMapper, SrpmsProjectBudgetDetail> implements ISrpmsProjectBudgetDetailService {


    @Autowired
    private SrpmsProjectBudgetDetailMapper srpmsProjectBudgetDetailMapper;

    @Override
    public IPage<SrpmsProjectBudgetDetail> selectPage(SrpmsProjectBudgetDetailQueryForm queryForm ) {
        QueryWrapper<SrpmsProjectBudgetDetail> queryWrapper = new QueryWrapper <SrpmsProjectBudgetDetail>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectBudgetDetailMapper.selectPage(new Page<SrpmsProjectBudgetDetail>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsProjectBudgetDetail> selectList(SrpmsProjectBudgetDetailQueryForm queryForm) {
        QueryWrapper<SrpmsProjectBudgetDetail> queryWrapper = new QueryWrapper <SrpmsProjectBudgetDetail>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectBudgetDetailMapper.selectList(queryWrapper);
    }

    /*
        for (SrpmsProjectDeptJoinVo joinDeptVo: deptJoinVoList){
     *             SrpmsProjectDeptJoin deptJoinEntity = new SrpmsProjectDeptJoin();
     *             BeanUtils.copyProperties(joinDeptVo, deptJoinEntity);
     *             deptJoinEntity.setJoinWay(joinWay.getCode());
     *             deptJoinEntity.setProjectId(projectId);
     *             deptJoinList.add(deptJoinEntity);
     *         }
     *         UpdateWrapper<SrpmsProjectDeptJoin> deptJoinWrapper = new UpdateWrapper<SrpmsProjectDeptJoin>() ;
     *         deptJoinWrapper.eq(SrpmsProjectDeptJoin.JOIN_WAY, joinWay.getCode());
     *         deptJoinWrapper.eq(SrpmsProjectDeptJoin.PROJECT_ID, projectId);
     *         this.remove(deptJoinWrapper);
     *         this.saveBatch(deptJoinList);

     */

    @Override
    public void cleanAndSaveBudgetDetail(List<SrpmsProjectBudgetDetailVo> budgetDetailVoList, BudgetCategoryEnums budgetCategoryEnums, Long projectId) {
        if (budgetCategoryEnums == null) return;
        UpdateWrapper<SrpmsProjectBudgetDetail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(SrpmsProjectBudgetDetail.BUDGET_CATEGORY, budgetCategoryEnums.getCode());
        updateWrapper.eq(SrpmsProjectBudgetDetail.PROJECT_ID, projectId);
        this.remove(updateWrapper);
        if (budgetDetailVoList == null){
            return;
        }
        List<SrpmsProjectBudgetDetail> budgetDetailEntityList = new ArrayList<>();
        for (SrpmsProjectBudgetDetailVo budgetDetailVo: budgetDetailVoList){
            SrpmsProjectBudgetDetail budgetDetailEntity = new SrpmsProjectBudgetDetail();
            BeanUtils.copyProperties(budgetDetailVo, budgetDetailEntity);
            budgetDetailEntity.setBudgetDetail(budgetDetailVo.getBudgetDetail().toJSONString());
            budgetDetailEntity.setBudgetCategory(budgetCategoryEnums.getCode());
            budgetDetailEntity.setProjectId(projectId);
            budgetDetailEntityList.add(budgetDetailEntity);
        }
        this.saveBatch(budgetDetailEntityList);
    }

    @Override
    public List<SrpmsProjectBudgetDetailVo> queryBudgetDetailListByCategory(BudgetCategoryEnums budgetCategoryEnums, Long projectId) {
        List<SrpmsProjectBudgetDetailVo> voList = new ArrayList<>();
        if (budgetCategoryEnums == null){
            return voList;
        }
        QueryWrapper<SrpmsProjectBudgetDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectBudgetDetail.BUDGET_CATEGORY, budgetCategoryEnums.getCode());
        queryWrapper.eq(SrpmsProjectBudgetDetail.PROJECT_ID, projectId);
        queryWrapper.orderByAsc(SrpmsProjectBudgetDetail.BUDGET_YEAR);
        List<SrpmsProjectBudgetDetail> entityList = this.list(queryWrapper);

        for (SrpmsProjectBudgetDetail BudgetDetailEntity: entityList){
            SrpmsProjectBudgetDetailVo budgetDetailVo = new SrpmsProjectBudgetDetailVo();
            BeanUtils.copyProperties(BudgetDetailEntity, budgetDetailVo);
            budgetDetailVo.setBudgetDetail(JSON.parseArray(BudgetDetailEntity.getBudgetDetail()));
            voList.add(budgetDetailVo);
        }
        return voList;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectBudgetDetail> getQueryWrapper(QueryWrapper<SrpmsProjectBudgetDetail> queryWrapper,BaseQueryForm<SrpmsProjectBudgetDetailQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getProjectId())){
            queryWrapper.like(SrpmsProjectBudgetDetail.PROJECT_ID,srpmsProjectBudgetDetail.getProjectId());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getProjectNum())){
            queryWrapper.like(SrpmsProjectBudgetDetail.PROJECT_NUM,srpmsProjectBudgetDetail.getProjectNum());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getBudgetCategory())){
            queryWrapper.like(SrpmsProjectBudgetDetail.BUDGET_CATEGORY,srpmsProjectBudgetDetail.getBudgetCategory());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getBudgetYear())){
            queryWrapper.like(SrpmsProjectBudgetDetail.BUDGET_YEAR,srpmsProjectBudgetDetail.getBudgetYear());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getTaskName())){
            queryWrapper.like(SrpmsProjectBudgetDetail.TASK_NAME,srpmsProjectBudgetDetail.getTaskName());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getSerial())){
            queryWrapper.like(SrpmsProjectBudgetDetail.SERIAL,srpmsProjectBudgetDetail.getSerial());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getTaskDeptName())){
            queryWrapper.like(SrpmsProjectBudgetDetail.TASK_DEPT_NAME,srpmsProjectBudgetDetail.getTaskDeptName());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getTaskLeadPersonId())){
            queryWrapper.like(SrpmsProjectBudgetDetail.TASK_LEAD_PERSON_ID,srpmsProjectBudgetDetail.getTaskLeadPersonId());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getTaskJoinPersonId())){
            queryWrapper.like(SrpmsProjectBudgetDetail.TASK_JOIN_PERSON_ID,srpmsProjectBudgetDetail.getTaskJoinPersonId());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getBudgetAmount())){
            queryWrapper.like(SrpmsProjectBudgetDetail.BUDGET_AMOUNT,srpmsProjectBudgetDetail.getBudgetAmount());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getDeptName())){
            queryWrapper.like(SrpmsProjectBudgetDetail.DEPT_NAME,srpmsProjectBudgetDetail.getDeptName());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getOrgCode())){
            queryWrapper.like(SrpmsProjectBudgetDetail.ORG_CODE,srpmsProjectBudgetDetail.getOrgCode());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getDeptCategory())){
            queryWrapper.like(SrpmsProjectBudgetDetail.DEPT_CATEGORY,srpmsProjectBudgetDetail.getDeptCategory());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getDeptTaskContent())){
            queryWrapper.like(SrpmsProjectBudgetDetail.DEPT_TASK_CONTENT,srpmsProjectBudgetDetail.getDeptTaskContent());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getRemark())){
            queryWrapper.like(SrpmsProjectBudgetDetail.REMARK,srpmsProjectBudgetDetail.getRemark());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getBudgetDetail())){
            queryWrapper.like(SrpmsProjectBudgetDetail.BUDGET_DETAIL,srpmsProjectBudgetDetail.getBudgetDetail());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getCreateTime())){
            queryWrapper.like(SrpmsProjectBudgetDetail.CREATE_TIME,srpmsProjectBudgetDetail.getCreateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getCreateBy())){
            queryWrapper.like(SrpmsProjectBudgetDetail.CREATE_BY,srpmsProjectBudgetDetail.getCreateBy());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getUpdateTime())){
            queryWrapper.like(SrpmsProjectBudgetDetail.UPDATE_TIME,srpmsProjectBudgetDetail.getUpdateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDetail.getUpdateBy())){
            queryWrapper.like(SrpmsProjectBudgetDetail.UPDATE_BY,srpmsProjectBudgetDetail.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}


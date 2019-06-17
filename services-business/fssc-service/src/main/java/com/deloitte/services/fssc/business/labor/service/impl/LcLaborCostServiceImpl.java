package com.deloitte.services.fssc.business.labor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.labor.param.LcLaborCostQueryForm;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.deloitte.services.fssc.business.labor.mapper.LcLaborCostMapper;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :  LcLaborCost服务实现类
 * @Modified :
 */
@Service
@Transactional
public class LcLaborCostServiceImpl extends ServiceImpl<LcLaborCostMapper, LcLaborCost> implements ILcLaborCostService {


    @Autowired
    private LcLaborCostMapper lcLaborCostMapper;

    @Override
    public IPage<LcLaborCost> selectPage(LcLaborCostQueryForm queryForm ) {
        QueryWrapper<LcLaborCost> queryWrapper = new QueryWrapper <LcLaborCost>();
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),LcLaborCost.DOCUMENT_NUM,queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),LcLaborCost.DOCUMENT_STATUS,queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPayStatus()),LcLaborCost.PAY_STATUS,queryForm.getPayStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),LcLaborCost.CREATE_USER_NAME,queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),LcLaborCost.UNIT_ID,queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),LcLaborCost.DEPT_ID,queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),LcLaborCost.PROJECT_ID,queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),LcLaborCost.MAIN_TYPE_ID,queryForm.getMainTypeId());

        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getStartAmount()),LcLaborCost.SHOULD_GIVE_TOTAL_AMOUNT,queryForm.getStartAmount());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getEndAmount()),LcLaborCost.SHOULD_GIVE_TOTAL_AMOUNT,queryForm.getEndAmount());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getStartTime()),LcLaborCost.CREATE_TIME,queryForm.getStartTime());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getEndTime()),LcLaborCost.CREATE_TIME,queryForm.getEndTime());
        queryWrapper.orderByDesc(LcLaborCost.UPDATE_TIME);

        return lcLaborCostMapper.selectPage(new Page<LcLaborCost>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<LcLaborCost> selectList(LcLaborCostQueryForm queryForm) {
        QueryWrapper<LcLaborCost> queryWrapper = new QueryWrapper <LcLaborCost>();
        //getQueryWrapper(queryWrapper,queryForm);
        return lcLaborCostMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByExpenseMainTypeLcIds(List<Long> idList) {
        return lcLaborCostMapper.countByExpenseMainTypeIds(idList) > 0 ? true : false;
    }

}


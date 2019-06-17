package com.deloitte.services.fssc.business.pe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.pe.param.PerSelfAssessmentQueryForm;
import com.deloitte.services.fssc.business.pe.entity.PerSelfAssessment;
import com.deloitte.services.fssc.business.pe.mapper.PerSelfAssessmentMapper;
import com.deloitte.services.fssc.business.pe.service.IPerSelfAssessmentService;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :  PerSelfAssessment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PerSelfAssessmentServiceImpl extends ServiceImpl<PerSelfAssessmentMapper, PerSelfAssessment> implements IPerSelfAssessmentService {


    @Autowired
    private PerSelfAssessmentMapper perSelfAssessmentMapper;

    @Override
    public IPage<PerSelfAssessment> selectPage(PerSelfAssessmentQueryForm queryForm ) {
        QueryWrapper<PerSelfAssessment> queryWrapper = new QueryWrapper <>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),PerSelfAssessment.DOCUMENT_NUM,queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),PerSelfAssessment.DOCUMENT_STATUS,queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectDuty()),PerSelfAssessment.PROJECT_DUTY,queryForm.getProjectDuty());
        queryWrapper.eq(StringUtil.isNotEmpty(queryForm.getDoUnitId()),PerSelfAssessment.DO_UNIT_ID,queryForm.getDoUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDoDeptId()),PerSelfAssessment.DO_DEPT_ID,queryForm.getDoDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),PerSelfAssessment.PROJECT_ID,queryForm.getProjectId());
        queryWrapper.isNotNull(PerSelfAssessment.PROJECT_ID);
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),PerSelfAssessment.PROJECT_START_TIME,queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),PerSelfAssessment.PROJECT_START_TIME,queryForm.getTimeEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),PerSelfAssessment.FUND_SOURCE_AMOUNT_YEAR,queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),PerSelfAssessment.FUND_SOURCE_AMOUNT_YEAR,queryForm.getMoneyEnd());
        return perSelfAssessmentMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<PerSelfAssessment> selectList(PerSelfAssessmentQueryForm queryForm) {
        QueryWrapper<PerSelfAssessment> queryWrapper = new QueryWrapper <>();
        //getQueryWrapper(queryWrapper,queryForm);
        return perSelfAssessmentMapper.selectList(queryWrapper);
    }




}


package com.deloitte.services.fssc.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.report.param.ReportMergeRelationQueryForm;
import com.deloitte.services.fssc.report.entity.ReportMergeRelation;
import com.deloitte.services.fssc.report.mapper.ReportMergeRelationMapper;
import com.deloitte.services.fssc.report.service.IReportMergeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportMergeRelation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReportMergeRelationServiceImpl extends ServiceImpl<ReportMergeRelationMapper, ReportMergeRelation> implements IReportMergeRelationService {


    @Autowired
    private ReportMergeRelationMapper reportMergeRelationMapper;

    @Override
    public IPage<ReportMergeRelation> selectPage(ReportMergeRelationQueryForm queryForm ) {
        QueryWrapper<ReportMergeRelation> queryWrapper = new QueryWrapper <ReportMergeRelation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reportMergeRelationMapper.selectPage(new Page<ReportMergeRelation>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ReportMergeRelation> selectList(ReportMergeRelationQueryForm queryForm) {
        QueryWrapper<ReportMergeRelation> queryWrapper = new QueryWrapper <ReportMergeRelation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reportMergeRelationMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ReportMergeRelation> getQueryWrapper(QueryWrapper<ReportMergeRelation> queryWrapper,ReportMergeRelationQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getId())){
            queryWrapper.eq(ReportMergeRelation.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getReportId())){
            queryWrapper.eq(ReportMergeRelation.REPORT_ID,queryForm.getReportId());
        }
        if(StringUtils.isNotBlank(queryForm.getReportType())){
            queryWrapper.eq(ReportMergeRelation.REPORT_TYPE,queryForm.getReportType());
        }
        if(StringUtils.isNotBlank(queryForm.getMergeReportId())){
            queryWrapper.eq(ReportMergeRelation.MERGE_REPORT_ID,queryForm.getMergeReportId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ReportMergeRelation.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ReportMergeRelation.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ReportMergeRelation.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ReportMergeRelation.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ReportMergeRelation.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ReportMergeRelation.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ReportMergeRelation.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(ReportMergeRelation.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(ReportMergeRelation.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }
     */
}


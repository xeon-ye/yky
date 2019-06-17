package com.deloitte.services.fssc.business.pe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.pe.param.PerSelfTargetQueryForm;
import com.deloitte.services.fssc.business.pe.entity.PerSelfTarget;
import com.deloitte.services.fssc.business.pe.mapper.PerSelfTargetMapper;
import com.deloitte.services.fssc.business.pe.service.IPerSelfTargetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :  PerSelfTarget服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PerSelfTargetServiceImpl extends ServiceImpl<PerSelfTargetMapper, PerSelfTarget> implements IPerSelfTargetService {


    @Autowired
    private PerSelfTargetMapper perSelfTargetMapper;

    @Override
    public IPage<PerSelfTarget> selectPage(PerSelfTargetQueryForm queryForm ) {
        QueryWrapper<PerSelfTarget> queryWrapper = new QueryWrapper <PerSelfTarget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return perSelfTargetMapper.selectPage(new Page<PerSelfTarget>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<PerSelfTarget> selectList(PerSelfTargetQueryForm queryForm) {
        QueryWrapper<PerSelfTarget> queryWrapper = new QueryWrapper <PerSelfTarget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return perSelfTargetMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<PerSelfTarget> getQueryWrapper(QueryWrapper<PerSelfTarget> queryWrapper,PerSelfTargetQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(PerSelfTarget.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(PerSelfTarget.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(PerSelfTarget.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getFirstPer())){
            queryWrapper.eq(PerSelfTarget.FIRST_PER,queryForm.getFirstPer());
        }
        if(StringUtils.isNotBlank(queryForm.getSecondPer())){
            queryWrapper.eq(PerSelfTarget.SECOND_PER,queryForm.getSecondPer());
        }
        if(StringUtils.isNotBlank(queryForm.getThiredPer())){
            queryWrapper.eq(PerSelfTarget.THIRED_PER,queryForm.getThiredPer());
        }
        if(StringUtils.isNotBlank(queryForm.getThiredUnitId())){
            queryWrapper.eq(PerSelfTarget.THIRED_UNIT_ID,queryForm.getThiredUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getThiredUnitCode())){
            queryWrapper.eq(PerSelfTarget.THIRED_UNIT_CODE,queryForm.getThiredUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getThiredUnitName())){
            queryWrapper.eq(PerSelfTarget.THIRED_UNIT_NAME,queryForm.getThiredUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getYearPerValue())){
            queryWrapper.eq(PerSelfTarget.YEAR_PER_VALUE,queryForm.getYearPerValue());
        }
        if(StringUtils.isNotBlank(queryForm.getRealCompleteValue())){
            queryWrapper.eq(PerSelfTarget.REAL_COMPLETE_VALUE,queryForm.getRealCompleteValue());
        }
        if(StringUtils.isNotBlank(queryForm.getScoreValue())){
            queryWrapper.eq(PerSelfTarget.SCORE_VALUE,queryForm.getScoreValue());
        }
        if(StringUtils.isNotBlank(queryForm.getScored())){
            queryWrapper.eq(PerSelfTarget.SCORED,queryForm.getScored());
        }
        if(StringUtils.isNotBlank(queryForm.getReason())){
            queryWrapper.eq(PerSelfTarget.REASON,queryForm.getReason());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(PerSelfTarget.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(PerSelfTarget.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(PerSelfTarget.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(PerSelfTarget.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(PerSelfTarget.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(PerSelfTarget.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(PerSelfTarget.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(PerSelfTarget.EX5,queryForm.getEx5());
        }
        return queryWrapper;
    }
     */
}


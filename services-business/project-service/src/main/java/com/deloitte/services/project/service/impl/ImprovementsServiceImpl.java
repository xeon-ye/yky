package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ImprovementsQueryForm;
import com.deloitte.services.project.entity.Improvements;
import com.deloitte.services.project.mapper.ImprovementsMapper;
import com.deloitte.services.project.service.IImprovementsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Improvements服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ImprovementsServiceImpl extends ServiceImpl<ImprovementsMapper, Improvements> implements IImprovementsService {


    @Autowired
    private ImprovementsMapper improvementsMapper;

    @Override
    public IPage<Improvements> selectPage(ImprovementsQueryForm queryForm ) {
        QueryWrapper<Improvements> queryWrapper = new QueryWrapper <Improvements>();
        //getQueryWrapper(queryWrapper,queryForm);
        return improvementsMapper.selectPage(new Page<Improvements>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Improvements> selectList(ImprovementsQueryForm queryForm) {
        QueryWrapper<Improvements> queryWrapper = new QueryWrapper <Improvements>();
        //getQueryWrapper(queryWrapper,queryForm);
        return improvementsMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Improvements> getQueryWrapper(QueryWrapper<Improvements> queryWrapper,ImprovementsQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getImprovementsId())){
            queryWrapper.eq(Improvements.IMPROVEMENTS_ID,queryForm.getImprovementsId());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentId())){
            queryWrapper.eq(Improvements.ATTACHMENT_ID,queryForm.getAttachmentId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectAbs())){
            queryWrapper.eq(Improvements.PROJECT_ABS,queryForm.getProjectAbs());
        }
        if(StringUtils.isNotBlank(queryForm.getInputDate())){
            queryWrapper.eq(Improvements.INPUT_DATE,queryForm.getInputDate());
        }
        if(StringUtils.isNotBlank(queryForm.getCoveredArea())){
            queryWrapper.eq(Improvements.COVERED_AREA,queryForm.getCoveredArea());
        }
        if(StringUtils.isNotBlank(queryForm.getRepairArea())){
            queryWrapper.eq(Improvements.REPAIR_AREA,queryForm.getRepairArea());
        }
        if(StringUtils.isNotBlank(queryForm.getRepairWork())){
            queryWrapper.eq(Improvements.REPAIR_WORK,queryForm.getRepairWork());
        }
        if(StringUtils.isNotBlank(queryForm.getImplCycle())){
            queryWrapper.eq(Improvements.IMPL_CYCLE,queryForm.getImplCycle());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingCenter())){
            queryWrapper.eq(Improvements.FUNDING_CENTER,queryForm.getFundingCenter());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingDirector())){
            queryWrapper.eq(Improvements.FUNDING_DIRECTOR,queryForm.getFundingDirector());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingOther())){
            queryWrapper.eq(Improvements.FUNDING_OTHER,queryForm.getFundingOther());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingTotal())){
            queryWrapper.eq(Improvements.FUNDING_TOTAL,queryForm.getFundingTotal());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Improvements.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Improvements.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Improvements.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Improvements.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Improvements.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Improvements.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Improvements.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Improvements.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Improvements.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Improvements.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Improvements.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}


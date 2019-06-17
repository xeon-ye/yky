package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.AchieveResearchQueryForm;
import com.deloitte.services.project.entity.AchieveResearch;
import com.deloitte.services.project.mapper.AchieveResearchMapper;
import com.deloitte.services.project.service.IAchieveResearchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :  AchieveResearch服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AchieveResearchServiceImpl extends ServiceImpl<AchieveResearchMapper, AchieveResearch> implements IAchieveResearchService {


    @Autowired
    private AchieveResearchMapper achieveResearchMapper;

    @Override
    public IPage<AchieveResearch> selectPage(AchieveResearchQueryForm queryForm ) {
        QueryWrapper<AchieveResearch> queryWrapper = new QueryWrapper <AchieveResearch>();
        //getQueryWrapper(queryWrapper,queryForm);
        return achieveResearchMapper.selectPage(new Page<AchieveResearch>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AchieveResearch> selectList(AchieveResearchQueryForm queryForm) {
        QueryWrapper<AchieveResearch> queryWrapper = new QueryWrapper <AchieveResearch>();
        //getQueryWrapper(queryWrapper,queryForm);
        return achieveResearchMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AchieveResearch> getQueryWrapper(QueryWrapper<AchieveResearch> queryWrapper,AchieveResearchQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(AchieveResearch.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AchieveResearch.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(AchieveResearch.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(AchieveResearch.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(AchieveResearch.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(AchieveResearch.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(AchieveResearch.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(AchieveResearch.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(AchieveResearch.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(AchieveResearch.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(AchieveResearch.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getId())){
            queryWrapper.eq(AchieveResearch.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getResearchfundsId())){
            queryWrapper.eq(AchieveResearch.RESEARCHFUNDS_ID,queryForm.getResearchfundsId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(AchieveResearch.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getAwardPaperCode())){
            queryWrapper.eq(AchieveResearch.AWARD_PAPER_CODE,queryForm.getAwardPaperCode());
        }
        if(StringUtils.isNotBlank(queryForm.getAwardPaperName())){
            queryWrapper.eq(AchieveResearch.AWARD_PAPER_NAME,queryForm.getAwardPaperName());
        }
        if(StringUtils.isNotBlank(queryForm.getDuringFiveYear())){
            queryWrapper.eq(AchieveResearch.DURING_FIVE_YEAR,queryForm.getDuringFiveYear());
        }
        if(StringUtils.isNotBlank(queryForm.getLastYear())){
            queryWrapper.eq(AchieveResearch.LAST_YEAR,queryForm.getLastYear());
        }
        return queryWrapper;
    }
     */
}


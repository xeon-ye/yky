package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ResearchfundsQueryForm;
import com.deloitte.services.project.entity.Researchfunds;
import com.deloitte.services.project.mapper.ResearchfundsMapper;
import com.deloitte.services.project.service.IResearchfundsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :  Researchfunds服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ResearchfundsServiceImpl extends ServiceImpl<ResearchfundsMapper, Researchfunds> implements IResearchfundsService {


    @Autowired
    private ResearchfundsMapper researchfundsMapper;

    @Override
    public IPage<Researchfunds> selectPage(ResearchfundsQueryForm queryForm ) {
        QueryWrapper<Researchfunds> queryWrapper = new QueryWrapper <Researchfunds>();
        //getQueryWrapper(queryWrapper,queryForm);
        return researchfundsMapper.selectPage(new Page<Researchfunds>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Researchfunds> selectList(ResearchfundsQueryForm queryForm) {
        QueryWrapper<Researchfunds> queryWrapper = new QueryWrapper <Researchfunds>();
        //getQueryWrapper(queryWrapper,queryForm);
        return researchfundsMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Researchfunds> getQueryWrapper(QueryWrapper<Researchfunds> queryWrapper,ResearchfundsQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Researchfunds.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Researchfunds.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Researchfunds.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Researchfunds.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Researchfunds.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Researchfunds.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Researchfunds.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Researchfunds.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Researchfunds.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Researchfunds.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Researchfunds.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getId())){
            queryWrapper.eq(Researchfunds.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getResearchfundsId())){
            queryWrapper.eq(Researchfunds.RESEARCHFUNDS_ID,queryForm.getResearchfundsId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Researchfunds.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getPeriodCode())){
            queryWrapper.eq(Researchfunds.PERIOD_CODE,queryForm.getPeriodCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPeriodName())){
            queryWrapper.eq(Researchfunds.PERIOD_NAME,queryForm.getPeriodName());
        }
        if(StringUtils.isNotBlank(queryForm.getNumProFiveYear())){
            queryWrapper.eq(Researchfunds.NUM_PRO_FIVE_YEAR,queryForm.getNumProFiveYear());
        }
        if(StringUtils.isNotBlank(queryForm.getFundFiveYear())){
            queryWrapper.eq(Researchfunds.FUND_FIVE_YEAR,queryForm.getFundFiveYear());
        }
        if(StringUtils.isNotBlank(queryForm.getFundsLastYear())){
            queryWrapper.eq(Researchfunds.FUNDS_LAST_YEAR,queryForm.getFundsLastYear());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenditure())){
            queryWrapper.eq(Researchfunds.EXPENDITURE,queryForm.getExpenditure());
        }
        return queryWrapper;
    }
     */
}


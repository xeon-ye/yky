package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.EconomicQueryForm;
import com.deloitte.services.project.entity.Economic;
import com.deloitte.services.project.mapper.EconomicMapper;
import com.deloitte.services.project.service.IEconomicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-15
 * @Description :  Economic服务实现类
 * @Modified :
 */
@Service
@Transactional
public class EconomicServiceImpl extends ServiceImpl<EconomicMapper, Economic> implements IEconomicService {


    @Autowired
    private EconomicMapper economicMapper;

    @Override
    public IPage<Economic> selectPage(EconomicQueryForm queryForm ) {
        QueryWrapper<Economic> queryWrapper = new QueryWrapper <Economic>();
        //getQueryWrapper(queryWrapper,queryForm);
        return economicMapper.selectPage(new Page<Economic>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Economic> selectList(EconomicQueryForm queryForm) {
        QueryWrapper<Economic> queryWrapper = new QueryWrapper <Economic>();
        //getQueryWrapper(queryWrapper,queryForm);
        return economicMapper.selectList(queryWrapper);
    }

    @Override
    public List<Economic> getList(String replyId) {
        QueryWrapper<Economic> queryWrapper = new QueryWrapper <Economic>();
        queryWrapper.eq(Economic.REPLY_ID, replyId);
        return economicMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteEcoList(String applicationId) {
        QueryWrapper<Economic> queryWrapper = new QueryWrapper <Economic>();
        queryWrapper.eq(Economic.APPLICATION_ID, applicationId);
        economicMapper.delete(queryWrapper);
    }

    @Override
    public List<Economic> getAllList(String projectId) {
        QueryWrapper<Economic> queryWrapper = new QueryWrapper <Economic>();
        queryWrapper.eq(Economic.PROJECT_ID, projectId);
        return economicMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Economic> getQueryWrapper(QueryWrapper<Economic> queryWrapper,EconomicQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getEconomicId())){
            queryWrapper.eq(Economic.ECONOMIC_ID,queryForm.getEconomicId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(Economic.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Economic.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getEcoCode())){
            queryWrapper.eq(Economic.ECO_CODE,queryForm.getEcoCode());
        }
        if(StringUtils.isNotBlank(queryForm.getEcoName())){
            queryWrapper.eq(Economic.ECO_NAME,queryForm.getEcoName());
        }
        if(StringUtils.isNotBlank(queryForm.getEcoYear())){
            queryWrapper.eq(Economic.ECO_YEAR,queryForm.getEcoYear());
        }
        if(StringUtils.isNotBlank(queryForm.getAppFunding())){
            queryWrapper.eq(Economic.APP_FUNDING,queryForm.getAppFunding());
        }
        if(StringUtils.isNotBlank(queryForm.getAppBudget())){
            queryWrapper.eq(Economic.APP_BUDGET,queryForm.getAppBudget());
        }
        if(StringUtils.isNotBlank(queryForm.getAppOther())){
            queryWrapper.eq(Economic.APP_OTHER,queryForm.getAppOther());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewFunding())){
            queryWrapper.eq(Economic.REVIEW_FUNDING,queryForm.getReviewFunding());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewBudget())){
            queryWrapper.eq(Economic.REVIEW_BUDGET,queryForm.getReviewBudget());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewOther())){
            queryWrapper.eq(Economic.REVIEW_OTHER,queryForm.getReviewOther());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyFunding())){
            queryWrapper.eq(Economic.REPLY_FUNDING,queryForm.getReplyFunding());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyBudget())){
            queryWrapper.eq(Economic.REPLY_BUDGET,queryForm.getReplyBudget());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyOther())){
            queryWrapper.eq(Economic.REPLY_OTHER,queryForm.getReplyOther());
        }
        if(StringUtils.isNotBlank(queryForm.getCarryAmount())){
            queryWrapper.eq(Economic.CARRY_AMOUNT,queryForm.getCarryAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Economic.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Economic.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Economic.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Economic.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Economic.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Economic.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Economic.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Economic.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Economic.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Economic.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Economic.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyId())){
            queryWrapper.eq(Economic.REPLY_ID,queryForm.getReplyId());
        }
        return queryWrapper;
    }
     */
}


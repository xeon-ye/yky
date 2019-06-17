package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.param.ActBakQueryForm;
import com.deloitte.services.project.entity.ActBak;
import com.deloitte.services.project.entity.Budget;
import com.deloitte.services.project.mapper.ActBakMapper;
import com.deloitte.services.project.service.IActBakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  ActBak服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ActBakServiceImpl extends ServiceImpl<ActBakMapper, ActBak> implements IActBakService {


    @Autowired
    private ActBakMapper actBakMapper;

    @Override
    public IPage<ActBak> selectPage(ActBakQueryForm queryForm ) {
        QueryWrapper<ActBak> queryWrapper = new QueryWrapper <ActBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return actBakMapper.selectPage(new Page<ActBak>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ActBak> selectList(ActBakQueryForm queryForm) {
        QueryWrapper<ActBak> queryWrapper = new QueryWrapper <ActBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return actBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAllList(String applicationId) {
        QueryWrapper<ActBak> queryWrapper = new QueryWrapper <ActBak>();
        queryWrapper.eq(ActBak.APPLICATION_ID,applicationId);
        actBakMapper.delete(queryWrapper);
    }

    @Override
    public List<ActBak> getList(String applicationId) {
        QueryWrapper<ActBak> queryWrapper = new QueryWrapper <ActBak>();
        queryWrapper.eq(ActBak.APPLICATION_ID,applicationId);
        return actBakMapper.selectList(queryWrapper);
    }

    @Override
    public List<ActBak> getListByRepId(String replyId) {
        QueryWrapper<ActBak> queryWrapper = new QueryWrapper <ActBak>();
        queryWrapper.eq(ActBak.REPLY_ID,replyId);
        return actBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteListByRepId(String replyId) {
        QueryWrapper<ActBak> queryWrapper = new QueryWrapper <ActBak>();
        queryWrapper.eq(ActBak.REPLY_ID,replyId);
        actBakMapper.delete(queryWrapper);
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ActBak> getQueryWrapper(QueryWrapper<ActBak> queryWrapper,ActBakQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getActId())){
            queryWrapper.eq(ActBak.ACT_ID,queryForm.getActId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(ActBak.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getActNo())){
            queryWrapper.eq(ActBak.ACT_NO,queryForm.getActNo());
        }
        if(StringUtils.isNotBlank(queryForm.getActName())){
            queryWrapper.eq(ActBak.ACT_NAME,queryForm.getActName());
        }
        if(StringUtils.isNotBlank(queryForm.getDescription())){
            queryWrapper.eq(ActBak.DESCRIPTION,queryForm.getDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getQuantityFrenquence())){
            queryWrapper.eq(ActBak.QUANTITY_FRENQUENCE,queryForm.getQuantityFrenquence());
        }
        if(StringUtils.isNotBlank(queryForm.getSubExpense())){
            queryWrapper.eq(ActBak.SUB_EXPENSE,queryForm.getSubExpense());
        }
        if(StringUtils.isNotBlank(queryForm.getPriceStandard())){
            queryWrapper.eq(ActBak.PRICE_STANDARD,queryForm.getPriceStandard());
        }
        if(StringUtils.isNotBlank(queryForm.getActPlan())){
            queryWrapper.eq(ActBak.ACT_PLAN,queryForm.getActPlan());
        }
        if(StringUtils.isNotBlank(queryForm.getRemarks())){
            queryWrapper.eq(ActBak.REMARKS,queryForm.getRemarks());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewAmount())){
            queryWrapper.eq(ActBak.REVIEW_AMOUNT,queryForm.getReviewAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewRemark())){
            queryWrapper.eq(ActBak.REVIEW_REMARK,queryForm.getReviewRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyId())){
            queryWrapper.eq(ActBak.REPLY_ID,queryForm.getReplyId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayAmount())){
            queryWrapper.eq(ActBak.REPLAY_AMOUNT,queryForm.getReplayAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayRemark())){
            queryWrapper.eq(ActBak.REPLAY_REMARK,queryForm.getReplayRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ActBak.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ActBak.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ActBak.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ActBak.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ActBak.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ActBak.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ActBak.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(ActBak.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(ActBak.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(ActBak.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(ActBak.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getIsRelated())){
            queryWrapper.eq(ActBak.IS_RELATED,queryForm.getIsRelated());
        }
        return queryWrapper;
    }
     */
}


package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ActQueryForm;
import com.deloitte.services.project.entity.Act;
import com.deloitte.services.project.mapper.ActMapper;
import com.deloitte.services.project.service.IActService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description :  Act服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ActServiceImpl extends ServiceImpl<ActMapper, Act> implements IActService {


    @Autowired
    private ActMapper actMapper;

    @Override
    public IPage<Act> selectPage(ActQueryForm queryForm ) {
        QueryWrapper<Act> queryWrapper = new QueryWrapper <Act>();
        //getQueryWrapper(queryWrapper,queryForm);
        return actMapper.selectPage(new Page<Act>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Act> selectList(ActQueryForm queryForm) {
        QueryWrapper<Act> queryWrapper = new QueryWrapper <Act>();

        return actMapper.selectList(queryWrapper);
    }

    @Override
    public List<Act> getActList(String applicationId) {
        QueryWrapper<Act> queryWrapper = new QueryWrapper <Act>();
        queryWrapper.eq(Act.APPLICATION_ID,applicationId);
        return actMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteList(String applicationId) {
        QueryWrapper<Act> queryWrapper = new QueryWrapper <Act>();
        queryWrapper.eq(Act.APPLICATION_ID, applicationId);
        actMapper.delete(queryWrapper);
    }

    @Override
    public void deleteRepList(String replyId) {
        QueryWrapper<Act> queryWrapper = new QueryWrapper <Act>();
        queryWrapper.eq(Act.REPLY_ID, replyId);
        actMapper.delete(queryWrapper);
    }

    @Override
    public List<Act> getRepActList(String replyId) {
        QueryWrapper<Act> queryWrapper = new QueryWrapper <Act>();
        queryWrapper.eq(Act.REPLY_ID,replyId);
        return actMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Act> getQueryWrapper(QueryWrapper<Act> queryWrapper,ActQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getActId())){
            queryWrapper.eq(Act.ACT_ID,queryForm.getActId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Act.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getActNo())){
            queryWrapper.eq(Act.ACT_NO,queryForm.getActNo());
        }
        if(StringUtils.isNotBlank(queryForm.getActName())){
            queryWrapper.eq(Act.ACT_NAME,queryForm.getActName());
        }
        if(StringUtils.isNotBlank(queryForm.getDescription())){
            queryWrapper.eq(Act.DESCRIPTION,queryForm.getDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getQuantityFrenquence())){
            queryWrapper.eq(Act.QUANTITY_FRENQUENCE,queryForm.getQuantityFrenquence());
        }
        if(StringUtils.isNotBlank(queryForm.getSubExpense())){
            queryWrapper.eq(Act.SUB_EXPENSE,queryForm.getSubExpense());
        }
        if(StringUtils.isNotBlank(queryForm.getPriceStandard())){
            queryWrapper.eq(Act.PRICE_STANDARD,queryForm.getPriceStandard());
        }
        if(StringUtils.isNotBlank(queryForm.getActPlan())){
            queryWrapper.eq(Act.ACT_PLAN,queryForm.getActPlan());
        }
        if(StringUtils.isNotBlank(queryForm.getRemarks())){
            queryWrapper.eq(Act.REMARKS,queryForm.getRemarks());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewAmount())){
            queryWrapper.eq(Act.REVIEW_AMOUNT,queryForm.getReviewAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewRemark())){
            queryWrapper.eq(Act.REVIEW_REMARK,queryForm.getReviewRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyId())){
            queryWrapper.eq(Act.REPLY_ID,queryForm.getReplyId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayAmount())){
            queryWrapper.eq(Act.REPLAY_AMOUNT,queryForm.getReplayAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayRemark())){
            queryWrapper.eq(Act.REPLAY_REMARK,queryForm.getReplayRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Act.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Act.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Act.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Act.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Act.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Act.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Act.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Act.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Act.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Act.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Act.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getIsRelated())){
            queryWrapper.eq(Act.IS_RELATED,queryForm.getIsRelated());
        }
        return queryWrapper;
    }
     */
}


package com.deloitte.platform.basic.bpmservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.bpmservice.param.BpmPositionRelationshipQueryForm;
import com.deloitte.platform.basic.bpmservice.entity.BpmPositionRelationship;
import com.deloitte.platform.basic.bpmservice.mapper.BpmPositionRelationshipMapper;
import com.deloitte.platform.basic.bpmservice.service.IBpmPositionRelationshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :  BpmPositionRelationship服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BpmPositionRelationshipServiceImpl extends ServiceImpl<BpmPositionRelationshipMapper, BpmPositionRelationship> implements IBpmPositionRelationshipService {


    @Autowired
    private BpmPositionRelationshipMapper bpmPositionRelationshipMapper;

    @Override
    public IPage<BpmPositionRelationship> selectPage(BpmPositionRelationshipQueryForm queryForm ) {
        QueryWrapper<BpmPositionRelationship> queryWrapper = new QueryWrapper <BpmPositionRelationship>();
        //getQueryWrapper(queryWrapper,queryForm);
        return bpmPositionRelationshipMapper.selectPage(new Page<BpmPositionRelationship>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BpmPositionRelationship> selectList(BpmPositionRelationshipQueryForm queryForm) {
        QueryWrapper<BpmPositionRelationship> queryWrapper = new QueryWrapper <BpmPositionRelationship>();
        //getQueryWrapper(queryWrapper,queryForm);
        return bpmPositionRelationshipMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BpmPositionRelationship> getQueryWrapper(QueryWrapper<BpmPositionRelationship> queryWrapper,BaseQueryForm<BpmPositionRelationshipQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(bpmPositionRelationship.getBpmPositionId())){
            queryWrapper.like(BpmPositionRelationship.BPM_POSITION_ID,bpmPositionRelationship.getBpmPositionId());
        }
        if(StringUtils.isNotBlank(bpmPositionRelationship.getPhysicalPositionId())){
            queryWrapper.like(BpmPositionRelationship.PHYSICAL_POSITION_ID,bpmPositionRelationship.getPhysicalPositionId());
        }
        if(StringUtils.isNotBlank(bpmPositionRelationship.getChargeOrgId())){
            queryWrapper.like(BpmPositionRelationship.CHARGE_ORG_ID,bpmPositionRelationship.getChargeOrgId());
        }
        if(StringUtils.isNotBlank(bpmPositionRelationship.getState())){
            queryWrapper.like(BpmPositionRelationship.STATE,bpmPositionRelationship.getState());
        }
        if(StringUtils.isNotBlank(bpmPositionRelationship.getEffectTime())){
            queryWrapper.like(BpmPositionRelationship.EFFECT_TIME,bpmPositionRelationship.getEffectTime());
        }
        if(StringUtils.isNotBlank(bpmPositionRelationship.getLoseEfficacyTime())){
            queryWrapper.like(BpmPositionRelationship.LOSE_EFFICACY_TIME,bpmPositionRelationship.getLoseEfficacyTime());
        }
        if(StringUtils.isNotBlank(bpmPositionRelationship.getCreateTime())){
            queryWrapper.like(BpmPositionRelationship.CREATE_TIME,bpmPositionRelationship.getCreateTime());
        }
        if(StringUtils.isNotBlank(bpmPositionRelationship.getUpdateTime())){
            queryWrapper.like(BpmPositionRelationship.UPDATE_TIME,bpmPositionRelationship.getUpdateTime());
        }
        return queryWrapper;
    }
     */
}


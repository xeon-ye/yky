package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.AllActBakQueryForm;
import com.deloitte.services.project.entity.AllActBak;
import com.deloitte.services.project.mapper.AllActBakMapper;
import com.deloitte.services.project.service.IAllActBakService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  AllActBak服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AllActBakServiceImpl extends ServiceImpl<AllActBakMapper, AllActBak> implements IAllActBakService {


    @Autowired
    private AllActBakMapper allActBakMapper;

    @Override
    public IPage<AllActBak> selectPage(AllActBakQueryForm queryForm ) {
        QueryWrapper<AllActBak> queryWrapper = new QueryWrapper <AllActBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return allActBakMapper.selectPage(new Page<AllActBak>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AllActBak> selectList(AllActBakQueryForm queryForm) {
        QueryWrapper<AllActBak> queryWrapper = new QueryWrapper <AllActBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return allActBakMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AllActBak> getQueryWrapper(QueryWrapper<AllActBak> queryWrapper,AllActBakQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getAllActId())){
            queryWrapper.eq(AllActBak.ALL_ACT_ID,queryForm.getAllActId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(AllActBak.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getFinSourceCode())){
            queryWrapper.eq(AllActBak.FIN_SOURCE_CODE,queryForm.getFinSourceCode());
        }
        if(StringUtils.isNotBlank(queryForm.getFinSource())){
            queryWrapper.eq(AllActBak.FIN_SOURCE,queryForm.getFinSource());
        }
        if(StringUtils.isNotBlank(queryForm.getAllInvestment())){
            queryWrapper.eq(AllActBak.ALL_INVESTMENT,queryForm.getAllInvestment());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyQuantity())){
            queryWrapper.eq(AllActBak.REPLY_QUANTITY,queryForm.getReplyQuantity());
        }
        if(StringUtils.isNotBlank(queryForm.getCarryOverEndOfLastYear())){
            queryWrapper.eq(AllActBak.CARRY_OVER_END_OF_LAST_YEAR,queryForm.getCarryOverEndOfLastYear());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetApplication())){
            queryWrapper.eq(AllActBak.BUDGET_APPLICATION,queryForm.getBudgetApplication());
        }
        if(StringUtils.isNotBlank(queryForm.getRemarks())){
            queryWrapper.eq(AllActBak.REMARKS,queryForm.getRemarks());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyId())){
            queryWrapper.eq(AllActBak.REPLY_ID,queryForm.getReplyId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayAmount())){
            queryWrapper.eq(AllActBak.REPLAY_AMOUNT,queryForm.getReplayAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayRemark())){
            queryWrapper.eq(AllActBak.REPLAY_REMARK,queryForm.getReplayRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(AllActBak.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AllActBak.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(AllActBak.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(AllActBak.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(AllActBak.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(AllActBak.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(AllActBak.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(AllActBak.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(AllActBak.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(AllActBak.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(AllActBak.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}


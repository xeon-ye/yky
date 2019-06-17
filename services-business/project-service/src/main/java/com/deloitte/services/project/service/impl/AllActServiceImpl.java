package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.AllActQueryForm;
import com.deloitte.platform.api.project.vo.AllActVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.entity.AllAct;
import com.deloitte.services.project.mapper.AllActMapper;
import com.deloitte.services.project.service.IAllActService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.minidev.json.writer.BeansMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  AllAct服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AllActServiceImpl extends ServiceImpl<AllActMapper, AllAct> implements IAllActService {


    @Autowired
    private AllActMapper allActMapper;

    @Override
    public IPage<AllAct> selectPage(AllActQueryForm queryForm ) {
        QueryWrapper<AllAct> queryWrapper = new QueryWrapper <AllAct>();
        //getQueryWrapper(queryWrapper,queryForm);
        return allActMapper.selectPage(new Page<AllAct>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AllAct> selectList(AllActQueryForm queryForm) {
        QueryWrapper<AllAct> queryWrapper = new QueryWrapper <AllAct>();
        //getQueryWrapper(queryWrapper,queryForm);
        return allActMapper.selectList(queryWrapper);
    }

    @Override
    public List<AllAct> getAllActVo(long applicationID) {
        QueryWrapper<AllAct> queryWrapper = new QueryWrapper <AllAct>();
        queryWrapper.eq(AllAct.APPLICATION_ID,applicationID);
        return allActMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AllAct> getQueryWrapper(QueryWrapper<AllAct> queryWrapper,AllActQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getAllActId())){
            queryWrapper.eq(AllAct.ALL_ACT_ID,queryForm.getAllActId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(AllAct.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getFinSourceCode())){
            queryWrapper.eq(AllAct.FIN_SOURCE_CODE,queryForm.getFinSourceCode());
        }
        if(StringUtils.isNotBlank(queryForm.getFinSource())){
            queryWrapper.eq(AllAct.FIN_SOURCE,queryForm.getFinSource());
        }
        if(StringUtils.isNotBlank(queryForm.getAllInvestment())){
            queryWrapper.eq(AllAct.ALL_INVESTMENT,queryForm.getAllInvestment());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyQuantity())){
            queryWrapper.eq(AllAct.REPLY_QUANTITY,queryForm.getReplyQuantity());
        }
        if(StringUtils.isNotBlank(queryForm.getCarryOverEndOfLastYear())){
            queryWrapper.eq(AllAct.CARRY_OVER_END_OF_LAST_YEAR,queryForm.getCarryOverEndOfLastYear());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetApplication())){
            queryWrapper.eq(AllAct.BUDGET_APPLICATION,queryForm.getBudgetApplication());
        }
        if(StringUtils.isNotBlank(queryForm.getRemarks())){
            queryWrapper.eq(AllAct.REMARKS,queryForm.getRemarks());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyId())){
            queryWrapper.eq(AllAct.REPLY_ID,queryForm.getReplyId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayAmount())){
            queryWrapper.eq(AllAct.REPLAY_AMOUNT,queryForm.getReplayAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayRemark())){
            queryWrapper.eq(AllAct.REPLAY_REMARK,queryForm.getReplayRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(AllAct.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AllAct.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(AllAct.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(AllAct.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(AllAct.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(AllAct.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(AllAct.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(AllAct.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(AllAct.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(AllAct.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(AllAct.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}


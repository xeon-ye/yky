package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ChangeBudgetBakQueryForm;
import com.deloitte.services.project.entity.ChangeBudgetBak;
import com.deloitte.services.project.mapper.ChangeBudgetBakMapper;
import com.deloitte.services.project.service.IChangeBudgetBakService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  ChangeBudgetBak服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ChangeBudgetBakServiceImpl extends ServiceImpl<ChangeBudgetBakMapper, ChangeBudgetBak> implements IChangeBudgetBakService {


    @Autowired
    private ChangeBudgetBakMapper changeBudgetBakMapper;

    @Override
    public IPage<ChangeBudgetBak> selectPage(ChangeBudgetBakQueryForm queryForm ) {
        QueryWrapper<ChangeBudgetBak> queryWrapper = new QueryWrapper <ChangeBudgetBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return changeBudgetBakMapper.selectPage(new Page<ChangeBudgetBak>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ChangeBudgetBak> selectList(ChangeBudgetBakQueryForm queryForm) {
        QueryWrapper<ChangeBudgetBak> queryWrapper = new QueryWrapper <ChangeBudgetBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return changeBudgetBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteList(String mainId) {
        QueryWrapper<ChangeBudgetBak> queryWrapper = new QueryWrapper <ChangeBudgetBak>();
        queryWrapper.eq(ChangeBudgetBak.MAINT_ID,mainId);
        changeBudgetBakMapper.delete(queryWrapper);
    }

    @Override
    public List<ChangeBudgetBak> getList(String mainId) {
        QueryWrapper<ChangeBudgetBak> queryWrapper = new QueryWrapper <ChangeBudgetBak>();
        queryWrapper.eq(ChangeBudgetBak.MAINT_ID,mainId);
        return changeBudgetBakMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ChangeBudgetBak> getQueryWrapper(QueryWrapper<ChangeBudgetBak> queryWrapper,ChangeBudgetBakQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(ChangeBudgetBak.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(ChangeBudgetBak.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getExecutionId())){
            queryWrapper.eq(ChangeBudgetBak.EXECUTION_ID,queryForm.getExecutionId());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetCode())){
            queryWrapper.eq(ChangeBudgetBak.BUDGET_CODE,queryForm.getBudgetCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetName())){
            queryWrapper.eq(ChangeBudgetBak.BUDGET_NAME,queryForm.getBudgetName());
        }
        if(StringUtils.isNotBlank(queryForm.getFiscalAmount())){
            queryWrapper.eq(ChangeBudgetBak.FISCAL_AMOUNT,queryForm.getFiscalAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getSettelAmount())){
            queryWrapper.eq(ChangeBudgetBak.SETTEL_AMOUNT,queryForm.getSettelAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetYear())){
            queryWrapper.eq(ChangeBudgetBak.BUDGET_YEAR,queryForm.getBudgetYear());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ChangeBudgetBak.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ChangeBudgetBak.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ChangeBudgetBak.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ChangeBudgetBak.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ChangeBudgetBak.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ChangeBudgetBak.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ChangeBudgetBak.EXT3,queryForm.getExt3());
        }
        return queryWrapper;
    }
     */
}


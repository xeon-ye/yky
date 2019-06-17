package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.MaintBudgetQueryForm;
import com.deloitte.services.project.entity.MaintBudget;
import com.deloitte.services.project.mapper.MaintBudgetMapper;
import com.deloitte.services.project.service.IMaintBudgetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  MaintBudget服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MaintBudgetServiceImpl extends ServiceImpl<MaintBudgetMapper, MaintBudget> implements IMaintBudgetService {


    @Autowired
    private MaintBudgetMapper maintBudgetMapper;

    @Override
    public IPage<MaintBudget> selectPage(MaintBudgetQueryForm queryForm ) {
        QueryWrapper<MaintBudget> queryWrapper = new QueryWrapper <MaintBudget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return maintBudgetMapper.selectPage(new Page<MaintBudget>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MaintBudget> selectList(MaintBudgetQueryForm queryForm) {
        QueryWrapper<MaintBudget> queryWrapper = new QueryWrapper <MaintBudget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return maintBudgetMapper.selectList(queryWrapper);
    }

    @Override
    public List<MaintBudget> getAllList(String mainId) {
        QueryWrapper<MaintBudget> queryWrapper = new QueryWrapper <MaintBudget>();
        queryWrapper.eq(MaintBudget.MAINT_ID,mainId);
        return maintBudgetMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAllList(String mainId) {
        QueryWrapper<MaintBudget> queryWrapper = new QueryWrapper <MaintBudget>();
        queryWrapper.eq(MaintBudget.MAINT_ID,mainId);
        maintBudgetMapper.delete(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MaintBudget> getQueryWrapper(QueryWrapper<MaintBudget> queryWrapper,MaintBudgetQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getMaintBudgetId())){
            queryWrapper.eq(MaintBudget.MAINT_BUDGET_ID,queryForm.getMaintBudgetId());
        }
        if(StringUtils.isNotBlank(queryForm.getMaintId())){
            queryWrapper.eq(MaintBudget.MAINT_ID,queryForm.getMaintId());
        }
        if(StringUtils.isNotBlank(queryForm.getActCode())){
            queryWrapper.eq(MaintBudget.ACT_CODE,queryForm.getActCode());
        }
        if(StringUtils.isNotBlank(queryForm.getActName())){
            queryWrapper.eq(MaintBudget.ACT_NAME,queryForm.getActName());
        }
        if(StringUtils.isNotBlank(queryForm.getActAmount())){
            queryWrapper.eq(MaintBudget.ACT_AMOUNT,queryForm.getActAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MaintBudget.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MaintBudget.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MaintBudget.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MaintBudget.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(MaintBudget.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(MaintBudget.EXT2,queryForm.getExt2());
        }
        return queryWrapper;
    }
     */
}


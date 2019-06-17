package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseContentCommitmentUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseContentCommitmentUnit;
import com.deloitte.services.fssc.base.mapper.BaseContentCommitmentUnitMapper;
import com.deloitte.services.fssc.base.service.IBaseContentCommitmentUnitService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description :  BaseContentCommitmentUnit服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseContentCommitmentUnitServiceImpl extends ServiceImpl<BaseContentCommitmentUnitMapper, BaseContentCommitmentUnit> implements IBaseContentCommitmentUnitService {


    @Autowired
    private BaseContentCommitmentUnitMapper baseContentCommitmentUnitMapper;

    @Override
    public IPage<BaseContentCommitmentUnit> selectPage(BaseContentCommitmentUnitQueryForm queryForm ) {
        QueryWrapper<BaseContentCommitmentUnit> queryWrapper = new QueryWrapper<BaseContentCommitmentUnit>();
        this.getQueryWrapper(queryWrapper, queryForm);
        return baseContentCommitmentUnitMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);

        }

    @Override
    public List<BaseContentCommitmentUnit> selectList(BaseContentCommitmentUnitQueryForm queryForm) {
        QueryWrapper<BaseContentCommitmentUnit> queryWrapper = new QueryWrapper <BaseContentCommitmentUnit>();
        getQueryWrapper(queryWrapper,queryForm);
        return baseContentCommitmentUnitMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyeContentCommitmentUnitStatus(List<Long> ids, String status) {
        QueryWrapper<BaseContentCommitmentUnit> queryWrapper=new QueryWrapper <BaseContentCommitmentUnit>();
        queryWrapper.in(BaseContentCommitmentUnit.ID,ids);
        List<BaseContentCommitmentUnit> baseContentCommitmentUnitList=this.list(queryWrapper);
        for (BaseContentCommitmentUnit baseContentCommitmentUnit:baseContentCommitmentUnitList) {
            baseContentCommitmentUnit.setValidFlag(status);
        }
        return this.updateBatchById(baseContentCommitmentUnitList);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<BaseContentCommitmentUnit> getQueryWrapper(QueryWrapper<BaseContentCommitmentUnit> queryWrapper,
                                                                   BaseContentCommitmentUnitQueryForm queryForm){

        //条件拼接

        if(StringUtils.isNotBlank(queryForm.getOrgUnitName())){
            queryWrapper.like(BaseContentCommitmentUnit.ORG_UNIT_NAME,queryForm.getOrgUnitName());
        }

        if(StringUtils.isNotBlank(queryForm.getValidFlag())){
            queryWrapper.like(BaseContentCommitmentUnit.VALID_FLAG,queryForm.getValidFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgUnitCode())){
            queryWrapper.like(BaseContentCommitmentUnit.ORG_UNIT_CODE,queryForm.getOrgUnitCode());
        }

        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.like(BaseContentCommitmentUnit.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.like(BaseContentCommitmentUnit.UPDATE_BY,queryForm.getUpdateBy());
        }

        return queryWrapper;
    }

}

